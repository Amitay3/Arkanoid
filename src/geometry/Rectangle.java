package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle in a 2D space.
 * It provides methods to calculate intersection points with a given line and to access rectangle properties.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a Rectangle with the specified location and dimensions.
     *
     * @param upperLeft The upper-left point of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line The line to check for intersections with.
     * @return A list of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

        Line topEdge = new Line(upperLeft, upperRight);
        Line rightEdge = new Line(upperRight, lowerRight);
        Line bottomEdge = new Line(lowerRight, lowerLeft);
        Line leftEdge = new Line(lowerLeft, upperLeft);

        if (line.isIntersectingInOnePoint(topEdge)) {
            intersectionPoints.add(line.intersectionWith(topEdge));
        }
        if (line.isIntersectingInOnePoint(rightEdge)) {
            intersectionPoints.add(line.intersectionWith(rightEdge));
        }
        if (line.isIntersectingInOnePoint(bottomEdge)) {
            intersectionPoints.add(line.intersectionWith(bottomEdge));
        }
        if (line.isIntersectingInOnePoint(leftEdge)) {
            intersectionPoints.add(line.intersectionWith(leftEdge));
        }

        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
