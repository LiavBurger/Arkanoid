//Liav Burger 208277871
package arkanoid.game;
import biuoop.DrawSurface;

/**
 * All the game objects are sprites (Ball, block, paddle...).
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * This method will be in charge of adding the sprites to the game, calling the appropriate game methods.
     * @param gameLevel game
     */
    void addToGame(GameLevel gameLevel);
}