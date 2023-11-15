package co.andrescol.multithread1.mutualexclusion.shopper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShopperSync implements GarlicCountable {

    private static int GARLIC_COUNT = 0;

    /**
     * Locks have one problem, if a thread locks the mutex several times, and it does not unlock it, other threads can not use the mutex,
     * (the name of this problem is deadlock).
     * Reentrant mutex can be blocked multiple times and must be unlocked the same times
     */
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            lock.lock();
            GARLIC_COUNT++;
            lock.unlock();
        }
    }

    @Override
    public int getGarlicCount() {
        return GARLIC_COUNT;
    }
}
