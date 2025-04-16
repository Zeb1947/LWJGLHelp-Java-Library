package org.lwjglhelp.libraries;

import org.lwjgl.opengl.GL11;

public class Entity {
    public int x, y;  // Position of the entity
    public int textureID;  // OpenGL texture ID
    public String texturePath;  // Path to the texture

    private float scaleX = 1.0f, scaleY = 1.0f;  // Default scale factors for X and Y axes

    public Entity(int x, int y, String texturePath) {
        this.x = x;
        this.y = y;
        this.texturePath = texturePath;
    }

    public void render() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        
        GL11.glBegin(GL11.GL_QUADS);
        
        // Bottom-left corner
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);

        // Bottom-right corner (scaled by scaleX)
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + 64 * scaleX, y);

        // Top-right corner (scaled by scaleY)
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + 64 * scaleX, y + 64 * scaleY);

        // Top-left corner (scaled by scaleX and scaleY)
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + 64 * scaleY);
        
        GL11.glEnd();
    }
    
    // Render with custom scaling factors
    public void renderWithScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        
        GL11.glBegin(GL11.GL_QUADS);
        
        // Bottom-left corner
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);

        // Bottom-right corner (scaled by scaleX)
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + 64 * scaleX, y);

        // Top-right corner (scaled by scaleY)
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + 64 * scaleX, y + 64 * scaleY);

        // Top-left corner (scaled by scaleX and scaleY)
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + 64 * scaleY);
        
        GL11.glEnd();
    }

    // Load texture from file path
    public void load() {
        String absolutePath = TextureLoader.SetTexture(this.texturePath);
        System.out.println("Loading texture from: " + absolutePath);
        this.textureID = TextureLoader.loadTexture(absolutePath);
    }
}