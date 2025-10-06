package interfaces;

import graphics.Ball;
import graphics.Block;

/**
 * The HitListener interface represents objects that can listen for hit events.
 * It provides a method to handle hit events when a block is hit by a ball.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit The block that is being hit.
     * @param hitter The ball that is hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
