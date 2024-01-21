package co.andrescol.javaglobalmentoring.advancedmultithreading;

import java.util.concurrent.RecursiveTask;
import java.math.BigInteger;

/**
 * This factorial was taken from the Java code, this takes less time than {@link FactorialTask} why????
 */
public class FactorialJavaExample {
    static class JavaExampleFactorialTask extends RecursiveTask<BigInteger> {
        private final int from, to;
        JavaExampleFactorialTask(int from, int to) { this.from = from; this.to = to; }
        protected BigInteger compute() {
            int range = to - from;
            if (range == 0) {                       // base case
                return BigInteger.valueOf(from);
            } else if (range == 1) {                // too small to parallelize
                return BigInteger.valueOf(from).multiply(BigInteger.valueOf(to));
            } else {                                // split in half
                int mid = from + range / 2;
                JavaExampleFactorialTask leftTask = new JavaExampleFactorialTask(from, mid);
                leftTask.fork();         // perform about half the work locally
                return new JavaExampleFactorialTask(mid + 1, to).compute()
                        .multiply(leftTask.join());
            }
        }
    }
}
