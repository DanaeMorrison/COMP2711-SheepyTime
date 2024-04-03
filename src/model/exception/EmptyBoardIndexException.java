package model.exception;

public class EmptyBoardIndexException extends GameLogicViolationException{
    public EmptyBoardIndexException(String message){
        super(message);
    }
}
