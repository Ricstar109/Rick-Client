package dev.ricstar.rickclient;

import dev.ricstar.rickclient.screen.RickMenuScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.KeyMapping.Category;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;

/** Rick Client is a client-side UI mock-up. It does not add gameplay modules. */
public class RickClient implements ClientModInitializer {
    public static final String MOD_ID = "rickclient";
    public static int accentIndex = 0;
    public static final int[] ACCENTS = {
            0xFFFF78B7, 0xFFFF9BCB, 0xFFE879B8, 0xFFB783FF,
            0xFF78B9FF, 0xFF69D6C2, 0xFFFFB36B, 0xFFE7E7F0
    };
    public static final String[] ACCENT_NAMES = {
            "Rose", "Cotton Candy", "Orchid", "Lavender",
            "Sky", "Mint", "Peach", "Silver"
    };

    public static int accent() { return ACCENTS[accentIndex]; }
    public static String accentName() { return ACCENT_NAMES[accentIndex]; }
    public static void nextAccent() { accentIndex = (accentIndex + 1) % ACCENTS.length; }

    @Override
    public void onInitializeClient() {
        // No gameplay hooks, combat logic, movement logic or HUD modifications.
    }
}
