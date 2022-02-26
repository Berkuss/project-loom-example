package src;

import java.util.stream.IntStream;

public class RandomSort implements Runnable {

    private Integer size;
    private static final int CONSTANT = 63;

    @Override
    public void run() {
        Integer integer = IntStream.rangeClosed(0, Integer.MAX_VALUE).limit(size).boxed()
                .sorted()
                .map(i -> i % CONSTANT)
                .sorted()
                .reduce(Integer::sum)
                .orElse(-1);
    }

    public RandomSort(Integer size) {
        this.size = size;
    }

    public RandomSort() {
        this.size = 10;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
