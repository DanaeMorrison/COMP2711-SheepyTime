package model;

import java.util.ArrayList;

public class RestingPhasePutNewTile extends RestingPhaseAction{
    
    private RestingPhase phase;
    private DreamTileBoard board;
    private ArrayList<DreamTile> market;

    public RestingPhasePutNewTile(RestingPhase phase, DreamTileBoard board){
        this.phase = phase;
        this.board = board;
        market = phase.getMarket();
    }


    public int putNewTile(int tileNum, int location ) {
        try {
            if (market.get(tileNum) == null) {
                return getErrEmptyTile();
            } else if (board.occupied(location)) {
                return getErrAlreadyOccupied();
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            return getErrInvalidInput();
        }

        board.addTile(location, market.remove(tileNum));

        int numTokenToPlace = 3;
        if(getTilePlacementBonus(location)){
            numTokenToPlace = 1;
        }
        helpCatchZzz(location, numTokenToPlace, getTilePlacementBonus(location), board, phase.getCurrentPlayer());

        phase.fillMarket();
        return getOperationSucceed();
    }

        /**
     * Helper method that get the placement bonus of the dreamtile at certain location
     * @param location desired location
     * @return true if the placementbonus is infinity ZToken, otherwise 3 normal ZTokens
     */
    private boolean getTilePlacementBonus(int location){
        return board.getTile(location).isInfiniteBonus();
    }

}
