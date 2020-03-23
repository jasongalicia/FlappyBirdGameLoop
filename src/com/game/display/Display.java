package com.game.display;

import com.game.gfx.ImageLoader;
import com.game.globals.Globals;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Jason Anthony Galicia
 */
public class Display {
    
    // Canvas and JFrame Components
    private JFrame frame;
    private Canvas canvas;
    
    // Characteristics
    private String windowTitle;
    private int windowWidth, windowHeight;
    
    // Buttons for game over
    private JButton restartButton;
    private JButton shareButton;
    private JButton leaderboardsButton;
    
    // Action Event for buttons
    private ActionListener restartListener;
    private ActionListener shareListener;
    private ActionListener leaderboardListener;
    
    private boolean restartPressed;
    private boolean sharePressed;
    private boolean leaderboardPressed;
    
    /**
     * The constructor to display content to the screen.
     * @param windowTitle The JFrame title
     * @param windowWidth The JFrame width
     * @param windowHeight The JFrame height
     */ 
    public Display(String windowTitle, int windowWidth, int windowHeight) {
        this.windowTitle = windowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        initComponents();
    }
    
    /**
     * Initialize the components for the JFrame and Canvas.
     */
    private void initComponents() {
        restartPressed = false;
        sharePressed = false;
        leaderboardPressed = false;
        
        restartListener = (ActionEvent ae) -> {
            System.out.println("Restart Listener was pressed.");
            restartPressed = true;
        };
        
        shareListener = (ActionEvent ae) -> {
            System.out.println("Share Listener was pressed.");
            sharePressed = true;
        };
        
        leaderboardListener = (ActionEvent ae) -> {
            System.out.println("Leaderboard Listener was pressed.");
            leaderboardPressed = true;
        };
        
        frame = new JFrame(windowTitle);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        restartButton = new JButton();
        restartButton.setSize(100, 34);
        restartButton.setLocation(30, 200);
        restartButton.setIcon(new ImageIcon
            (ImageLoader.loadImage(Globals.RESTART_BTN)));
        restartButton.addActionListener(restartListener);
        restartButton.setVisible(false);
        frame.add(restartButton);
        
        shareButton = new JButton();
        shareButton = new JButton();
        shareButton.setSize(100, 34);
        shareButton.setLocation(158, 200);
        shareButton.setIcon(new ImageIcon
            (ImageLoader.loadImage(Globals.SHARE_BTN)));
        shareButton.addActionListener(shareListener);
        shareButton.setVisible(false);
        frame.add(shareButton);
        
        leaderboardsButton = new JButton();
        leaderboardsButton = new JButton();
        leaderboardsButton = new JButton();
        leaderboardsButton.setSize(228, 34);
        leaderboardsButton.setLocation(30, 250);
        leaderboardsButton.setIcon(new ImageIcon
            (ImageLoader.loadImage(Globals.LEADERBOARDS_BTN)));
        leaderboardsButton.addActionListener(leaderboardListener);
        leaderboardsButton.setVisible(false);
        frame.add(leaderboardsButton);
        
        canvas = new Canvas();
        
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        canvas.setMaximumSize(new Dimension(windowWidth, windowHeight));
        canvas.setMinimumSize(new Dimension(windowWidth, windowHeight));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Sets the buttons visible state.
     * @param data Whether to display it or not (true/false).
     */
    public void setButtonsVisibleState(boolean data) {
        restartButton.setVisible(data);
        shareButton.setVisible(data);
        leaderboardsButton.setVisible(data);
    }
    
    /**
     * Sets the restart button pressed status.
     * @param data Boolean variable.
     */
    public void setRestartButton(boolean data) {
        this.restartPressed = data;
    }
    
    /**
     * Sets the share button pressed status.
     * @param data Boolean variable.
     */
    public void setShareButton(boolean data) {
        this.sharePressed = data;
    }
    
    /**
     * Sets the leaderboard button pressed status.
     * @param data Boolean variable.
     */
    public void setLeaderboardStatus(boolean data) {
        this.leaderboardPressed = data;
    }
    
    /**
     * Adds any swing component to the display.
     * @param data The component to add to the display.
     */
    public void addComponent(Component data) {
        frame.add(data);
    }
    
    /**
     * Gets the Canvas.
     * @return The Canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    /**
     * Gets the JFrame.
     * @return The JFrame
     */
    public JFrame getFrame() {
        return frame;
    }
    
    /**
     * Sets the status of the restart button.
     * @return True if pressed, false if not.
     */
    public boolean getRestartButtonStatus() {
        return restartPressed;
    }
    
    /**
     * Sets the status of the share button.
     * @return True if pressed, false if not.
     */
    public boolean getShareButtonStatus() {
        return sharePressed;
    }
    
    /**
     * Sets the status of the Leaderboard button.
     * @return True if pressed, false if not.
     */
    public boolean getLeaderboardButtonStatus() {
        return leaderboardPressed;
    }
}
