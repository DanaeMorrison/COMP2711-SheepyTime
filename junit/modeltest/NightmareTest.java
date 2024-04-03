package junit.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Nightmare;

/**
 * The test class NightmareTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
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
