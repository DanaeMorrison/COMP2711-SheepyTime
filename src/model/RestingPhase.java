package model;

/**
 * Class for Resting Phase
 * @author Dylan Kim
 * @version 1.0
 */

import java.util.ArrayList;

public class RestingPhase {
    private final int OPERATION_SUCCEED = 1;
    private final int OPERATION_NOT_FULLFILLED = 0;
    private final int ERR_NOT_ENOUGH_Z_TOKEN = -1;
    private final int ERR_HAVE_Z_TOKEN = -2;
    private final int ERR_EMPTY_TILE = -3;
    private final int ERR_ALREADY_OCCUPIED = -4;

    private ArrayList<Player> players;
    private DreamTileCollection dreamTiles;
    private DreamTileBoard board;
    private ArrayList<DreamTile> market;
    private int currPlayerIndex;

    public RestingPhase(ArrayList<Player> players, DreamTileCollection dreamTiles, DreamTileBoard board) {
        this.players = players;
        currPlayerIndex = 0;
        this.dreamTiles = dreamTiles;
        this.board = board;
        createMarket();
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

    private Player getCurrentPlayer() {
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
            helpCatchZzz(location, numZzzToken);
            return OPERATION_NOT_FULLFILLED;

        } else if (numZzzToken == 2) {
            helpCatchZzz(location, numZzzToken);
            return OPERATION_SUCCEED;
        }

        throw new IllegalArgumentException("Invalid input for numZzzToken: Should be either 1 or 2");
    }

    public int moveZzz(int from, int to, int numZzzToken) {
        if (!board.occupied(from) || !board.occupied(to)) {
            return ERR_EMPTY_TILE;
        } else if (haveEnoughZ()) {
            return ERR_HAVE_Z_TOKEN;
        }

        if (numZzzToken == 1) {
            removeZToken(from);
            helpCatchZzz(to, numZzzToken);
            return OPERATION_NOT_FULLFILLED;
        } else if (numZzzToken == 2) {
            removeZToken(from);
            helpCatchZzz(to, numZzzToken);
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

    private void helpCatchZzz(int location, int numZzzToken) {
        DreamTile tile = board.getTile(location);
        for (int i = 0; i < numZzzToken; i++) {
            tile.addToken(getCurrentPlayer(), false);
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

        //TODO: implement putting ZzzToken to the dreamtile

        fillMarket();
        return OPERATION_SUCCEED;
    }

    public boolean isBoardfull() {
        for (int i = 0; i < 10; i++) {
            if (!board.occupied(i)) {
                return true;
            }
        }
        return false;
    }

    public String[] getBoardStatus() {
        String[] boardStatus = new String[10];
        for (int i = 0; i < 10; i++) {
            if (board.occupied(i)) {
                boardStatus[i] = "O";
            } else {
                boardStatus[i] = "X";
            }
            boardStatus[i] = boardStatus[i]+=printZToken(i);
        }
        return boardStatus;
    }

    private String printZToken(int location){
        DreamTile tile = board.getTile(location);
        ArrayList<ZToken> zTokens = tile.getTokens();
        String result = "";
        for (int i=0 ; i<zTokens.size() ; i++){
            if(zTokens.get(i).getOwner().equals(getCurrentPlayer())){
                result +="*";
            }
        }
        return result;
    }

}
/**

1 Dylan 
2 
3 Nightmare
4 
5
6 Danae
7
8 
9
10
---fence---

6: 

 */