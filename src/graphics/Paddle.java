package graphics;

import game.Game;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Sprite;
import geometry.Velocity;
import interfaces.Collidable;

import java.awt.Color;

/**
 * The Paddle class represents the paddle in the Arkanoid game.
 * It provides methods to move the paddle, draw it, and handle collisions with other objects.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private Color color;
    private KeyboardSensor keyboard;
    private double speed;

    /**
     * Constructs a Paddle with the specified rectangle, color, keyboard sensor, and speed.
     *
     * @param rectangle The rectangle representing the paddle's shape and position.
     * @param color     The color of the paddle.
     * @param keyboard  The keyboard sensor to control the paddle's movement.
     * @param speed     The speed at which the paddle moves.
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard, double speed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * Moves the paddle to the left, wrapping around the screen if necessary.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - speed;
        if (newX < 30) { // Wrap around to the right side within the screen
            newX = 770 - this.rectangle.getWidth();
        }
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right, wrapping around the screen if necessary.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + speed;
        if (newX + this.rectangle.getWidth() > 770) { // Wrap around to the left side within the screen
            newX = 30;
        }
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Notifies the paddle that time has passed.
     * This method is part of the Sprite interface.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface The surface to draw the paddle on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Returns the rectangle representing the paddle's collision shape.
     *
     * @return The rectangle representing the paddle's collision shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the collision with a ball, changing its velocity based on the collision point.
     *
     * @param ball            The ball that hit the paddle.
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity of the ball after the collision.
     */
    @Override
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDoublex();
        double dy = currentVelocity.getDoubley();
        double rectLeftX = rectangle.getUpperLeft().getX();
        double regionWidth = rectangle.getWidth() / 5;
        double collisionX = collisionPoint.getX();
        int region;

        if (collisionX < rectLeftX + regionWidth) {
            region = 1;
        } else if (collisionX < rectLeftX + 2 * regionWidth) {
            region = 2;
        } else if (collisionX < rectLeftX + 3 * regionWidth) {
            region = 3;
        } else if (collisionX < rectLeftX + 4 * regionWidth) {
            region = 4;
        } else {
            region = 5;
        }

        double speed = Math.sqrt(dx * dx + dy * dy);
        Velocity newVelocity = currentVelocity;

        switch (region) {
            case 1:
                newVelocity = Velocity.fromAngleAndSpeed(300, speed, true);
                break;
            case 2:
                newVelocity = Velocity.fromAngleAndSpeed(330, speed, true);
                break;
            case 3:
                newVelocity = new Velocity(dx, -dy);
                break;
            case 4:
                newVelocity = Velocity.fromAngleAndSpeed(30, speed, true);
                break;
            case 5:
                newVelocity = Velocity.fromAngleAndSpeed(60, speed, true);
                break;
            default:
                //do nothing
        }


        if (collisionPoint.getY() == rectangle.getUpperLeft().getY()) {
            newVelocity = new Velocity(newVelocity.getDoublex(), -Math.abs(newVelocity.getDoubley()));
        }

        return newVelocity;
    }

    /**
     * Adds the paddle to the game as a sprite and collidable.
     *
     * @param g The game to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
