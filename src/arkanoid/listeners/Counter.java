package arkanoid.listeners;
/**
 * Counter is a simple class that is used for counting things.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     * initialize the counter to 0
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * construct a counter from an initial value.
     * @param value the given initial value.
     */
    public Counter(int value) {
        this.counter = value;
    }
    /**
     * add number to current count.
     * @param number number
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract number from current count.
     * @param number number
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     * @return number
     */
    public int getValue() {
        return this.counter;
    }
}