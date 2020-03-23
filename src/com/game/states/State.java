package com.game.states;

import com.game.display.Display;
import java.awt.Graphics;

/**
 *
 * @author Jason Anthony Galicia
 */
public abstract class State {
    
    // The current state..
    private static State currentState = null;
    
    /**
     * Sets the state.
     * @param state The type of state.
     */
    public static void setState(State state) {
        currentState = state;
    }
    
    /**
     * Gets the current state.
     * @return The state.
     */
    public static State getState() {
        return currentState;
    }
    
    /**
     * The abstract method to update the state.
     * @param ticks The game ticks.
     */
    public abstract void update(int ticks, Display display);
    
    /**
     * The abstract method to render the state components to the screen.
     * @param g The graphics object.
     */
    public abstract void render(Graphics g);
}
 