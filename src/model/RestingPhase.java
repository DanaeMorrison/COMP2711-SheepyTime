package model;

/**
 * Class for Resting Phase
 * @author Dylan Kim
 * @version 1.0
 */

import java.util.ArrayList;

public class RestingPhase {
    
    private final int OPERATION_CATCH_Z = 11;
    private final int OPERATION_MOVE_Z = 12;
    private final int OPERATION_PLACE_NEW_TILE = 13;
    private final int OPERATION_SUCCEED = 1;
    private final int OPERATION_NOT_FULLFILLED = 0;

    private final int ERR_NOT_ENOUGH_Z_TOKEN = -1;
    private final int ERR_HAVE_Z_TOKEN = -2;
    private final int ERR_EMPTY_TILE = -3;
    private final int ERR_ALREADY_OCCUPIED = -4;

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
    public int getOperationMoveZ(){
        return OPERATION_MOVE_Z;
    }
    public int getOperationPlaceNewTile(){
        return OPERATION_PLACE_NEW_TILE;
    }

    /**
     * Getter method for each errorcode and successcode
     * 
     * @return corresponding errorcode and successcode
     */
    public int getOperationSucceed() {
        return OPERATION_SUCCEED;
    }

    public int getOperationNotFulfilled() {
        return OPERATION_NOT_FULLFILLED;
    }

    public int getErrNotEnoughZToken() {
        return ERR_NOT_ENOUGH_Z_TOKEN;
    }

    public int getErrHaveZToken() {
        return ERR_HAVE_Z_TOKEN;
    }

    public int getErrEmptyTile() {
        return ERR_EMPTY_TILE;
    }

    public int getErrAlreadyOccupied() {
        return ERR_ALREADY_OCCUPIED;
    }

    public boolean isChoiceValid(int choice){
        return ((choice==0)||(choice==1));
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
     * Method for catching ZzzToken, when the player has enough ZzzToken in their
     * supply(inventory)
     * 
     * @param location    location of desired DreamTile
     * @param numZzzToken number of desired number of Zzz Token to catch
     * @return
     */
    public int catchZzz(int location, int numZzzToken) {
        if (!haveEnoughZ()) {
            return ERR_NOT_ENOUGH_Z_TOKEN;
        } else if (!board.occupied(location)) {
            return ERR_EMPTY_TILE;
        }

        if (numZzzToken == 1) {
            helpCatchZzz(location, numZzzToken, false);
            return OPERATION_NOT_FULLFILLED;

        } else if (numZzzToken == 2) {
            helpCatchZzz(location, numZzzToken, false);
            return OPERATION_SUCCEED;
        }

        throw new IllegalArgumentException("Invalid input for numZzzToken: Should be either 1 or 2");
    }

    public int moveZzz(int from, int to, int numZzzToken,DreamTileBoard board) {
        if (!board.occupied(from) || !board.occupied(to)) {
            return ERR_EMPTY_TILE;
        } else if (haveEnoughZ()) {
            return ERR_HAVE_Z_TOKEN;
        }

        if (numZzzToken == 1) {
            removeZToken(from);
            helpCatchZzz(to, numZzzToken, false);
            return OPERATION_NOT_FULLFILLED;
        } else if (numZzzToken == 2) {
            removeZToken(from);
            helpCatchZzz(to, numZzzToken, false);
            return OPERATION_SUCCEED;
        }
        throw new IllegalArgumentException("Invalid input for numZzzToken: Should be either 1 or 2");
    }

    /**
     * Helper method to remove one ZToken from one DreamTile owned by the current
     * Player
     * 
     * @param location location of the desired DreamTile
     * @throws IllegalStateException if there is no ZToken occupied by the current
     *                               player
     */
    private void removeZToken(int location) {
        DreamTile tile = board.getTile(location);
        ArrayList<ZToken> zTokens = tile.getTokens();
        for (int i = 0; i < zTokens.size(); i++) {
            if (zTokens.get(i).getOwner().equals(getCurrentPlayer())) {
                zTokens.remove(i);
                return;
            }
        }
        throw new IllegalStateException("There is no ZToken occupied by current Player");
    }

    private void helpCatchZzz(int location, int numZzzToken, boolean isInfinity) {
        DreamTile tile = board.getTile(location);
        for (int i = 0; i < numZzzToken; i++) {
            tile.addToken(getCurrentPlayer(), isInfinity);
            getCurrentPlayer().setZtokens(getCurrentPlayer().getZtokens() - 1);
        }
    }

    /**
     * Helper method that checks whether the current player has any Zzztoken or not
     * 
     * @return true if the current player has any Zzztoken, false otherwise
     */
    private boolean haveEnoughZ() {
        return (getCurrentPlayer().getZtokens() > 0);
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
     * Helper method that refill the market with new DreamTile
     * 
     * @throws IllegalStateException Since the maximum number of dreamtiles in
     *                               market is 4, it throws an exception
     *                               when the function is called even if market
     *                               already contains 4 dreamtiles
     */
    private void fillMarket() {
        if (market.size() == 4) {
            throw new IllegalStateException("Market is already full");
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

    /**
     * Method that place one dreamtile from market on the board
     * 
     * @param tileNum  number of desired dreamtiles in market
     * @param location desired location to place the tile
     * @return corresponding error code, or success code
     */
    public int putNewDreamTile(int tileNum, int location) {
        if (market.get(tileNum) == null) {
            return ERR_EMPTY_TILE;
        } else if (board.occupied(location)) {
            return ERR_ALREADY_OCCUPIED;
        }

        board.addTile(location, market.remove(tileNum));

        int numTokenToPlace = 3;
        if(getTilePlacementBonus(location)){
            numTokenToPlace = 1;
        }
        helpCatchZzz(location, numTokenToPlace, getTilePlacementBonus(location));

        fillMarket();
        return OPERATION_SUCCEED;
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