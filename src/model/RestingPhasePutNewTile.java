package model;

import java.util.ArrayList;

public class RestingPhasePutNewTile extends RestingPhaseAction{
    

    private ArrayList<DreamTile> market;

    public RestingPhasePutNewTile(RestingPhase phase, DreamTileBoard tileBoard){
        super(phase, tileBoard);
        market = phase.getMarket();
    }


    public int putNewTile(int tileNum, int location ) {
        try {
            if (market.get(tileNum) == null) {
                return getErrEmptyTile();
            } else if (getBoard().occupied(location)) {
                return getErrAlreadyOccupied();
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            return getErrInvalidInput();
        }

        getBoard().addTile(location, market.remove(tileNum));

        int numTokenToPlace = 3;
        if(getTilePlacementBonus(location)){
            numTokenToPlace = 1;
        }
        helpCatchZzz(location, numTokenToPlace, getTilePlacementBonus(location));

        getRestingPhase().fillMarket();
        return getOperationSucceed();
    }

        /**
     * Helper method that get the placement bonus of the dreamtile at certain location
     * @param location desired location
     * @return true if the placementbonus is infinity ZToken, otherwise 3 normal ZTokens
     */
    private boolean getTilePlacementBonus(int location){
        return getBoard().getTile(location).isInfiniteBonus();
    }

}
