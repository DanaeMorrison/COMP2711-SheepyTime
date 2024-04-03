package model.exception;

public class GameLogicViolationException extends RuntimeException {
    public GameLogicViolationException(String message){
        super(message);
    }
}
