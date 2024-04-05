package model.exception;

/**
 * If the user is trying to catch more ZToken than it was supposed to be
 * @author Dylan Kim
 * @version 1.0
 */
public class IllegalZTokenAmountException extends GameLogicViolationException {
    public IllegalZTokenAmountException(String message) {
        super(message);
    }
}
