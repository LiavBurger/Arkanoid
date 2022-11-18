package arkanoid.game;

import arkanoid.listeners.HitListener;

/**
 * The HitNotifier interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl hit listener
     */
    void addHitListener(HitListener hl);
    /**
     * remove hl as a listener to hit events.
     * @param hl hit listener
     */
    void removeHitListener(HitListener hl);
    // This method is called whenever the beingHit object is hit.
}