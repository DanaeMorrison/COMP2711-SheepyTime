import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DreamTileViewerTest {

    @Test
    public void testPrintRule() {
        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an instance of DreamTileViewer
        DreamTileViewer tileViewer = new DreamTileViewer();

        // Test with tileName = "action hero"
        tileViewer.printRule("action hero");
        String expectedOutput = "\n" +
                "DREAM TILE\n" +
                "\n" +
                "ACTION HERO: If you are scared, gain 3 winks\n" +
                "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Test with tileName = "final sprint"
        outputStream.reset();
        tileViewer.printRule("final sprint");
        expectedOutput = "\n" +
                "DREAM TILE\n" +
                "\n" +
                "FINAL SPRINT: if you are scared, move forward 7 spaces\n" +
                "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Test with tileName = "cool kids club"
        outputStream.reset();
        tileViewer.printRule("cool kids club");
        expectedOutput = "\n" +
                "DREAM TILE\n" +
                "\n" +
                "COOL KIDS CLUB: Move your pillow down 1\n" +
                "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Test with tileName = "bounce ahead"
        outputStream.reset();
        tileViewer.printRule("bounce ahead");
        expectedOutput = "\n" +
                "DREAM TILE\n" +
                "\n" +
                "BOUNCE AHEAD: Move forward 1 space\n" +
                "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Reset System.out
        System.setOut(System.out);
    }
}