package arkanoid.listeners;

import arkanoid.game.Ball;
import arkanoid.game.Block;
import arkanoid.game.GameLevel;

/**
 * A BallRemover is in charge of removing balls from the game.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter numberOfBalls;

    /**
     * Constructor.
     * @param gameLevel game
     * @param numberOfBalls number of balls to be removed.
     */
    public BallRemover(GameLevel gameLevel, Counter numberOfBalls) {
        this.gameLevel = gameLevel;
        this.numberOfBalls = numberOfBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.numberOfBalls.decrease(1);
    }
}
