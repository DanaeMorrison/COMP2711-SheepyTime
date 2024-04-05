package model.exception;

/**
 * When the player is trying to get a new Dream Tile from empty market index
 * @author Dylan Kim
 * @version 1.0
 */
public class EmptyMarketIndexException extends GameLogicViolationException{
    public EmptyMarketIndexException(String message){
        super(message);
    }
    
}
