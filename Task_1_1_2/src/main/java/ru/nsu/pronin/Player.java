package ru.nsu.pronin;

import java.util.List;
import java.util.ArrayList;

/**
 * Класс Player представляет игрока в игре Блэкджек.
 * Игрок управляет своими картами, суммой очков и отображением карт на руках.
 */
public class Player {

    /**
     * Список карт на руках у игрока.
     */
    final List<Card> hand;

    /**
     * Конструктор для создания игрока с пустыми руками.
     */
    public Player() {
        hand = new ArrayList<>();
    }

    /**
     * Добавляет карту на руки игрока.
     *
     * @param card карта, которую нужно добавить на руки игрока.
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * Получает сумму очков на руках игрока.
     * Туз считается сначала как 11, но если общая сумма превышает 21, его значение
     * может быть изменено на 1.
     *
     * @return сумма очков на руках игрока.
     */
    public int getHandValue() {
        int handValue = 0;
        int numAces = 0;

        // Проходим по картам на руках игрока
        for (Card card : hand) {
            if (card.getValue().equals("Туз")) {
                numAces++;
                handValue += 11; // Изначально считаем Туз как 11
            } else if (card.getValue().equals("Валет") || card.getValue().equals("Дама")
                    || card.getValue().equals("Король")) {
                handValue += 10; // Для картинок (Валет, Дама, Король) добавляем 10 очков
            } else {
                handValue += Integer.parseInt(card.getValue()); // Для числовых карт добавляем их значение
            }
        }

        // Обработка тузов (у туза может быть значение 1 или 11)
        while (handValue > 21 && numAces > 0) {
            handValue -= 10; // Превращаем Туз из 11 в 1, если сумма больше 21
            numAces--;
        }

        return handValue;
    }

    /**
     * Отображает карты на руках игрока и их сумму.
     */
    public void displayHand() {
        System.out.print("\tВаши карты: ");
        for (Card card : hand) {
            System.out.print("[" + card + "] ");
        }
        System.out.println("=> " + getHandValue());
    }

    /**
     * Проверяет, превысил ли игрок 21 очко.
     *
     * @return true, если сумма очков больше 21, иначе false.
     */
    public boolean hasBusted() {
        return getHandValue() > 21;
    }
}
