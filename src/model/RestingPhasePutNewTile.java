package model;

import java.util.ArrayList;

import model.exception.AlreadyOccupiedOnBoardException;
import model.exception.EmptyMarketIndexException;
import model.exception.MarketIndexOutOfBoundsException;

public class RestingPhasePutNewTile extends RestingPhaseAction {

    private ArrayList<DreamTile> market;

    public RestingPhasePutNewTile(RestingPhase phase, DreamTileBoard tileBoard) {
        super(phase, tileBoard);
        market = phase.getMarket();
    }

    public boolean putNewTile(int tileNum, int location) {
        if (tileNum > 4 || tileNum < 1) {
            throw new MarketIndexOutOfBoundsException("There are only 4 options! Please type from 1 to 4.");
        } else if (market.get(tileNum) == null) {
            throw new EmptyMarketIndexException("That tile is sold out! Please choose a different tile");
        } else if (getBoard().occupied(location)) {
            throw new AlreadyOccupiedOnBoardException("Uh oh! Someone already put tile here! Try different location!");
        }

        getBoard().addTile(location, market.remove(tileNum-1));

        int numTokenToPlace = 3;
        if (getTilePlacementBonus(location)) {
            numTokenToPlace = 1;
        }
        catchZ(location, numTokenToPlace, getTilePlacementBonus(location));

        getRestingPhase().fillMarket();
        return true;
    }

    /**
     * Helper method that get the placement bonus of the dreamtile at certain
     * location
     * 
     * @param location desired location
     * @return true if the placementbonus is infinity ZToken, otherwise 3 normal
     *         ZTokens
     */
    private boolean getTilePlacementBonus(int location) {
        return getBoard().getTile(location).isInfiniteBonus();
    }

}
