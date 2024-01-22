package co.andrescol.javaglobalmentoring.advancemultithreading.task1;

import co.andrescol.javaglobalmentoring.advancedmultithreading.task1.FactorialTask;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialForkJoinTest {

    @ParameterizedTest
    @ArgumentsSource(FactorialForkJoinArgumentProvider.class)
    public void factorialWithRangeTest(int number, BigInteger expected) {
        BigInteger result = new FactorialTask(number, true).invoke();
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ArgumentsSource(FactorialForkJoinArgumentProvider.class)
    public void factorialNormalTest(int number, BigInteger expected) {
        BigInteger result = new FactorialTask(number, false).invoke();
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ArgumentsSource(FactorialForkJoinArgumentProvider.class)
    public void factorialSingleThreadTest(int number, BigInteger expected) {
        assertEquals(expected, FactorialTask.calculateFactorial(number));
    }
}
