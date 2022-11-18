package arkanoid.game;
import arkanoid.listeners.Counter;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The LivesIndicator class will be used to represent the lives left.
 */
public class LivesIndicator implements Sprite {
    private final Counter lives;
    private final Rectangle rectangle;

    /**
     * Constructor.
     * @param score score
     */
    public LivesIndicator(Counter score) {
        this.lives = score;
        this.rectangle = new Rectangle(0, 0, 800, 20);
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) this.rectangle.getWidth() / 6 - 30, (int) this.rectangle.getHeight() - 3,
                "Lives: " + this.lives.getValue(), 17);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
