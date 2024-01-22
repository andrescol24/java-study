package co.andrescol.javaglobalmentoring.advancedmultithreading.task1;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {

    private final int to;
    private final int from;
    private final boolean withRange;
    private static final int MAX_RANGE = 1000;

    private FactorialTask(int from, int to, boolean withRange) {
        this.from = from;
        this.to = to;
        this.withRange = withRange;
    }

    public FactorialTask(int n, boolean withRange) {
        this(2, n, withRange);
    }

    @Override
    protected BigInteger compute() {
        if(to < 2) {
            return BigInteger.ONE;
        }
        return withRange ? computeWithRange() : computeNormal();
    }

    private BigInteger computeWithRange() {
        int range = to - from;
        if(range < MAX_RANGE) {
            return calculateFactorial(from, to);
        }
        int mid = from + range/2;
        FactorialTask left = new FactorialTask(from, mid, withRange);
        left.fork();
        FactorialTask right = new FactorialTask(mid + 1, to, withRange);
        return right.compute().multiply(left.join());
    }

    private BigInteger computeNormal() {
        int range = to - from;
        if(range == 0) {
            return BigInteger.valueOf(from);
        } else if (range == 1) {
            return BigInteger.valueOf(from).multiply(BigInteger.valueOf(to));
        } else {
            int mid = from + range / 2;
            FactorialTask left = new FactorialTask(from, mid, withRange);
            left.fork();
            FactorialTask right = new FactorialTask(mid + 1, to, withRange);
            return right.compute().multiply(left.join());
        }
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
