package model.exception;

public class MarketIndexOutOfBoundsException extends GameLogicViolationException{
    public MarketIndexOutOfBoundsException(String message){
        super(message);
    }
}
