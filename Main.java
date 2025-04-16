

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import org.lwjglhelp.libraries.*;

@SuppressWarnings("unused")
public class Main {
    private long window;

    private int windowWidth = 800, windowHeight = 600;
    private String windowTitle = "Title";

    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    // Entities
    // e.g. public Entity background = new Entity(0, 0, "textures/background.png");

    public void run() {
        init();
        loop();

        // Clean up and terminate
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        // Set up an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window = GLFW.glfwCreateWindow(windowWidth, windowHeight, windowTitle, 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key >= 0 && key < keys.length) {
                keys[key] = action != GLFW.GLFW_RELEASE; // True if pressed, false if released
            }

            if (action == GLFW.GLFW_PRESS) {
                if (key == GLFW.GLFW_KEY_F2) {
                    System.out.println("F2 key pressed!");
                }
            }

            if (action == GLFW.GLFW_RELEASE) {
                if (key == GLFW.GLFW_KEY_F2) {
                    System.out.println("F2 key released");
                }
            }
        });

        // Make OpenGL context current BEFORE doing OpenGL operations
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);

        // Enable 2D rendering
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // Load entities
    }

    private void loop() {
        GL.createCapabilities();

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            handleInputConstant();

            // Check for collision
            // e.g.
            
//            if (isCollision(entity1, entity2)) {
//                System.out.println("u collided");
//                GLFW.glfwSetWindowShouldClose(window, true);
//                return;
//            }


            // Render entities
            // e.g. background.renderWithScale(arguments);
            
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

//    private boolean isCollision(Entity entity1, Entity entity2) {
//        // Check for overlapping of the player and meteor (bounding box collision)
//        return entity1.x < entity2.x + 64 &&
//        		entity1.x + 64 > entity2.x &&
//               entity1.y < entity2.y + 64 &&
//               entity1.y + 64 > entity2.y;
//    }

    private void handleInputConstant() {
        // Check if W key is pressed constantly
        if (keys[GLFW.GLFW_KEY_W]) {
        	System.out.println("W key pressed!");
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}