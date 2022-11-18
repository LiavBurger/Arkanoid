//Liav Burger 208277871
package geometry;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

/**
 * Classname: Line
 * Description:
 * A line (actually a line-segment) connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    private Point start = null;
    private Point end = null;

    // constructors

    /**
     * @param start Start point.
     * @param end   End point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    /**
     * @param x1 Start point x.
     * @param y1 Start point y.
     * @param x2 End point x.
     * @param y2 End point y.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }


    /**
     * @return Return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return Returns the middle point of the line.
     */
    public Point middle() {
        double x, y;
        x = (this.start.getX() + this.end.getX()) / 2;
        y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return Returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return Returns the end point of the line
     */
    public Point end() {
        return this.end;
    }

    static boolean onTheLine(Point p1, Point p2, Point p3) {
        if (p2.getX() <= Math.max(p1.getX(), p3.getX()) && p2.getX() >= Math.min(p1.getX(), p3.getX())
                && p2.getY() <= Math.max(p1.getY(), p3.getY()) && p2.getY() >= Math.min(p1.getY(), p3.getY())) {
            return true;
        }
        return false;
    }

    /**
     * @param other other point
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        int d1 = direction(this.start, this.end, other.start);
        int d2 = direction(this.start, this.end, other.end);
        int d3 = direction(other.start, other.end, this.start);
        int d4 = direction(other.start, other.end, this.end);

        //If lines are exactly the same
        if (this.equals(other)) {
            return true;
        }

        if ((d1 != d2) && (d3 != d4)) {
            return true;
        }

        if (d1 == 0 && onTheLine(this.start, other.start, this.end)) {
            return true;
        }
        if (d2 == 0 && onTheLine(this.start, other.end, this.end)) {
            return true;
        }
        if (d3 == 0 && onTheLine(other.start, this.start, other.end)) {
            return true;
        }
        if (d4 == 0 && onTheLine(other.start, this.end, other.end)) {
            return true;
        }

        return false;
    }

    /**
     * @param other line
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }

        if (this.equals(other)) {
            return null;
        }

        double miny1 = Math.min(this.start.getY(), this.end.getY());
        double maxy1 = Math.max(this.start.getY(), this.end.getY());
        double miny2 = Math.min(other.start.getY(), other.end.getY());
        double maxy2 = Math.max(other.start.getY(), other.end.getY());

        double slope1 = getSlope(this.start, this.end); // m1
        double slope2 = getSlope(other.start, other.end); // m2

        double b1 = this.start.getY() - (slope1 * this.start.getX());  // y=mx+b ---> b1 = y1 - m1 * x1
        double b2 = other.start.getY() - (slope2 * other.start.getX()); // y=mx+b ---> b2 = y2 - m2 * x2

        //if one is parallel to x-axis and one is parallel to y-axis
        if (this.start.getX() == this.end.getX()) {
            if (other.start.getY() == other.end.getY()) {
                return new Point(this.start.getX(), other.start.getY());
            }
        }
        if (other.start.getX() == other.end.getX()) {
            if (this.start.getY() == this.end.getY()) {
                return new Point(other.start.getX(), this.start.getY());
            }
        }

        //if one line is parallel to y-axis
        if (this.start.getX() == this.end.getX() && slope2 != POSITIVE_INFINITY
                && slope2 != NEGATIVE_INFINITY && slope2 != 0) {
            return new Point(this.start.getX(), slope2 * this.start.getX() + b2);
        }
        if (other.start.getX() == other.end.getX() && slope1 != POSITIVE_INFINITY
                && slope1 != NEGATIVE_INFINITY && slope1 != 0) {
            return new Point(other.start.getX(), slope1 * other.start.getX() + b1);
        }

        if (this.start.getX() == this.end.getX()) {
            double yValue = this.start.getX() * slope1 + b1;
            if (yValue <= maxy2 && yValue >= miny2) {
                return new Point(this.start.getX(), yValue);
            }
            return null;
        }

        if (other.start.getX() == other.end.getX()) {
            double yValue = other.start.getX() * slope2 + b2;
            if (yValue <= maxy1 && yValue >= miny1) {
                return new Point(other.start.getX(), yValue);
            }
            return null;
        }


        double x = ((-b1 + b2) / (slope1 - slope2));

        //return the intersection point
        //if one line is completely in another line return null
        if (slope1 == slope2 && b1 == b2) {
            if (this.start.getX() < other.start.getX() && this.end.getX() > other.end.getX()) {
                return null;
            }
            if (this.end.getX() < other.start.getX() && this.start.getX() > other.end.getX()) {
                return null;
            }
            if (this.start.getX() < other.end.getX() && this.end.getX() > other.start.getX()) {
                return null;
            }
            if (this.end.getX() < other.end.getX() && this.start.getX() > other.start.getX()) {
                return null;
            }
        }


        //if the lines have multiple intersection points, return null
        if (slope1 == slope2) {
            if (this.start.distance(other.start) > 0 && this.start.distance(other.start) < this.length()) {
                return null;
            }
            if (this.start.distance(other.end) > 0 && this.start.distance(other.end) < this.length()) {
                return null;
            }
            if (this.end.distance(other.start) > 0 && this.end.distance(other.start) < this.length()) {
                return null;
            }
            if (this.end.distance(other.end) > 0 && this.end.distance(other.end) < this.length()) {
                return null;
            }
        }

        //parallel to Y-axis
        double min1 = Math.min(this.start.getX(), this.end.getX());
        double max1 = Math.max(this.start.getX(), this.end.getX());
        double min2 = Math.min(other.start.getX(), other.end.getX());
        double max2 = Math.max(other.start.getX(), other.end.getX());

        //if both are parallel to x-axis
        if (slope1 == 0 && slope2 == 0) {
            if (this.start.getY() != other.start.getY()) {
                return null;
            }
            if (min1 == max2) {
                return new Point(min1, this.start.getY());
            }
            if (min2 == max1) {
                return new Point(min2, this.start.getY());
            }
            return null;
        }

        if (slope1 == 0) {
            if (other.start.getX() == other.end.getX()) {
                if (other.start.getX() <= max1 && other.start.getX() >= min1) {
                    return new Point(other.start.getX(), this.end.getY());
                }
                return null;
            }
            double xValue = (this.start.getY() - b2) / slope2;
            if (xValue <= max1 && xValue >= min1) {
                return new Point(xValue, this.start.getY());
            }
            return null;
        }

        if (slope2 == 0) {
            if (this.start.getX() == this.end.getX()) {
                if (this.start.getX() <= max2 && this.start.getX() >= min2) {
                    return new Point(this.start.getX(), other.end.getY());
                }
                return null;
            }
            double xValue = (other.start.getY() - b1) / slope1;
            if (xValue <= max2 && xValue >= min2) {
                return new Point(xValue, other.start.getY());
            }
        }
        return new Point(x, (slope1 * x) + b1);
    }

    /**
     * This function checks if lines are equal.
     *
     * @param other other line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * @param start point
     * @param end   point
     * @return orientation between the 2 points.
     */
    private double getSlope(Point start, Point end) {
        if (start.getY() == end.getY()) {
            return 0;
        }
        return ((start.getY() - end.getY()) / (start.getX() - end.getX()));
    }

    // To find direction of 3 points (p, q, r).
    // The function returns following values:
    // 0 --> The lines are collinear.
    // 1 --> The lines direction is clockwise.
    // 2 --> The lines direction is Counterclockwise
    private static int direction(Point p1, Point p2, Point p3) {

        double value = ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()))
                - ((p2.getX() - p1.getX()) * (p3.getY() - p2.getY()));
        // clock or counterclockwise
        if (value == 0) {
            return 0;
        }
        if (value > 0) {
            return 1;
        }
        return 2;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect rectangle
     * @return null or closest intersection to start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = new ArrayList<Point>();
        list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        Point closest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance(this.start) < closest.distance(this.start)) {
                closest = list.get(i);
            }
        }
        return closest;
    }

    /**
     * @param point point to check.
     * @return true/false
     */
    public boolean isPointOnLine(Point point) {
        //if (distance(A, C) + distance(B, C) == distance(A, B)) point is on the line
        return this.start.distance(point) + this.end.distance(point) == this.length();
    }

}