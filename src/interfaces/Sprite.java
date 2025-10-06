package interfaces;

import biuoop.DrawSurface;

/**
 * The Sprite interface represents objects that can be drawn on the screen and can be notified that time has passed.
 */
public interface Sprite {

    /**
     * Draws the sprite to the screen.
     *
     * @param d The surface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}
