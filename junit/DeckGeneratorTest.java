package junit;

import org.junit.Test;
import org.junit.Before;

import model.Card;
import model.Deck;
import model.DeckGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

//TODO: deck.size() do not exist

public class DeckGeneratorTest {
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testMakeDeckWithNightmare1() {
        DeckGenerator deckGenerator = new DeckGenerator(deck, 2, 1);
        deckGenerator.makeDeck();

        
        //assertEquals(40, deck.size());

        // Verify the properties of specific cards in the deck
        Card card1 = findCardWithMoves(new int[] { 1 });
        assertNotNull(card1);
        assertEquals(2, card1.getZtokens());
        assertEquals(0, card1.getWinks());

        Card card2 = findCardWithMoves(new int[] { 2, 3 });
        assertNotNull(card2);
        assertEquals(1, card2.getZtokens());
        assertEquals(0, card2.getWinks());

        Card card3 = findCardWithMoves(new int[] { 4 });
        assertNotNull(card3);
        assertEquals(1, card3.getZtokens());
        assertEquals(1, card3.getWinks());

        Card card4 = findCardWithMoves(new int[] { 5 });
        assertNotNull(card4);
        assertEquals(0, card4.getZtokens());
        assertEquals(1, card4.getWinks());

        Card card5 = findCardWithMoves(new int[] { 6 });
        assertNotNull(card5);
        assertEquals(0, card5.getZtokens());
        assertEquals(2, card5.getWinks());
    }

    @Test
    public void testMakeDeckWithNightmare2() {
        DeckGenerator deckGenerator = new DeckGenerator(deck, 2, 2);
        deckGenerator.makeDeck();

        //assertEquals(40, deck.size());

        // Verify the properties of specific cards in the deck
        Card card1 = findCardWithMoves(new int[] { 1, 2 });
        assertNotNull(card1);
        assertEquals(0, card1.getZtokens());
        assertEquals(0, card1.getWinks());

        Card card2 = findCardWithMoves(new int[] { 3, 4 });
        assertNotNull(card2);
        assertEquals(1, card2.getZtokens());
        assertEquals(0, card2.getWinks());

        Card card3 = findCardWithMoves(new int[] { 5, 6 });
        assertNotNull(card3);
        assertEquals(0, card3.getZtokens());
        assertEquals(1, card3.getWinks());

        Card card4 = findCardWithMoves(new int[] { 7 });
        assertNotNull(card4);
        assertEquals(0, card4.getZtokens());
        assertEquals(2, card4.getWinks());
    }

    @Test
    public void testMakeDeckWithNightmare3() {
        DeckGenerator deckGenerator = new DeckGenerator(deck, 2, 3);
        deckGenerator.makeDeck();

        //assertEquals(40, deck.size());

        // Verify the properties of specific cards in the deck
        Card card1 = findCardWithMoves(new int[] { 1, 2, 3 });
        assertNotNull(card1);
        assertEquals(0, card1.getZtokens());
        assertEquals(0, card1.getWinks());

        Card card2 = findCardWithMoves(new int[] { 4, 5, 6 });
        assertNotNull(card2);
        assertEquals(0, card2.getZtokens());
        assertEquals(1, card2.getWinks());

        Card card3 = findCardWithMoves(new int[] { 7, 8 });
        assertNotNull(card3);
        assertEquals(1, card3.getZtokens());
        assertEquals(1, card3.getWinks());
    }

    private Card findCardWithMoves(int[] moves) {
        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            if (Arrays.equals(card.getMoves(), moves)) {
                return card;
            }
        }
        return null;
    }
}