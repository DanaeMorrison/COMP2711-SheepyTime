

import org.junit.Test;

import model.Deck;
import model.Nightmare;
import model.Player;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InitializerTest {

    @Test
    public void testGeneratePlayers() {
        // Set up input stream for user input
        String input = "Dan\nJulie\nToby\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Create an instance of Initializer
        Initializer initializer = new Initializer();

        // Test with n = 3
        ArrayList<Player> players = initializer.generatePlayers(3);
        assertEquals(3, players.size());
        assertEquals("Dan", players.get(0).getName());
        assertEquals("Julie", players.get(1).getName());
        assertEquals("Toby", players.get(2).getName());

        // Reset System.in
        System.setIn(System.in);
    }

    @Test
    public void testGenerateParams() {
        // Set up input stream for user input
        String input = "4\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Create an instance of Initializer
        Initializer initializer = new Initializer();

        // Test
        int playerCount = initializer.generateParams();
        assertEquals(4, playerCount);

        // Reset System.in
        System.setIn(System.in);
    }

    @Test
    public void testGenerateCards() {
        // Create an instance of Initializer
        Initializer initializer = new Initializer();

        // Create a deck and players
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Dan", 0));
        players.add(new Player("Julie", 1));
        players.add(new Player("Toby", 2));

        Nightmare nightmare = new Nightmare("Wolf", 1);

        // Generate cards
        initializer.generateCards(deck, players, nightmare);

        // Check that each player has 3 cards in their hand
        for (Player player : players) {
            assertEquals(2, player.getHand().size());
        }
    }
}