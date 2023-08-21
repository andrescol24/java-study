package co.andrescol.leadcode.week3;

import co.andrescol.leadcode.LeadCodeRunnable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WeekCode implements LeadCodeRunnable {

    @Override
    public void run() {
        System.out.println(uniqueOccurrences(new int[]{1,2,2,1,1,3}));
        System.out.println(uniqueOccurrences(new int[]{1,2}));
        System.out.println(uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
    }

    /**
     * Exercise <a href="https://leetcode.com/problems/unique-number-of-occurrences/">...</a>
     * @param arr array
     * @return true if there are not repeated numbers
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> uniqueNumbers = new HashMap<>();
        for (int j : arr) {
            int ocurrences = Optional.ofNullable(uniqueNumbers.get(j)).orElse(0);
            uniqueNumbers.put(j, ++ocurrences);
        }
        Collection<Integer> values = uniqueNumbers.values();
        long occurrences = values.stream()
                .filter(occurrence -> Collections.frequency(values, occurrence) > 1)
                .count();
        return occurrences == 0;
    }
}
