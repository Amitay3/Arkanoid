package geometry;

/**
 * The Velocity class represents a velocity vector with horizontal and vertical components.
 * It provides methods to apply the velocity to a point, calculate speed, and create a velocity from angle and speed.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a Velocity with the specified horizontal and vertical components.
     *
     * @param dx The horizontal component of the velocity.
     * @param dy The vertical component of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the horizontal component of the velocity.
     *
     * @return The horizontal component of the velocity.
     */
    public double getDoublex() {
        return dx;
    }

    /**
     * Returns the vertical component of the velocity.
     *
     * @return The vertical component of the velocity.
     */
    public double getDoubley() {
        return dy;
    }

    /**
     * Takes a point with position (x, y) and returns a new point with position (x + dx * scale, y + dy * scale).
     *
     * @param p The point to apply the velocity to.
     * @param scale The scaling factor for the velocity.
     * @return A new point with the updated position.
     */
    public Point applyToPoint(Point p, double scale) {
        double newX = p.getX() + this.dx * scale;
        double newY = p.getY() + this.dy * scale;
        return new Point(newX, newY);
    }

    /**
     * Returns the speed of the velocity vector.
     *
     * @return The speed of the velocity vector.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the angle of the velocity vector in degrees.
     *
     * @return The angle of the velocity vector in degrees.
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    /**
     * Creates a Velocity from the specified angle and speed.
     *
     * @param angle The angle of the velocity vector in degrees.
     * @param speed The speed of the velocity vector.
     * @param invertY Whether to invert the vertical component of the velocity.
     * @return A new Velocity instance with the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed, boolean invertY) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        if (invertY) {
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }
}
