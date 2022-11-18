package animation;

import biuoop.DrawSurface;

/**
 * Animation class.
 */
public interface Animation {
    /**
     * This method draws the object on the screen.
     * @param d draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method returns true / false depending on if the animation should continue / stop.
     * @return true / false
     */
    boolean shouldStop();
}