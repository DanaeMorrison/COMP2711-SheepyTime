package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.PlayerBoard;

//TODO: Test Failed

/**
 * The test class PlayerBoardTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class PlayerBoardTest {

    @Test
    public void testEmptyBoard() {
        // Create a PlayerBoard instance
        PlayerBoard playerBoard = new PlayerBoard();

        // Test the emptyBoard method
        playerBoard.emptyBoard();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                assertTrue(playerBoard.occupied(i));
            } else {
                assertFalse(playerBoard.occupied(i));
            }
        }
    }

    @Test
    public void testOccupied() {
        // Create a PlayerBoard instance
        PlayerBoard playerBoard = new PlayerBoard();

        // Test the occupied method
        assertTrue(playerBoard.occupied(0));
        assertFalse(playerBoard.occupied(1));
    }

    @Test
    public void testGetIndex() {
        // Create a PlayerBoard instance
        PlayerBoard playerBoard = new PlayerBoard();

        // Test the getIndex method
        int index = playerBoard.getIndex();
        assertEquals(0, index);
    }

    @Test
    public void testAdvance() {
        // Create a PlayerBoard instance
        PlayerBoard playerBoard = new PlayerBoard();

        // Test the advance method
        playerBoard.advance(3);
        assertFalse(playerBoard.occupied(0));
        assertTrue(playerBoard.occupied(3));
    }

    @Test
    public void testJump() {
        // Create a PlayerBoard instance
        PlayerBoard playerBoard = new PlayerBoard();

        // Test the jump method
        playerBoard.jump(5);
        assertFalse(playerBoard.occupied(0));
        assertTrue(playerBoard.occupied(5));
        for (int i = 1; i < 10; i++) {
            if (i != 5) {
                assertFalse(playerBoard.occupied(i));
            }
        }
    }

    @Test
    public void testIsCrossing() {
        // Create a PlayerBoard instance
        PlayerBoard playerBoard = new PlayerBoard();

        // Test the isCrossing method
        assertFalse(playerBoard.isCrossing(5));
        assertTrue(playerBoard.isCrossing(10));
        assertTrue(playerBoard.isCrossing(15));
    }
}