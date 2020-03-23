package com.game.launcher;

import com.game.engine.Engine;
import com.game.globals.Globals;

/**
 *
 * @author Jason Anthony Galicia
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(Globals.WINDOW_TITLE, 
                Globals.GAME_WINDOW_WIDTH, Globals.GAME_WINDOW_HEIGHT);
        engine.start();
    }
}
