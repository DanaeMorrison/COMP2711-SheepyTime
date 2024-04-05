package model.exception;

/**
 * Exception that manages when the player is trying to put a new Dream Tile on the board at occupied location
 * @author Dylan Kim
 * @version 1.0
 */
public class AlreadyOccupiedOnBoardException extends GameLogicViolationException{
    public AlreadyOccupiedOnBoardException(String message) {
        super(message);
    }
}
