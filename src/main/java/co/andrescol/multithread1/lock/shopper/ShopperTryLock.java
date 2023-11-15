package co.andrescol.multithread1.lock.shopper;

import java.util.concurrent.locks.ReentrantLock;

public class ShopperTryLock extends Shopper {
    protected static int ITEMS_ON_NOTE_PAD = 0;
    protected static final ReentrantLock pencil = new ReentrantLock();

    public ShopperTryLock(String name) {
        super(name);
    }

    @Override
    void incrementItemsInNotepad(int amount) {
        ITEMS_ON_NOTE_PAD += amount;
    }

    @Override
    public void run() {
        while(ITEMS_ON_NOTE_PAD <= 20) {
            if(this.itemsToAdd > 0 && pencil.tryLock()) {
                this.addItemsToNotepad();
                pencil.unlock();
            } else {
                this.goToShop();
            }
        }
    }
}
