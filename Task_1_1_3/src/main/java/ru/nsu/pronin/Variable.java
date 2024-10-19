package ru.nsu.pronin;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Variable} class represents a variable in an expression. It supports methods to print the variable name,
 * calculate its derivative with respect to a given variable, and evaluate its value from a string of variable assignments.
 *
 * <p>This class extends the {@link Expression} class, and overrides its methods to provide functionality for handling variables.</p>
 *
 * <p>For example, if the variable is {@code x}, the class will be able to return its value from a set of variable assignments
 * such as {@code "x=10; y=5"}.</p>
 */
class Variable extends Expression {

    /**
     * The name of the variable (e.g., "x").
     */
    private final String name;

    /**
     * Constructs a {@code Variable} object with the specified name.
     *
     * @param name the name of the variable
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the variable as a string.
     *
     * @return the name of the variable
     */
    @Override
    public String print() {
        return name;
    }

    /**
     * Calculates the derivative of the variable with respect to the given variable.
     *
     * <p>If the variable name matches the given variable, the derivative is {@code 1}.
     * Otherwise, the derivative is {@code 0}.</p>
     *
     * @param var the variable with respect to which the derivative is taken
     * @return a {@link Number} object representing the derivative (either 1 or 0)
     */
    @Override
    public Expression derivative(String var) {
        if (name.equals(var)) {
            return new Number(1); // производная по самой себе = 1
        } else {
            return new Number(0); // производная по другой переменной = 0
        }
    }

    /**
     * Evaluates the variable by extracting its value from a given string of variable assignments.
     *
     * <p>The input string should be in the format "x=10; y=5", where the variable names are separated by semicolons,
     * and the values are assigned using the "=" sign. The method will throw a {@link RuntimeException} if the variable
     * is not found in the input string.</p>
     *
     * @param variables a string representing the variable assignments (e.g., "x=10; y=5")
     * @return the value of the variable
     * @throws RuntimeException if the variable is not found in the input string
     */
    @Override
    public int eval(String variables) {
        // Парсинг строки переменных вида "x=10; y=5"
        Map<String, Integer> variableMap = parseVariables(variables);

        // Если переменная есть в карте значений, возвращаем её значение
        if (variableMap.containsKey(name)) {
            return variableMap.get(name);
        } else {
            throw new RuntimeException("Не найдено значение для переменной: " + name);
        }
    }

    /**
     * Parses a string of variable assignments and returns a map of variable names and their corresponding values.
     *
     * <p>The input string should be in the format "x=10; y=5". If the string is not properly formatted,
     * a {@link RuntimeException} will be thrown.</p>
     *
     * @param variables a string representing the variable assignments
     * @return a map containing the variable names as keys and their values as integers
     * @throws RuntimeException if the input string is not properly formatted
     */
    private Map<String, Integer> parseVariables(String variables) {
        Map<String, Integer> map = new HashMap<>();

        // Разделяем строку по символу ";"
        String[] pairs = variables.split(";");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String variableName = keyValue[0].trim(); // Имя переменной
                int variableValue = Integer.parseInt(keyValue[1].trim()); // Значение переменной
                map.put(variableName, variableValue);
            } else {
                throw new RuntimeException("Неправильный формат переменных: " + pair);
            }
        }
        return map;
    }
}
