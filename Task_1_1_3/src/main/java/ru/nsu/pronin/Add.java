package ru.nsu.pronin;

/**
 * The {@code Add} class represents an addition
 * operation between two {@link Expression} objects.
 * It provides methods for printing the expression,
 * calculating its derivative with respect to a variable,
 * and evaluating the expression given specific variable values.
 *
 * <p>This class extends the {@link Expression} class, and overrides its methods
 * to provide specific functionality for addition.</p>
 *
 * <p>For example, given two expressions {@code left}
 * and {@code right}, this class will compute
 * the result of {@code left + right}.</p>

 */
class Add extends Expression {

    /**
     * The left operand of the addition.
     */
    private final Expression left;

    /**
     * The right operand of the addition.
     */
    private final Expression right;

    /**
     * Constructs an {@code Add} object with the given left and right expressions.
     *
     * @param left  the left operand of the addition
     * @param right the right operand of the addition
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns a string representation of the addition
     * expression in the form "(left + right)".
     *
     * @return a string representation of the expression
     */
    @Override
    public String print() {
        return "(" + left.print() + "+" + right.print() + ")";
    }

    /**
     * Calculates the derivative of the addition
     * expression with respect to the specified variable.
     *
     * <p>The derivative of a sum is the sum of the derivatives:
     * (f + g)' = f' + g', where {@code f} and {@code g}
     * are the left and right expressions, respectively.</p>
     *
     * @param var the variable with respect to which the derivative is taken
     * @return a new {@link Add} object representing the derivative of the addition
     */
    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    /**
     * Evaluates the addition expression by computing the result of {@code left + right}.
     *
     * <p>Both operands are evaluated, and their sum is returned.</p>
     *
     * @param variables a string representing
     *                  the values of the variables (implementation-specific)
     * @return the result of the addition
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) + right.eval(variables);
    }
}
