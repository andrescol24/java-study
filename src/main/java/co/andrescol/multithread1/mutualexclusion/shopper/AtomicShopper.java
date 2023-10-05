package co.andrescol.multithread1.mutualexclusion.shopper;

import java.util.concurrent.atomic.AtomicInteger;

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
