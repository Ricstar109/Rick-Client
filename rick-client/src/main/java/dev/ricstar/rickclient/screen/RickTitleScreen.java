package dev.ricstar.rickclient.screen;

import dev.ricstar.rickclient.RickClient;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;

/** Sakura-Startmenü von Rick Client. */
public class RickTitleScreen extends Screen {
    private static final int PETAL_COUNT = 90;
    private static final int[] PETAL_COLORS = {0xE6FFC6DC, 0xDDFFB0CF, 0xCCFFD9E8, 0xE6F7A8C6};

    private final long start = System.currentTimeMillis();
    private Button swingButton;

    public RickTitleScreen() {
        super(Component.literal("Rick Client"));
    }

    @Override
    protected void init() {
        int w = 200;
        int h = 20;
        int x = this.width / 2 - w / 2;
        int y = this.height / 2 - 6;

        this.addRenderableWidget(Button.builder(Component.translatable("menu.singleplayer"),
                b -> this.minecraft.setScreen(new SelectWorldScreen(this))).bounds(x, y, w, h).build());
        this.addRenderableWidget(Button.builder(Component.translatable("menu.multiplayer"),
                b -> this.minecraft.setScreen(new JoinMultiplayerScreen(this))).bounds(x, y + 26, w, h).build());
        this.addRenderableWidget(Button.builder(Component.translatable("menu.options"),
                b -> this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options)))
                .bounds(x, y + 52, 98, h).build());
        this.addRenderableWidget(Button.builder(Component.translatable("menu.quit"),
                b -> this.minecraft.stop()).bounds(x + 102, y + 52, 98, h).build());

        this.swingButton = this.addRenderableWidget(Button.builder(swingLabel(), b -> {
            RickClient.sakuraSwing = !RickClient.sakuraSwing;
            b.setMessage(swingLabel());
        }).bounds(x, y + 84, w, h).build());
    }

    private static Component swingLabel() {
        return Component.literal("Sakura-Schlag: " + (RickClient.sakuraSwing ? "an" : "aus"));
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    /** Eigener Hintergrund: Abenddämmerung in Rosa und Violett mit fallenden Blütenblättern. */
    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fillGradient(0, 0, this.width, this.height, 0xFF2A1740, 0xFFD97BA6);
        graphics.fillGradient(0, this.height / 2, this.width, this.height, 0x00000000, 0x66FFD1E3);

        float t = (System.currentTimeMillis() - start) / 1000.0F;
        for (int i = 0; i < PETAL_COUNT; i++) {
            float r1 = frac(i * 0.6180339F + 0.13F);
            float r2 = frac(i * 0.7548776F + 0.57F);
            float r3 = frac(i * 0.5698402F + 0.31F);
            float speed = 14.0F + r2 * 26.0F;
            float cycle = this.height + 24.0F;
            float y = (t * speed + r1 * cycle) % cycle - 12.0F;
            float x = r3 * this.width + (float) Math.sin(t * (0.5F + r2) + i) * (14.0F + r1 * 22.0F);
            int s = 2 + (int) (r2 * 3.0F);
            int color = PETAL_COLORS[i % PETAL_COLORS.length];
            int px = (int) x;
            int py = (int) y;
            graphics.fill(px, py, px + s * 2, py + s, color);
            graphics.fill(px + 1, py + s, px + s * 2 - 1, py + s + 1, color);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);

        Component title = Component.literal("Rick Client").withStyle(ChatFormatting.BOLD);
        float scale = 4.0F;
        graphics.pose().pushMatrix();
        graphics.pose().translate(this.width / 2.0F, this.height / 2.0F - 70.0F);
        graphics.pose().scale(scale, scale);
        graphics.text(this.font, title, -this.font.width(title) / 2, 0, 0xFFFFE6F0, true);
        graphics.pose().popMatrix();

        Component sub = Component.literal("Sakura Edition");
        graphics.text(this.font, sub, this.width / 2 - this.font.width(sub) / 2, this.height / 2 - 28, 0xFFFFC6DC, true);

        Component foot = Component.literal("Rick Client 1.0 · Minecraft 26.2");
        graphics.text(this.font, foot, 4, this.height - 12, 0xCCFFE6F0, true);
    }

    private static float frac(float v) {
        return v - (float) Math.floor(v);
    }
}
