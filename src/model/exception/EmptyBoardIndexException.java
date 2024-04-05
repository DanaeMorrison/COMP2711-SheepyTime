package model.exception;

/**
 * When the player is trying to access Dream Tile from empty board index
 * @author Dylan Kim
 * @version 1.0
 */
public class EmptyBoardIndexException extends GameLogicViolationException{
    public EmptyBoardIndexException(String message){
        super(message);
    }
}
