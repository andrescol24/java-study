package co.andrescol.multithread1.mutualexclusion;

public class Shopper implements Runnable {

    public static int GARLIC_COUNT = 0;

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            GARLIC_COUNT++;
        }
    }
}
