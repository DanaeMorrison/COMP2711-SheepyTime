package model.exception;

public class BoardIndexOutOfBoundsException extends GameLogicViolationException{
    public BoardIndexOutOfBoundsException(String message){
        super(message);
    }
    
}
