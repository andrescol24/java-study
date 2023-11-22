package co.andrescol.multithread2.conditionalvariable;

/**
 * Condition variable serve as a flag to let others threads know that the look is free.
 * That signal is useful to avoid overload in the threads looking the block all the time.
 */
public class Main {
    public static void main(String args[]) {
        for (int i=0; i<5; i++)
            new HungryPerson(i).start();
    }
}