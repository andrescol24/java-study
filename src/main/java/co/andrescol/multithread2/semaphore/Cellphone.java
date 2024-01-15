package co.andrescol.multithread2.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Cellphone extends Thread {
    /*
    Semaphore servers as a mutex that permits several thread to use a different lock for the same code at the same time,
    the number of threads that can use the mutex is the parameter permits.
     */
    private static Semaphore charger = new Semaphore(2);

    public Cellphone(String name) {
        super(name);
    }
    @Override
    public void run() {
        try {
            charger.acquire();
            System.out.println(this.getName() + " Is charging the cellphone");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.getName() + " Has charged the cellphone!");
            charger.release();
        }
    }
}
