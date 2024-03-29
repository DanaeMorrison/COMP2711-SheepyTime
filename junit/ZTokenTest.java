package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.ZToken;

/**
 * The test class ZTokenTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ZTokenTest {

    private ZToken zToken;
    private Player owner;

    @Before
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
