package ru.nsu.pronin;

/**
 * The {@code Sub} class represents a subtraction
 * operation between two {@link Expression} objects.
 * It provides methods for printing the expression,
 * calculating its derivative with respect to a variable,
 * and evaluating the expression given specific variable values.
 *
 * <p>For example, given two expressions {@code left} and {@code right}, the class will compute
 * the result of {@code left - right}.</p>
 *
 * <p>This class extends the {@link Expression} class, and overrides its methods
 * to provide specific functionality for subtraction.</p>
 */
class Sub extends Expression {

    /**
     * The left operand of the subtraction.
     */
    private final Expression left;

    /**
     * The right operand of the subtraction.
     */
    private final Expression right;

    /**
     * Constructs a {@code Sub} object with the given left and right expressions.
     *
     * @param left  the left operand of the subtraction
     * @param right the right operand of the subtraction
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns a string representation of the
     * subtraction expression in the form "(left-right)".
     *
     * @return a string representation of the expression
     */
    @Override
    public String print() {
        return "(" + left.print() + "-" + right.print() + ")";
    }

    /**
     * Calculates the derivative of the subtraction
     * expression with respect to the specified variable.
     *
     * <p>The derivative of {@code left - right} is
     * {@code left' - right'}, where {@code left'} and {@code right'}
     * are the derivatives of the left and right expressions, respectively.</p>
     *
     * @param var the variable with respect to which the derivative is taken
     * @return a new {@code Sub} object representing the derivative of the subtraction
     */
    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    /**
     * Evaluates the subtraction expression by computing the result of {@code left - right}.
     *
     * <p>Each operand is evaluated first, and then their difference is returned.</p>
     *
     * @param variables a string representing the
     *                 values of the variables (implementation-specific)
     * @return the result of the subtraction
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) - right.eval(variables);
    }
}
