//Liav Burger 208277871
package arkanoid.game;

import geometry.Point;

/**
 *This class will be used to keep collision information.
 * Stores collision point and the object that had been collided with.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor.
     * @param collisionPoint collision Point.
     * @param collisionObject collision Object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Getter.
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Getter.
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
