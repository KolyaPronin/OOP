package ru.nsu.pronin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the Variable class.
 * <p>
 * This test verifies the correct behavior of the Variable operation,
 * including its print, derivative, and eval methods.
 */
class VariableTest {

    /**
     * Test the print method for Variable expression.
     * Ensures that the string representation of a variable is
     * formatted correctly.
     */
    @Test
    void testPrint() {
        Expression var = new Variable("x");
        assertEquals("x", var.print());
    }

    /**
     * Test the derivative method for Variable expression.
     * Verifies that the derivative of a variable is calculated correctly.
     * The derivative of a variable with respect to itself is 1, and
     * with respect to any other variable is 0.
     */
    @Test
    void testDerivative() {
        Expression var = new Variable("x");
        assertEquals("1", var.derivative("x").print());
        assertEquals("0", var.derivative("y").print());
    }

    /**
     * Test the eval method for Variable expression.
     * Verifies that the variable evaluation works correctly based on
     * the provided mapping of variable names to values.
     */
    @Test
    void testEval() {
        Expression var = new Variable("x");
        assertEquals(5, var.eval("x=5"));
        assertThrows(RuntimeException.class, () -> var.eval("y=5"));
    }
}
