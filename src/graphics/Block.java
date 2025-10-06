package graphics;

import game.Game;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import interfaces.HitListener;
import geometry.Velocity;

/**
 * The Block class represents a block in the Arkanoid game.
 * It provides methods to draw the block, handle collisions, and notify listeners of hit events.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private boolean isGameBlock;
    private boolean isDeathRegion;

    /**
     * Constructs a Block with the specified rectangle, color, and type flags.
     *
     * @param rectangle     The rectangle representing the block's shape and position.
     * @param color         The color of the block.
     * @param isGameBlock   Indicates if the block is part of the game.
     * @param isDeathRegion Indicates if the block is a death region.
     */
    public Block(Rectangle rectangle, Color color, boolean isGameBlock, boolean isDeathRegion) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.isGameBlock = isGameBlock;
        this.isDeathRegion = isDeathRegion;
    }

    /**
     * Returns the rectangle representing the block's collision shape.
     *
     * @return The rectangle representing the block's collision shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Checks if the block is part of the game.
     *
     * @return true if the block is part of the game, false otherwise.
     */
    public boolean isGameBlock() {
        return this.isGameBlock;
    }

    /**
     * Checks if the block is a death region.
     *
     * @return true if the block is a death region, false otherwise.
     */
    public boolean isDeathRegion() {
        return this.isDeathRegion;
    }

    /**
     * Handles the collision with a ball, changing its velocity and notifying listeners.
     *
     * @param hitter          The ball that hit the block.
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity of the ball after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDoublex();
        double dy = currentVelocity.getDoubley();

        double rectLeftX = rectangle.getUpperLeft().getX();
        double rectRightX = rectLeftX + rectangle.getWidth();
        double rectTopY = rectangle.getUpperLeft().getY();
        double rectBottomY = rectTopY + rectangle.getHeight();

        double epsilon = 1e-10;

        boolean verticalEdgeHit = (Math.abs(collisionPoint.getX() - rectLeftX) < epsilon
                || Math.abs(collisionPoint.getX() - rectRightX) < epsilon)
                && collisionPoint.getY() >= rectTopY && collisionPoint.getY() <= rectBottomY;
        boolean horizontalEdgeHit = (Math.abs(collisionPoint.getY() - rectTopY) < epsilon
                || Math.abs(collisionPoint.getY() - rectBottomY) < epsilon)
                && collisionPoint.getX() >= rectLeftX && collisionPoint.getX() <= rectRightX;

        if (verticalEdgeHit) {
            dx = -dx;
        }
        if (horizontalEdgeHit) {
            dy = -dy;
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
            if (isGameBlock) {
                hitter.setColor(this.color);
            }
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface The surface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Notifies the block that time has passed.
     * This method is part of the Sprite interface.
     */
    @Override
    public void timePassed() {
        // No action needed
    }

    /**
     * Adds the block to the game as a sprite and collidable.
     *
     * @param g The game to add the block to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the color of the ball matches the color of the block.
     *
     * @param ball The ball to check.
     * @return true if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Removes the block from the game.
     *
     * @param g The game to remove the block from.
     */
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Notifies all registered listeners about a hit event.
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param h The hit listener to add.
     */
    public void addHitListener(HitListener h) {
        hitListeners.add(h);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param h The hit listener to remove.
     */
    public void removeHitListener(HitListener h) {
        hitListeners.remove(h);
    }
}
