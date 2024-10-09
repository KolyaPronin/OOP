package ru.nsu.pronin;

import java.util.Scanner;

/**
 * Main класс, запускающий игру в Блэкджек.
 * Пользователь может сыграть несколько раундов, играя против дилера.
 */
public class Main {
    /**
     * Главный метод программы, который запускает игру.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в Блэкджек!");

        int roundNumber = 1; // Номер раунда
        int playerWins = 0;  // Количество побед игрока
        int dealerWins = 0;  // Количество побед дилера

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Раунд " + roundNumber);
            System.out.println("Дилер раздал карты");

            // Создаем новые колоды, игрока и дилера
            Deck deck = new Deck();
            Player player = new Player();
            Dealer dealer = new Dealer();
            deck.shuffleDeck();                     // Перемешиваем колоду
            player.addCardToHand(deck.drawCard());  // Раздаем карты игроку и дилеру
            player.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());
            player.displayHand();                   // Отображаем карты игрока и закрытую карту дилера
            dealer.displayHand(false);

            // Ход игрока
            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("Введите '1', чтобы взять карту, и '0', чтобы остановиться.");
                int choice = scanner.nextInt();

                if (choice == 1) { // Игрок берет карту
                    player.addCardToHand(deck.drawCard());
                    player.displayHand();

                    // Проверяем на перебор
                    if (player.hasBusted()) {
                        System.out.println("Вы проиграли! Перебор очков.");
                        dealerWins++; // Дилер выигрывает
                        break;
                    }
                } else { // Игрок остановился
                    playerTurn = false;
                }
            }

            if (player.hasBusted()) {
                // Выводим счет и начинаем новый раунд
                System.out.println("Счет " + playerWins
                        + ":" + dealerWins + " в пользу дилера.");
                roundNumber++;
                continue;
            }

            // Ход дилера
            System.out.println("\nХод дилера");
            dealer.displayHand(true);

            while (dealer.getHandValue() < 17) {
                dealer.addCardToHand(deck.drawCard());
                dealer.displayHand(true);
            }

            if (dealer.getHandValue() > 21) {
                System.out.println("Дилер проиграл! Перебор очков.");
                playerWins++; // Игрок выигрывает
            } else {
                // Определяем, кто выиграл
                int playerScore = player.getHandValue();
                int dealerScore = dealer.getHandValue();

                System.out.println("Ваши очки: " + playerScore);
                System.out.println("Очки дилера: " + dealerScore);

                if (playerScore > dealerScore) {
                    System.out.println("Вы выиграли раунд!");
                    playerWins++; // Игрок выигрывает
                } else if (playerScore < dealerScore) {
                    System.out.println("Дилер выиграл раунд.");
                    dealerWins++; // Дилер выигрывает
                } else {
                    System.out.println("Ничья.");
                }
            }

            // Отображаем текущий счет
            if (playerWins > dealerWins) {
                System.out.println("Счет " + playerWins
                        + ":" + dealerWins + " в вашу пользу.");
            } else if (dealerWins > playerWins) {
                System.out.println("Счет " + playerWins
                        + ":" + dealerWins + " в пользу дилера.");
            } else {
                System.out.println("Счет " + playerWins
                        + ":" + dealerWins + ". Ничья.");
            }

            // Спрашиваем, хочет ли игрок продолжить
            System.out.println("\nХотите сыграть еще раз? (1 - да, 0 - нет)");
            int playAgain = scanner.nextInt();
            if (playAgain == 0) {
                gameOver = true;
            } else {
                roundNumber++; // Переходим к следующему раунду
            }
        }

        scanner.close();
        System.out.println("Спасибо за игру!");
    }
}
