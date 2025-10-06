package graphics;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class manages a collection of Sprite objects.
 * It provides methods to add, remove, notify, and draw all sprites in the collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This method calls the timePassed() method on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites); // Make a copy of the list
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * This method calls the drawOn(d) method on all sprites.
     *
     * @param d The surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites); // Make a copy of the list
        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }
}
