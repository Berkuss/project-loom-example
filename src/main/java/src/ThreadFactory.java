package src;

public class ThreadFactory {
    public static Thread makeVirtualThread(Runnable r) {
        return Thread.ofVirtual().factory().newThread(r);
    }

    public static Thread makeThread(Runnable r) {
        return Thread.ofPlatform().factory().newThread(r);
    }
}
