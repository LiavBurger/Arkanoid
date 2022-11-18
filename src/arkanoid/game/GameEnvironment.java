//Liav Burger 208277871
package arkanoid.game;
import geometry.Point;
import geometry.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class will be a collection of the objects which ball can collide with.
 * The ball will know the game environment, and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    private final List<Collidable> list = new ArrayList<>();

    // add the given collidable to the environment.

    /**
     * This function adds a collidable object to the list of all collidable objects in the game environment.
     * @param c The collidable object.
     */
    public void addCollidable(Collidable c) {
        list.add(c);
    }

    /**
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory trajectory of the moving object.
     * @return The information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidables = new ArrayList<Collidable>(this.list);
        if (this.getCollidables().isEmpty()) {
            return null;
        }
        int index = 0;
        Point closest = null;
        for (int i = 0; i < collidables.size(); i++) {
            Point temp = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            if (temp != null) {
                closest = temp;
                index = i;
                break;
            }
        }
        if (closest != null) {
            for (int i = 0; i < collidables.size(); i++) {
                Point temp = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
                if (temp != null) {
                    double distanceClosest = trajectory.start().distance(closest);
                    double distanceTemp = trajectory.start().distance(temp);
                    if (distanceTemp < distanceClosest) {
                        closest = temp;
                        index = i;
                    }
                }
            }
            return new CollisionInfo(closest, collidables.get(index));
        }
        return null;
    }

    /**
     * @return Return the collidables list.
     */
    public List<Collidable> getCollidables() {
        return this.list;
    }
}