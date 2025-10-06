package geometry;

import interfaces.Collidable;

/**
 * The CollisionInfo class stores information about a collision event.
 * It provides methods to access the point of collision and the object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a CollisionInfo with the specified collision point and collidable object.
     *
     * @param collisionPoint The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
