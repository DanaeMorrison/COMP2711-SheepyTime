package model.exception;

/**
 * If the system create a GameLogic that is not corresponding to the number of Players
 * @author Dylan Kim
 * @version 1.0
 */
public class InvalidScoreLogicCreationException extends GameLogicViolationException{
        public InvalidScoreLogicCreationException(String message){
            super(message);
        }
}
