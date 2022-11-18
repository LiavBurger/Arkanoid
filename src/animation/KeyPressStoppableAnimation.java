package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class is a decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor keyboard sensor
     * @param key end key
     * @param animation animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }

        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
