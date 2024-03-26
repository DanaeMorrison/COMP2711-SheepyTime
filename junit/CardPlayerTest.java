package junit;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

import model.Card;
import model.CardPlayer;
import model.Player;
import model.PlayerBoard;
import model.Card.Builder;

public class CardPlayerTest {
    private CardPlayer cardPlayer;
    private Player player;
    private PlayerBoard playerBoard;

    @Before
    public void setUp() {
        cardPlayer = new CardPlayer();
        player = new Player(); // Create a simple player object
        playerBoard = new PlayerBoard(); // Create a simple player board object
        player.setBoard(playerBoard); // Assign the player board to the player
    }

    @Test
    public void testPlayCardNonNightmare() {
        // Prepare input for the scanner
        String input = "1\n"; // Choose move ability
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Card nonNightmareCard = new Card.Builder(false, false)
                                        .withMoves(new int[]{1, 2})
                                        .withWinks(3)
                                        .withZtokens(2)
                                        .build();

        player.setWinks(0);
        player.setZtokens(0);

        cardPlayer.playCard(nonNightmareCard, player);

        assertEquals(3, player.getWinks());
        assertEquals(2, player.getZtokens());
    }

    @Test
    public void testPlayCardNightmare() {
        // Prepare input for the scanner (not needed for nightmare cards)
        Card nightmareCard = new Card.Builder(true, true)
                                    .withMoves(new int[]{1})
                                    .withJumpPos(5) // Assume jump position is 5
                                    .build();

        player.setWinks(0);
        player.setZtokens(0);
        playerBoard.setCurrentPosition(3); // Assume the player is at position 3

        cardPlayer.playCard(nightmareCard, player);

        assertEquals(0, player.getWinks()); // Winks should not be affected by nightmare cards
        assertEquals(0, player.getZtokens()); // Ztokens should not be affected by nightmare cards
        assertEquals(5, playerBoard.getCurrentPosition()); // Player should be moved to the jump position
    }
}// re-do this test 