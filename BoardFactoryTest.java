

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.IllegalArgumentException;
import org.junit.jupiter.api.Assertions;

/**
 * The test class BoardFactoryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BoardFactoryTest
{
    /**
     * Default constructor for test class BoardFactoryTest
     */
public BoardFactoryTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
@BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
@AfterEach
    public void tearDown()
    {
    }
    
@Test
    public void testCreateBoardWithPlayerType() {
        BoardFactory boardFactory = new BoardFactory();
        String boardType = "Player";
        BoardInterface board = boardFactory.createBoard(boardType);
        Assertions.assertTrue(board instanceof PlayerBoard);
    }

@Test
    public void testCreateBoardWithNightmareType() {
        BoardFactory boardFactory = new BoardFactory();
        String boardType = "Nightmare";
        BoardInterface board = boardFactory.createBoard(boardType);
        Assertions.assertTrue(board instanceof NightmareBoard);
    }

@Test
    public void testCreateBoardWithInvalidType() {
        BoardFactory boardFactory = new BoardFactory();
        String boardType = "InvalidType";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boardFactory.createBoard(boardType);
        });
    }
}