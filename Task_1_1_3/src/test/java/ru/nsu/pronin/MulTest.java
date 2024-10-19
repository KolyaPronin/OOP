package ru.nsu.pronin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the Mul class.
 * <p>
 * This test verifies the correct behavior of the Mul operation,
 * including its print, derivative, and eval methods.
 */
class MulTest {

    /**
     * Test the print method for Mul expression.
     * Ensures that the string representation of the multiplication
     * expression is formatted correctly.
     */
    @Test
    void testPrint() {
        Expression mul = new Mul(new Number(3), new Number(4));
        assertEquals("(3*4)", mul.print());
    }

    /**
     * Test the derivative method for Mul expression.
     * Verifies that the derivative of a multiplication expression is
     * calculated using the product rule.
     */
    @Test
    void testDerivative() {
        Expression mul = new Mul(new Variable("x"), new Number(2));
        assertEquals("((1*2)+(x*0))", mul.derivative("x").print());
    }

    /**
     * Test the eval method for Mul expression.
     * Verifies that the multiplication is evaluated correctly.
     */
    @Test
    void testEval() {
        Expression mul = new Mul(new Number(3), new Number(4));
        assertEquals(12, mul.eval(""));
    }
}
