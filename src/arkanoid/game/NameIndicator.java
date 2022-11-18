package arkanoid.game;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The NameIndicator class will be used to represent the name of the level.
 */
public class NameIndicator implements Sprite {
    private final String levelName;
    private final Rectangle rectangle;

    /**
     * Constructor.
     * @param levelName name of the level
     */
    public NameIndicator(String levelName) {
        this.levelName = levelName;
        this.rectangle = new Rectangle(0, 0, 800, 20);
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) this.rectangle.getWidth() - 230, (int) this.rectangle.getHeight() - 3,
                "Level Name: " + levelName, 17);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
