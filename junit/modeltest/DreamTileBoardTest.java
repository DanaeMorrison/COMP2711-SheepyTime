package junit.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.DreamTile;
import model.DreamTileBoard;

/**
 * The test class DreamTileBoardTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class DreamTileBoardTest {

    private DreamTileBoard dreamTileBoard;

    @Before
    public void setUp() {
        dreamTileBoard = new DreamTileBoard();
    }

    @Test
    public void testGetTile() {
        DreamTile dreamTile = new DreamTile("Dream Tile");
        dreamTileBoard.addTile(0, dreamTile);

        DreamTile retrievedTile = dreamTileBoard.getTile(0);
        assertNotNull("Retrieved tile should not be null", retrievedTile);
        assertEquals("Retrieved tile should be the same as the added tile", dreamTile, retrievedTile);
    }

    @Test
    public void testOccupied() {
        assertFalse("Tile at index 0 should not be occupied", dreamTileBoard.occupied(0));

        DreamTile dreamTile = new DreamTile("Dream Tile");
        dreamTileBoard.addTile(0, dreamTile);

        assertTrue("Tile at index 0 should be occupied", dreamTileBoard.occupied(0));
    }

    @Test
    public void testEmptyBoard() {
        DreamTile dreamTile = new DreamTile("Dream Tile");
        dreamTileBoard.addTile(0, dreamTile);

        dreamTileBoard.emptyBoard();

        assertFalse("Tile at index 0 should not be occupied after emptying the board", dreamTileBoard.occupied(0));
    }

    @Test
    public void testAddTile() {
        DreamTile dreamTile = new DreamTile("Dream Tile");

        boolean added = dreamTileBoard.addTile(0, dreamTile);
        assertTrue("Tile should be added successfully", added);

        DreamTile retrievedTile = dreamTileBoard.getTile(0);
        assertNotNull("Retrieved tile should not be null", retrievedTile);
        assertEquals("Retrieved tile should be the same as the added tile", dreamTile, retrievedTile);

        added = dreamTileBoard.addTile(0, new DreamTile("Another Tile"));
        assertFalse("Tile should not be added at an occupied index", added);
    }
}