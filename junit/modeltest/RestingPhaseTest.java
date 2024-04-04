package junit.modeltest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.DreamTileCollection;
import model.Player;
import model.RestingPhase;

public class RestingPhaseTest {
    private RestingPhase phase;

    @Before
    public void setUp(){
        ArrayList<Player> players = new ArrayList<>();
        DreamTileCollection tiles = new DreamTileCollection();
        Player player = new Player("name", 1);
        phase = new RestingPhase(null, null);
    }

    @Test
    public void testFillMarket() {

    }

    @Test
    public void testGetCurrentPlayer() {

    }

    @Test
    public void testGetMarket() {

    }

    @Test
    public void testIsChoiceValid() {

    }

    @Test
    public void testSetNextPlayer() {

    }
}
