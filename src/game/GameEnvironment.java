package game;

import java.util.ArrayList;
import java.util.List;
import interfaces.Collidable;
import geometry.Line;
import geometry.CollisionInfo;
import geometry.Point;
import geometry.Rectangle;

/**
 * The GameEnvironment class manages a collection of collidable objects in the game.
 * It provides methods to add and remove collidables and to get information about collisions.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs a GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes the given collidable from the environment.
     *
     * @param c The collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Returns the information about the closest collision that is going to occur.
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, returns null.
     *
     * @param trajectory The trajectory of the moving object.
     * @return The CollisionInfo about the closest collision, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollisionPoint = null;
        Collidable closestCollisionObject = null;
        double closestDistance = Double.POSITIVE_INFINITY;

        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect);
            if (intersection != null) {
                double distance = trajectory.start().distance(intersection);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCollisionPoint = intersection;
                    closestCollisionObject = collidable;
                }
            }
        }

        if (closestCollisionPoint != null) {
            return new CollisionInfo(closestCollisionPoint, closestCollisionObject);
        }
        return null;
    }
}
