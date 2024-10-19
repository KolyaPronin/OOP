package ru.nsu.pronin;

/**
 * The {@code Expression} class serves as an
 * abstract base class for all mathematical expressions.
 * It defines the common interface that all
 * specific expression types must implement, such as printing the expression,
 * calculating its derivative with respect
 * to a variable, and evaluating the expression.
 *
 * <p>Classes that extend {@code Expression} must
 * provide concrete implementations for these methods.</p>
 *
 * <p>Examples of subclasses include {@link Add}
 * (for addition), {@link Sub} (for subtraction),
 * {@link Mul} (for multiplication), {@link Div}
 * (for division), {@link Variable} (for variables),
 * and {@link Number} (for constants).</p>
 *
 * @author Pronin
 */
public abstract class Expression {

    /**
     * Returns a string representation of the expression.
     *
     * <p>This method is meant to provide a human-readable form of the expression,
     * such as "(x + y)" or "5". Subclasses should override this method to define
     * their own string representations.</p>
     *
     * @return the string representation of the expression
     */
    public abstract String print();

    /**
     * Calculates the derivative of the expression with respect to the given variable.
     *
     * <p>Subclasses must implement this method to return
     * the derivative of the specific expression.
     * For example, for a constant number, the derivative is zero, while for a variable,
     * the derivative with respect to itself is one.</p>
     *
     * @param var the variable with respect to which the derivative is taken
     * @return the derivative of the expression as a new {@link Expression} object
     */
    public abstract Expression derivative(String var);

    /**
     * Evaluates the expression based on a string of variable assignments.
     *
     * <p>This method calculates the value of the expression given the values of any variables
     * involved in the expression. The input string typically consists of variable assignments
     * in the format "x=10; y=5". Subclasses should define how the evaluation is performed
     * based on their specific expression type.</p>
     *
     * @param variables a string representing the values of variables (e.g., "x=10; y=5")
     * @return the result of evaluating the expression
     * @throws RuntimeException if the evaluation fails (e.g., missing variable values)
     */
    public abstract int eval(String variables);
}
