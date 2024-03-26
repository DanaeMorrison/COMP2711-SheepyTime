package junit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Deck;
import model.DeckGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class DeckGeneratorTest {
    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testMakeDeckWithNightmare1() {
        DeckGenerator deckGenerator = new DeckGenerator(deck, 2, 1);
        deckGenerator.makeDeck();

        Assertions.assertEquals(40, deck.size());

        // Verify the properties of specific cards in the deck
        Card card1 = findCardWithMoves(new int[]{1});
        Assertions.assertNotNull(card1);
        Assertions.assertEquals(2, card1.getZtokens());
        Assertions.assertEquals(0, card1.getWinks());

        Card card2 = findCardWithMoves(new int[]{2, 3});
        Assertions.assertNotNull(card2);
        Assertions.assertEquals(1, card2.getZtokens());
        Assertions.assertEquals(0, card2.getWinks());

        Card card3 = findCardWithMoves(new int[]{4});
        Assertions.assertNotNull(card3);
        Assertions.assertEquals(1, card3.getZtokens());
        Assertions.assertEquals(1, card3.getWinks());

        Card card4 = findCardWithMoves(new int[]{5});
        Assertions.assertNotNull(card4);
        Assertions.assertEquals(0, card4.getZtokens());
        Assertions.assertEquals(1, card4.getWinks());

        Card card5 = findCardWithMoves(new int[]{6});
        Assertions.assertNotNull(card5);
        Assertions.assertEquals(0, card5.getZtokens());
        Assertions.assertEquals(2, card5.getWinks());
    }

    @Test
    public void testMakeDeckWithNightmare2() {
        DeckGenerator deckGenerator = new DeckGenerator(deck, 2, 2);
        deckGenerator.makeDeck();

        Assertions.assertEquals(40, deck.size());

        // Verify the properties of specific cards in the deck
        Card card1 = findCardWithMoves(new int[]{1, 2});
        Assertions.assertNotNull(card1);
        Assertions.assertEquals(0, card1.getZtokens());
        Assertions.assertEquals(0, card1.getWinks());

        Card card2 = findCardWithMoves(new int[]{3, 4});
        Assertions.assertNotNull(card2);
        Assertions.assertEquals(1, card2.getZtokens());
        Assertions.assertEquals(0, card2.getWinks());

        Card card3 = findCardWithMoves(new int[]{5, 6});
        Assertions.assertNotNull(card3);
        Assertions.assertEquals(0, card3.getZtokens());
        Assertions.assertEquals(1, card3.getWinks());

        Card card4 = findCardWithMoves(new int[]{7});
        Assertions.assertNotNull(card4);
        Assertions.assertEquals(0, card4.getZtokens());
        Assertions.assertEquals(2, card4.getWinks());
    }

    @Test
    public void testMakeDeckWithNightmare3() {
        DeckGenerator deckGenerator = new DeckGenerator(deck, 2, 3);
        deckGenerator.makeDeck();

        Assertions.assertEquals(40, deck.size());

        // Verify the properties of specific cards in the deck
        Card card1 = findCardWithMoves(new int[]{1, 2, 3});
        Assertions.assertNotNull(card1);
        Assertions.assertEquals(0, card1.getZtokens());
        Assertions.assertEquals(0, card1.getWinks());

        Card card2 = findCardWithMoves(new int[]{4, 5, 6});
        Assertions.assertNotNull(card2);
        Assertions.assertEquals(0, card2.getZtokens());
        Assertions.assertEquals(1, card2.getWinks());

        Card card3 = findCardWithMoves(new int[]{7, 8});
        Assertions.assertNotNull(card3);
        Assertions.assertEquals(1, card3.getZtokens());
        Assertions.assertEquals(1, card3.getWinks());
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