package model.exception;

/**
 * When the player doesn't have enough Z Token to catch
 * @author Dylan Kim
 * @version 1.0
 */
public class NotEnoughZTokenInSupplyException extends GameLogicViolationException{
    public NotEnoughZTokenInSupplyException(String message){
        super(message);
    }
}
