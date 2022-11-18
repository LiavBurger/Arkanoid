package arkanoid.levels;

import arkanoid.game.Block;
import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import arkanoid.game.Velocity;
import biuoop.DrawSurface;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 4 class.
 */
public class Level4 implements LevelInformation {
    private final List<Block> blocks;

    /**
     * constructor.
     */
    public Level4() {
        this.blocks = new ArrayList<>();

        double blockWidth = 40;
        int blockHeight = 25;
        int x = 20;
        Point upperLeft;
        Block block;
        for (int i = 0; i <= 18; i++) {
            upperLeft = new Point(x + (i * blockWidth), blockHeight * 5);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#967216"));
            this.blocks().add(block);
            upperLeft = new Point(x + (i * blockWidth), blockHeight * 6);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#A18232"));
            this.blocks().add(block);
            upperLeft = new Point(x + (i * blockWidth), blockHeight * 7);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#B19957"));
            this.blocks().add(block);
            upperLeft = new Point(x + (i * blockWidth), blockHeight * 8);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#C0AD79"));
            this.blocks().add(block);
            upperLeft = new Point(x + (i * blockWidth), blockHeight * 9);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#CDBE90"));
            this.blocks().add(block);
            upperLeft = new Point(x + (i * blockWidth), blockHeight * 10);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#E4D6AC"));
            this.blocks().add(block);
        }
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = -45; i <= 45; i += 45) {

            velocities.add(Velocity.fromAngleAndSpeed(i, 6));
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Middle East";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#cbbfae"));
                d.fillRectangle(0, 0, 800, 600);
                drawPyramid(d, new Point(400, 460));
                drawPyramid(d, new Point(180, 350));
                drawPyramid(d, new Point(630, 400));
            }

            private void drawPyramid(DrawSurface d, Point center) {
                d.setColor(Color.decode("#ad9b80"));
                int nextY = 0;
                for (int i = 1; i <= 100; i = i + 2) {
                    d.drawLine((int) center.getX() - i, (int) center.getY() + nextY,
                            (int) center.getX() + i, (int) center.getY() + nextY);
                    d.drawLine((int) center.getX() + i, (int) center.getY() + nextY,
                            (int) center.getX() + (int) (i * 1.2), (int) center.getY() + (int) (nextY / 1.5));
                    nextY = nextY + 2;

                }

            }

            @Override
            public void timePassed() {
            }

            @Override
            public void addToGame(GameLevel gameLevel) {
                gameLevel.addSprite(this);
            }
        };
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 114;
    }

}
