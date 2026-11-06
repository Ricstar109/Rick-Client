package dev.ricstar.rickclient.screen;

import dev.ricstar.rickclient.RickClient;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;

/** Pink Rick Client title screen. Purely visual. */
public class RickTitleScreen extends Screen {
    public RickTitleScreen() { super(Component.literal("Rick Client")); }

    @Override
    protected void init() {
        int w = 210, h = 22;
        int x = this.width / 2 - w / 2;
        int y = this.height / 2 - 10;
        this.addRenderableWidget(Button.builder(Component.literal("Singleplayer"), b -> this.minecraft.setScreen(new SelectWorldScreen(this))).bounds(x, y, w, h).build());
        this.addRenderableWidget(Button.builder(Component.literal("Multiplayer"), b -> this.minecraft.setScreen(new JoinMultiplayerScreen(this))).bounds(x, y + 28, w, h).build());
        this.addRenderableWidget(Button.builder(Component.literal("Rick Client Menu"), b -> this.minecraft.setScreen(new RickMenuScreen(this))).bounds(x, y + 56, w, h).build());
        this.addRenderableWidget(Button.builder(Component.literal("Options"), b -> this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options))).bounds(x, y + 84, 102, h).build());
        this.addRenderableWidget(Button.builder(Component.literal("Quit"), b -> this.minecraft.stop()).bounds(x + 108, y + 84, 102, h).build());
    }

    @Override
    public boolean shouldCloseOnEsc() { return false; }

    @Override
    public void extractBackground(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        g.fillGradient(0, 0, this.width, this.height, 0xFF211522, 0xFF4A2440);
        g.fillGradient(0, this.height / 2, this.width, this.height, 0x00211522, 0xAA9E426F);
        int accent = RickClient.accent();
        g.fill(0, 0, this.width, 3, accent);
        g.fill(this.width / 2 - 150, this.height / 2 - 100, this.width / 2 + 150, this.height / 2 - 98, 0x55FFFFFF);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(g, mouseX, mouseY, partialTick);
        Component title = Component.literal("RICK CLIENT");
        g.pose().pushMatrix();
        g.pose().translate(this.width / 2.0F, this.height / 2.0F - 83.0F);
        g.pose().scale(3.2F, 3.2F);
        g.text(this.font, title, -this.font.width(title) / 2, 0, 0xFFFFE8F2, true);
        g.pose().popMatrix();
        Component sub = Component.literal("A clean interface. Your style.");
        g.text(this.font, sub, this.width / 2 - this.font.width(sub) / 2, this.height / 2 - 48, 0xFFFFBBD7, true);
        Component foot = Component.literal("Rick Client 1.0.2  ·  UI only");
        g.text(this.font, foot, 8, this.height - 14, 0xCCFFE6F0, true);
    }
}
