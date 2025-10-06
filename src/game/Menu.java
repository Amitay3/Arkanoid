package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;

public class Menu {
    private final GUI gui;
    private final KeyboardSensor keyboard;
    private final Sleeper sleeper;

    public Menu() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.keyboard = gui.getKeyboardSensor();
        this.sleeper = new Sleeper();
    }

    public void showMenu() {
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Background gradient style
            d.setColor(new Color(20, 20, 60));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new Color(80, 120, 250)); 
            d.fillRectangle(0, 0, 800, 200);

            // Title
            d.setColor(new Color(255, 220, 0)); 
            d.drawText(270, 150, "ARKANOID", 70);

            // Menu options
            d.setColor(Color.WHITE);
            d.drawText(270, 320, "Press SPACE to Play", 30);
            d.drawText(240, 380, "Press H for How To Play", 30);
            d.drawText(300, 440, "Press E to Exit", 30);

            gui.show(d);

            // Handle inputs
            if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                Game game = new Game(gui);
                game.initialize();
                game.run();
            } else if (keyboard.isPressed("h")) {
                showInstructions(gui);
            } else if (keyboard.isPressed("e")) {
                gui.close();
                System.exit(0);
            }

            sleeper.sleepFor(16);
        }
    }


public static void gameLost(GUI gui, int score) {
    KeyboardSensor keyboard = gui.getKeyboardSensor();
    Sleeper sleeper = new Sleeper();

    while (true) {
        DrawSurface d = gui.getDrawSurface();

        // Background gradient effect (simulated)
        d.setColor(new Color(255, 240, 240));
        d.fillRectangle(0, 0, 800, 600);

        // Title text
        d.setColor(new Color(200, 0, 0)); // deep red
        d.drawText(250, 180, "GAME OVER!", 60);

        // Score display
        d.setColor(Color.BLACK);
        d.drawText(290, 270, "Your Score: " + score, 35);

        // Replay and quit instructions
        d.setColor(new Color(60, 60, 60));
        d.drawText(150, 370, "Press SPACE to play again", 25);
        d.drawText(150, 420, "Press E to exit", 25);

        gui.show(d);

        // Handle input
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            Game newGame = new Game(gui);
            newGame.initialize();
            newGame.run();
            break;
        }

        if (keyboard.isPressed("e")) {
            gui.close();
            System.exit(0);
        }

        sleeper.sleepFor(16);
    }
}



public static void gameWon(GUI gui, int score) {
    KeyboardSensor keyboard = gui.getKeyboardSensor();
    Sleeper sleeper = new Sleeper();

    while (true) {
        DrawSurface d = gui.getDrawSurface();

        // Background with bright celebratory tone
        d.setColor(new Color(255, 250, 200));
        d.fillRectangle(0, 0, 800, 600);

        // Main "You Won!" title
        d.setColor(new Color(255, 200, 0)); 
        d.drawText(270, 180, "YOU WON!", 60);

        // Score display
        d.setColor(new Color(50, 50, 120)); 
        d.drawText(290, 270, "Your Score: " + score, 35);

        // Replay and exit instructions
        d.setColor(new Color(70, 70, 70));
        d.drawText(150, 370, "Press SPACE to play again", 25);
        d.drawText(150, 420, "Press E to exit", 25);

        gui.show(d);

        // Handle keyboard input
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            Game newGame = new Game(gui);
            newGame.initialize();
            newGame.run();
            break;
        }

        if (keyboard.isPressed("e")) {
            gui.close();
            System.exit(0);
        }

        sleeper.sleepFor(16);
    }
}


    // Show instructions screen
    private void showInstructions(GUI gui) {
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Soft light background
            d.setColor(new Color(240, 245, 255));
            d.fillRectangle(0, 0, 800, 600);

            // Header
            d.setColor(new Color(60, 90, 200));
            d.drawText(250, 120, "HOW TO PLAY", 50);

            // Instructions
            d.setColor(Color.BLACK);
            d.drawText(150, 220, "->  Use the RIGHT arrow key to move right", 25);
            d.drawText(150, 260, "<-  Use the LEFT arrow key to move left", 25);
            d.drawText(150, 300, "  Break all blocks to win the game", 25);
            d.drawText(150, 340, "  Don't let the balls fall below the paddle!", 25);

            // Back/start prompt
            d.setColor(new Color(40, 40, 40));
            d.drawText(180, 460, "Press SPACE to Start Playing", 30);
            d.drawText(230, 510, "Press B to Return to Menu", 25);

            gui.show(d);

            if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                Game game = new Game(gui);
                game.initialize();
                game.run();
                break;
            }

            if (keyboard.isPressed("b")) {
                return;
            }

            sleeper.sleepFor(16);
        }
    }

}
