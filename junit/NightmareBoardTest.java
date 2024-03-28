package junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.NightmareBoard;

/**
 * The test class NightmareBoardTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NightmareBoardTest {


    private NightmareBoard nightmareBoard;

    @Before
    public void setUp() {
        nightmareBoard = new NightmareBoard();
    }

    @Test
    public void testEmptyBoard() {
        nightmareBoard.emptyBoard();
        for (int i = 0; i < 10; i++) {
            assertFalse(nightmareBoard.occupied(i));
        }
    }

    @Test
    public void testOccupied() {
        assertTrue(nightmareBoard.occupied(0));
        for (int i = 1; i < 10; i++) {
            assertFalse(nightmareBoard.occupied(i));
        }
    }

    @Test
    public void testGetIndex() {
        int expectedIndex = 0;
        int actualIndex = nightmareBoard.getIndex();
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testAdvance() {
        int n = 3;
        nightmareBoard.advance(n);
        for (int i = 0; i < 10; i++) {
            if (i == n % 10) {
                assertTrue(nightmareBoard.occupied(i));
            } else {
                assertFalse(nightmareBoard.occupied(i));
            }
        }
    }

    @Test
    public void testTraveledSpaces() {
        int n = 4;
        int[] expectedSpaces = { 0, 1, 2, 3 };
        int[] actualSpaces = nightmareBoard.traveledSpaces(n);
        assertArrayEquals(expectedSpaces, actualSpaces);
    }

    @Test
    public void testJump() {
        int n = 5;
        nightmareBoard.jump(n);
        for (int i = 0; i < 10; i++) {
            if (i == n % 10) {
                assertTrue(nightmareBoard.occupied(i));
            } else {
                assertFalse(nightmareBoard.occupied(i));
            }
        }
    }
}
