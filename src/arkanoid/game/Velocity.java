//Liav Burger 208277871
package arkanoid.game;

import geometry.Point;

/**
 * Classname: Velocity
 * Description: Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    // constructors

    /**
     * Constructor from x,y values.
     * @param x speed on x-axis.
     * @param y speed on y-axis.
     */
    public Velocity(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * Constructor from angle and speed.
     * @param angle angle of movement.
     * @param speed speed of the object.
     * @return Return the velocity of each axis using angle and speed vector.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * get dx value.
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get dy value.
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p point
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(this.getDx() + p.getX(), this.getDy() + p.getY());
    }
}