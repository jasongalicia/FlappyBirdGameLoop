package com.game.gameobjects;

import com.game.globals.Globals;
import com.game.gfx.ImageLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jason Anthony Galicia
 */
public class Flappy extends Entity {
    
    // Setting the image of Flappy.
    protected int flappyColorNumber;
    protected BufferedImage currentImage;
    private BufferedImage flappySet[][];
    
    // For jumping and angle movement.
    private double yMotion = 0;
    private int rotation;
    private AffineTransform t;
    
    /**
     * Constructor to initialize components.
     * @param path The file path.
     * @param x The starting x-coordinate.
     * @param y The starting y-coordinate.
     * @param width The width.
     * @param height The height.
     * @param flappyColorNumber The color number for Flappy.
     */
    public Flappy(String path, float x, float y, int width, int height, 
            int flappyColorNumber) {
        super(path, x, y, width, height);
        this.flappyColorNumber = flappyColorNumber;
        rotation = 0;
        
        initSet();
    }
    
    /**
     * Initializes all images going to be used for animation.
     */
    private void initSet() {
        flappySet = new BufferedImage[3][3];
        
        flappySet[0][0] = ImageLoader.loadImage(Globals.BB_DOWNFLAP);
        flappySet[0][1] = ImageLoader.loadImage(Globals.BB_MIDFLAP);
        flappySet[0][2] = ImageLoader.loadImage(Globals.BB_UPFLAP);
        
        flappySet[1][0] = ImageLoader.loadImage(Globals.YB_DOWNFLAP);
        flappySet[1][1] = ImageLoader.loadImage(Globals.YB_MIDFLAP);
        flappySet[1][2] = ImageLoader.loadImage(Globals.YB_UPFLAP);
        
        flappySet[2][0] = ImageLoader.loadImage(Globals.RB_DOWNFLAP);
        flappySet[2][1] = ImageLoader.loadImage(Globals.RB_MIDFLAP);
        flappySet[2][2] = ImageLoader.loadImage(Globals.RB_UPFLAP);
        
        switch (flappyColorNumber) {
            case 0:
                currentImage = flappySet[0][0];
                break;
            case 1:
                currentImage = flappySet[1][0];
                break;
            default:
                currentImage = flappySet[2][0];
                break;
        }
       
    }
    
    /**
     * Checks if flappy is colliding with another Game Object.
     * @param object The Game Object.
     * @return True if colliding, false if not.
     */
    public boolean isColliding(GameObject object) {
        return bounds.intersects(object.bounds);
    }

    @Override
    /**
     * The method to update everything about flappy.
     * @param ticks The game ticks/frames.
     * @param gameStarted A Boolean, whether game has started or not.
     * @param gameOver A Boolean, whether game is over or not.
     * @param isColliding A Boolean, whether this entity is colliding with a 
     * Game Object.
     * @param inputReceived A Boolean, whether an input has been received. 
     */
    public void update(int ticks, boolean gameStarted, boolean gameOver, 
            boolean isColliding, boolean inputReceived) {
        updateHitBox();
        if (rotation != -90) animateFlappy(ticks);
        if (gameStarted && !gameOver) {
            applyGravity(ticks);
        }
        if (inputReceived) {
            if (!isColliding) {
                flappyJump();
            }
        }
    }
    
    /**
     * Updates the hit-box for collision detection for flappy.
     */
    private void updateHitBox() {
        bounds.x = (int)x;
        bounds.y = (int)y;
        bounds.width = width;
        bounds.height = height;
    } 
    
    /**
     * Makes flappy jump.
     */
    private void flappyJump() {
        // Motion 
        if (yMotion > 0) yMotion = 0;
        yMotion -= 9;
        float newY = getY() + (float)yMotion;
        setY(newY);
        
        rotation = 30;
    }
    
    /**
     * Changes this Entities y-value to always be in free fall.
     * @param ticks 
     */
    private void applyGravity(int ticks) {
        if (ticks % 1 == 0 && yMotion < 40) {
            yMotion+=0.53;
            float newY = getY() + (float)yMotion;
            setY(newY);
            if (yMotion > 10) {
                rotation -= 8;
                if (rotation <= -90) rotation = -90;
            }
        }
    }
    
    /**
     * Animates flappy.
     * @param ticks The ticks needed to animate flappys wings.
     */
    private void animateFlappy(int ticks) {
        switch (flappyColorNumber) {
            case 0:
        switch (ticks) {
            case 10:
            case 40:
                currentImage = flappySet[0][1];
                break;
            case 20:
            case 50:
                currentImage = flappySet[0][2];
                break;
            case 30:
            case 60:
            case 0:
                currentImage = flappySet[0][0];
                break;
            default:
                break;
        }
        break;
            case 1:
        switch (ticks) {
            case 10:
            case 40:
                currentImage = flappySet[1][1];
                break;
            case 20:
            case 50:
                currentImage = flappySet[1][2];
                break;
            case 30:
            case 60:
            case 0:
                currentImage = flappySet[1][0];
                break;
            default:
                break;
        }
        break;
            default:
        switch (ticks) {
            case 10:
            case 40:
                currentImage = flappySet[2][1];
                break;
            case 20:
            case 50:
                currentImage = flappySet[2][2];
                break;
            case 30:
            case 60:
            case 0:
                currentImage = flappySet[2][0];
                break;
            default:
                break;
        }
        break;  
        }
    }

    @Override
    /**
     * The method to render each variable on flappy.
     * @param g The graphics object needed.
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        t = AffineTransform.getTranslateInstance(bounds.x, bounds.y);
        t.rotate(-Math.toRadians(rotation), width/2, height/2);
        g2d.drawImage(currentImage, t, null);
        
        // Drawing the hitbox
        g2d.setColor(Color.yellow);
        //g2d.fillRect((int)bounds.x, (int)bounds.y, bounds.width, 
        //    bounds.height);
    }
}
