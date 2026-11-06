package dev.ricstar.rickclient.mixin;

import dev.ricstar.rickclient.screen.RickTitleScreen;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/** Ab 26.2 liegt das Setzen des Bildschirms in der Klasse Gui statt in Minecraft. */
@Mixin(Gui.class)
public abstract class MinecraftMixin {
    @ModifyVariable(method = "setScreen", at = @At("HEAD"), argsOnly = true, require = 0)
    private Screen rickclient$replaceTitle(Screen screen) {
        if (screen != null && screen.getClass() == TitleScreen.class) {
            return new RickTitleScreen();
        }
        return screen;
    }
}
