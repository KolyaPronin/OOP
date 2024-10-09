package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;


/**
 * Unit tests for the Blackjack game.
 */
class MainTest {

    private Deck deck;
    private Player player;
    private Dealer dealer;

    /**
     * Sets up the necessary objects before each test.
     */
    @BeforeEach
    void setUp() {
        deck = new Deck();
        player = new Player();  // Creates a new Player instance
        dealer = new Dealer();  // Creates a new Dealer instance
    }

    /**
     * Tests the creation of a Card object.
     */
    @Test
    void testCardCreation() {
        Card card = new Card("Пики", "Туз");
        assertEquals("Пики", card.suit);
        assertEquals("Туз", card.value);
    }

    /**
     * Tests the toString() method of a Card object.
     */
    @Test
    void testCardToString() {
        Card card = new Card("Пики", "Туз");
        assertEquals("Пики Туз (1/11)", card.toString());
    }

    /**
     * Tests the creation of a full Deck.
     */
    @Test
    void testDeckCreation() {
        assertEquals(52, deck.cards.size()); // Checks if the deck has 52 cards
    }

    /**
     * Tests drawing a card from the Deck.
     */
    @Test
    void testDrawCard() {
        Card card = deck.drawCard();
        assertNotNull(card);
        assertEquals(51, deck.cards.size());
    }

    /**
     * Tests adding a card to the Player's hand.
     */
    @Test
    void testAddCardToHand() {
        Card card = new Card("Пики", "10");
        player.addCardToHand(card);  // Uses the Player instance
        assertEquals(1, player.hand.size());
    }

    /**
     * Tests calculating the value of the Player's hand.
     */
    @Test
    void testGetHandValue() {
        player.addCardToHand(new Card("Пики", "10"));
        player.addCardToHand(new Card("Червы", "Валет"));
        assertEquals(20, player.getHandValue());
    }

    /**
     * Tests if the Player has exceeded 21 points.
     */
    @Test
    void testHasBusted() {
        player.addCardToHand(new Card("Пики", "10"));
        player.addCardToHand(new Card("Червы", "10"));
        player.addCardToHand(new Card("Бубны", "2"));
        assertTrue(player.hasBusted()); // Checks if the player has busted
    }

    /**
     * Tests adding a card to the Dealer's hand.
     */
    @Test
    void testDealerAddCardToHand() {
        Card card = new Card("Трефы", "Туз");
        dealer.addCardToHand(card);  // Uses the Dealer instance
        assertEquals(1, dealer.hand.size());
    }

    /**
     * Tests calculating the value of the Dealer's hand.
     */
    @Test
    void testDealerGetHandValue() {
        dealer.addCardToHand(new Card("Червы", "Туз"));
        dealer.addCardToHand(new Card("Бубны", "9"));
        assertEquals(20, dealer.getHandValue());
    }

    /**
     * Test for the case when the deck is empty.
     */
    @Test
    void testDrawCardWhenDeckIsEmpty() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard(); // Вытаскиваем все карты
        }                             // Проверяем, что при попытке вытянуть
        assertNull(deck.drawCard());  // карту из пустой колоды возвращается null
    }

    /**
     * Test in case the dealer takes several cards.
     */
    @Test
    void testDealerAceValueSwitch() {
        dealer.addCardToHand(new Card("Пики", "Туз"));
        dealer.addCardToHand(new Card("Червы", "10"));
        dealer.addCardToHand(new Card("Бубны", "5"));
        assertEquals(16, dealer.getHandValue());
    }

    /**
     * And logic for changing the value of the ace.
     */
    @Test
    void testDealerDrawUntil17() {
        dealer.addCardToHand(new Card("Пики", "7"));
        dealer.addCardToHand(new Card("Червы", "9"));
        assertEquals(16, dealer.getHandValue()); // Проверяем начальное значение

        dealer.addCardToHand(new Card("Бубны", "2"));
        assertEquals(18, dealer.getHandValue());
    }

    /**
     * Test for the case when there is an ace in the hand, and its value changes from 11 to 1.
     */
    @Test
    void testAceValueSwitch() {
        player.addCardToHand(new Card("Пики", "Туз"));
        player.addCardToHand(new Card("Червы", "10"));
        player.addCardToHand(new Card("Бубны", "2"));
        assertEquals(13, player.getHandValue());
    }

    /**
     * Tests that check that all cards are used.
     */
    @Test
    void testCardToStringFaceCards() {
        Card jack = new Card("Пики", "Валет");
        assertEquals("Пики Валет (10)", jack.toString());

        Card queen = new Card("Червы", "Дама");
        assertEquals("Червы Дама (10)", queen.toString());

        Card king = new Card("Трефы", "Король");
        assertEquals("Трефы Король (10)", king.toString());
    }

    /**
     * Tests that check that all cards are used.
     */
    @Test
    void testCardToStringAce() {
        Card ace = new Card("Бубны", "Туз");
        assertEquals("Бубны Туз (1/11)", ace.toString());
    }

    /**
     * Test case when player hand value exactly equals 21.
     */
    @Test
    void testPlayerExactly21() {
        player.addCardToHand(new Card("Пики", "10"));
        player.addCardToHand(new Card("Червы", "Туз"));
        assertEquals(21, player.getHandValue());  // Проверяем, что значение руки игрока равно 21
    }

    /**
     * Test case when player has multiple Aces and the total value should stay under 21.
     */
    @Test
    void testMultipleAcesInHand() {
        player.addCardToHand(new Card("Пики", "Туз"));
        player.addCardToHand(new Card("Червы", "Туз"));
        player.addCardToHand(new Card("Бубны", "9"));
        assertEquals(21, player.getHandValue());  // Проверяем, что правильное значение для двух тузов и 9
    }

    /**
     * Test case when dealer has exactly 17 points and stops.
     */
    @Test
    void testDealerStopsAt17() {
        dealer.addCardToHand(new Card("Пики", "10"));
        dealer.addCardToHand(new Card("Червы", "7"));
        assertEquals(17, dealer.getHandValue());  // Проверяем, что дилер остановится при 17
    }

    /**
     * Test case when dealer busts (over 21).
     */
    @Test
    void testDealerBust() {
        dealer.addCardToHand(new Card("Пики", "10"));
        dealer.addCardToHand(new Card("Червы", "10"));
        dealer.addCardToHand(new Card("Бубны", "5"));
        assertTrue(dealer.getHandValue() > 21);  // Проверяем, что дилер перебирает
    }

    /**
     * Test case when dealer draws until they reach 17 or more.
     */
    @Test
    void testDealerDrawsUntil17() {
        dealer.addCardToHand(new Card("Пики", "7"));
        dealer.addCardToHand(new Card("Червы", "6"));
        assertEquals(13, dealer.getHandValue());  // Проверяем начальную сумму

        dealer.addCardToHand(new Card("Бубны", "4"));
        assertEquals(17, dealer.getHandValue());  // Проверяем сумму после добора
    }

    /**
     * Checks that no card is repeated in the given deck of cards.
     *
     * @param cards the list of cards to check for duplicates
     */
    private static void notRepeatInDeck(List<Card> cards) {
        for (Card card : cards) {
            long count = cards.stream().filter(card::equals).count();
            assertEquals(1, count);
        }
    }

    /**
     * Tests the initialization of the deck to ensure it is not null and contains 52 unique cards.
     */
    @Test
    void deckInitializationTest() {
        Deck deck = new Deck();
        assertNotNull(deck);
        notRepeatInDeck(deck.cards);
        assertEquals(52, deck.cards.size()); // Check if there are 52 cards
    }

    /**
     * Tests the value of the dealer's hand to ensure it calculates correctly to 21 with an Ace and a Ten.
     */
    @Test
    void dealerHandValueTest() {
        Dealer dealer = new Dealer();
        dealer.addCardToHand(new Card("Пики", "Туз")); // Ace
        dealer.addCardToHand(new Card("Черви", "10")); // Ten
        assertEquals(21, dealer.getHandValue()); // Dealer's hand value should be 21
    }

    /**
     * Tests the value of the player's hand with an Ace and an Eight to ensure it calculates correctly to 19.
     */
    @Test
    void playerHandValueWithAcesTest() {
        Player player = new Player();
        player.addCardToHand(new Card("Трефы", "Туз")); // Ace
        player.addCardToHand(new Card("Бубны", "8"));  // Eight
        assertEquals(19, player.getHandValue()); // Player's hand value should be 19
    }

    /**
     * Tests whether the player has busted (exceeded a hand value of 21).
     */
    @Test
    void playerBustedTest() {
        Player player = new Player();
        player.addCardToHand(new Card("Черви", "10")); // Ten
        player.addCardToHand(new Card("Пики", "8"));   // Eight
        player.addCardToHand(new Card("Бубны", "4"));  // Four
        assertTrue(player.hasBusted()); // Player should have busted
    }

    /**
     * Tests the dealer's drawing mechanism, ensuring that the dealer stands at 17 or higher.
     */
    @Test
    void dealerDrawsTest() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        dealer.addCardToHand(new Card("Пики", "10"));
        dealer.addCardToHand(new Card("Черви", "6"));
        assertEquals(16, dealer.getHandValue());

        // Simulate dealer drawing cards
        dealer.addCardToHand(deck.drawCard()); // Simulate dealer drawing another card
        assertTrue(dealer.getHandValue() >= 17); // Dealer should stand at 17 or higher
    }

    /**
     * Tests player input handling by simulating user actions during the game.
     */
    @Test
    void playerInputTest() {
        String simulatedInput = "1\n0\n1\n0\n0\n"; // Simulate input for player actions
        InputStream originalIn = System.in;  // Save the original System.in

        try {
            // Replace System.in with simulated input
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Main.main(new String[0]); // Run the main method to simulate the game
            // Here you could check the player state or scores if needed
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }

    /**
     * Tests a full game simulation by simulating a predefined series of player actions.
     */
    @Test
    void fullGameTest() {
        String simulatedInput = "1\n1\n0\n0\n"; // Simulate full game input
        InputStream originalIn = System.in;

        try {
            // Replace System.in with simulated input
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Main.main(new String[0]); // Run the main method to simulate the game
            // Check final scores or other game outcomes if needed
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }
}
