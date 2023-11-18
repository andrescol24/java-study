package co.andrescol.multithread1.readwritelock.calendar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCalendarUser extends CalendarUser {

    private static int today = 0;
    private static final Lock lock = new ReentrantLock();

    public LockCalendarUser(String name) {
        super(name);
    }

    @Override
    Lock getReadLock() {
        return lock;
    }

    @Override
    Lock getWriteLock() {
        return lock;
    }

    @Override
    Object getMainMarket() {
        return lock;
    }

    @Override
    int getToday() {
        return today;
    }

    @Override
    void setToday(int today) {
        LockCalendarUser.today = today;
    }
}
