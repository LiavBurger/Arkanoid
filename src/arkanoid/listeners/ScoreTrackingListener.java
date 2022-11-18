package arkanoid.listeners;

import arkanoid.game.Ball;
import arkanoid.game.Block;

/**
 * ScoreTrackingListener Class.
 * Hitting a block is worth 5 points.
 * Clearing an entire level (destroying all blocks) is worth another 100 points.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
            this.currentScore.increase(5);
    }
}