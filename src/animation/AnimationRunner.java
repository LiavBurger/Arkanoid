package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond = 60;
    private final Sleeper sleeper;

    /**
     * Constructor.
     * @param gui gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * This function runs the animation.
     * @param animation animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}