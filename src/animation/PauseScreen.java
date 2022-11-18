package animation;

import biuoop.DrawSurface;

/**
 * A very simple animation, that will display a screen with the message:
 * "paused -- press space to continue", until a key is pressed.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}