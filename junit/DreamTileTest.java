package junit;

import org.junit.Before;
import org.junit.Test;
//import org.junit.Before;
//import org.junit.Test;

import model.DreamTile;
import model.Player;
import model.ZToken;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * The test class DreamTileTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */

public class DreamTileTest {

    private DreamTile dreamTile;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        dreamTile = new DreamTile("Dream Tile");
        player1 = new Player("Player 1", 1);
        player2 = new Player("Player 2", 2);
    }

    @Test
    public void testGetTileName() {
        String tileName = dreamTile.getTileName();
        assertEquals("Tile name should be 'Dream Tile'", "Dream Tile", tileName);
    }

    @Test
    public void testCanUse_NoTokens() {
        assertFalse("Tile should not be usable by Player 1", dreamTile.canUse(player1));
    }

    @Test
    public void testCanUse_WithToken() {
        dreamTile.addToken(player1, true);
        assertTrue("Tile should be usable by Player 1", dreamTile.canUse(player1));
        assertFalse("Tile should not be usable by Player 2", dreamTile.canUse(player2));
    }

    @Test
    public void testAddToken() {
        ZToken token = new ZToken(true, player1);
        dreamTile.addToken(token);
        ArrayList<ZToken> tokens = dreamTile.getTokens();
        assertEquals("Token should be added to the Dream Tile", 1, tokens.size());
        assertSame("Added token should be the same as the retrieved token", token, tokens.get(0));
    }

    @Test
    public void testRemovePlayerAccess() {
        ZToken token1 = new ZToken(true, player1);
        ZToken token2 = new ZToken(false, player2);
        dreamTile.addToken(token1);
        dreamTile.addToken(token2);

        dreamTile.removePlayerAccess(player1);
        ArrayList<ZToken> tokens = dreamTile.getTokens();

        // TODO: should we add a emthod getTokens?

        assertEquals("Token should be removed from the Dream Tile", 1, tokens.size());
        assertSame("Remaining token should be the same as the retrieved token", token2, tokens.get(0));
    }
}