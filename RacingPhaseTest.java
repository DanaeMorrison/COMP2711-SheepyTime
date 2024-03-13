import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
public class RacingPhaseTest {
    private RacingPhase racingPhase;
    private ArrayList<Player> players;
    private Deck deck;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Player 1", 1));
        players.add(new Player("Player 2", 2));
        deck = new Deck();
        racingPhase = new RacingPhase(players, deck);
    }

    @Test
    void testStartPhase() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card.Builder(false, false).build());
        hand.add(new Card.Builder(true,true).build());
        hand.add(new Card.Builder(false, true).build());


        // Set player 1's hand
        players.get(0).setHand(new ArrayList<>(hand));

        // Created to simulate user input
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());


        System.setIn(inputStream);

        // Call the startPhase method
        racingPhase.startPhase();

        // Assert that the correct inputs were printed
        String expectedOutput = "Player 1's card #0\n" +
                "Card 1\n" +
                "Player 1's card #1\n" +
                "Card 2\n" +
                "Player 1's card #2\n" +
                "Card 3\n" +
                "Input which card you would like to use from your hand (integer)\n";
        assertEquals(expectedOutput, getOutput());
    }
    
    private String getOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        racingPhase.startPhase();
        System.out.flush();
        return outputStream.toString();
    }

}
