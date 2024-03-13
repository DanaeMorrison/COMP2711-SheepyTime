

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ZTokenTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ZTokenTest
{
    /**
     * Default constructor for test class ZTokenTest
     */
    public ZTokenTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    /*@BeforeEach
    public void setUp()
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

    private ZToken zToken;
    private Player owner;

    @BeforeEach
    public void setUp() {
        owner = new Player("Test Player", 1);
        zToken = new ZToken(true, owner);
    }

    @Test
    public void testGetOwner() {
        Player expectedOwner = owner;
        Player actualOwner = zToken.getOwner();
        assertEquals(expectedOwner, actualOwner);
    }

    @Test
    public void testIsInfinite() {
        assertTrue(zToken.isInfinite());
    }
}

