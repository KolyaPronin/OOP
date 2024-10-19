package ru.nsu.pronin;

/**
 * The {@code Div} class represents a division operation between two {@link Expression} objects.
 * It provides methods for printing the expression, calculating its derivative with respect to a variable,
 * and evaluating the expression given specific variable values.
 *
 * <p>For example, given two expressions {@code left} and {@code right}, this class will compute
 * the result of {@code left / right}.</p>
 *
 * <p>This class extends the {@link Expression} class, and overrides its methods
 * to provide specific functionality for division.</p>
 */
class Div extends Expression {

    /**
     * The left operand of the division (numerator).
     */
    private final Expression left;

    /**
     * The right operand of the division (denominator).
     */
    private final Expression right;

    /**
     * Constructs a {@code Div} object with the given left and right expressions.
     *
     * @param left  the left operand of the division
     * @param right the right operand of the division
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns a string representation of the division expression in the form "(left / right)".
     *
     * @return a string representation of the expression
     */
    @Override
    public String print() {
        return "(" + left.print() + "/" + right.print() + ")";
    }

    /**
     * Calculates the derivative of the division expression with respect to the specified variable.
     *
     * <p>It applies the quotient rule: (f / g)' = (f' * g - f * g') / g^2, where {@code f} and {@code g} are the
     * left and right expressions, respectively.</p>
     *
     * @param var the variable with respect to which the derivative is taken
     * @return a new {@link Div} object representing the derivative of the division
     */
    @Override
    public Expression derivative(String var) {
        // Правило производной для деления: (f/g)' = (f' * g - f * g') / g^2
        return new Div(
                new Sub(
                        new Mul(left.derivative(var), right),  // f' * g
                        new Mul(left, right.derivative(var))   // f * g'
                ),
                new Mul(right, right)  // g^2
        );
    }

    /**
     * Evaluates the division expression by computing the result of {@code left / right}.
     *
     * <p>First, both operands are evaluated, and the division is performed. If the denominator evaluates to zero,
     * an {@link ArithmeticException} is thrown.</p>
     *
     * @param variables a string representing the values of the variables (implementation-specific)
     * @return the result of the division
     * @throws ArithmeticException if the denominator evaluates to zero
     */
    @Override
    public int eval(String variables) {
        int rightValue = right.eval(variables);
        if (rightValue == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return left.eval(variables) / rightValue;
    }
}
