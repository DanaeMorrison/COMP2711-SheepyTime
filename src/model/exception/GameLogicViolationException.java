package model.exception;

/**
 * SuperClass of all GameLogicViolation
 * @author Dylan Kim
 * @version 1.0
 */
public class GameLogicViolationException extends RuntimeException {
    public GameLogicViolationException(String message){
        super(message);
    }
}
