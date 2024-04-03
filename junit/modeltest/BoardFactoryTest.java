package junit.modeltest;

import org.junit.Test;

import model.BoardFactory;
import model.BoardInterface;
import model.NightmareBoard;
import model.PlayerBoard;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.lang.IllegalArgumentException;

/**
 * The test class BoardFactoryTest.
 *
 * @author Sobechi Cornella Madueke-Aniemeka(ver 1.0), Dylan(ver 2.0)
 * @version 2.0
 */
public class BoardFactoryTest
{

@Test
    public void testCreateBoardWithPlayerType() {
        BoardFactory boardFactory = new BoardFactory();
        String boardType = "Player";
        BoardInterface board = boardFactory.createBoard(boardType);
        assertTrue(board instanceof PlayerBoard);
    }

@Test
    public void testCreateBoardWithNightmareType() {
        BoardFactory boardFactory = new BoardFactory();
        String boardType = "Nightmare";
        BoardInterface board = boardFactory.createBoard(boardType);
        assertTrue(board instanceof NightmareBoard);
    }

@Test
    public void testCreateBoardWithInvalidType() {
        BoardFactory boardFactory = new BoardFactory();
        String boardType = "InvalidType";
        assertThrows(IllegalArgumentException.class, () -> {
            boardFactory.createBoard(boardType);
        });
    }
}