import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DreamTileTest {

    private DreamTile dreamTile;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        dreamTile = new DreamTile("Dream Tile");
        player1 = new Player("Player 1", 1);
        player2 = new Player("Player 2", 2);
    }

    @Test
    public void testGetTileName() {
        String tileName = dreamTile.getTileName();
        assertEquals("Dream Tile", tileName, "Tile name should be 'Dream Tile'");
    }

    @Test
    public void testCanUse_NoTokens() {
        assertFalse(dreamTile.canUse(player1), "Tile should not be usable by Player 1");
    }

    @Test
    public void testCanUse_WithToken() {
        dreamTile.addToken(player1, true);
        assertTrue(dreamTile.canUse(player1), "Tile should be usable by Player 1");
        assertFalse(dreamTile.canUse(player2), "Tile should not be usable by Player 2");
    }

    @Test
    public void testAddToken() {
        ZToken token = new ZToken(true, player1);
        dreamTile.addToken(token);
        ArrayList<ZToken> tokens = dreamTile.getTokens();
        assertEquals(1, tokens.size(), "Token should be added to the Dream Tile");
        assertSame(token, tokens.get(0), "Added token should be the same as the retrieved token");
    }

    @Test
    public void testRemovePlayerAccess() {
        ZToken token1 = new ZToken(true, player1);
        ZToken token2 = new ZToken(false, player2);
        dreamTile.addToken(token1);
        dreamTile.addToken(token2);

        dreamTile.removePlayerAccess(player1);
        ArrayList<ZToken> tokens = dreamTile.getTokens();
        assertEquals(1, tokens.size(), "Token should be removed from the Dream Tile");
        assertSame(token2, tokens.get(0), "Remaining token should be the same as the retrieved token");
    }
}