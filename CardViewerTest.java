

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
/**
 * The test class CardViewerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CardViewerTest
{
    private Card card;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOutput;

    @BeforeEach
    public void setUp() {
        card = new Card(true, false, new int[]{1, 2}, 3, 4, 5, 6);
        outputStream = new ByteArrayOutputStream();
        originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOutput);
    }

    @Test
    public void testRulePrintNightmare1() {
        CardViewer cardViewer = new CardViewer(card, 1);
        cardViewer.rulePrint();
        String expectedOutput = "            \n" +
                                "____________\n" +
                                "            \n" +
                                "    WOLF    \n" +
                                "            \n" +
                                "            \n" +
                                "Move nightmare by 1 spaces\n" +
                                "            \n" +
                                "            \n" +
                                "____________\n";
        Assertions.assertTrue(outputStream.toString().contains(expectedOutput.trim()));
    }

    @Test
    public void testRulePrintNightmare2() {
        CardViewer cardViewer = new CardViewer(card, 2);
        cardViewer.rulePrint();
        String expectedOutput = "            \n" +
                                "____________\n" +
                                "            \n" +
                                "    BUMP    \n" +
                                "            \n" +
                                "            \n" +
                                "Jump nightmare 5 spaces forward\n" +
                                "            \n" +
                                "            \n" +
                                "____________\n";
        Assertions.assertTrue(outputStream.toString().contains(expectedOutput.trim()));
    }

    @Test
    public void testRulePrintNightmare3() {
        CardViewer cardViewer = new CardViewer(card, 3);
        cardViewer.rulePrint();
        String expectedOutput = "            \n" +
                                "____________\n" +
                                "            \n" +
                                "   SPIDER   \n" +
                                "            \n" +
                                "            \n" +
                                "Jump nightmare to web token\n" +
                                "If nightmare doesn't move, move web token forward by 6 spaces\n" +
                                "            \n" +
                                "            \n" +
                                "____________\n";
        Assertions.assertTrue(outputStream.toString().contains(expectedOutput.trim()));
    }

    @Test
    public void testRulePrintNotNightmare() {
        CardViewer cardViewer = new CardViewer(card, 0);
        cardViewer.rulePrint();
        String expectedOutput = "            \n" +
                                "____________\n" +
                                "            \n" +
                                "   SLEEP    \n" +
                                "            \n" +
                                "            \n" +
                                "Move 1 OR 2 spaces\n" +
                                "            \n" +
                                "            \n" +
                                "OR      \n" +
                                "Gain 4 Wink\n" +
                                "OR      \n" +
                                "Catch 3 Zzz\n" +
                                "            \n" +
                                "            \n" +
                                "____________\n";
        Assertions.assertTrue(outputStream.toString().contains(expectedOutput.trim()));
    }
}
// not working, checkback and fix 