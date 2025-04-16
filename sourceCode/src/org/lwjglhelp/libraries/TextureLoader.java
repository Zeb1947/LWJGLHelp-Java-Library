package org.lwjglhelp.libraries;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

@SuppressWarnings("unused")
public class TextureLoader {
    public static int loadTexture(String texturePath) {
        int textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        // Texture parameters
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        // Load image
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer image = STBImage.stbi_load(texturePath, width, height, channels, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load texture: " + STBImage.stbi_failure_reason());
            }

            // Upload the texture to OpenGL
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glGenerateMipmap(GL_TEXTURE_2D);

            // Free memory
            STBImage.stbi_image_free(image);
        }

        return textureID;
    }
    
    public static String SetTexture(String texturePath) {
        return Paths.get("src", texturePath).toAbsolutePath().toString();
    }
    
    public static void render(int x, int y, int textureID) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        
        GL11.glBegin(GL11.GL_QUADS);
        
        // Bottom-left
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);

        // Bottom-right
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + 64, y);

        // Top-right
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + 64, y + 64);

        // Top-left
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + 64);
        
        GL11.glEnd();
    }
}