package arkanoid.levels;

import arkanoid.game.Block;
import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import arkanoid.game.Velocity;
import biuoop.DrawSurface;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Level 3 class.
 */
public class Level3 implements LevelInformation {
    private final List<Block> blocks;

    /**
     * constructor.
     */
    public Level3() {
        this.blocks = new ArrayList<>();

        int blockWidth = 50; // (800 - 40)/15 == (frame width - walls) / (number of blocks)
        int blockHeight = 25;
        Point upperLeft;
        Block block;
        for (int i = 1; i <= 12; i++) {
            upperLeft = new Point(130 + (i * blockWidth), blockHeight * 5);
            block = new Block(upperLeft, blockWidth, blockHeight);
            block.setColor(Color.decode("#504ABE"));
            this.blocks().add(block);
            if (i > 1) {
                upperLeft = new Point(130 + (i * blockWidth), blockHeight * 6);
                block = new Block(upperLeft, blockWidth, blockHeight);
                block.setColor(Color.decode("#5f59f7"));
                this.blocks().add(block);
            }
            if (i > 2) {
                upperLeft = new Point(130 + (i * blockWidth), blockHeight * 7);
                block = new Block(upperLeft, blockWidth, blockHeight);
                block.setColor(Color.decode("#6592fd"));
                this.blocks().add(block);
            }
            if (i > 3) {
                upperLeft = new Point(130 + (i * blockWidth), blockHeight * 8);
                block = new Block(upperLeft, blockWidth, blockHeight);
                block.setColor(Color.decode("#44C2FD"));
                this.blocks().add(block);
            }
            if (i > 4) {
                upperLeft = new Point(130 + (i * blockWidth), blockHeight * 9);
                block = new Block(upperLeft, blockWidth, blockHeight);
                block.setColor(Color.decode("#8C61FF"));
                this.blocks().add(block);
            }
            if (i > 5) {
                upperLeft = new Point(130 + (i * blockWidth), blockHeight * 10);
                block = new Block(upperLeft, blockWidth, blockHeight);
                block.setColor(Color.decode("#a17dff"));
                this.blocks().add(block);
            }
        }
    }
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = -45; i <= 90; i += 90) {

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
        return "At The Beach";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private ArrayList<Integer> fishX = new ArrayList<Integer>(Arrays.asList(100, 200, 150, 170, 50, 70));

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#C0AD79"));
                d.fillRectangle(0, 500, 800, 500);
                d.setColor(Color.decode("#78caff"));
                d.fillRectangle(0, 0, 800, 500);
                d.setColor(Color.WHITE);
                drawFish(d, new Point(fishX.get(0), 400));
                drawFish(d, new Point(fishX.get(1), 400));
                drawFish(d, new Point(fishX.get(2), 360));
                drawFish(d, new Point(fishX.get(3), 440));
                drawFish(d, new Point(fishX.get(4), 360));
                drawFish(d, new Point(fishX.get(5), 440));
            }

            private void drawFish(DrawSurface d, Point center) {
                int size = 20;
                Point end1 = new Point(center.getX() - size, center.getY() - size);
                Point end2 = new Point(center.getX() - size, center.getY() + size);
                //draw tail
                d.drawLine((int) center.getX(), (int) center.getY(), (int) end1.getX(), (int) end1.getY());
                d.drawLine((int) center.getX(), (int) center.getY(), (int) end2.getX(), (int) end2.getY());
                d.drawLine((int) end1.getX(), (int) end1.getY(), (int) end2.getX(), (int) end2.getY());

                //draw head
                d.drawOval((int) center.getX(), (int) center.getY() - 16, 60, 35);
                d.fillCircle((int) center.getX() + size + 20, (int) center.getY() - 5, 3);
            }

            @Override
            public void timePassed() {
                for (int i = 0; i < fishX.size(); i++) {
                    if (fishX.get(i) < 790) {
                        fishX.set(i, fishX.get(i) + 2);
                    } else {
                        fishX.set(i, 10);
                    }
                }
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
        return 57;
    }
}
