package ru.nsu.pronin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the Number class.
 * This test verifies the correct behavior of the Number operation,
 * including its print, derivative, and eval methods.
 */
class NumberTest {

    /**
     * Test the print method for Number expression.
     * Ensures that the string representation of a number is
     * formatted correctly.
     */
    @Test
    void testPrint() {
        Expression num = new Number(42);
        assertEquals("42", num.print());
    }

    /**
     * Test the derivative method for Number expression.
     * Verifies that the derivative of a number is always 0, as the
     * derivative of a constant is 0.
     */
    @Test
    void testDerivative() {
        Expression num = new Number(42);
        assertEquals("0", num.derivative("x").print());
    }

    /**
     * Test the eval method for Number expression.
     * Verifies that the evaluation of a number simply returns the number itself.
     */
    @Test
    void testEval() {
        Expression num = new Number(42);
        assertEquals(42, num.eval(""));
    }
}
