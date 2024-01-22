package co.andrescol.javaglobalmentoring.advancedmultithreading.task2;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class QuickSortTask extends RecursiveAction{
    private final AtomicIntegerArray array;
    private final int left;
    private final int right;

    private QuickSortTask(AtomicIntegerArray array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    public QuickSortTask(int[] array) {
        this(new AtomicIntegerArray(array), 0, array.length - 1);
    }

    public int[] getArray() {
        int[] newArray = new int[array.length()];
        for(int i = 0; i < array.length(); i++) {
            newArray[i] = array.get(i);
        }
        return newArray;
    }

    @Override
    protected void compute() {
        /*
         * Code took from <a href="https://puntocomnoesunlenguaje.blogspot.com/2012/12/java-quicksort.html">Page</a>
         * @return ordered array
         */
        int pivote = array.get(left);
        int i = left;
        int j = right;

        while (i < j) {
            while (array.get(i) <= pivote && i < j) i++;
            while (array.get(j) > pivote) j--;
            if (i < j) {
                int aux = array.get(i);
                array.set(i, array.get(j));
                array.set(j, aux);
            }
        }
        array.set(left, array.get(j));
        array.set(j, pivote);
        if (left < j - 1) {
            invokeAll(new QuickSortTask(array, left, j - 1));
        }
        if (j + 1 < right) {
            invokeAll(new QuickSortTask(array, j + 1, right));
        }
    }
}
