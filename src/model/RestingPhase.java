package model;

/**
 * Class for Resting Phase
 * @author Dylan Kim
 * @version 1.0
 */

import java.util.ArrayList;

public class RestingPhase {
    
    private final int OPERATION_CATCH_Z = 11;
    private final int OPERATION_PLACE_NEW_TILE = 13;

    private ArrayList<Player> players;
    private DreamTileCollection dreamTiles;
    private ArrayList<DreamTile> market;
    private int currPlayerIndex;
    private DreamTileBoard board;

    public RestingPhase(ArrayList<Player> players, DreamTileCollection dreamTiles, DreamTileBoard board) {
        this.players = players;
        currPlayerIndex = 0;
        this.dreamTiles = dreamTiles;
        this.board = board;
        createMarket();
    }

    public int getOperationCatchZ(){
        return OPERATION_CATCH_Z;
    }
    public int getOperationPlaceNewTile(){
        return OPERATION_PLACE_NEW_TILE;
    }

    public Player getCurrentPlayer() {
        return players.get(currPlayerIndex);
    }

    public boolean setNextPlayer() {
        if (currPlayerIndex == players.size() - 1) {
            return false;
        }
        currPlayerIndex++;
        return true;
    }
    /**
     * Helper method that create the market
     */
    private void createMarket() {
        market = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            market.add(dreamTiles.takeTile());
        }
    }

    /**
     * Method that refill the market with new DreamTile if there is an empty space in the market
     * 
     */
    public void fillMarket() {
        if (market.size() == 4) {
            return;
        }
        market.add(dreamTiles.takeTile());
    }

    /**
     * getter method for market
     * 
     * @return market
     */
    public ArrayList<DreamTile> getMarket() {
        return market;
    }
           
}


    // public int moveZzz(int from, int to, int numZzzToken,DreamTileBoard board) {
    //     try {
    //         if (!board.occupied(from) || !board.occupied(to)) {
    //             return ERR_EMPTY_TILE;
    //         }
    //     } catch (ArrayIndexOutOfBoundsException aiobe) {
    //         return ERR_Invalid_Input;
    //     }
        
    //     if (haveEnoughZ()) {
    //         return ERR_HAVE_Z_TOKEN;
    //     }
    //     removeZToken(from);
    //     helpCatchZzz(to, numZzzToken, false);

    //     if(numZzzToken==1){
    //         return OPERATION_NOT_FULLFILLED;
    //     }
    //     else if (numZzzToken==2) {
    //         return OPERATION_SUCCEED;
    //     }
    //     throw new IllegalArgumentException("Invalid input for numZzzToken: Should be either 1 or 2");
    // }

    // /**
    //  * Helper method to remove one ZToken from one DreamTile owned by the current
    //  * Player
    //  * 
    //  * @param location location of the desired DreamTile
    //  * @throws IllegalStateException if there is no ZToken occupied by the current
    //  *                               player
    //  */
    // private void removeZToken(int location) {
    //     DreamTile tile = board.getTile(location);
    //     ArrayList<ZToken> zTokens = tile.getTokens();
    //     for (int i = 0; i < zTokens.size(); i++) {
    //         if (zTokens.get(i).getOwner().equals(getCurrentPlayer())) {
    //             zTokens.remove(i);
    //             return;
    //         }
    //     }
    //     throw new IllegalStateException("There is no ZToken occupied by current Player");
    // }