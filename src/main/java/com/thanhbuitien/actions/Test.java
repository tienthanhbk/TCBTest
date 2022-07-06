package com.thanhbuitien.actions;

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

            logger.info("Done test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
