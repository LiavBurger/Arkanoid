//Liav Burger 208277871
package arkanoid.game;
import arkanoid.listeners.HitListener;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A block is every object that can be hit.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle collisionRectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    //Constructors
    /**
     * Constructor.
     * @param upperLeft upper left point.
     * @param width width of the block.
     * @param height height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        this.collisionRectangle = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param rectangle rectangle block.
     */
    public Block(Rectangle rectangle) {
        this.collisionRectangle = rectangle;
    }

    /**
     * Set color.
     * @param color color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Return the color of the block.
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // Return the "collision shape" of the object.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //If the collision point is not on the object, velocity stays the same.
        if (!isPointOnTheRectangle(collisionPoint)) {
            return currentVelocity;
        }
        this.notifyHit(hitter);
        //If the collision point is a corner
        if (intersectWithCorner(collisionPoint, this.collisionRectangle)) {

            //if the ball is moving right and up
            if (currentVelocity.getDx() > 0 && currentVelocity.getDy() < 0) {
                //if the ball hits top left corner
                if (collisionPoint.equals(this.collisionRectangle.getUpperLeft())) {
                    return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
                }
                //if the ball hits bottom right corner
                if (collisionPoint.equals(this.collisionRectangle.getBottomRight())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
                //if the ball hits bottom left corner
                if (collisionPoint.equals(this.collisionRectangle.getBottomLeft())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
            }

            //if the ball is moving right and down
            if (currentVelocity.getDx() > 0 && currentVelocity.getDy() > 0) {
                //if the ball hits bottom left corner
                if (collisionPoint.equals(this.collisionRectangle.getBottomLeft())) {
                    return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
                }
                //if the ball hits top right corner
                if (collisionPoint.equals(this.collisionRectangle.getUpperRight())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
                //if the ball hits top left corner
                if (collisionPoint.equals(this.collisionRectangle.getUpperLeft())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
            }

            //if the ball is moving left and down
            if (currentVelocity.getDx() < 0 && currentVelocity.getDy() > 0) {
                //if the ball hits top left corner
                if (collisionPoint.equals(this.collisionRectangle.getUpperLeft())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
                //if the ball hits bottom right corner
                if (collisionPoint.equals(this.collisionRectangle.getBottomRight())) {
                    return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
                }
                //if the ball hits top right corner
                if (collisionPoint.equals(this.collisionRectangle.getUpperRight())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
            }

            //if the ball is moving left and up
            if (currentVelocity.getDx() < 0 && currentVelocity.getDy() < 0) {
                //if the ball hits bottom left corner
                if (collisionPoint.equals(this.collisionRectangle.getBottomLeft())) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                }
                //if the ball hits top right corner
                if (collisionPoint.equals(this.collisionRectangle.getUpperRight())) {
                    return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
                }
                //if the ball hits bottom right corner
                if (collisionPoint.equals(this.collisionRectangle.getBottomRight())) {
                    return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
                }
            }
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        Line left = new Line(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getBottomLeft());
        Line right = new Line(this.collisionRectangle.getUpperRight(), this.collisionRectangle.getBottomRight());
        Line top = new Line(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getUpperRight());
        Line bottom = new Line(this.collisionRectangle.getBottomLeft(), this.collisionRectangle.getBottomRight());
        Line wall = intersectedWall(collisionPoint, this.collisionRectangle);
        assert wall != null;
        if (wall.equals(left)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (wall.equals(right)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (wall.equals(top)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (wall.equals(bottom)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        return null;
    }

    /**
     * Checks if a point is on the frame of a rectangle (block).
     * @param point     Point to check.
     * @return True / false depending on if the point is on or not.
     */
    public boolean isPointOnTheRectangle(Point point) {
        Line left = new Line(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getBottomLeft());
        Line right = new Line(this.collisionRectangle.getUpperRight(), this.collisionRectangle.getBottomRight());
        Line top = new Line(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getUpperRight());
        Line bottom = new Line(this.collisionRectangle.getBottomLeft(), this.collisionRectangle.getBottomRight());

        //if the point is not on the rectangle at all
        if (point.getY() > bottom.start().getY() || point.getY() < top.start().getY()
                || point.getX() < left.start().getX() || point.getX() > right.start().getX()) {
            return false;
        }
        return true;
    }

    /**
     * This function checks if the intersection point is on one of the corners.
     * @param point Intersection point
     * @param rectangle Block object
     * @return True/false depending on if one of the corners is the intersection point.
     */
    private boolean intersectWithCorner(Point point, Rectangle rectangle) {
        return point.equals(rectangle.getUpperLeft()) || point.equals(rectangle.getUpperRight())
                || point.equals(rectangle.getBottomLeft()) || point.equals(rectangle.getBottomRight());
    }

    /**
     * Returns the wall of the block that has been intersected with.
     * @param collisionPoint Collision point
     * @param rectangle Block object.
     * @return Returns the intersected wall of the block.
     */
    private Line intersectedWall(Point collisionPoint, Rectangle rectangle) {
        assert (isPointOnTheRectangle(collisionPoint));
        assert (!intersectWithCorner(collisionPoint, rectangle));
        if (collisionPoint.getX() == rectangle.getUpperLeft().getX()) {
            return new Line(rectangle.getUpperLeft(), rectangle.getBottomLeft());
        }
        if (collisionPoint.getX() == rectangle.getUpperRight().getX()) {
            return new Line(rectangle.getUpperRight(), rectangle.getBottomRight());
        }
        if (collisionPoint.getY() == rectangle.getUpperLeft().getY()) {
            return new Line(rectangle.getUpperLeft(), rectangle.getUpperRight());
        }
        if (collisionPoint.getY() == rectangle.getBottomLeft().getY()) {
            return new Line(rectangle.getBottomLeft(), rectangle.getBottomRight());
        }
        return null;
    }

    /**
     * draw the rectangle on the given DrawSurface.
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                              (int) this.collisionRectangle.getUpperLeft().getY(),
                              (int) this.collisionRectangle.getWidth(),
                              (int) this.collisionRectangle.getHeight());

        surface.setColor(Color.black);
        surface.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                              (int) this.collisionRectangle.getUpperLeft().getY(),
                              (int) this.collisionRectangle.getWidth(),
                              (int) this.collisionRectangle.getHeight());
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * Adds the block object to the game.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * removes the block object from the game.
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Adds a hit listener.
     * @param hl hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener.
     * @param hl hit listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all the registered HitListener objects by calling their hitEvent method.
     * @param hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}