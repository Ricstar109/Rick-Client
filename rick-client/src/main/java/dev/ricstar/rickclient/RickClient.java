package dev.ricstar.rickclient;

import net.fabricmc.api.ClientModInitializer;

/** Rick Client: rein visuell, client-seitig, ohne Gameplay-Änderungen. */
public class RickClient implements ClientModInitializer {
    public static final String MOD_ID = "rickclient";

    /** Schlag-Animation an/aus (nur Optik der Hand, ändert weder Reichweite noch Cooldown). */
    public static boolean sakuraSwing = true;

    @Override
    public void onInitializeClient() {
        // Alles Weitere läuft über die Mixins und das Startmenü.
    }
}
