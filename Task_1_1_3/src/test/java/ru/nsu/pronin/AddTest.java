package ru.nsu.pronin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the Add class.
 * This test verifies the correct behavior of the Add operation,
 * including its print, derivative, and eval methods.
 */
class AddTest {

    /**
     * Test the print method for Add expression.
     * Ensures that the string representation of the expression
     * is formatted correctly.
     */
    @Test
    void testPrint() {
        Expression add = new Add(new Number(2), new Number(3));
        assertEquals("(2+3)", add.print());
    }

    /**
     * Test the derivative method for Add expression.
     * Verifies that the derivative of an addition expression is the
     * sum of the derivatives of the individual terms.
     */
    @Test
    void testDerivative() {
        Expression add = new Add(new Variable("x"), new Number(2));
        assertEquals("(1+0)", add.derivative("x").print());
    }

    /**
     * Test the eval method for Add expression.
     * Verifies that the evaluation of an addition expression produces
     * the correct result.
     */
    @Test
    void testEval() {
        Expression add = new Add(new Number(2), new Number(3));
        assertEquals(5, add.eval(""));
    }
}
