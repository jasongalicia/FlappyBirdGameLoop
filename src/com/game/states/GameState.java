package com.game.states;

import com.game.display.Display;
import com.game.globals.Globals;
import com.game.gameobjects.Flappy;
import com.game.inputmanager.InputManager;
import com.game.utils.Util;
import com.game.world.World;
import java.awt.Graphics;

/**
 *
 * @author Jason Anthony Galicia
 */
public class GameState extends State {
     
    // Key Input manager
    private InputManager inputManager;
    
    // The Game State has a world and other components
    private World world;
    private Flappy player;
    private int flappyColorNumber;
    
    // Game Details
    private boolean gameOver;
    private boolean gameStarted;
    
    /**
     * Constructor to initialize the GameState class.
     * @param inputManager The input manager for keyboard and mouse inputs.
     */
    public GameState(InputManager inputManager) {
        this.inputManager = inputManager;
        
        init();
    }
    
    /**
     * Initializes components.
     */
    private void init() {
        gameOver = false;
        gameStarted = false;
        flappyColorNumber = Util.randomizeFlappyColor();
        player = new Flappy("", 126, 220, Globals.FLAPPY_WIDTH, 
                Globals.FLAPPY_HEIGHT, flappyColorNumber);
        world = new World(this, player, flappyColorNumber);
    }
    
    @Override
    /**
     * Updates the state of the program.
     */
    public void update(int ticks, Display display) {
        if (inputReceived()) {
            gameStarted = true;
        }
        world.update(ticks, inputReceived(), display);
        inputManager.setInputReceived(false);
    }

    @Override
    /**
     * Renders the states components to the screen.
     */
    public void render(Graphics g) {
        world.render(g, gameOver, gameStarted);
    }
    
    /**
     * Gets the input received variable.
     * @return The input received boolean.
     */
    public boolean inputReceived() {
        return inputManager.getInputReceived();
    }
    
    /**
     * Gets the game over variable.
     * @return The game over boolean.
     */
    public boolean getGameOverStatus() {
        return gameOver;
    }
    
    /**
     * Gets the game started variable.
     * @return The game started boolean.
     */
    public boolean getGameStartedStatus() {
        return gameStarted;
    }
    
    /**
     * Sets the game over variable.
     * @param data The data to determine whether the game is over or not 
     * (true/false).
     */
    public void setGameOver(boolean data) {
        gameOver = data;
    }
    
    /**
     * Sets the game started variable.
     * @param data The data to determine whether the game
     * has started or not (true/false).
     */
    public void setGameStarted(boolean data) {
        gameStarted = data;
    }
    
    /**
     * Gets the score of the player.
     * @return The score as an integer.
     */
    public int getScore() {
        return player.getScore();
    }
}
