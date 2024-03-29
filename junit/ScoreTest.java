package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Score;

/**
 * The test class ScoreTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ScoreTest {
    /**
     * Default constructor for test class ScoreTest
     */
    private Score score;
    private Player player;

    @Before
    public void setUp() {
        player = new Player("Test Player", 1);
        score = new Score(player);
    }

    @Test
    public void testWon_PlayerPosGreaterThanPillowPos_ReturnsTrue() {
        score.setPlayerPos(10);
        score.setPillowPos(5);
        assertTrue(score.won());
    }

    @Test
    public void testWon_PlayerPosEqualToPillowPos_ReturnsTrue() {
        score.setPlayerPos(5);
        score.setPillowPos(5);
        assertTrue(score.won());
    }

    @Test
    public void testWon_PlayerPosLessThanPillowPos_ReturnsFalse() {
        score.setPlayerPos(3);
        score.setPillowPos(8);
        assertFalse(score.won());
    }

    @Test
    public void testSetPlayerPos() {
        score.setPlayerPos(7);
        assertEquals(7, score.getPlayerPos());
    }

    @Test
    public void testSetPillowPos() {
        score.setPillowPos(3);
        assertEquals(3, score.getPillowPos());
    }

    @Test
    public void testSetPillowMod() {
        score.setPillowMod(2);
        assertEquals(2, score.getPillowMod());
    }

    @Test
    public void testGetPlayerPos() {
        score.setPlayerPos(10);
        int playerPos = score.getPlayerPos();
        assertEquals(10, playerPos);
    }

    @Test
    public void testGetPillowPos() {
        score.setPillowPos(5);
        int pillowPos = score.getPillowPos();
        assertEquals(5, pillowPos);
    }

    @Test
    public void testGetPillowMod() {
        score.setPillowMod(-1);
        int pillowMod = score.getPillowMod();
        assertEquals(-1, pillowMod);
    }
}