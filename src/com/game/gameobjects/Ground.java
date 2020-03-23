package com.game.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jason Anthony Galicia
 */
public class Ground extends GameObject {
    
    // The speed the ground is moving...
    protected float speed;
    
    /**
     * Constructor to initialize variables of this Ground Game Object.
     * @param speed The pipe speed.
     * @param path The file path.
     * @param x The x value.
     * @param y The y value.
     * @param width The width.
     * @param height The Height.
     */
    public Ground(float speed, String path, float x, float y, int width, 
            int height) {
        super(path, x, y, width, height);
        this.speed = speed;
    }
    
    /**
     * Gets the speed of this object.
     * @return The speed as a float.
     */
    public float getSpeed() {
        return speed;
    }
    
    /**
     * Sets the speed of this object.
     * @param data The new speed as a float.
     */
    public void setSpeed(float data) {
        this.speed = data;
    }

    @Override
    /**
     * Updates the movement of the ground.
     */
    public void update() {
        float newX = getX() - speed;
        setX(newX);
    }

    @Override
    /**
     * Renders the ground onto the screen.
     */
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        
        // Drawing the hitbox
        g.setColor(Color.yellow);
        //g.fillRect((int)x, (int)y, width, height);
    }
}
