package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


}
