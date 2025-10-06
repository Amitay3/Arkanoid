package interfaces;

/**
 * The HitNotifier interface represents objects that can notify listeners of hit events.
 * It provides methods to add and remove hit listeners.
 */
public interface HitNotifier {

    /**
     * Adds a HitListener to the list of listeners to hit events.
     *
     * @param hl The hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners to hit events.
     *
     * @param hl The hit listener to remove.
     */
    void removeHitListener(HitListener hl);
}
