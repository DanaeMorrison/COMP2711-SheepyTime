package junit;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Score;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * The test class PlayerTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class PlayerTest {


    private Player player;

    @Before
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

    /*
     * @Test
     * public void testSetHand() {
     * ArrayList<Card> hand = new ArrayList<>();
     * Card testCard = new Card();
     * testCard.setName("Test Card");
     * hand.add(testCard);
     * player.setHand(hand);
     * assertEquals(hand, player.getHand());
     * 
     * }
     */

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
    public void testIsSetScared() {
        assertEquals(0, player.isScared());
        player.setScaredStatus(1);
        assertEquals(1, player.isScared());
        player.setScaredStatus(2);
        assertEquals(2, player.isScared());
        
    }


    @Test
    public void testSetBrave() {
        player.setScaredStatus(1);
        player.setBrave();
        assertEquals(0, player.isScared());
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
