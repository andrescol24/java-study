package co.andrescol.multithread1.mutualexclusion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShopperSync implements Runnable {

    public static int GARLIC_COUNT = 0;
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            lock.lock();
            GARLIC_COUNT++;
            lock.unlock();
        }
    }
}
