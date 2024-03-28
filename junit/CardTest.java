package junit;

import org.junit.Test;

import model.Card;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
public class CardTest {

    @Test
    public void testCardBuilder() {
        int[] moves = {1, 2, 3};
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
        assertEquals(jumpPos, card.getJumpPos());
        assertEquals(spiderMove, card.getSpiderMove());
    }

    @Test
    public void testCardSettersAndGetters() {
        int[] moves = {1, 2, 3};
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

        int[] newMoves = {4, 5};
        int newZTokens = 10;
        int newWinks = 3;
        int newJumpPos = 6;
        int newSpiderMove = 4;

        card.setMoves(newMoves);
        card.setZtokens(newZTokens);
        card.setWinks(newWinks);
        card.setJumpPos(newJumpPos);
        card.setSpiderMove(newSpiderMove);

        assertEquals(true, card.bothConditions());
        assertEquals(false, card.isNightmare());
        assertArrayEquals(newMoves, card.getMoves());
        assertEquals(newZTokens, card.getZtokens());
        assertEquals(newWinks, card.getWinks());
        assertEquals(newJumpPos, card.getJumpPos());
        assertEquals(newSpiderMove, card.getSpiderMove());
    }
}