//Liav Burger 208277871
package arkanoid.game;

import geometry.Point;
import geometry.Rectangle;

/**
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return The rectangle that has been collided with.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint Collision point.
     * @param currentVelocity Current velocity of the object that hit.
     * @param hitter ball that hit.
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
