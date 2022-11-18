package animation;

import biuoop.DrawSurface;

/**
 * A very simple animation, that will display a screen with the message:
 * "paused -- press space to continue", until a key is pressed.
 */
public class EndScreen implements Animation {
    private final boolean stop;
    private final int score;
    private final int numberOfLives;

    /**
     * Constructor.
     * @param score score
     * @param numberOfLives number of lives
     */
    public EndScreen(int score, int numberOfLives) {
        this.stop = false;
        this.score = score;
        this.numberOfLives = numberOfLives;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.numberOfLives > 0) {
            d.drawText(50, 150, "You Win!", 32);
        } else {
            d.drawText(50, 150, "Game Over!", 32);
        }
        d.drawText(50, 250, "Your score is: " + this.score, 32);
        d.drawText(50, 550, "Press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}