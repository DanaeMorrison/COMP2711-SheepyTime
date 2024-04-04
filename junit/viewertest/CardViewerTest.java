package junit.viewertest;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import model.Card;
import view.CardViewer;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//TODO: Test Failed

/**
 * The test class CardViewerTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class CardViewerTest
{
    private Card card;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOutput;

    @Before
    public void setUp() {
        card = new Card(true, false, new int[]{1, 2}, 3, 4, 5, 6);
        outputStream = new ByteArrayOutputStream();
        originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
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
        assertTrue(outputStream.toString().contains(expectedOutput.trim()));
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
        assertTrue(outputStream.toString().contains(expectedOutput.trim()));
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
        assertTrue(outputStream.toString().contains(expectedOutput.trim()));
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
        assertTrue(outputStream.toString().contains(expectedOutput.trim()));
    }

    
    @After
    public void tearDown() {
        System.setOut(originalOutput);
    }
}
// not working, checkback and fix 