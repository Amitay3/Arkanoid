package listeners;

import game.Game;
import interfaces.HitListener;
import graphics.Ball;
import graphics.Block;

/**
 * The BlockRemover class is responsible for removing blocks from the game when they are hit.
 * It also keeps count of the number of blocks that remain in the game.
 * It implements the HitListener interface to respond to hit events.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with the specified game and counter for remaining blocks.
     *
     * @param game The game from which blocks will be removed.
     * @param remainingBlocks The counter for the remaining blocks in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the block from the game, decreases the counter of remaining blocks,
     * and removes this listener from the block.
     *
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isGameBlock()) {
            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
        }
    }
}
