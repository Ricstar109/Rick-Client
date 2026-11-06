# Rick Client (Fabric, Minecraft 26.2)

Rein visuell, nur Client, keine Cheats. Sakura-Startmenü plus Sakura-Schlag-Animation.

## Jar ohne Java auf dem PC bauen (GitHub)
1. Auf github.com ein kostenloses Konto anlegen.
2. Neues Repository "rick-client" erstellen (Public).
3. "Add file" > "Upload files": den kompletten Inhalt dieses Ordners hineinziehen (auch `.github`), dann "Commit changes".
4. Oben "Actions" öffnen. Der Build "Build" startet von selbst (ca. 3-5 Minuten).
5. Fertigen Lauf anklicken und unten unter "Artifacts" `rick-client-jar` herunterladen. Darin liegt die Jar.

## Lokal bauen
JDK 25 und Gradle 9.5.1 installieren, dann `gradle build`. Die Jar liegt in `build/libs/`.

## Modrinth
Texte und Icon stehen im Ordner `modrinth/`. Anleitung: `modrinth/PROJECT_INFO.md`.
