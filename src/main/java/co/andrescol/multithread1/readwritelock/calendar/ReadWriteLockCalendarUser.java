package co.andrescol.multithread1.readwritelock.calendar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCalendarUser extends CalendarUser {
    private static int today = 0;
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public ReadWriteLockCalendarUser(String name) {
        super(name);
    }

    @Override
    Lock getReadLock() {
        return readLock;
    }

    @Override
    Lock getWriteLock() {
        return writeLock;
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
        ReadWriteLockCalendarUser.today = today;
    }
}
