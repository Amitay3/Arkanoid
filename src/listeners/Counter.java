package listeners;

/**
 * The Counter class is used to keep track of a count, which can be increased or decreased.
 */
public class Counter {
    private int count;

    /**
     * Constructs a Counter with an initial count of zero.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Adds the specified number to the current count.
     *
     * @param number The number to add to the current count.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtracts the specified number from the current count.
     *
     * @param number The number to subtract from the current count.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return The current count.
     */
    public int getValue() {
        return this.count;
    }
}
