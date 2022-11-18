package arkanoid.levels;

import arkanoid.game.Block;
import arkanoid.game.Sprite;
import arkanoid.game.Velocity;

import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * Counts the number of balls in a level.
     * @return number of balls in a level.
     */
    int numberOfBalls();

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * The initial velocity of each ball.
     * @return list with the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Finds the speed of the paddle.
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Finds the width of the paddle.
     * @return the width of the paddle.
     */
    int paddleWidth();


    /**
     * The level name will be displayed at the top of the screen.
     * @return level name
     */
    String levelName();

    // Returns a sprite with the background of the level
    /**
     * @return Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of the blocks.
     */
    List<Block> blocks();


    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
}