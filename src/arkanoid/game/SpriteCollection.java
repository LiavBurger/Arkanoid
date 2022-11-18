//Liav Burger 208277871
package arkanoid.game;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection will hold a collection of sprites.
 */
public class SpriteCollection {
    private final List<Sprite> list = new ArrayList<>();

    /**
     * This function adds a sprite object to the list of all sprite objects.
     * @param s The collidable object.
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }
    /**
     * this method removes a sprite object from collection.
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.list);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     * @param d Draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < list.size(); i++) {
            this.list.get(i).drawOn(d);
        }
    }
}