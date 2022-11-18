package animation;

import arkanoid.game.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private final double numOfMilliSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final Sleeper sleeper;

    /**
     * Constructor.
     * @param numOfSeconds number of seconds
     * @param countFrom Countdown beginning number
     * @param gameScreen game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfMilliSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.decode("#6673e5"));
        d.drawText(383, 340, Integer.toString(this.countFrom) + "...", 65);
        if (this.countFrom != 3) {
            this.sleeper.sleepFor((long) (this.numOfMilliSeconds / 3));
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}