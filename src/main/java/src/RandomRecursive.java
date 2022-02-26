package src;

public class RandomRecursive implements Runnable {

    private final Integer maxDeep;

    public RandomRecursive(Integer maxDeep) {
        this.maxDeep = maxDeep;
    }

    @Override
    public void run() {
        factorial(maxDeep);
    }

    private Integer factorial(Integer i) {
        if (i.equals(1)) return 1;
        else return factorial(i - 1) * i;
    }
}
