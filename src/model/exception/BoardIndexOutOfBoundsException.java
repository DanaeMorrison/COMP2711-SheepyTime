package model.exception;

/**
 * When the player is trying to get access to outside of the board index boundary
 * @author Dylan Kim
 * @version 1.0
 */
public class BoardIndexOutOfBoundsException extends GameLogicViolationException{
    public BoardIndexOutOfBoundsException(String message){
        super(message);
    }
    
}
