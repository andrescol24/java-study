package co.andrescol.multithread1.lock;

import co.andrescol.multithread1.lock.shopper.Shopper;
import co.andrescol.multithread1.lock.shopper.ShopperTryLock;
import co.andrescol.multithread1.lock.shopper.SimpleShopper;

import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        This call uses the Shopper class what is not using the TryLock, that means
        it will take more time because the lock locks the threads and does not allow to do
        any other action meanwhile the threads are waiting
         */
        countTime(() -> callShoppers(new SimpleShopper("Andres"), new SimpleShopper("Melba")));

        /*
        This call uses the trylock Lock's method, it will try to lock and if the lock is blocked it will continue
        shopping another things, for that reason this approach is faster
         */
        countTime(() -> callShoppers(new ShopperTryLock("Yelitsa"), new ShopperTryLock("Michael")));

    }

    private static void callShoppers(Shopper shopper1, Shopper shopper2) {
        try {
            // Reflection Constructor<T> constructor = (Constructor<T>) shopper.getConstructor(String.class);
            Thread andres = new Thread(shopper1);
            Thread melba = new Thread(shopper2);
            andres.start();
            melba.start();
            andres.join();
            melba.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static  void countTime(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long finish = System.currentTimeMillis();
        System.out.println("==== Elapse time: " + (finish - start)/1000 + " seconds ====\n");
    }
}
