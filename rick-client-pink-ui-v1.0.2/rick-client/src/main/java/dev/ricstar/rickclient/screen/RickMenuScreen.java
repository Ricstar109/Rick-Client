package dev.ricstar.rickclient.screen;

import dev.ricstar.rickclient.RickClient;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.List;

/** ClickGUI-inspired interface mock-up. All cards are display-only. */
public class RickMenuScreen extends Screen {
    private final Screen parent;
    private String page = "Overview";
    private static final List<String> PAGES = List.of("Overview", "Visuals", "Interface", "Profiles", "Settings");

    public RickMenuScreen(Screen parent) {
        super(Component.literal("Rick Client"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.clearWidgets();
        int left = Math.max(14, this.width / 2 - 330);
        int top = Math.max(12, this.height / 2 - 205);
        int panelW = Math.min(660, this.width - 28);
        int sideW = 142;
        int navY = top + 54;
        for (int i = 0; i < PAGES.size(); i++) {
            final String name = PAGES.get(i);
            this.addRenderableWidget(Button.builder(Component.literal((name.equals(page) ? "●  " : "   ") + name), b -> {
                page = name;
                rebuildWidgets();
            }).bounds(left + 12, navY + i * 30, sideW - 22, 22).build());
        }
        this.addRenderableWidget(Button.builder(Component.literal("Accent: " + RickClient.accentName() + "  ↻"), b -> {
            RickClient.nextAccent();
            rebuildWidgets();
        }).bounds(left + 12, top + 230, sideW - 22, 24).build());
        this.addRenderableWidget(Button.builder(Component.literal("Back"), b -> this.minecraft.setScreen(parent)).bounds(left + 12, top + 270, sideW - 22, 22).build());
        this.addRenderableWidget(Button.builder(Component.literal("Close  ×"), b -> this.minecraft.setScreen(parent)).bounds(left + panelW - 94, top + 12, 80, 20).build());
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        g.fillGradient(0, 0, this.width, this.height, 0xE916111B, 0xF52A1A2A);
        int left = Math.max(14, this.width / 2 - 330);
        int top = Math.max(12, this.height / 2 - 205);
        int panelW = Math.min(660, this.width - 28);
        int panelH = Math.min(410, this.height - 24);
        int accent = RickClient.accent();
        g.fill(left, top, left + panelW, top + panelH, 0xF51D1923);
        g.fill(left, top, left + 2, top + panelH, accent);
        g.fill(left, top, left + panelW, top + 2, accent);
        g.fill(left + 142, top + 2, left + 143, top + panelH, 0x553FFFFF);
        g.fill(left + 12, top + 42, left + panelW - 12, top + 43, 0x44FFFFFF);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(g, mouseX, mouseY, partialTick);
        int left = Math.max(14, this.width / 2 - 330);
        int top = Math.max(12, this.height / 2 - 205);
        int contentX = left + 162;
        int contentW = Math.min(470, this.width - contentX - 22);
        int accent = RickClient.accent();
        g.text(this.font, "RICK CLIENT", left + 14, top + 13, 0xFFFFEAF3, true);
        g.text(this.font, "UI CONFIGURATION", left + 14, top + 28, 0xFFBDA9B8, false);
        g.text(this.font, page.toUpperCase(), contentX, top + 58, accent, true);
        g.text(this.font, "A minimal client interface", contentX, top + 74, 0xFFBDAFBB, false);

        if (page.equals("Overview")) {
            card(g, contentX, top + 104, contentW, 68, "Welcome back", "Rick Client is a visual interface project.", accent);
            card(g, contentX, top + 184, contentW, 68, "Client status", "UI preview  ·  No gameplay modules installed", accent);
            card(g, contentX, top + 264, contentW, 68, "Personalization", "Change the accent color from the sidebar.", accent);
        } else if (page.equals("Visuals")) {
            card(g, contentX, top + 104, contentW, 68, "Color palette", "Accent color: " + RickClient.accentName(), accent);
            card(g, contentX, top + 184, contentW, 68, "Interface style", "Dark panels  ·  Soft pink highlights", accent);
            card(g, contentX, top + 264, contentW, 68, "Decorations", "No falling petals or screen particles", accent);
        } else if (page.equals("Interface")) {
            card(g, contentX, top + 104, contentW, 68, "Menu layout", "Sidebar navigation with clean content cards", accent);
            card(g, contentX, top + 184, contentW, 68, "Animations", "Static interface preview", accent);
            card(g, contentX, top + 264, contentW, 68, "Screen effects", "No in-world effects are used", accent);
        } else if (page.equals("Profiles")) {
            card(g, contentX, top + 104, contentW, 68, "Default", "Current visual layout", accent);
            card(g, contentX, top + 184, contentW, 68, "Profile system", "Profile cards are preview-only", accent);
        } else {
            card(g, contentX, top + 104, contentW, 68, "About Rick Client", "A client-side menu and screen design only.", accent);
            card(g, contentX, top + 184, contentW, 68, "Build", "Minecraft 26.2  ·  Java 25  ·  Fabric", accent);
            card(g, contentX, top + 264, contentW, 68, "Functionality", "No combat, movement, HUD or world changes", accent);
        }
        g.text(this.font, "RICK CLIENT  /  UI ONLY", contentX, top + Math.min(390, this.height - top - 18), 0x887F7480, false);
    }

    private void card(GuiGraphicsExtractor g, int x, int y, int w, int h, String title, String detail, int accent) {
        g.fill(x, y, x + w, y + h, 0xFF29232F);
        g.fill(x, y, x + 3, y + h, accent);
        g.text(this.font, title, x + 13, y + 13, 0xFFFFEAF3, true);
        g.text(this.font, detail, x + 13, y + 36, 0xFFBFB2C0, false);
    }

    @Override
    public boolean shouldCloseOnEsc() { return true; }
}
