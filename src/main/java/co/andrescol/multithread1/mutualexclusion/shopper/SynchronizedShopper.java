package co.andrescol.multithread1.mutualexclusion.shopper;

public class SynchronizedShopper implements GarlicCountable {

    private static int GARLIC_COUNT = 0;

    /**
     * A synchronized method will add a lock intrinsic to the level of the method,
     * if the method is static the lock will be a class level, else the method is not static
     * the lock will be at instance level
     */
    private static synchronized void addGarlic() {
        GARLIC_COUNT++;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            addGarlic();
            /*
            We can use the statement, class for class scope or 'this' for instance scope
            synchronized (SynchronizedShopper.class) {
                GARLIC_COUNT++;
            }*/

        }
    }

    @Override
    public int getGarlicCount() {
        return GARLIC_COUNT;
    }
}
