package com.thanhbuitien.actions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        calculator = new Calculator();
    }

    @Test
    void testCal() {
        Integer integer = calculator.method1();
    }
}
