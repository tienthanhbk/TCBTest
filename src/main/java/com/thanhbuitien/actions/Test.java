package com.thanhbuitien.actions;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Test implements Runnable {
    Logger logger = LoggerFactory.getLogger(Test.class);

    @Override
    public void run() {
        try {
            logger.info("Start test");
            // Test something here

            Percentile percentile = new Percentile();
            percentile.setData(new double[]{1, 2, 3, 4, 5});
            System.out.println(percentile.evaluate(60.5));
            logger.info("Done test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
