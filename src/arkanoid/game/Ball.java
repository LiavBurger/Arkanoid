//Liav Burger 208277871
package arkanoid.game;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import java.awt.Color;

/**
 *Classname: Ball
 * Description: Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment game;

    // constructors
    /**
     * @param center center point of the ball
     * @param r radius of the ball
     * @param color color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        setVelocity(0, 0);
    }

    /**
     * @param x value of center point of the ball
     * @param y value of center point of the ball
     * @param r radius of the ball
     * @param color color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        setVelocity(0, 0);
    }

    // accessors
    /**
     * @return Center point x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return Center point y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return Velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    //setters
    /**
     * Set center point of the ball.
     * @param center center point
     */
    public void setCenter(Point center) {
        this.center = center;
    }
    /**
     * Set the velocity boundary of the ball.
     * @param v boundary.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set the top boundary of the ball.
     * @param x speed on x-axis.
     * @param y speed on y-axis.
     */
    public void setVelocity(double x, double y)  {
        this.velocity = new Velocity(x, y);
    }


    /**
     * Set game environment.
     * @param g game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.game = g;
    }


    /**
     * draw the ball on the given DrawSurface.
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * moves the ball one step in the direction of the velocity.
     * If it hits a wall, reverses the direction of the same axis.
     */
    public void moveOneStep() {
        CollisionInfo info = this.game.getClosestCollision(ballTrajectory());
        //if no collision, keep going
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    private Line ballTrajectory() {
        if (velocity.getDx() >= 0 && velocity.getDy() >= 0) {
            Point end = new Point(this.center.getX() + this.velocity.getDx() + this.radius,
                    this.center.getY() + this.velocity.getDy() + this.radius);
            return new Line(this.center, end);
        }
        if (velocity.getDx() >= 0 && velocity.getDy() <= 0) {
            Point end = new Point(this.center.getX() + this.velocity.getDx() + this.radius,
                    this.center.getY() + this.velocity.getDy() - this.radius);
            return new Line(this.center, end);
        }
        if (velocity.getDx() <= 0 && velocity.getDy() >= 0) {
            Point end = new Point(this.center.getX() + this.velocity.getDx() - this.radius,
                    this.center.getY() + this.velocity.getDy() + this.radius);
            return new Line(this.center, end);
        }
        if (velocity.getDx() <= 0 && velocity.getDy() <= 0) {
            Point end = new Point(this.center.getX() + this.velocity.getDx() - this.radius,
                    this.center.getY() + this.velocity.getDy() - this.radius);
            return new Line(this.center, end);
        }

        return null;
    }

    /**
     * Adds the ball to a game.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removes the ball from a game.
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}