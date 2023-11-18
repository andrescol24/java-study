package co.andrescol.multithread1.readwritelock.calendar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class CalendarUser implements Runnable {

    private static final String[] WEEK_DAYS = {"Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat"};
    private final String name;
    public CalendarUser(String name) {
        this.name = name;
    }
    abstract Lock getReadLock();
    abstract Lock getWriteLock();
    abstract Object getMainMarket();
    abstract int getToday();
    abstract void setToday(int today);

    @Override
    public void run() {
        while (getToday() < WEEK_DAYS.length - 1 ) {
            if (this.name.contains("Writer")) {
                getWriteLock().lock();
                setToday((getToday() + 1) % 7);
                System.out.println(this.name + " updated date to " + WEEK_DAYS[getToday()]);
                getWriteLock().unlock();
            } else {
                getReadLock().lock();
                if (getMainMarket() instanceof ReentrantReadWriteLock lock)
                    System.out.println(this.name + " sees that today is " + WEEK_DAYS[getToday()] + "; readers " + lock.getReadLockCount());
                else
                    System.out.println(this.name + " sees that today is " + WEEK_DAYS[getToday()]);
                getReadLock().unlock();
            }
        }
    }



}
