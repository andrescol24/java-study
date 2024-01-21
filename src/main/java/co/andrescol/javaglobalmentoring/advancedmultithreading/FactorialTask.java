package co.andrescol.javaglobalmentoring.advancedmultithreading;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {

    private final int to;
    private final int from;
    private static final int MAX_RANGE = 1000;

    private FactorialTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public FactorialTask(int n) {
        this(2, n);
    }

    @Override
    protected BigInteger compute() {
        int range = to - from;
        if(range < MAX_RANGE) {
            return calculateFactorial(from, to);
        }
        int mid = from + range/2;
        FactorialTask left = new FactorialTask(from, mid);
        left.fork();
        FactorialTask right = new FactorialTask(mid + 1, to);
        return right.compute().multiply(left.join());
    }

    private static BigInteger calculateFactorial(int from, int to) {
        if(from == to) {
            return BigInteger.valueOf(to);
        }
        if(to == 1 || to == 0) {
            return BigInteger.ONE;
        }

        BigInteger result = BigInteger.valueOf(from);
        int actualNumber = from;
        do {
            actualNumber++;
            result = result.multiply(BigInteger.valueOf(actualNumber));
        } while(actualNumber < to);
        return result;
    }

    public static BigInteger calculateFactorial(int n) {
        return calculateFactorial(2, n);
    }
}
