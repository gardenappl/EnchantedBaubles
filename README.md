# EnchantedBaubles
A Minecraft mod that allows enchantements on Baubles. Still heavily work in progress.

**Note**: it still can't run as a normal Minecraft mod. It can run in a deobfuscated environment, but you'll have to put
```-Dfml.coreMods.load=goldenapple.enchbaubles.asm.EnchBaublesLoadingPlugin``` in the *VM* arguments (*not* the Program
arguments). Otherwise FML won't load the built-in coremod, and the mod won't work.
