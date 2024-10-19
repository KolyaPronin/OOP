package ru.nsu.pronin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the Sub class.
 * <p>
 * This test verifies the correct behavior of the Sub operation,
 * including its print, derivative, and eval methods.
 */
class SubTest {

    /**
     * Test the print method for Sub expression.
     * Ensures that the string representation of the subtraction
     * expression is formatted correctly.
     */
    @Test
    void testPrint() {
        Expression sub = new Sub(new Number(5), new Number(3));
        assertEquals("(5-3)", sub.print());
    }

    /**
     * Test the derivative method for Sub expression.
     * Verifies that the derivative of a subtraction expression is
     * calculated as the difference of the derivatives of the individual terms.
     */
    @Test
    void testDerivative() {
        Expression sub = new Sub(new Variable("x"), new Number(2));
        assertEquals("(1-0)", sub.derivative("x").print());
    }

    /**
     * Test the eval method for Sub expression.
     * Verifies that the subtraction is evaluated correctly.
     */
    @Test
    void testEval() {
        Expression sub = new Sub(new Number(5), new Number(3));
        assertEquals(2, sub.eval(""));
    }
}
