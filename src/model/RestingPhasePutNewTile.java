package model;

import java.util.ArrayList;

import model.exception.AlreadyOccupiedOnBoardException;
import model.exception.EmptyMarketIndexException;
import model.exception.MarketIndexOutOfBoundsException;

/**
 * Class that is responsible for choosing a new Dream Tile from market and place on the board
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhasePutNewTile extends RestingPhaseAction {

    private ArrayList<DreamTile> market;

    public RestingPhasePutNewTile(RestingPhase phase, DreamTileBoard tileBoard) {
        super(phase, tileBoard);
        market = phase.getMarket();
    }

    /**
     * Method that puts a new dream tile on the board
     * 
     * @throw MarketIndexOutOfBoundsException if user input is out of bound [1,4]
     * @throw EmptyMarketIndexException if there is no DreamTile in the market at the desired index
     * @throw AlreadyOccupiedOnBoardException if there is already a dream tile on the board at the desired index
     * 
     * @return true if all the operations were successful
     */
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

    public void putNewTileInSolo(DreamTile tile){
        for(int i=0; i<10;i++){
            if(!getBoard().occupied(i)){
                getBoard().addTile(i,tile);
            }
        }
    }

}
