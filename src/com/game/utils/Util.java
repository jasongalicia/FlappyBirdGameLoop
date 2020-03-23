package com.game.utils;

import com.game.globals.Globals;
/**
 *
 * @author Jason Anthony Galicia
 */
public class Util {
    
    /**
     * Randomize the color of flappy.
     * @return The number which will be used to indicate which color flappy 
     * will be.
     */
    public static int randomizeFlappyColor() {
        int min = 0;
        int max = 2;
        
        double random = (Math.random()*((max-min)+1))+min;
        return (int)random;
    }
    
    /**
     * Randomizes the height of the top pipe.
     * @return The height of the top pipe.
     */
    public static int randomTopPipeHeight() {
        int min = 110;
        int max = (int)(Globals.GAME_WINDOW_HEIGHT * 0.4);
        
        double random = (Math.random()*((max-min)+1))+min;
        return (int)random;
    }
}
