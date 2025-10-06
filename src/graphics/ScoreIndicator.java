package graphics;

import biuoop.DrawSurface;
import interfaces.Sprite;
import listeners.Counter;
import java.awt.Color;

/**
 * The ScoreIndicator class represents the score display in the Arkanoid game.
 * It provides methods to draw the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a ScoreIndicator with the specified score counter.
     *
     * @param score The counter that tracks the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d The surface to draw the score indicator on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + this.score.getValue(), 15);
    }

    /**
     * Notifies the score indicator that time has passed.
     * This method is part of the Sprite interface.
     */
    @Override
    public void timePassed() {
        // No action needed
    }
}
