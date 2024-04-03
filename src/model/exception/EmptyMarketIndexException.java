package model.exception;

public class EmptyMarketIndexException extends GameLogicViolationException{
    public EmptyMarketIndexException(String message){
        super(message);
    }
    
}
