package ru.nsu.pronin;

/**
 * The {@code Number} class represents a constant numerical value in an expression.
 * It provides methods for printing the number, calculating its derivative, and evaluating it.
 *
 * <p>This class extends the {@link Expression} class, and overrides its methods
 * to handle constant numbers.</p>
 *
 * <p>For example, if the value of the number is 5, this class will represent the constant expression {@code 5}.</p>
 */
class Number extends Expression {

    /**
     * The constant numerical value of this expression.
     */
    private final int value;

    /**
     * Constructs a {@code Number} object with the given integer value.
     *
     * @param value the integer value to be represented
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the number.
     *
     * @return the string representation of the number
     */
    @Override
    public String print() {
        return Integer.toString(value);
    }

    /**
     * Calculates the derivative of a constant number, which is always zero.
     *
     * @param var the variable with respect to which the derivative is taken (not relevant for constants)
     * @return a new {@link Number} object representing the value 0
     */
    @Override
    public Expression derivative(String var) {
        return new Number(0); // производная константы всегда равна 0
    }

    /**
     * Evaluates the number, which simply returns the constant value.
     *
     * @param variables a string representing variable assignments (ignored for constants)
     * @return the constant numerical value
     */
    @Override
    public int eval(String variables) {
        return value; // просто возвращаем значение числа
    }
}
