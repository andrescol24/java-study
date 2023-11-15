package co.andrescol.multithread1.lock.shopper;

import java.util.concurrent.locks.ReentrantLock;

public class SimpleShopper extends Shopper {

    protected static int ITEMS_ON_NOTE_PAD = 0;
    protected static final ReentrantLock pencil = new ReentrantLock();

    public SimpleShopper(String name) {
        super(name);
    }

    @Override
    public void run() {
        while(ITEMS_ON_NOTE_PAD <= 20) {
            if(this.itemsToAdd > 0) {
                pencil.lock();
                this.addItemsToNotepad();
                pencil.unlock();
            } else {
                this.goToShop();
            }
        }
    }

    @Override
    void incrementItemsInNotepad(int amount) {
        ITEMS_ON_NOTE_PAD += amount;
    }
}
