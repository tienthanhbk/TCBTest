package com.thanhbuitien.common;

import org.apache.commons.math3.stat.StatUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilsTest {
    private List<Integer> getRandomList(int size, int bound) {
        List<Integer> list = new ArrayList<>(size);
        while (list.size() < size) {
            Random random = new Random();
            list.add(random.nextInt(bound));
        }
        return list;
    }

    private static Double norm(Double f, int scale) {
        return Math.floor(f * scale) / scale;
    }

    /**
     * Test random 99 lists, which has size from 1 to 99.
     * Each list, test with random percentile.
     */
    @Test
    void testCal() {
        for (int i = 1; i < 100; i++) {
            List<Integer> values = getRandomList(i, 10*i);
            Double percentile = Math.random()*100;
            Double trueQuantile = StatUtils.percentile(values.stream().mapToDouble(d -> d).toArray(), percentile);
            values = values.stream().sorted().collect(Collectors.toList());
            Double calQuantile = Utils.calPercentile(values, percentile);

            int scale = 10000;
            assertEquals(norm(trueQuantile, scale), norm(calQuantile, scale));
        }
    }
}
