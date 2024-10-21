package ru.nsu.pronin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for the Div class.
 * This test verifies the correct behavior of the Div operation,
 * including its print, derivative, and eval methods.
 */
class DivTest {

    /**
     * Test the print method for Div expression.
     * Ensures that the string representation of the division
     * expression is formatted correctly.
     */
    @Test
    void testPrint() {
        Expression div = new Div(new Number(10), new Number(2));
        assertEquals("(10/2)", div.print());
    }

    /**
     * Test the derivative method for Div expression.
     * Verifies that the derivative of a division expression is calculated
     * using the quotient rule.
     */
    @Test
    void testDerivative() {
        Expression div = new Div(new Variable("x"), new Number(2));
        assertEquals("(((1*2)-(x*0))/(2*2))", div.derivative("x").print());
    }

    /**
     * Test the eval method for Div expression.
     * Verifies that the division is evaluated correctly.
     * Also checks that division by zero is handled properly by throwing an exception.
     */
    @Test
    void testEval() {
        Expression div = new Div(new Number(10), new Number(2));
        assertEquals(5, div.eval(""));

        // Test division by zero
        Expression divByZero = new Div(new Number(10), new Number(0));
        assertThrows(ArithmeticException.class, () -> divByZero.eval(""));
    }
}
