package com.game.gameobjects;

import com.game.gfx.ImageLoader;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jason Anthony Galicia
 */
public abstract class GameObject {
    
    // GameObject componenets.
    protected BufferedImage image;
    protected String path;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    
    /**
     * Constructor to initialize components.
     * @param path The file path.
     * @param x The starting x-coordinate.
     * @param y The starting y-coordinate.
     * @param width The width.
     * @param height The height.
     */
    public GameObject(String path, float x, float y, int width, int height) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
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
     * Returns the current x coordinate of the Game Object
     * @return The x value as a float.
     */
    public float getX() {
        return x;
    }
    
    /**
     * Returns the current y coordinate of the Game Object
     * @return The y value as a float.
     */
    public float getY() {
        return y;
    }
    
    /**
     * Returns the current width of the Game Object
     * @return The width value as an integer.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the current height of the Game Object
     * @return The height value as an integer.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Returns the current width of the Game Object
     * @return The width value as an integer.
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Sets the new x value of the Game Object.
     * @param data The new x value as a float.
     */
    public void setX(float data) {
        bounds.x = (int)data;
        this.x = data;
    }
    
    /**
     * Sets the new y value of the Game Object.
     * @param data The new y value as a float.
     */
    public void setY(float data) {
        bounds.y = (int)data;
        this.y = data;
    }
    
    /**
     * Sets the new width value of the Game Object.
     * @param data The new x value as an integer.
     */
    public void setWidth(int data) {
        this.width = data;
    }
    
    /**
     * Sets the new height value of the Game Object.
     * @param data The new height value as an integer.
     */
    public void setHeight(int data) {
        this.height = data;
    }
    
    /**
     * Sets the new Image value of the Game Object.
     * @param data The new image value as an BufferedImage.
     */
    public void setImage(BufferedImage data) {
        this.image = data;
    }
    
    /**
     * The abstract method to update everything about this Game Object.
     */
    public abstract void update();
    
    /**
     * The abstract method to render everything about this Game Object 
     * to the screen.
     * @param g The Graphics object.
     */
    public abstract void render(Graphics g);
}
