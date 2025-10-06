package geometry;

/**
 * The Point class represents a point in a 2D space.
 * It provides methods to calculate distance to another point and to check for equality with another point.
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = 1e-7;

    /**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other The other point.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * @param other The other point to compare with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.getX()) < EPSILON && Math.abs(this.y - other.getY()) < EPSILON;
    }

    /**
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return y;
    }
}
