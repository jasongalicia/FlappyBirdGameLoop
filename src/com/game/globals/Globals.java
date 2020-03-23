package com.game.globals;

/**
 *
 * @author Jason Anthony Galicia
 */
public class Globals {
    // A billion
    public static final int BILLION = 1000000000;
    
    // Frame componenets
    public static final String WINDOW_TITLE = "Flappy Bird RW V1.0.0";
    public static final int GAME_WINDOW_WIDTH = 288;
    public static final int GAME_WINDOW_HEIGHT = 512;
    
    // Scaling
    public static final double INTRO_MSG_XSCALE = 0.18;
    public static final double INTRO_MSG_YSCALE = 0.1;
    
    // Game Pieces
    public static final int GROUND_START_Y = 400;
    public static final int GROUND_HEIGHT = 112;
    public static final int GROUND_SPEED = 2;
    
    public static final int PIPE_SPEED = 2;
    public static final int PIPE_WIDTH = 52;
    public static final int PIPE_OUTTER_SPACING = 160;
    public static final int PIPE_INNER_SPACING = 100;
    public static final int PIPE_START_X = 600;
    
    public static final int FLAPPY_WIDTH = 34;
    public static final int FLAPPY_HEIGHT = 24;
    
    // 10 Pixels per second gravity
    public static final float GRAVITY = 10;
    
    // Textures.................................................................
    public static final String INTRO_GAME_MSG = "/textures/intro-game-message"
            + ".png";
    public static final String GAMEOVER_GAME_MSG = "/textures/msg_gameover.png";
    public static final String RESTART_BTN = "/textures/restart.png";
    public static final String SHARE_BTN = "/textures/share.PNG";
    public static final String LEADERBOARDS_BTN = "/textures/leaderboard.png";
    
    public static final String DAY_BACKGROUND = "/textures/daybackground.png";
    public static final String NIGHT_BACKGROUND = "/textures/nightbackground"
            + ".png";
    public static final String GROUND = "/textures/ground-pic.png";
    public static final String GREEN_TOP_PIPE = "/textures/upper-pipe-green.png"
            + "";
    public static final String GREEN_BOTTOM_PIPE = "/textures/lower-pipe-green"
            + ".png";
    public static final String RED_TOP_PIPE = "/textures/upper-pipe-red.png";
    public static final String RED_BOTTOM_PIPE = "/textures/lower-pipe-red.png";
    
    public static final String BB_DOWNFLAP = "/textures/bb-downflap.png";
    public static final String BB_MIDFLAP = "/textures/bb-midflap.png";
    public static final String BB_UPFLAP = "/textures/bb-upflap.png";
    
    public static final String YB_DOWNFLAP = "/textures/yb-downflap.png";
    public static final String YB_MIDFLAP = "/textures/yb-midflap.png";
    public static final String YB_UPFLAP = "/textures/yb-upflap.png";
    
    public static final String RB_DOWNFLAP = "/textures/rb-downflap.png";
    public static final String RB_MIDFLAP = "/textures/rb-midflap.png";
    public static final String RB_UPFLAP = "/textures/rb-upflap.png";
    
    public static final String SCORE_0 = "/textures/score_0.png";
    public static final String SCORE_1 = "/textures/score_1.png";
    public static final String SCORE_2 = "/textures/score_2.png";
    public static final String SCORE_3 = "/textures/score_3.png";
    public static final String SCORE_4 = "/textures/score_4.png";
    public static final String SCORE_5 = "/textures/score_5.png";
    public static final String SCORE_6 = "/textures/score_6.png";
    public static final String SCORE_7 = "/textures/score_7.png";
    public static final String SCORE_8 = "/textures/score_8.png";
    public static final String SCORE_9 = "/textures/score_9.png";
    
    // Audio Files..............................................................
    public static final String FLAPPY_JUMP = "/audio/flappy-jump-effect.wav";
    public static final String FLAPPY_COLLISION = "/audio/flappy-collision-"
            + "effect.wav";
    public static final String FLAPPY_GAIN_POINT = "/audio/flappy-gain-point"
            + ".wav";
    public static final String FLAPPY_DIE_EFFECT = "/audio/flappy-die-effect"
            + ".wav";
    
    // Database.................................................................
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://us-cdbr-iron-east-04."
            + "cleardb.net/heroku_76662f7ef1da94f";
    public static final String DB_USERNAME = "bed7ff65051d2d";
    public static final String DB_PASSWORD = "d1faaab7";
    
    // Link
    public static final String SIGN_UP_LINK = "https://fbird-analytical-"
            + "dashboard.herokuapp.com/signup";
}
