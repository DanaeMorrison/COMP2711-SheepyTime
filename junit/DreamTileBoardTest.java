package junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DreamTile;
import model.DreamTileBoard;

import static org.junit.jupiter.api.Assertions.*;

public class DreamTileBoardTest {

    private DreamTileBoard dreamTileBoard;

    @BeforeEach
    public void setUp() {
        dreamTileBoard = new DreamTileBoard();
    }

    @Test
    public void testGetTile() {
        DreamTile dreamTile = new DreamTile("Dream Tile");
        dreamTileBoard.addTile(0, dreamTile);

        DreamTile retrievedTile = dreamTileBoard.getTile(0);
        assertNotNull(retrievedTile, "Retrieved tile should not be null");
        assertEquals(dreamTile, retrievedTile, "Retrieved tile should be the same as the added tile");
    }

    @Test
    public void testOccupied() {
        assertFalse(dreamTileBoard.occupied(0), "Tile at index 0 should not be occupied");

        DreamTile dreamTile = new DreamTile("Dream Tile");
        dreamTileBoard.addTile(0, dreamTile);

        assertTrue(dreamTileBoard.occupied(0), "Tile at index 0 should be occupied");
    }

    @Test
    public void testEmptyBoard() {
        DreamTile dreamTile = new DreamTile("Dream Tile");
        dreamTileBoard.addTile(0, dreamTile);

        dreamTileBoard.emptyBoard();

        assertFalse(dreamTileBoard.occupied(0), "Tile at index 0 should not be occupied after emptying the board");
    }

    @Test
    public void testAddTile() {
        DreamTile dreamTile = new DreamTile("Dream Tile");

        boolean added = dreamTileBoard.addTile(0, dreamTile);
        assertTrue(added, "Tile should be added successfully");

        DreamTile retrievedTile = dreamTileBoard.getTile(0);
        assertNotNull(retrievedTile, "Retrieved tile should not be null");
        assertEquals(dreamTile, retrievedTile, "Retrieved tile should be the same as the added tile");

        added = dreamTileBoard.addTile(0, new DreamTile("Another Tile"));
        assertFalse(added, "Tile should not be added at an occupied index");
    }
}