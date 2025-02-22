package junit.modeltest;

import org.junit.Test;

import model.Card;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * The test class CardTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class CardTest {

    @Test
    public void testCardBuilder() {
        int[] moves = { 1, 2, 3 };
        int zTokens = 5;
        int winks = 2;
        int jumpPos = 4;
        int spiderMove = 3;

        Card card = new Card.Builder(true, false)
                .withMoves(moves)
                .withZtokens(zTokens)
                .withWinks(winks)
                .withJumpPos(jumpPos)
                .withSpiderMove(spiderMove)
                .build();

        assertEquals(true, card.bothConditions());
        assertEquals(false, card.isNightmare());
        assertArrayEquals(moves, card.getMoves());
        assertEquals(zTokens, card.getZtokens());
        assertEquals(winks, card.getWinks());
        assertEquals(jumpPos, card.getJumpMove());
        assertEquals(spiderMove, card.getSpiderMove());
    }

    @Test
    public void testCardSettersAndGetters() {
        int[] moves = { 1, 2, 3 };
        int zTokens = 5;
        int winks = 2;
        int jumpPos = 4;
        int spiderMove = 3;

        Card card = new Card.Builder(true, false)
                .withMoves(moves)
                .withZtokens(zTokens)
                .withWinks(winks)
                .withJumpPos(jumpPos)
                .withSpiderMove(spiderMove)
                .build();

        int[] newMoves = { 4, 5 };
        int newZTokens = 10;
        int newWinks = 3;
        int newJumpPos = 6;
        int newSpiderMove = 4;

        card.setMoves(newMoves);
        card.setZtokens(newZTokens);
        card.setWinks(newWinks);
        card.setJumpMove(newJumpPos);
        card.setSpiderMove(newSpiderMove);

        assertEquals(true, card.bothConditions());
        assertEquals(false, card.isNightmare());
        assertArrayEquals(newMoves, card.getMoves());
        assertEquals(newZTokens, card.getZtokens());
        assertEquals(newWinks, card.getWinks());
        assertEquals(newJumpPos, card.getJumpMove());
        assertEquals(newSpiderMove, card.getSpiderMove());
    }
}