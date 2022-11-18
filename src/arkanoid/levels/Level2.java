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
import java.util.Random;

/**
 * Level 2 class.
 */
public class Level2 implements LevelInformation {
    private final List<Block> blocks;

    /**
     * constructor.
     */
    public Level2() {
            this.blocks = new ArrayList<Block>();

        int blockWidth = 50; // (800 - 40)/15 == (frame width - walls) / (number of blocks)
        int blockHeight = 25;
        Point upperLeft = new Point(50, 200);

        Block block;
        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.red);
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }

        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.orange);
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }

        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.yellow);
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }
        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.green);
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }
        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.blue);
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }
        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.decode("#a60082"));
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }
        for (int i = 0; i < 2; i++) {
            createBlock(upperLeft, blockWidth, blockHeight, Color.decode("#ee82ee"));
            upperLeft = new Point(upperLeft.getX() + blockWidth, upperLeft.getY());
        }
    }

    private void createBlock(Point upperLeft, int blockWidth, int blockHeight, Color color) {
        Block block = new Block(upperLeft, blockWidth, blockHeight);
        block.setColor(color);
        this.blocks().add(block);
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = -81; i <= 81; i += 18) {
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
        return 600;
    }

    @Override
    public String levelName() {
        return "Snowstorm";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private final Point moonCenter = new Point(130, 105);
            private DrawSurface d;

            @Override
            public void drawOn(DrawSurface d) {
                this.d = d;
                d.setColor(Color.decode("#2e4482"));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.white);
                int moonSize = 50;
                d.fillCircle((int) this.moonCenter.getX(), (int) this.moonCenter.getY(), moonSize);
            }

            @Override
            public void timePassed() {
                for (int i = 0; i <= 10; i++) {
                    Random rand = new Random(); // create a random-number generator
                    int x = rand.nextInt(760) + 40; // get integer in range 1-400
                    int y = rand.nextInt(560) + 40; // get integer in range 1-300
                    drawSnowFlake(d, new Point(x, y));
                }
            }

            @Override
            public void addToGame(GameLevel gameLevel) {
                gameLevel.addSprite(this);
            }

            public void drawSnowFlake(DrawSurface d, Point center) {
                d.setColor(Color.WHITE);
                d.drawCircle((int) center.getX(), (int) center.getY(), 1);
            }
        };
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}
