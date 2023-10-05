package co.andrescol.multithread1.lifecycle;

/**
 * Two threads cooking soup
 */
public class ThreadLifecycleDemo {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Barron started & requesting Olivia's help.");
        Thread olivia = new ChefOlivia(); // NEW State
        System.out.println("  Olivia State: " + olivia.getState());

        System.out.println("Barron tells Olivia to start.");
        olivia.start(); // RUNNABLE State
        System.out.println("  Olivia State: " + olivia.getState());

        System.out.println("Barron continues cooking soup.");
        Thread.sleep(500);
        System.out.println("  Olivia State: " + olivia.getState()); // Thread.sleep into olivia thread

        System.out.println("Barron patiently waits for Olivia to finish and join...");
        olivia.join(); // will wait olivia thread, then Olivia thread will have TERMINATED State
        System.out.println("  Olivia State: " + olivia.getState());

        System.out.println("Barron and Olivia are both done!");
    }
}