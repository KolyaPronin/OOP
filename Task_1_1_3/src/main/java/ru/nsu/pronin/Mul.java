package ru.nsu.pronin;

/**
 * The {@code Mul} class represents a multiplication operation between two {@link Expression} objects.
 * It provides methods for printing the expression, calculating its derivative with respect to a variable,
 * and evaluating the expression given specific variable values.
 *
 * <p>For example, given two expressions {@code left} and {@code right}, this class will compute
 * the result of {@code left * right}.</p>
 *
 * <p>This class extends the {@link Expression} class, and overrides its methods
 * to provide specific functionality for multiplication.</p>
 */
class Mul extends Expression {

    /**
     * The left operand of the multiplication.
     */
    private final Expression left;

    /**
     * The right operand of the multiplication.
     */
    private final Expression right;

    /**
     * Constructs a {@code Mul} object with the given left and right expressions.
     *
     * @param left  the left operand of the multiplication
     * @param right the right operand of the multiplication
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns a string representation of the multiplication expression in the form "(left * right)".
     *
     * @return a string representation of the expression
     */
    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    /**
     * Calculates the derivative of the multiplication expression with respect to the specified variable.
     *
     * <p>It applies the product rule: (f * g)' = f' * g + f * g', where {@code f} and {@code g} are the
     * left and right expressions, respectively.</p>
     *
     * @param var the variable with respect to which the derivative is taken
     * @return a new {@link Add} object representing the derivative of the multiplication
     */
    @Override
    public Expression derivative(String var) {
        // Применяем правило произведения (f * g)' = f' * g + f * g'
        return new Add(
                new Mul(left.derivative(var), right),  // f' * g
                new Mul(left, right.derivative(var))   // f * g'
        );
    }

    /**
     * Evaluates the multiplication expression by computing the product of {@code left * right}.
     *
     * <p>Each operand is evaluated first, and then their product is returned.</p>
     *
     * @param variables a string representing the values of the variables (implementation-specific)
     * @return the result of the multiplication
     */
    @Override
    public int eval(String variables) {
        return left.eval(variables) * right.eval(variables);
    }
}
