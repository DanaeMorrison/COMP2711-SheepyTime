package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Nightmare;

/**
 * The test class NightmareTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NightmareTest {

    private Nightmare nightmare;

    @Before
    public void setUp() {
        nightmare = new Nightmare("Test Nightmare", 1);
    }

    @Test
    public void testGetName() {
        // String expectedName = "Test Nightmare";
        // String actualName = nightmare.getName();
        // assertEquals(expectedName, actualName);
        assertEquals("Test Nightmare", nightmare.getName());
    }

    @Test
    public void testIsNightmare() {
        assertTrue(nightmare.isNightmare());
    }

    @Test
    public void testGetBoard() {
        assertNotNull(nightmare.getBoard());
    }
}
