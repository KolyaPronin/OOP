package ru.nsu.pronin;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс Deck представляет собой колоду карт для игры в Блэкджек.
 * Включает в себя методы для создания, перемешивания и вытаскивания карт.
 */
public class Deck {
    /**
     * Список карт в колоде.
     */
    final List<Card> cards;

    /**
     * Конструктор для создания новой колоды карт.
     * Инициализирует колоду и сразу перемешивает её.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck(); // Инициализация колоды карт
        shuffleDeck(); // Перемешивание колоды
    }

    /**
     * Инициализация колоды карт, создавая все 52 карты (4 масти и 13 значений).
     */
    private void initializeDeck() {
        String[] suits = {"Пики", "Червы", "Бубны", "Трефы"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
    }

    /**
     * Перемешивание колоды карт с помощью метода {@link Collections#shuffle(List)}.
     */
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    /**
     * Вытаскивание верхней карты из колоды.
     *
     * @return карта из колоды или {@code null}, если колода пуста.
     */
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Удаляем и возвращаем верхнюю карту
        } else {
            return null; // Возвращаем null, если колода пуста
        }
    }
}
