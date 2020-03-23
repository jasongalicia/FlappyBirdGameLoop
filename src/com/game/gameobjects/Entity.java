package com.game.gameobjects;

import com.game.gfx.ImageLoader;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jason Anthony Galicia
 */
public abstract class Entity {
    
    // Entities componenets.
    protected BufferedImage image;
    protected String path;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    
    // The entities score.
    private int score;
    
    /**
     * Constructor to initialize components.
     * @param path The file path.
     * @param x The starting x-coordinate.
     * @param y The starting y-coordinate.
     * @param width The width.
     * @param height The height.
     */
    public Entity(String path, float x, float y, int width, int height) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        score = 0;
        init();
    }
    
    /**
     * Initialize components.
     */
    private void init() {
        bounds = new Rectangle((int)x, (int)y, width, height);
        image = ImageLoader.loadImage(path);
    }
    
    /**
     * Returns the current x coordinate of the Entity.
     * @return The x value as a float.
     */
    public float getX() {
        return x;
    }
    
    /**
     * Returns the current y coordinate of the Entity
     * @return The y value as a float.
     */
    public float getY() {
        return y;
    }
    
    /**
     * Returns the current width of the Entity.
     * @return The width value as an integer.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the current height of the Entity.
     * @return The value has an integer.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Returns the current BufferedImage of the Entity.
     * @return The image as a BufferedImage.
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Returns the current score of the Entity.
     * @return The score as an integer.
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Sets the x coordinate of the Entity.
     * @param data The new x coordinate as a float.
     */
    public void setX(float data) {
        this.x = data;
    }
    
    /**
     * Sets the y coordinate of the Entity.
     * @param data The new y coordinate as a float.
     */
    public void setY(float data) {
        this.y = data;
    }
    
    /**
     * Sets the width of the Entity.
     * @param data The new width as an integer.
     */
    public void setWidth(int data) {
        this.width = data;
    }
    
    /**
     * Sets the height of the Entity.
     * @param data The new height as an integer.
     */
    public void setHeight(int data) {
        this.height = data;
    }
    
    /**
     * Sets the Image of the Entity.
     * @param data The new width as an integer.
     */
    public void setImage(BufferedImage data) {
        this.image = data;
    }
    
    /**
     * Sets the score of the Entity.
     * @param data The new score as an integer.
     */
    public void setScore(int data) {
        this.score = data;
    }
    
    /**
     * The abstract method to update everything about this entity.
     * @param ticks The game ticks/frames.
     * @param gameStarted A Boolean, whether game has started or not.
     * @param gameOver A Boolean, whether game is over or not.
     * @param isColliding A Boolean, whether this entity is colliding with a 
     * Game Object.
     * @param inputReceived A Boolean, whether an input has been received. 
     */
    public abstract void update(int ticks, boolean gameStarted, 
            boolean gameOver, boolean isColliding, boolean inputReceived);
    /**
     * The abstract method to render each variable on this entity.
     * @param g The graphics object needed.
     */
    public abstract void render(Graphics g);
}
