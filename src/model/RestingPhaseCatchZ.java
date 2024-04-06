package model;

import model.exception.BoardIndexOutOfBoundsException;
import model.exception.EmptyBoardIndexException;
import model.exception.IllegalZTokenAmountException;

/**
 * Class that is responsible for catching Z Token on the dreamTile located on
 * board
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseCatchZ extends RestingPhaseAction {

    private DreamTileBoard board;

    public RestingPhaseCatchZ(RestingPhase phase, DreamTileBoard tileBoard) {
        super(phase, tileBoard);
    }

    /**
     * Method to catch a desired amount of Z Token on the desired DreamTile
     * 
     * @param location  index number of the desired DreamTile
     * @param numZToken desired amount of Z Token
     * 
     * @throw BoardIndexOutOfBoundsException If the player input is out of boundary [1,10]
     * @throw EmptyBoardIndexException If there is no dreamtile on the board at desired index
     * @throw IllegalZTokenCatchException If player is trying to catch more than 2 ZToken
     * 
     * @return false if the player catches only one Z Token, true if player catches all 2 Z Tokens
     */
    public boolean catchZ(int location, int numZToken) {
        if (location < 1 || location > 10) {
            throw new BoardIndexOutOfBoundsException("Please type from 1 to 10!");
        } else if (!board.occupied(location)) {
            throw new EmptyBoardIndexException("There is no Dream Tile on this location, please choose other location!");
        } else if (numZToken < 1 || numZToken > 2) {
            throw new IllegalZTokenAmountException("You can put either 1 or 2 Z Tokens!");
        }

        catchZ(location - 1, numZToken, false);

        if (numZToken == 1) {
            return false;
        }
        return true;
    }

}
