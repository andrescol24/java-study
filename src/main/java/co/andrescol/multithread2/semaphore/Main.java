package co.andrescol.multithread2.semaphore;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Cellphone("user #" + i).start();
        }
    }
}
