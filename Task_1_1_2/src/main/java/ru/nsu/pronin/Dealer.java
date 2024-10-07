package ru.nsu.pronin;

import java.util.List;
import java.util.ArrayList;

/**
 * Класс Dealer представляет дилера в игре Блэкджек.
 * Дилер управляет своими картами, суммой очков и отображением карт на руках.
 */
public class Dealer {

    /**
     * Список карт на руках у дилера.
     */
    final List<Card> hand;

    /**
     * Конструктор для создания дилера с пустыми руками.
     */
    public Dealer() {
        hand = new ArrayList<>();
    }

    /**
     * Добавляет карту на руки дилера.
     *
     * @param card карта, которую нужно добавить на руки дилера.
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * Получает сумму очков на руках дилера.
     * Туз считается сначала как 11, но если общая сумма превышает 21, его значение
     * может быть изменено на 1.
     *
     * @return сумма очков на руках дилера.
     */
    public int getHandValue() {
        int handValue = 0;
        int numAces = 0;

        // Проходим по картам на руках дилера
        for (Card card : hand) {
            if (card.getValue().equals("Туз")) {
                numAces++;
                handValue += 11; // Изначально добавляем туз как 11
            } else if (card.getValue().equals("Валет") || card.getValue().equals("Дама")
                    || card.getValue().equals("Король")) {
                handValue += 10; // Для картинок (Валет, Дама, Король) добавляем 10 очков
            } else {
                handValue += Integer.parseInt(card.getValue()); // Для числовых карт добавляем их значение
            }
        }

        // Обрабатываем тузов, если общая сумма больше 21
        while (handValue > 21 && numAces > 0) {
            handValue -= 10; // Превращаем один туз из 11 в 1, если сумма больше 21
            numAces--;
        }

        return handValue;
    }

    /**
     * Отображает карты на руках дилера. Если showAllCards равно true, показываются
     * все карты, если false, одна карта скрыта.
     *
     * @param showAllCards флаг, указывающий, следует ли показать все карты на руках дилера.
     */
    public void displayHand(boolean showAllCards) {
        System.out.print("\tКарты дилера: ");
        if (showAllCards) {
            for (Card card : hand) {
                System.out.print("[" + card + "] ");
            }
            System.out.println("=> " + getHandValue());
        } else {
            System.out.print("[" + hand.get(0) + ", <закрытая карта]\n\n");
        }
    }
}
