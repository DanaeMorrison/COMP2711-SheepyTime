package model.exception;

public class IllegalMovementException extends GameLogicViolationException{
    public IllegalMovementException(String message){
        super(message);
    }
}
