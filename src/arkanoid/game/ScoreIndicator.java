package arkanoid.game;
import arkanoid.listeners.Counter;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The ScoreIndicator class will be used to represent the score.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final Rectangle rectangle;

    /**
     * Constructor.
     * @param score score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.rectangle = new Rectangle(0, 0, 800, 20);
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(),
                        (int) this.rectangle.getHeight());

        d.setColor(Color.BLACK);
        d.drawText((int) this.rectangle.getWidth() / 3 + 70, (int) this.rectangle.getHeight() - 3,
                   "Score: " + this.score.getValue(), 17);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
