package co.andrescol.multithread1.mutualexclusion;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicShopper implements GarlicCountable {

    private static final AtomicInteger GARLIC_COUNT = new AtomicInteger();
    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            GARLIC_COUNT.incrementAndGet();
        }
    }

    @Override
    public int getGarlicCount() {
        return GARLIC_COUNT.get();
    }
}
