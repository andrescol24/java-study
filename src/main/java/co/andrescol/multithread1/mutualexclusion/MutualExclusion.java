package co.andrescol.multithread1.mutualexclusion;

public class MutualExclusion {
    public static void main(String[] args) throws InterruptedException {
        mutualExclusion();
        mutualExclusionSolution();
    }

    /**
     * With this implementation we got a value less than 2 millions because
     * the Shopper runnable gets and modifies the GARLIC_COUNT overriding the value several times because of the
     * Mutual exclusion.
     */
    private static void mutualExclusion() throws InterruptedException {
        Thread shopper1 = new Thread(new Shopper());
        Thread shopper2 = new Thread(new Shopper());
        shopper1.start();
        shopper2.start();
        shopper2.join();
        shopper1.join();

        System.out.println("Getting " + Shopper.GARLIC_COUNT + " vs expected 2.000.000");
    }

    /**
     * {@link ShopperSync} uses the concurrent.Lock locker to lock threads accessing to the
     * GARLIC_COUNT variable and this solves the mutual exclusion.
     * The concurrent operations should be atomics
     */
    private static void mutualExclusionSolution() throws InterruptedException {
        Thread shopper1 = new Thread(new ShopperSync());
        Thread shopper2 = new Thread(new ShopperSync());
        shopper1.start();
        shopper2.start();
        shopper2.join();
        shopper1.join();

        System.out.println("Getting " + ShopperSync.GARLIC_COUNT + " vs expected 2.000.000");
    }
}
