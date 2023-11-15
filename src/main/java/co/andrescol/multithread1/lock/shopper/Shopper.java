package co.andrescol.multithread1.lock.shopper;

public abstract class Shopper implements Runnable {
    private final String name;
    protected int itemsToAdd;

    public Shopper(String name) {
        this.name = name;
        this.itemsToAdd = 0;
    }

    abstract void incrementItemsInNotepad(int amount);

    protected void addItemsToNotepad() {
        try {
            incrementItemsInNotepad(this.itemsToAdd);
            System.out.println(this.name + " added " + this.itemsToAdd + " item(s) to notedpad");
            this.itemsToAdd = 0;
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void goToShop() {
        try {
            Thread.sleep(100);
            this.itemsToAdd++;
            System.out.println(this.name + " found something to buy.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
