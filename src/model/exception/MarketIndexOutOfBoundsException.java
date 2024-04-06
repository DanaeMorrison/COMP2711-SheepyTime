package model.exception;

/**
 * When the player is trying to access outside of the market index boundary
 * @author Dylan Kim
 * @version 1.0
 */
public class MarketIndexOutOfBoundsException extends GameLogicViolationException{
    public MarketIndexOutOfBoundsException(String message){
        super(message);
    }
}
