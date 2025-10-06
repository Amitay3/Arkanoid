package graphics;

import game.Game;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import interfaces.Sprite;
import game.GameEnvironment;
import geometry.Velocity;
import interfaces.Collidable;
import geometry.CollisionInfo;

import java.awt.Color;

/**
 * The Ball class represents a ball in the Arkanoid game.
 * It provides methods to draw the ball, move it, and handle collisions with other objects.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a Ball with the specified center, radius, and color.
     *
     * @param center The center point of the ball.
     * @param r      The radius of the ball.
     * @param color  The color of the ball.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructs a Ball with the specified coordinates for the center, radius, and color.
     *
     * @param x     The x-coordinate of the center point.
     * @param y     The y-coordinate of the center point.
     * @param r     The radius of the ball.
     * @param color The color of the ball.
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Returns the x-coordinate of the center point.
     *
     * @return The x-coordinate of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the center point.
     *
     * @return The y-coordinate of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return The radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return The color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color The new color of the ball.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the center point of the ball.
     *
     * @return The center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Returns the area of the ball.
     *
     * @return The area of the ball.
     */
    public int getArea() {
        return (int) (Math.PI * Math.pow(this.radius, 2));
    }

    /**
     * Returns the diameter of the ball.
     *
     * @return The diameter of the ball.
     */
    public int getDiameter() {
        return (int) (2 * this.getSize());
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The surface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Notifies the ball that time has passed.
     * This method is part of the Sprite interface.
     */
    @Override
    public void timePassed() {
        moveOneStep(800, 600);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball with the specified horizontal and vertical components.
     *
     * @param dx The horizontal component of the velocity.
     * @param dy The vertical component of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the center point of the ball.
     *
     * @param center The new center point of the ball.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Returns the game environment of the ball.
     *
     * @return The game environment of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets the game environment of the ball.
     *
     * @param gameEnvironment The new game environment of the ball.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Moves the ball one step, considering collisions with other objects and boundaries.
     *
     * @param width  The width of the game area.
     * @param height The height of the game area.
     */
    public void moveOneStep(int width, int height) {
        double nextX = this.center.getX() + this.velocity.getDoublex();
        double nextY = this.center.getY() + this.velocity.getDoubley();

        Line trajectory = new Line(this.center, new Point(nextX, nextY));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);

        if (collisionInfo != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable collisionObject = collisionInfo.collisionObject();

            double collisionDx = collisionPoint.getX() - this.center.getX();
            double collisionDy = collisionPoint.getY() - this.center.getY();
            double adjustmentFactor = (Math.sqrt(collisionDx * collisionDx + collisionDy * collisionDy) - this.radius)
                    / Math.sqrt(collisionDx * collisionDx + collisionDy * collisionDy);
            double adjustedX = this.center.getX() + collisionDx * adjustmentFactor;
            double adjustedY = this.center.getY() + collisionDy * adjustmentFactor;

            this.center = new Point(adjustedX, adjustedY);

            this.velocity = collisionObject.hit(this, collisionPoint, this.velocity);

            double moveAwayX = this.velocity.getDoublex() * 0.05;
            double moveAwayY = this.velocity.getDoubley() * 0.05;
            this.center = new Point(this.center.getX() + moveAwayX, this.center.getY() + moveAwayY);
        } else {
            this.center = new Point(nextX, nextY);
        }

        if (this.center.getX() - this.radius < 0) {
            this.center = new Point(this.radius, this.center.getY());
            this.velocity = new Velocity(-this.velocity.getDoublex(), this.velocity.getDoubley());
        } else if (this.center.getX() + this.radius > width) {
            this.center = new Point(width - this.radius, this.center.getY());
            this.velocity = new Velocity(-this.velocity.getDoublex(), this.velocity.getDoubley());
        }

        if (this.center.getY() - this.radius < 0) {
            this.center = new Point(this.center.getX(), this.radius);
            this.velocity = new Velocity(this.velocity.getDoublex(), -this.velocity.getDoubley());
        }
    }

    /**
     * Adds the ball to the game as a sprite.
     *
     * @param g The game to add the ball to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from the game.
     *
     * @param g The game to remove the ball from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
