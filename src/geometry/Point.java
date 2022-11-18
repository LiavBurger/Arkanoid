//Liav Burger 208277871
package geometry;
/**
 * Classname: Point
 * Description:
 * A point has an x and a y value.
 * It can measure the distance to other points and if it is equal to another point.
 */
public class Point {
    private double x = 0;
    private double y = 0;
    public static final double ALLOWED_ERROR = Math.pow(10, -10);

    // constructor
    /**
     * Constructor for a point.
     *
     * @param x x value.
     * @param y y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function return the distance of this point to the other point.
     *
     * @param other Point variable.
     * @return The distance between the points.
     */
    public double distance(Point other) {
        double distanceValue;
        distanceValue = Math.pow((other.x - this.x), 2) + Math.pow((other.y - this.y), 2);
        distanceValue = Math.sqrt(distanceValue);
        return distanceValue;
    }

    /**
     * This function returns true is the points are equal, false otherwise.
     *
     * @param other Point variable.
     * @return true/false.
     */
    public boolean equals(Point other) {
        return Math.abs((this.getX() - other.getX())) < ALLOWED_ERROR
                && Math.abs(other.getY() - this.getY()) < ALLOWED_ERROR;
    }

    /**
     * This function returns x value of this point.
     *
     * @return x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This function returns y value of this point.
     *
     * @return y value.
     */
    public double getY() {
        return this.y;
    }
}