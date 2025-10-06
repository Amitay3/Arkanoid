package interfaces;

import geometry.Point;
import geometry.Rectangle;
import graphics.Ball;
import geometry.Velocity;

/**
 * The Collidable interface represents objects that can be collided with in the game.
 * It provides methods to get the collision shape and handle collisions.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return The rectangle representing the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it at the specified point with a given velocity.
     * The method returns the new velocity expected after the hit, based on the force the object inflicted.
     *
     * @param hitter The ball that hit the object.
     * @param collisionPoint The point at which the collision occurred.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
