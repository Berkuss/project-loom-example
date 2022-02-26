package src;

import java.util.LinkedList;
import java.util.Locale;
import java.util.stream.IntStream;

import static src.ThreadFactory.makeThread;
import static src.ThreadFactory.makeVirtualThread;

public class Main {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        runTest("Virtual Threads", Main::test_with_virtual_threads);
        runTest("Platform Threads", Main::test_with_platform_threads);

        System.out.println("RECURSIVE TESTS");
        runTest("Recursive Virtual Threads ", Main::test_recursive_with_virtual_threads);
        runTest("Recursive Platform Threads", Main::test_recursive_with_platform_threads);
    }

    public static void test_with_platform_threads() {

        long timeMillis = System.currentTimeMillis();
        LinkedList<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            Thread thread = makeThread(() -> new RandomSort(1000).run());
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        });
        System.out.println(System.currentTimeMillis() - timeMillis);
    }

    public static void test_with_virtual_threads() {
        long timeMillis = System.currentTimeMillis();
        LinkedList<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            Thread thread = makeVirtualThread(() -> new RandomSort(1000).run());
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        });
        System.out.println(System.currentTimeMillis() - timeMillis);
    }

    public static void test_recursive_with_virtual_threads() {
        long timeMillis = System.currentTimeMillis();
        LinkedList<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = makeVirtualThread(() -> new RandomRecursive(1000).run());
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        });
        System.out.println(System.currentTimeMillis() - timeMillis);
    }


    public static void test_recursive_with_platform_threads() {
        long timeMillis = System.currentTimeMillis();
        LinkedList<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = makeVirtualThread(() -> new RandomRecursive(1000).run());
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        });
        System.out.println(System.currentTimeMillis() - timeMillis);
    }

    private static void runTest(String name, Runnable testCase) {
        IntStream.range(1, 10).forEach(i -> {
            System.out.println("********************************************************");
            System.out.println(name.toUpperCase(Locale.ROOT) + "Test " + i + " Started...");
            testCase.run();
            System.out.println(name.toUpperCase(Locale.ROOT) + "test finished.");
        });
    }

}
