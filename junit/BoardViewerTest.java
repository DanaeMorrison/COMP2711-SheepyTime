package junit;
import org.junit.Before;
import org.junit.Test;

import model.Nightmare;
import model.Player;
import view.BoardViewer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * The test class BoardViewerTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class BoardViewerTest {
    private BoardViewer boardViewer;
    private ArrayList<Player> players;
    private Nightmare nightmare;

    @Before
    public void setup() {
        boardViewer = new BoardViewer();
        players = new ArrayList<>();
        players.add(new Player("Dan", 0)); // Assuming the second argument is the starting position
        players.add(new Player("Julie", 0));
        nightmare = new Nightmare("Wolf", 1); // Assuming the second argument is the starting position
    }

    //TODO: Test Failed
    @Test
    public void testShowBoard() {
        // Redirect the standard output to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the showBoard method
        BoardViewer.showBoard(players, nightmare);
        System.setOut(System.out);

        // Verify  expected output
        String expectedOutput = "Space 0\n" +
                                "Space 1\n" +
                                "Space 2\n" +
                                "Space 3\n" +
                                "Space 4\n" +
                                "Space 5\n" +
                                "Space 6\n" +
                                "Space 7\n" +
                                "Space 8\n" +
                                "Space 9\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}