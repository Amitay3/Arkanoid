package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

import listeners.BlockRemover;
import listeners.BallRemover;
import listeners.ScoreTrackingListener;
import listeners.Counter;
import geometry.Point;
import geometry.Rectangle;
import graphics.Ball;
import graphics.Paddle;
import graphics.SpriteCollection;
import graphics.ScoreIndicator;
import graphics.Block;
import interfaces.Collidable;
import interfaces.Sprite;
import listeners.PrintingHitListener;

/**
 * The Game class manages the game flow, including initialization,
 * running the game loop, and handling the game environment.
 * It holds references to the game environment,
 * the GUI, and various counters for game elements like balls, blocks, and score.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    /**
     * Constructs a new Game instance, initializing the sprite collection, game environment, and counters.
     */
    public Game(GUI gui) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initializes the game, setting up the GUI, keyboard sensor, balls, blocks, and paddle.
     */
    public void initialize() {
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();

        // Create balls and add them to the game
        createBall(new Point(400, 300), 5, Color.WHITE, 3, 3);
        createBall(new Point(450, 350), 5, Color.WHITE, -3, 3);
        createBall(new Point(500, 300), 5, Color.WHITE, 3, -3);

        int blockWidth = 40;
        int blockHeight = 15;
        int blockSpacing = 0;
        int marginSize = 30;
        int topMargin = 50;
        int rows = 6;
        int blocksPerRow = 15;

        Color[] rowColors = {Color.GRAY, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PINK};

        // Create the shoulder blocks
        createShoulderBlocks(marginSize);

        PrintingHitListener printingHitListener = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);

        // Create the death region block
        Block deathRegionBlock = new Block(new Rectangle(new Point(marginSize, 600), 800 - 2 * marginSize, 30),
                Color.BLUE, false, true);
        deathRegionBlock.addToGame(this);
        deathRegionBlock.addHitListener(ballRemover);

        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.addSprite(scoreIndicator);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < blocksPerRow - i; j++) {
                int x = 800 - marginSize - (j + 1) * (blockWidth + blockSpacing);
                int y = topMargin + 30 + i * (blockHeight + blockSpacing); // Positioned under the top margin
                Rectangle rect = new Rectangle(new Point(x, y), blockWidth, blockHeight);
                Block block = new Block(rect, rowColors[i], true, false);
                block.addToGame(this);
                block.addHitListener(printingHitListener);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                remainingBlocks.increase(1);
            }
        }

        // Adjust the paddle position to sit on top of the bottom margin
        Rectangle paddleRect = new Rectangle(new Point(360, 600 - marginSize - 15), 80, 15); // Adjusted position
        Paddle paddle = new Paddle(paddleRect, Color.YELLOW, keyboard, 10.0);
        paddle.addToGame(this);
    }

    /**
     * Creates a ball and adds it to the game.
     *
     * @param center The center point of the ball.
     * @param radius The radius of the ball.
     * @param color  The color of the ball.
     * @param dx     The horizontal velocity of the ball.
     * @param dy     The vertical velocity of the ball.
     */
    private void createBall(Point center, int radius, Color color, double dx, double dy) {
        Ball ball = new Ball(center, radius, color);
        ball.setVelocity(dx, dy);
        ball.setGameEnvironment(environment);
        ball.addToGame(this);
        remainingBalls.increase(1);
    }

    /**
     * Creates the shoulder blocks (margins) around the play area.
     *
     * @param marginSize The size of the margins.
     */
    private void createShoulderBlocks(int marginSize) {
        // Create the top margin block
        Block topMarginBlock = new Block(new Rectangle(new Point(0, 0), 800, marginSize), Color.GRAY, false, false);
        topMarginBlock.addToGame(this);

        // Create the left margin block
        Block leftMarginBlock = new Block(new Rectangle(new Point(0, marginSize), marginSize, 600 - marginSize),
                Color.GRAY, false, false);
        leftMarginBlock.addToGame(this);

        // Create the right margin block
        Block rightMarginBlock =
                new Block(new Rectangle(new Point(800 - marginSize, marginSize), marginSize, 600 - marginSize),
                        Color.GRAY, false, false);
        rightMarginBlock.addToGame(this);

        // No bottom margin block to ensure balls can reach the death region
    }

    /**
     * Runs the game, starting the game loop which updates and draws all game elements.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (remainingBlocks.getValue() > 0 && remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            // Set the background color
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);

            // Draw all sprites
            this.sprites.drawAllOn(d);
            gui.show(d);

            // Notify all sprites that time has passed
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        if (remainingBlocks.getValue() == 0) {
            score.increase(100); // Award 100 points for clearing all blocks
            Menu.gameWon(this.gui, score.getValue());
        } else {
            Menu.gameLost(this.gui, score.getValue());
        }

    }

    /**
     * Returns the score counter for the game.
     *
     * @return The current score counter.
     */
    public Counter getScore() {
        return this.score;
    }
}
