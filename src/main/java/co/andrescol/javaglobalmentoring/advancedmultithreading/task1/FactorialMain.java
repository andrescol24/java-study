package co.andrescol.javaglobalmentoring.advancedmultithreading.task1;

import co.andrescol.timecalculator.TimeCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FactorialMain {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors + " processor" + (processors != 1 ? "s are " : " is ") + "available");
        List<Integer> factorials = Arrays.asList(25, 100_000);
        int times = 100;
        switch (args[0]) {
            case "without" ->
                    runTimerCalculator("Factorial without FJP of ", FactorialMain::toFactorialTaskWithoutFJP, factorials, times);
            case "with" ->
                    runTimerCalculator("Factorial with FJP of ", FactorialMain::toFactorialTaskWithFJP, factorials, times);
            case "javaexample" ->
                    runTimerCalculator("Factorial with Java Example of ", FactorialMain::toJavaExample, factorials, times);
        }
    }

    private static void runTimerCalculator(String message, Function<Integer, Runnable> function, List<Integer> parameters, int times) {
        for(Integer number: parameters) {
            Runnable runnable = function.apply(number);
            TimeCalculator calculator = new TimeCalculator(message + number, runnable, times);
            calculator.calculate();
        }
    }

    private static Runnable toFactorialTaskWithoutFJP(int x) {
        return () -> FactorialTask.calculateFactorial(x);
    }

    private static Runnable toFactorialTaskWithFJP(int x) {
        return () -> new FactorialTask(x, true).invoke();
    }

    private static Runnable toJavaExample(int x) {
        return () -> new FactorialTask(x, false).invoke();
    }
}
