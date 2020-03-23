package com.game.engine;

import com.game.display.Display;
import com.game.globals.Globals;
import com.game.inputmanager.InputManager;
import com.game.states.GameState;
import com.game.states.State;
import com.game.swingui.LoginUI;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jason Anthony Galicia
 */
public class Engine implements Runnable {
    
    // For Key & Mouse inputs
    private InputManager inputManager;
    
    // Needed for the game loop
    private Thread gt;
    private boolean isRunning;
    private int ticks = 0;
    
    // Window and Canvas variables
    private Display display;
    private String windowTitle;
    private int windowWidth, windowHeight;
    
    // Graphics and renderings
    private BufferStrategy bs;
    private Graphics g;
    
    // The Game States needing to be initialized
    private State gameState;
    
    /**
     * The constructor to initialize the Game Engine components.
     * @param windowTitle
     * @param windowWidth
     * @param windowHeight 
     */
    public Engine(String windowTitle, int windowWidth, int windowHeight) {
        this.windowTitle = windowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        isRunning = false;
        init();
    }
    
    /**
     * Initializes components.
     */
    private void init() {
        display = new Display(windowTitle, windowWidth, windowHeight);
        inputManager = new InputManager();
        display.getFrame().addKeyListener(inputManager);
        display.getCanvas().addMouseListener(inputManager);
        gameState = new GameState(inputManager);
        State.setState(gameState);
    }
    
    
    @Override
    /**
     * Runs the game loop.
     */
    public void run() {
        int fps = 60;
        double timePerTick = Globals.BILLION / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        ticks = 0;
        
        while(isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }
            
            if (timer >= Globals.BILLION) {
                //System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    
    /**
     * Starts the thread.
     */
    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        gt = new Thread(this);
        gt.start();
    }
    
    /**
     * Stops the thread.
     */
    public synchronized void stop() {
        if (isRunning) return;
        try {
            isRunning = false;
            gt.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    /**
     * Updates all game pieces
     */
    public void update() {
        if (State.getState() != null) {
            State.getState().update(ticks, display);
            if (display.getRestartButtonStatus()) {
                isRunning = false;
                Engine engine = new Engine(windowTitle, windowWidth, 
                        windowHeight);
                engine.start();
                display.getCanvas().setEnabled(false);
                display.getFrame().dispose();
            }
            else if (display.getShareButtonStatus()) {
                JOptionPane.showMessageDialog(null, "This feature currently "
                        + "is not working on RW V1.0.0. We are working on a "
                        + "current fix. "
                        + "Stay tuned for updates!");
                display.setShareButton(false);
            }
            else if (display.getLeaderboardButtonStatus()) {
                LoginUI ui = new LoginUI(display, this, (GameState)gameState);
                display.setLeaderboardStatus(false);
            }
        }
    }
    
    /**
     * Render images and all graphics unto the screen
     */
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        else {
            if (isRunning) g = bs.getDrawGraphics();
            // Clear the screen first
            g.clearRect(0, 0, windowWidth, windowHeight);
        
            // Draw all the game pieces here
            if (State.getState() != null) {
                State.getState().render(g);
            }
            // Dispose of everything
            if (isRunning) {
                bs.show();
                g.dispose();
            } 
        }
    }
    
    /**
     * Gets the window title.
     * @return The window title as a String.
     */
    public String getWindowTitle() {
        return windowTitle;
    }
    
    /**
     * Gets the window width.
     * @return The window width as an integer.
     */
    public int getWindowWidth() {
        return windowWidth;
    }
    
    /**
     * Gets the window height.
     * @return The window height as an integer.
     */
    public int getWindowHeight() {
        return windowHeight;
    }
    
    /**
     * Get the ticks for animation and in-game logic purposes.
     * @return The ticks variable as an integer.
     */
    public int getTicks() {
        return ticks;
    }
    
    /**
     * Returns the Engine.
     * @return The Engine object.
     */
    public Engine getEngine() {
        return this;
    }
    
    /**
     * Sets the isRunning boolean variable.
     * @param data The boolean data (true or false).
     */
    public void setIsRunning(boolean data) {
        this.isRunning = data;
    }
}