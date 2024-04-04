package junit.modeltest;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Card;
import model.Deck;

/**
 * The test class DeckTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class DeckTest {
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
        // Add some cards to the deck for testing
        deck.add(new Card.Builder(false, false).withMoves(new int[]{1, 2}).build());
        deck.add(new Card.Builder(false, false).withMoves(new int[]{3}).build());
        deck.add(new Card.Builder(true, true).withMoves(new int[]{1}).build());
        deck.add(new Card.Builder(true, true).withMoves(new int[]{2}).build());
    }

    // @Test
    // public void testDeckSize() {
    //     assertEquals(4, deck.getDeckSize());
    // }

    @Test
    public void testShuffle() {
        // Copy the deck before shuffling
        Deck originalDeck = new Deck();
        for (int i = 0; i < deck.getSize(); i++) {
            originalDeck.add(deck.takeCard());
        }

        // Shuffle the deck
        deck.shuffle();

        // Check that the deck is not equal to the original one
        assertNotEquals(originalDeck, deck);
    }

    @Test
    public void testTakeCard() {
        int initialSize = deck.getSize();
        Card card = deck.takeCard();
        assertEquals(initialSize - 1, deck.getSize());
        assertNotNull(card);
    }

    @Test
    public void testAddCard() {
        int initialSize = deck.getSize();
        deck.add(new Card.Builder(false, false).withMoves(new int[]{4}).build());
        assertEquals(initialSize + 1, deck.getSize());
    }
}
