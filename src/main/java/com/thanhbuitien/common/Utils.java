package com.thanhbuitien.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.StatUtils;

public class Utils {

    /**
     * - Let n be the length of the (sorted) array and 0 < p <= 100 be the desired percentile.
     * - If n = 1 return the unique array element (regardless of the value of p); otherwise
     * - Compute the estimated percentile position pos = p * (n + 1) / 100 and the difference, d between pos and floor(pos) (i.e. the fractional part of pos).
     * - If pos < 1 return the smallest element in the array.
     * - Else if pos >= n return the largest element in the array.
     * - Else let lower be the element in position floor(pos) in the array and let upper be the next element in the array. Return lower + d * (upper - lower)
     * @param values
     * @param p
     * @return
     */
    public static double calPercentile(List<Integer> values, double p) {
        int n = values.size();
        if (n == 1) {
            return values.get(0);
        }
        double pos = p * (n + 1) / 100f;
        if (pos < 1) {
            return values.get(0);
        }
        if (pos >= n) {
            return values.get(n - 1);
        }
        double f_pos = Math.floor(pos);

        double d = pos - f_pos;

        double lower = values.get((int)f_pos - 1);
        double upper = values.get((int)f_pos);

        return lower + d * (upper - lower);
    }

    public static List<Integer> randomIntList(int size, int bound) {
        List<Integer> list = new ArrayList<>(size);
        ThreadLocalRandom.current().ints(0, bound).distinct().limit(size).forEach(list::add);
        return list;
    }

    public static void main(String[] args) {
//        System.out.println(StatUtils.percentile(new double[]{2, 5, 3, 4, 1}, 60.5));
//        Percentile percentile = new Percentile();
//        percentile.setData(new double[]{1, 2, 3, 4, 5});
//        System.out.println(percentile.evaluate(60.5));
//
//        List<Integer> values = Arrays.asList(2, 5, 3, 4, 1);
//        System.out.println(percentile(values, 60.5));

        List<Integer> values = randomIntList(10, 40);
        for (Integer value: values) {
            System.out.println(value);
        }
        values = values.stream().sorted().collect(Collectors.toList());
        double p = 73;
        System.out.println(calPercentile(values, p));

        double[] doubles = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            doubles[i] = values.get(i);
        }
        System.out.println(StatUtils.percentile(doubles, p) == calPercentile(values, p));
    }
}
