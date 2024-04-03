package model.exception;

public class NotEnoughZTokenInSupplyException extends GameLogicViolationException{
    public NotEnoughZTokenInSupplyException(String message){
        super(message);
    }
}
