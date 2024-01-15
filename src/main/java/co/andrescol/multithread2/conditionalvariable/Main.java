package co.andrescol.multithread2.conditionalvariable;

public class Main {
    public static void main(String args[]) {
        for (int i=0; i<5; i++)
            new HungryPerson(i).start();
    }
}
