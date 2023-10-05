package co.andrescol.multithread1.lifecycle;

/**
 * We can create Threads extending Thread class and implementing the run method, or we can implement the Runnable interface
 * and crate a new Thread instance passing the Runnable instance
 */
public class ChefOlivia extends Thread {

    @Override
    public void run() {
        System.out.println("Olivia started & waiting for sausage to thaw...");
        try {
            Thread.sleep(3000);
            System.out.println("Olivia is done cutting sausage.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
