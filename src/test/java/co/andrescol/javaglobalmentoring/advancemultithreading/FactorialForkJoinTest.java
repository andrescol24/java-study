package co.andrescol.javaglobalmentoring.advancemultithreading;

import co.andrescol.javaglobalmentoring.advancedmultithreading.FactorialTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialForkJoinTest {

    @ParameterizedTest
    @ArgumentsSource(FactorialForkJoinArgumentProvider.class)
    public void generateMessageFromTextTemplateTest(int number, BigInteger expected) {
        BigInteger result = new FactorialTask(number).invoke();
        assertEquals(expected, result);

        assertEquals(expected, FactorialTask.calculateFactorial(number));
    }
}
