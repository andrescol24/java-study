package co.andrescol.timecalculator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public record TimeCalculator (String testName, Runnable runnable, int times){

    public void calculate() {
        List<Long> timesRecord = new LinkedList<>();

        for(int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            runnable.run();
            long end = System.currentTimeMillis();
            timesRecord.add(end - start);
        }
        printSummary(timesRecord);
    }

    private void printSummary(List<Long> timesRecord) {
        Collections.sort(timesRecord);
        long min = timesRecord.get(0);
        long max = timesRecord.get(timesRecord.size()-1);
        double average = timesRecord.stream().reduce(Long::sum).get();
        average = average / (double) times;
        System.out.println("Summary for task " + testName);
        System.out.println("min (ms): " + min);
        System.out.println("max (ms): " + max);
        System.out.println("avr (ms): " + average);
        System.out.println("===============\n");
    }
}
