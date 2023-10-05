package co.andrescol.multithread1.mutualexclusion;

public class MutualExclusion {
    public static void main(String[] args) throws InterruptedException {
        /*
         * With this implementation we got a value less than 2 millions because
         * the Shopper runnable gets and modifies the GARLIC_COUNT overriding the value several times because of the
         * Mutual exclusion.
         */
        runShoppers(new Shopper(), "Mutual exclusion");

        /*
         * {@link ShopperSync} uses the concurrent.Lock locker to lock threads accessing to the
         * GARLIC_COUNT variable and this solves the mutual exclusion.
         * The concurrent operations should be atomics
         */
        runShoppers(new ShopperSync(), "With Lock from concurrent");

        /*
        Using Atomic Integer
         */
        runShoppers(new AtomicShopper(), "With AtomicInteger");

    }

    private static void runShoppers(GarlicCountable garlicCountable, String message) throws InterruptedException {
        Thread shopper1 = new Thread(garlicCountable);
        Thread shopper2 = new Thread(garlicCountable);
        shopper1.start();
        shopper2.start();
        shopper2.join();
        shopper1.join();

        System.out.println(message + ": Getting " + garlicCountable.getGarlicCount() + " vs expected 2.000.000");
    }
}
