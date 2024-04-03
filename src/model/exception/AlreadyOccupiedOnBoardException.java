package model.exception;

public class AlreadyOccupiedOnBoardException extends GameLogicViolationException{
    public AlreadyOccupiedOnBoardException(String message) {
        super(message);
    }
}
