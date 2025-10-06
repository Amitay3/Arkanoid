package listeners;

import game.Game;
import interfaces.HitListener;
import graphics.Ball;
import graphics.Block;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit certain blocks.
 * It implements the HitListener interface to respond to hit events.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover with the specified game and counter for remaining balls.
     *
     * @param game The game from which balls will be removed.
     * @param remainingBalls The counter for the remaining balls in the game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the ball from the game and decreases the counter of remaining balls.
     *
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
