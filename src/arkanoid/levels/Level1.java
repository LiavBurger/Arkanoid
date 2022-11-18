package arkanoid.levels;

import arkanoid.game.Block;
import arkanoid.game.GameLevel;
import arkanoid.game.Sprite;
import arkanoid.game.Velocity;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 1 information.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -5));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);
                drawTarget(d);
            }

            private void drawTarget(DrawSurface d) {
                Rectangle target = blocks().get(0).getCollisionRectangle();
                Point targetPoint = new Point(
                        target.getUpperLeft().getX() + target.getWidth() / 2,
                        target.getUpperLeft().getY() + target.getHeight() / 2);
                d.setColor(Color.BLUE);
                drawLines(d, targetPoint);
                drawCircles(d, targetPoint);
            }


            private void drawLines(DrawSurface d, Point target) {
                d.drawLine((int) target.getX() - 30, (int) target.getY(),
                        (int) target.getX() - 150, (int) target.getY());
                d.drawLine((int) target.getX() + 30, (int) target.getY(),
                        (int) target.getX() + 150, (int) target.getY());
                d.drawLine((int) target.getX(), (int) target.getY() - 30,
                        (int) target.getX(), (int) target.getY() - 150);
                d.drawLine((int) target.getX(), (int) target.getY() + 30,
                        (int) target.getX(), (int) target.getY() + 150);
            }

            private void drawCircles(DrawSurface d, Point target) {
                d.drawCircle((int) target.getX(), (int) target.getY(), 50);
                d.drawCircle((int) target.getX(), (int) target.getY(), 90);
                d.drawCircle((int) target.getX(), (int) target.getY(), 130);
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
        List<Block> blocks = new ArrayList<>();
        int blockWidth = 35;
        int blockHeight = 35;
        Point upperLeft = new Point(380, 200);
        Block block = new Block(upperLeft, blockWidth, blockHeight);
        block.setColor(Color.red);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
