package model;

import model.exception.BoardIndexOutOfBoundsException;
import model.exception.EmptyMarketIndexException;
import model.exception.IllegalZTokenCatchException;

public class RestingPhaseCatchZ extends RestingPhaseAction {

    private DreamTileBoard board;

    public RestingPhaseCatchZ(RestingPhase phase, DreamTileBoard tileBoard) {
        super(phase, tileBoard);
    }

    public boolean catchZ(int location, int numZToken) {
        if (location < 1 || location > 10) {
            throw new BoardIndexOutOfBoundsException("Please type from 1 to 10!");
        } else if (!board.occupied(location)) {
            throw new EmptyMarketIndexException("There is no Dream Tile on this location, please choose other location!");
        } else if (numZToken < 1 || numZToken > 2) {
            throw new IllegalZTokenCatchException("You can put either 1 or 2 Z Tokens!");
        }

        catchZ(location - 1, numZToken, false);

        if (numZToken == 1) {
            return false;
        }
        return true;
    }

}
