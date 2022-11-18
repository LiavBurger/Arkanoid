package arkanoid.listeners;

import arkanoid.game.Ball;
import arkanoid.game.Block;

/**
 * Objects that want to be notified of hit events, should implement the HitListener interface,
 * and register themselves with a HitNotifier object using its addHitListener method.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit block that got hit
     * @param hitter ball that is hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}