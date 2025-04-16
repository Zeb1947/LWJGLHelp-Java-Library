LWJGLHelp is a java library for LWJGL. It was developed by me and in only about 10 - 20 minutes.
LWJGLHelp gives a slightly better experience for coding 2D games with LWJGL (3D support not currently added).
for LWJGLHelp to work you must have all your code, assets or anything within the src folder, subdirectories allowed.

here are some descriptions for all of the contents of the libary:

**org.lwjglhelp.libraries.Entity:**
can be used to make multible things: players, backgrounds, items, e.t.c.

**how to define (int x, int y, String texturePath:**
Entity player = new Entity(x, y, texturePath);

how to load (load entities in the init() function):
player.load();

how to render (render entities in the loop() function):
player.render();

or (float scaleX, float scaleY)

player.renderWithScale(scaleX, scaleY);

org.lwjglhelp.libraries.TextureLoader:****
can be used to load textures for entities, e.t.c.

how to define and use (String soundPath):
int textureID
String texturePath = TextureLoader.SetTexture("assets/player/player1.png"); // Gets absolute path of texture
textureID = TextureLoader.LoadTexture(texturePath);

**org.lwjglhelp.libraries.SoundManager:**

how to use:
SoundManager.playSound("soundpath"); // only for wav files, e.g. bgMusic.wav



**this version of LWJGLHelp is on v1.0, hope you make use of this library.**
