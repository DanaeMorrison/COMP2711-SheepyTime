

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class NightmareBoardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NightmareBoardTest
{
    /**
     * Default constructor for test class NightmareBoardTest
     */
    public NightmareBoardTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    /*public void setUp()
    {
    }*/

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    
    private NightmareBoard nightmareBoard;

    @BeforeEach
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
        int[] expectedSpaces = {0, 1, 2, 3};
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

