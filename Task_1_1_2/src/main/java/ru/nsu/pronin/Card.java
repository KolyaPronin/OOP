package ru.nsu.pronin;

/**
 * Класс Card представляет собой карту с мастью и значением для игры в Блэкджек.
 */
public class Card {

    /**
     * Масть карты (Червы, Бубны, Пики, Трефы).
     */
    final String suit;

    /**
     * Значение карты (2, 3, ..., 10, Валет, Дама, Король, Туз).
     */
    final String value;

    /**
     * Конструктор для создания карты с указанными мастью и значением.
     *
     * @param suit  масть карты (например, Пики, Червы).
     * @param value значение карты (например, 2, 3, ..., 10, Валет, Дама, Король, Туз).
     */
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Метод для получения значения карты.
     *
     * @return значение карты (например, 2, 3, ..., Валет, Дама, Король, Туз).
     */
    public String getValue() {
        return value;
    }

    /**
     * Переопределение метода toString для отображения карты.
     * В зависимости от значения карты, возвращает строку в формате "Масть (значение)".
     *
     * @return строковое представление карты.
     */
    @Override
    public String toString() {
        String cardDisplayValue;

        // Для картинок добавляем числовое значение, для туза оба возможных значения.
        switch (value) {
            case "Валет":
            case "Дама":
            case "Король":
                cardDisplayValue = value + " (10)";
                break;
            case "Туз":
                cardDisplayValue = value + " (1/11)";
                break;
            default:
                cardDisplayValue = "(" + value + ")";
                break;
        }

        return suit + " " + cardDisplayValue;
    }
}
