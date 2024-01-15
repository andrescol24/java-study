package co.andrescol.multithread2.raceandbarrier;

import java.util.LinkedList;
import java.util.List;

/**
 * A Barrier allows several threads to be synchronized, so we can configure priorities
 */
public class BarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Shopper> shoppers = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            shoppers.add(new Shopper("Andres-" + i));
            shoppers.add(new Shopper("Yelitsa-"+ i));
        }
        shoppers.forEach(Shopper::start);

        for(Shopper shopper : shoppers)
            shopper.join();
        System.out.println("We must buy " + Shopper.CHIPS_TO_BUY + " chip bags");
    }
}
