package listeners;

import interfaces.HitListener;
import graphics.Ball;
import graphics.Block;

/**
 * The ScoreTrackingListener class is responsible for updating the score when blocks are hit.
 * It implements the HitListener interface to respond to hit events.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter The counter that tracks the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It increases the score by 5 points.
     *
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
