package co.andrescol.javaglobalmentoring.advancemultithreading.task2;

import co.andrescol.javaglobalmentoring.advancedmultithreading.task2.QuickSortTask;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuickSortTest {

    @Test
    public void testQuickSort() {
        int[] array = new int[]{10, 8, 5, 6, 1, 10, 20};

        QuickSortTask task = new QuickSortTask(array);
        try(ForkJoinPool pool = new ForkJoinPool()) {
            pool.invoke(task);
        }

        Arrays.sort(array);
        boolean sameOrder = Arrays.equals(array, task.getArray());
        assertTrue(sameOrder);
    }
}
