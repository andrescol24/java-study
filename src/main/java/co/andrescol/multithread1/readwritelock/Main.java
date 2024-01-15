package co.andrescol.multithread1.readwritelock;

import co.andrescol.multithread1.readwritelock.calendar.CalendarUser;
import co.andrescol.multithread1.readwritelock.calendar.LockCalendarUser;
import co.andrescol.multithread1.readwritelock.calendar.ReadWriteLockCalendarUser;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        This run will use only the Lock class, that means the lock will lock readers and writers so readers can not read
        meanwhile writers are not writing.
         */
        runReadersWriters(LockCalendarUser.class);

        System.out.println("----------------- Changing implementation -----------------------------------\n");

        /*
        This run will use the WriteLock and ReadLock class, that means the lock will allow readers to read and they can
        read if the writers are not writing.
         */
        runReadersWriters(ReadWriteLockCalendarUser.class);
    }

    public static <T extends CalendarUser> void runReadersWriters(Class<T> runnableType) {
        List<Thread> threads = new LinkedList<>();
        try {
            Constructor<T> constructor = runnableType.getConstructor(String.class);
            for (int i = 0; i < 10; i++) {// Readers
                Thread reader = new Thread(constructor.newInstance("Reader-" + i));
                threads.add(reader);
                reader.start();
            }

            for (int i = 0; i < 2; i++) {// Readers
                Thread writer = new Thread(constructor.newInstance("Writer-" + i));
                threads.add(writer);
                writer.start();
            }

            for (Thread thread : threads)
                thread.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
