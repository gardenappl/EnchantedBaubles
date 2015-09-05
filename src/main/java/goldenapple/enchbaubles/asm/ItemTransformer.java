package goldenapple.enchbaubles.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import static goldenapple.enchbaubles.asm.EnchBaublesCoreContainer.RelaunchLogHelper;
import static org.objectweb.asm.Opcodes.*;

public class ItemTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(name.equals("net.minecraft.item.ItemStack") || name.equals("add")){
            RelaunchLogHelper.info("Found target class %s (transformed: %s)", name, transformedName);

            //Read the class
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

            for(MethodNode method : classNode.methods){ //iterating through all the methods in the class
                if(method.name.equals("isItemEnchantable") && method.desc.equals("()Z")){
                    RelaunchLogHelper.info("Found target method %s%s", method.name, method.desc);
                    patchIsItemEnchantable(method, false);
                }else if(method.name.equals("x") && method.desc.equals("()Z")){
                    RelaunchLogHelper.info("Found target method %s%s", method.name, method.desc);
                    patchIsItemEnchantable(method, true);
                }
            }

            //Write the class
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            classNode.accept(writer);
            return writer.toByteArray();
        }else if(name.equals("net.minecraft.item.Item") || name.equals("adb")){
            RelaunchLogHelper.info("Found target class %s (transformed: %s)", name, transformedName);

            //Read the class
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

            for(MethodNode method : classNode.methods){
                //NOTE: the ItemStack-sensitive version of getItemEnchantability is added by Forge, therefore it's name is never obfuscated.
                if(method.name.equals("getItemEnchantability") && method.desc.equals("(Lnet/minecraft/item/ItemStack;)I")){
                    RelaunchLogHelper.info("Found target method %s%s", method.name, method.desc);
                    patchGetItemEnchantability(method, false);
                }else if(method.name.equals("getItemEnchantability") && method.desc.equals("(Ladd;)I")) {
                    RelaunchLogHelper.info("Found target method %s%s", method.name, method.desc);
                    patchGetItemEnchantability(method, true);
                }
            }

            //Write the class
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            classNode.accept(writer);
            return writer.toByteArray();
        }
            return basicClass;
    }

    private static void patchGetItemEnchantability(MethodNode methodNode, boolean obfuscated){
        boolean injected = false;

        for(AbstractInsnNode node : methodNode.instructions.toArray()) { //iterating through all the instructions in the method
     /* In the Item.getItemEnchantability(ItemStack stack) method:
        By default, it's code is "return getItemEnchantability()", which represents the instructions
        INVOKEVIRTUAL <internal method name> and IRETURN. Our goal is to replace INVOKEVIRTUAL with an
        INVOKESTATIC that calls ItemHooks.getEnchantability(ItemStack stack) instead.
        First, we have to find those two instructions: */
            if (node instanceof MethodInsnNode){
                MethodInsnNode currentNode = (MethodInsnNode) node;
                if(currentNode.name.equals("getItemEnchantability") || currentNode.name.equals("c")) {
                    if (node.getNext().getOpcode() == IRETURN) { //if there is an IRETURN after that
                        InsnList toInject = new InsnList(); //And now we're modifying them
                        toInject.add(new VarInsnNode(ALOAD, 1)); //Load local variable 1 (the method's "stack" argument)
                        toInject.add(new MethodInsnNode(INVOKESTATIC, "goldenapple/enchbaubles/asm/ItemHooks", "getEnchantability", obfuscated ? "(Ladd;)I" : "(Lnet/minecraft/item/ItemStack;)I", false)); //Call our hook ("stack" will be given as the argument)
                        methodNode.instructions.insertBefore(node, toInject);
                        methodNode.instructions.remove(node); //Remove vanilla's getItemEnchantability call
                        injected = true;
                    }
                }
            }
        }

        if(injected)
            RelaunchLogHelper.info("Successfully patched target method.");
        else
            RelaunchLogHelper.error("Patch failed! Perhaps there's an incompatible coremod?");
    }

    private static void patchIsItemEnchantable(MethodNode methodNode, boolean obfuscated){
        boolean injected = false;

        for(AbstractInsnNode node : methodNode.instructions.toArray()){
     /* This method calculates some logic to determine if an item is a damageable tool.
        Our goal is to just skip all those calculations, call our hook and return the result
        (see ItemHooks.isItemEnchantable(ItemStack stack)*/
            if(node.getOpcode() == INVOKEVIRTUAL && !injected){ //We'll just find the first called method, override it and return. It's lazy but it works.
                InsnList toInject = new InsnList();
                toInject.add(new VarInsnNode(ALOAD, 0)); //Load local variable 0 ("this")
                toInject.add(new MethodInsnNode(INVOKESTATIC, "goldenapple/enchbaubles/asm/ItemHooks", "isEnchantable", obfuscated ? "(Ladd;)Z" : "(Lnet/minecraft/item/ItemStack;)Z", false)); //Call our hook ("this" will be given as the argument)
                toInject.add(new InsnNode(IRETURN)); //And return the value that we get
                methodNode.instructions.insertBefore(node, toInject);
                injected = true;
            }
        }

        if(injected)
            RelaunchLogHelper.info("Successfully patched target method.");
        else
            RelaunchLogHelper.error("Patch failed! Perhaps there's an incompatible coremod?");
    }
}
