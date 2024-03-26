

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
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
    
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Test Player", 1);
    }

    @Test
    public void testGetOrderPosition() {
        int expectedOrderPosition = 1;
        int actualOrderPosition = player.getOrderPosition();
        assertEquals(expectedOrderPosition, actualOrderPosition);
    }

    @Test
    public void testGetScoreboard() {
        assertNotNull(player.getScoreboard());
    }

    @Test
    public void testGetHand() {
        assertNotNull(player.getHand());
        assertTrue(player.getHand().isEmpty());
    }

    /*@Test
    public void testSetHand() {
    ArrayList<Card> hand = new ArrayList<>();
    Card testCard = new Card();
    testCard.setName("Test Card");
    hand.add(testCard);
    player.setHand(hand);
    assertEquals(hand, player.getHand());

    }*/

    @Test
    public void testGetName() {
        String expectedName = "Test Player";
        String actualName = player.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetWinks() {
        int expectedWinks = 0;
        int actualWinks = player.getWinks();
        assertEquals(expectedWinks, actualWinks);
    }

    @Test
    public void testSetWinks() {
        int winks = 3;
        player.setWinks(winks);
        assertEquals(winks, player.getWinks());
    }

    @Test
    public void testIsScared() {
        assertFalse(player.isScared());
    }

    @Test
    public void testSetScared() {
        player.setScared();
        assertTrue(player.isScared());
    }

    @Test
    public void testSetBrave() {
        player.setScared();
        player.setBrave();
        assertFalse(player.isScared());
    }

    @Test
    public void testSetScoreboard() {
        Score newScoreboard = new Score(player);
        player.setScoreboard(newScoreboard);
        assertEquals(newScoreboard, player.getScoreboard());
    }

    @Test
    public void testGetBoard() {
        assertNotNull(player.getBoard());
    }
}

