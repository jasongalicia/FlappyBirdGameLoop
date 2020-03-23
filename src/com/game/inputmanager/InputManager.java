package com.game.inputmanager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Jason Anthony Galicia
 */
public class InputManager implements KeyListener, MouseListener {
    
    // Input received boolean...
    private boolean inputReceived;
    
    /**
     * Default constructor.
     */
    public InputManager() {
        inputReceived = false;
    }
    
    /**
     * Gets the boolean determining whether or not an input has been received.
     * @return The inputReceived boolean.
     */
    public boolean getInputReceived() {
        return inputReceived;
    }
    
    /**
     * Sets the inputReceived boolean depending on the game logic.
     * @param data The data (true/false).
     */
    public void setInputReceived(boolean data) {
        this.inputReceived = data;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SPACE || 
                ke.getKeyCode() == KeyEvent.VK_UP ||
                ke.getKeyCode() == KeyEvent.VK_W) {
            inputReceived = true;
            System.out.println("A Key Input was received.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {
        inputReceived = true;
        System.out.println("A Mouse Input was received.");
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
}
