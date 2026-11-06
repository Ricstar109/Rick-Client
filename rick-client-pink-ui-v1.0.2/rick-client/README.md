# Rick Client

A client-side Minecraft Fabric mod focused strictly on its menu and screens.

## Included
- Pink/dark custom title screen
- ClickGUI-inspired Rick Client screen with sidebar navigation
- Accent color button with multiple selectable colors
- UI-only preview cards and settings pages

## Not included
- No combat, movement, or automation features
- No HUD/world overlays or visual effects in the world
- No hand/swing animation changes
- No Sakura petals or particle effects

## Build

Requires JDK 25 and Gradle. Open this project in IntelliJ IDEA or import it into your GitHub Actions build workflow, then run:

```bash
gradle build
```

The JAR is created in `build/libs/`.

Minecraft target: 26.2 · Fabric Loader 0.19.5 · Java 25
