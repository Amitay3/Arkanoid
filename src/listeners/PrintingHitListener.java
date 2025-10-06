package listeners;

import interfaces.HitListener;
import graphics.Ball;
import graphics.Block;

/**
 * The PrintingHitListener class is a simple implementation of the HitListener interface.
 * It prints a message to the console whenever a block is hit.
 */
public class PrintingHitListener implements HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * It prints a message to the console.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
    }
}
