package geometry;

import java.util.List;

/**
 * The Line class represents a line segment in a 2D space.
 * It provides methods to calculate length, middle point, and intersection with other lines and rectangles.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructs a Line with the specified start and end points.
     *
     * @param start The start point of the line.
     * @param end   The end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line with the specified coordinates for the start and end points.
     *
     * @param x1 The x-coordinate of the start point.
     * @param y1 The y-coordinate of the start point.
     * @param x2 The x-coordinate of the end point.
     * @param y2 The y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return The start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return The end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other The other line to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double epsilon = 1e-10;
        if (this.equals(other)) {
            return true;
        }

        if (this.start.equals(this.end)) {
            return other.containsPoint(this.start, epsilon);
        }
        if (other.start.equals(other.end)) {
            return this.containsPoint(other.start, epsilon);
        }

        if (!this.isCollinear(other)) {
            return intersectionWith(other) != null;
        }

        return this.isOverlapping(other);
    }

    /**
     * Checks if this line intersects with another line at one point.
     *
     * @param other The other line to check intersection with.
     * @return true if the lines intersect at one point, false otherwise.
     */
    public boolean isIntersectingInOnePoint(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Finds the intersection point with another line, if any.
     *
     * @param other The other line to find the intersection with.
     * @return The intersection point, or null if there is no intersection.
     */
    public Point intersectionWith(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denominator == 0) {
            if (this.equals(other)) {
                return null;
            }
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end;
            }
            return null;
        }

        double numeratorThisLine = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3));
        double numeratorOtherLine = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3));
        double fractionAlongThisLine = numeratorThisLine / denominator;
        double fractionAlongOtherLine = numeratorOtherLine / denominator;

        if (fractionAlongThisLine >= 0 && fractionAlongThisLine <= 1 && fractionAlongOtherLine >= 0
                && fractionAlongOtherLine <= 1) {
            double x = x1 + fractionAlongThisLine * (x2 - x1);
            double y = y1 + fractionAlongThisLine * (y2 - y1);
            return new Point(x, y);
        }

        return null;
    }

    /**
     * Checks if this line is collinear with another line.
     *
     * @param other The other line to check collinearity with.
     * @return true if the lines are collinear, false otherwise.
     */
    public boolean isCollinear(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double vx1 = x2 - x1;
        double vy1 = y2 - y1;
        double vx2 = x3 - x1;
        double vy2 = y3 - y1;
        double vx3 = x4 - x1;
        double vy3 = y4 - y1;

        double area1 = vx1 * vy2 - vx2 * vy1;
        double area2 = vx1 * vy3 - vx3 * vy1;

        double epsilon = 1e-10;
        return Math.abs(area1) < epsilon && Math.abs(area2) < epsilon;
    }

    /**
     * Checks if this line is overlapping with another line.
     *
     * @param other The other line to check overlap with.
     * @return true if the lines are overlapping, false otherwise.
     */
    public boolean isOverlapping(Line other) {
        if (!this.isCollinear(other)) {
            return false;
        }

        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        if (x1 == x2 && x3 == x4 && x1 == x3) {
            double maxY1 = Math.max(y1, y2);
            double minY1 = Math.min(y1, y2);
            double maxY3 = Math.max(y3, y4);
            double minY3 = Math.min(y3, y4);
            return maxY1 >= minY3 && maxY3 >= minY1;
        } else {
            double maxX1 = Math.max(x1, x2);
            double minX1 = Math.min(x1, x2);
            double maxX3 = Math.max(x3, x4);
            double minX3 = Math.min(x3, x4);
            return maxX1 >= minX3 && maxX3 >= minX1;
        }
    }

    /**
     * @param p       The point to check.
     * @param epsilon The tolerance for comparison.
     * @return true if the point lies on the line segment, false otherwise.
     */
    public boolean containsPoint(Point p, double epsilon) {
        if (start.equals(end)) {
            return start.equals(p);
        }

        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());

        if (p.getX() >= minX && p.getX() <= maxX && p.getY() >= minY && p.getY() <= maxY) {
            double distance = Math.abs((end.getY() - start.getY()) * p.getX() - (end.getX() - start.getX()) * p.getY()
                    + end.getX() * start.getY() - end.getY() * start.getX())
                    / Math.sqrt(Math.pow(end.getY() - start.getY(), 2) + Math.pow(end.getX() - start.getX(), 2));
            return distance < epsilon;
        }

        return false;
    }

    /**
     * @param other The other line to compare with.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * @param rect The rectangle to check intersection with.
     * @return The closest intersection point to the start of the line, or null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closesIntersection = intersectionPoints.get(0);
        double closestDistance = start.distance(closesIntersection);

        for (Point intersection : intersectionPoints) {
            double distance = start.distance(intersection);
            if (distance < closestDistance) {
                closesIntersection = intersection;
                closestDistance = distance;
            }
        }
        return closesIntersection;
    }
}
