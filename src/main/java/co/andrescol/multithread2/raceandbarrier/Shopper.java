package co.andrescol.multithread2.raceandbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shopper extends Thread {
    public static int CHIPS_TO_BUY = 1;
    private static final Lock lock = new ReentrantLock();

    /**
     * We have two options of barriers:
     * CyclicBarrier will wait until all parties are waiting
     * CountDownLatch will count down the calls and will free until the countdown finishes
     */
    private static final CountDownLatch barrier = new CountDownLatch(5); //new CyclicBarrier(10);

    public Shopper(String name) {super(name);}
    @Override
    public void run() {
        if (this.getName().contains("Andres")) {
            lock.lock();
            CHIPS_TO_BUY += 3;
            System.out.println(this.getName() + " ADDED 3 bags of chips.");
            lock.unlock();
            barrier.countDown();
            // waitBarrier(); //With CyclicBarrier.  This is on the bottom add the bags to perform that action first
        } else {
            waitBarrier(); // this is on the top of multiply to perform this task at the end.
            lock.lock();
            CHIPS_TO_BUY *= 2;
            lock.unlock();
            System.out.println(this.getName() + " MULTIPLIED by 2 the bags of chips.");
        }

    }
    public void waitBarrier() {
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
