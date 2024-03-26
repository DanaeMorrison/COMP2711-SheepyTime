package junit;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Nightmare;

/**
 * The test class NightmareTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NightmareTest
{

    private Nightmare nightmare;

    @BeforeEach
    public void setUp() {
        nightmare = new Nightmare("Test Nightmare");
    }

    @Test
    public void testGetName() {
        //String expectedName = "Test Nightmare";
        //String actualName = nightmare.getName();
        //assertEquals(expectedName, actualName);
        Nightmare nightmare = new Nightmare("Test Nightmare");
        assertEquals("Test Nightmare", nightmare.getName());
    }

    @Test
    public void testIsNightmare() {
        Nightmare nightmare = new Nightmare("Test Nightmare");
        assertTrue(nightmare.isNightmare());
    }

    @Test
    public void testGetBoard() {
        Nightmare nightmare = new Nightmare("Test Nightmare");
        assertNotNull(nightmare.getBoard());
    }
    }

   