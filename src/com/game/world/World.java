package com.game.world;

import com.game.audioplayer.AudioPlayer;
import com.game.display.Display;
import com.game.gameobjects.BottomPipe;
import com.game.gameobjects.Flappy;
import com.game.gameobjects.GameObject;
import com.game.gameobjects.Ground;
import com.game.gameobjects.TopPipe;
import com.game.gfx.ImageLoader;
import com.game.globals.Globals;
import com.game.states.GameState;
import com.game.utils.Util;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jason Anthony Galicia
 */
public class World {
    
    // Date for background check.
    private Date date;
    private int hour;
    private boolean isDaytime;
    
    // Display score img, background img, and display scores.
    private BufferedImage introGameMsg;
    private BufferedImage gameOverMsg;
    private BufferedImage backgroundImg;
    private BufferedImage[] displayScores;
    
    // The list of moving objects.
    private ArrayList<Ground> groundList;
    private ArrayList<TopPipe> topPipeList;
    private ArrayList<BottomPipe> bottomPipeList;
    
    // Flappy Bird.
    private Flappy flappy;
    private int flappyColorNumber;
    
    // Other...
    private GameState state;
    private AudioPlayer player;
    
    // Check if flappy has collided/
    private boolean isColliding;
    
    /**
     * Constructor class to initialize components in the world.
     * @param state The state.
     * @param flappy The flappy entity.
     * @param flappyColorNumber Flappy Bird's color number.
     */
    public World(GameState state, Flappy flappy, int flappyColorNumber) {
        this.flappy = flappy;
        this.flappyColorNumber = flappyColorNumber;
        this.state = state;
        isColliding = false;
        player = new AudioPlayer();
        
        setTime();
        setBufferedImages();
        initLists();
        initListComponents();
    }
    
    /**
     * Initialize the lists.
     */
    private void initLists() {
        groundList = new ArrayList<>();
        topPipeList = new ArrayList<>();
        bottomPipeList = new ArrayList<>();
    }
    
    /**
     * Initialize all the components.
     */
    private void initListComponents() {
        // Ground
        Ground ground1 = new Ground((float)Globals.GROUND_SPEED, Globals.GROUND,
                0, Globals.GROUND_START_Y, Globals.GAME_WINDOW_WIDTH, 
                Globals.GROUND_HEIGHT);
        Ground ground2 = new Ground((float)Globals.GROUND_SPEED, Globals.GROUND, 
                Globals.GAME_WINDOW_WIDTH, Globals.GROUND_START_Y, 
                Globals.GAME_WINDOW_WIDTH, Globals.GROUND_HEIGHT);
        groundList.add(ground1);
        groundList.add(ground2);
        
        // Pipes
        if (isDaytime) {
            // Top Pipe First
            int topHeight = Util.randomTopPipeHeight();
            TopPipe pipe1 = new TopPipe(Globals.PIPE_SPEED, 
                    Globals.GREEN_TOP_PIPE, Globals.PIPE_START_X, 0, 
                    Globals.PIPE_WIDTH, topHeight);
            topPipeList.add(pipe1);
            // Bottom Pipe Next
            int bottomY = topHeight + Globals.PIPE_INNER_SPACING;
            int bottomHeight = Globals.GROUND_START_Y - bottomY;
            BottomPipe pipe2 = new BottomPipe(Globals.PIPE_SPEED, 
                    Globals.GREEN_BOTTOM_PIPE, Globals.PIPE_START_X, 
                    (float)bottomY, Globals.PIPE_WIDTH, bottomHeight);
            bottomPipeList.add(pipe2);
        }
        else {
            // Top Pipe First
            int topHeight = Util.randomTopPipeHeight();
            TopPipe pipe1 = new TopPipe(Globals.PIPE_SPEED, 
                    Globals.RED_TOP_PIPE, Globals.PIPE_START_X, 0, 
                    Globals.PIPE_WIDTH, topHeight);
            topPipeList.add(pipe1);
            // Bottom Pipe Next
            int bottomY = topHeight + Globals.PIPE_INNER_SPACING;
            int bottomHeight = Globals.GROUND_START_Y - bottomY;
            BottomPipe pipe2 = new BottomPipe(Globals.PIPE_SPEED, 
                    Globals.RED_BOTTOM_PIPE, Globals.PIPE_START_X, 
                    (float)bottomY, Globals.PIPE_WIDTH, bottomHeight);
            bottomPipeList.add(pipe2);
        }
    }
    
    /**
     * Sets the current time of the local machine.
     */
    public void setTime() {
        date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * Gets the current hour. So if it was 7:42, the this would return, "7."
     * @return The hour as an integer.
     */
    public int getCurrentHour() {
        setTime();
        return hour;
    }
    
    /**
     * Set the initial buffered images.
     */
    public void setBufferedImages() {
        // Hours between 7:00 a.m and 8:00 p.m.
        setTime();
        if (hour > 7 && hour < 20){
            backgroundImg = ImageLoader.loadImage(Globals.DAY_BACKGROUND);
            isDaytime = true;
        }
        else {
            backgroundImg = ImageLoader.loadImage(Globals.NIGHT_BACKGROUND);
            isDaytime = false;
        }
        
        introGameMsg = ImageLoader.loadImage(Globals.INTRO_GAME_MSG);
        gameOverMsg = ImageLoader.loadImage(Globals.GAMEOVER_GAME_MSG);
        
        displayScores = new BufferedImage[10];
        displayScores[0] = ImageLoader.loadImage(Globals.SCORE_0);
        displayScores[1] = ImageLoader.loadImage(Globals.SCORE_1);
        displayScores[2] = ImageLoader.loadImage(Globals.SCORE_2);
        displayScores[3] = ImageLoader.loadImage(Globals.SCORE_3);
        displayScores[4] = ImageLoader.loadImage(Globals.SCORE_4);
        displayScores[5] = ImageLoader.loadImage(Globals.SCORE_5);
        displayScores[6] = ImageLoader.loadImage(Globals.SCORE_6);
        displayScores[7] = ImageLoader.loadImage(Globals.SCORE_7);
        displayScores[8] = ImageLoader.loadImage(Globals.SCORE_8);
        displayScores[9] = ImageLoader.loadImage(Globals.SCORE_9);
    }
    
    /**
     * Renders all graphics from the world to the screen.
     * @param g The graphic object.
     * @param gameOver Game over boolean.
     * @param gameStarted Game started boolean.
     */
    public void render(Graphics g, boolean gameOver, boolean gameStarted) {
        // Render the background first
        g.drawImage(backgroundImg, 0, 0, null);
        if (!gameStarted) g.drawImage(introGameMsg, 
                (int)(Globals.GAME_WINDOW_WIDTH*Globals.INTRO_MSG_XSCALE), 
                (int)(Globals.GAME_WINDOW_HEIGHT*Globals.INTRO_MSG_YSCALE),
                    null);
        renderPipes(g);
        renderPlayer(g);
        renderGround(g);
        if (gameStarted && !isColliding) renderScore(g, 50);
        if (isColliding) {
            g.drawImage(gameOverMsg, 
                (int)(Globals.GAME_WINDOW_WIDTH*Globals.INTRO_MSG_XSCALE), 
                (int)(Globals.GAME_WINDOW_HEIGHT*Globals.INTRO_MSG_YSCALE),
                    null);
            renderScore(g, 120);
        }
    }
    
    /**
     * Renders the score unto the screen.
     * @param g The graphics object.
     */
    private void renderScore(Graphics g, int y) {
        String digits = flappy.getScore() + "";
        
        for (int i = 0; i < digits.length(); i++) {
            if (digits.length() == 1) {
                g.drawImage(displayScores[digits.charAt(i) - '0'], 
                        Globals.GAME_WINDOW_WIDTH/2-12, y, null);
            }
            else if (digits.length() == 2 && flappy.getScore() < 20) {
                g.drawImage(displayScores[digits.charAt(0) - '0'], 
                        Globals.GAME_WINDOW_WIDTH/2-28, y, null);
                g.drawImage(displayScores[digits.charAt(1) - '0'], 
                        Globals.GAME_WINDOW_WIDTH/2-11, y, null);
            }
            else if (digits.length() == 2) {
                g.drawImage(displayScores[digits.charAt(0) - '0'], 
                        Globals.GAME_WINDOW_WIDTH/2-35, y, null);
                g.drawImage(displayScores[digits.charAt(1) - '0'], 
                        Globals.GAME_WINDOW_WIDTH/2-11, y, null);
            }
        }
    }
    
    /**
     * Renders the player to the screen.
     * @param g The graphics object.
     */
    private void renderPlayer(Graphics g) {
        flappy.render(g);
    }
    
    /**
     * Renders the ground to the screen.
     * @param g The graphics object.
     */
    private void renderGround(Graphics g) {
        for (int i = 0; i < groundList.size(); i++) {
            groundList.get(i).render(g);
        }
    }
    
    /**
     * Renders the pipes to the screen.
     * @param g The graphics object.
     */
    private void renderPipes(Graphics g) {
        for (int i = 0; i < topPipeList.size(); i++) {
            if (topPipeList.get(i).getX() < Globals.GAME_WINDOW_WIDTH && 
                    topPipeList.get(i).getX() > -Globals.PIPE_WIDTH)
                topPipeList.get(i).render(g);
        }
        
        for (int i = 0; i < bottomPipeList.size(); i++) {
            if (bottomPipeList.get(i).getX() < Globals.GAME_WINDOW_WIDTH && 
                    bottomPipeList.get(i).getX() > -Globals.PIPE_WIDTH)
                bottomPipeList.get(i).render(g);
        }
    }
    
    /**
     * Updates all game objects and entities.
     * @param ticks The ticks for the game.
     * @param inputReceived Input received boolean.
     * @param display The display / frame.
     */
    public void update(int ticks, boolean inputReceived, Display display) {
        // We don't really need to update the background, but the ground will 
        // definitely need to use this method.
        updatePlayer(state.getGameOverStatus(), state.getGameStartedStatus(), 
                ticks, state.inputReceived());
        updateGround(state.getGameOverStatus());
        updatePipes(state.getGameStartedStatus());
        updatePoints();
        
        if (isColliding) {
            display.setButtonsVisibleState(true);
        }
    }
    
    /**
     * Updates the points.
     */
    private void updatePoints() {
        // We use the topPipeList size because we can assume the pipes are 
        // alligned together so both x values will be the same
        if (!isColliding) {
            for (int i = 0; i < topPipeList.size(); i++) {
                if (topPipeList.get(i).getX() == 100) {
                    player.playSound(Globals.FLAPPY_GAIN_POINT);
                
                    int currentScore = flappy.getScore() + 1;
                    flappy.setScore(currentScore);
                    System.out.println(flappy.getScore());
                }
            }
        }
    }
    
    /**
     * Updates the variables and other characteristics of the player.
     * @param gameOver The game over boolean.
     * @param gameStarted The game started boolean.
     * @param ticks The ticks for the game.
     * @param inputReceived The input received boolean.
     */
    private void updatePlayer(boolean gameOver, boolean gameStarted, 
            int ticks, boolean inputReceived) {
        if (!gameOver) {
            if (inputReceived && !isColliding) 
                player.playSound(Globals.FLAPPY_JUMP);
            flappy.update(ticks, gameStarted, gameOver, isColliding, 
                    inputReceived);
        }
        
    }
    
    /**
     * Updates the variables and other characteristics of the ground list.
     * @param gameOver The game over boolean.
     */
    private void updateGround(boolean gameOver) {
        if (!gameOver) {
            for (int i = 0; i < groundList.size(); i++) {
                checkCollision(groundList.get(i));
                if (!isColliding) groundList.get(i).update();
                if (groundList.get(i).getX() == -Globals.GAME_WINDOW_WIDTH) {
                    groundList.remove(i);
                    Ground temp = new Ground((float)Globals.GROUND_SPEED, 
                        Globals.GROUND, Globals.GAME_WINDOW_WIDTH, 
                        Globals.GROUND_START_Y, Globals.GAME_WINDOW_WIDTH, 
                        Globals.GROUND_HEIGHT);
                    groundList.add(temp);
                }
            }
        }
    }
    
    /**
     * Checks the collision between flappy and another Game Object.
     * @param object The Game Object.
     */
    private void checkCollision(GameObject object) {
        if (flappy.isColliding(object)) {
            System.out.println("Collision Detected");
            if (!isColliding){
                player.playSound(Globals.FLAPPY_COLLISION);
            }
            isColliding = true;
            if (object instanceof Ground) {
                player.playSound(Globals.FLAPPY_DIE_EFFECT);
                System.out.println("Collision With Ground Detected");
                state.setGameOver(true);
            }
        }
    }
    
    /**
     * Updates the variables and other characteristics of both pipe lists.
     * @param gameStarted The gameStarted boolean.
     */
    private void updatePipes(boolean gameStarted) {
        if (gameStarted && !isColliding) {
                for (int i = 0; i < topPipeList.size(); i++) {
                    checkCollision(topPipeList.get(i));
                    checkCollision(bottomPipeList.get(i));
                    if (topPipeList.get(i).getX() == 288 && 
                        bottomPipeList.get(i).getX() == 288) {
                            String topPipePath, bottomPipePath = "";
                        if (isDaytime) {
                            topPipePath = Globals.GREEN_TOP_PIPE;
                            bottomPipePath = Globals.GREEN_BOTTOM_PIPE;
                        }
                        else {
                            topPipePath = Globals.RED_TOP_PIPE;
                            bottomPipePath = Globals.RED_BOTTOM_PIPE;
                        }
                        
                        int topHeight = Util.randomTopPipeHeight();
                        TopPipe temp1 = new TopPipe(2, topPipePath, 
                            topPipeList.get(i).getX() + 
                                Globals.PIPE_OUTTER_SPACING, 0, 
                                Globals.PIPE_WIDTH, topHeight);
                        topPipeList.add(temp1);
                        
                        int bottomY = topHeight + Globals.PIPE_INNER_SPACING;
                        int bottomHeight = Globals.GROUND_START_Y - bottomY;
                        BottomPipe temp2 = new BottomPipe(2, bottomPipePath, 
                           topPipeList.get(i).getX() + 
                                Globals.PIPE_OUTTER_SPACING, bottomY, 
                                Globals.PIPE_WIDTH, bottomHeight);
                        bottomPipeList.add(temp2);
                    }
                    if (topPipeList.get(i).getX() == -Globals.PIPE_WIDTH-1 
                        && bottomPipeList.get(i).getX() == 
                            -Globals.PIPE_WIDTH-1) {
                            topPipeList.remove(0);
                            bottomPipeList.remove(0);
                    }
                    topPipeList.get(i).update();
                    bottomPipeList.get(i).update();
                }
           }
        }
    }
    


