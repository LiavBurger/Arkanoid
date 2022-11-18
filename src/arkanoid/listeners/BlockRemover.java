package arkanoid.listeners;

import arkanoid.game.Ball;
import arkanoid.game.Block;
import arkanoid.game.GameLevel;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel game.
     * @param removedBlocks counter for how many blocks are remaining.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit block that got hit
     * @param hitter ball that is hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
    }
}