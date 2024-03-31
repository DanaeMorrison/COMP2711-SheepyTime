package model;

/**
 * Class for Resting Phase
 * @author Dylan Kim
 * @version 1.0
 */

import java.util.ArrayList;

public class RestingPhase {
    private final int OPERATION_SUCCESS = 1;
    private final int OPERATION_FAILED = 0;
    private final int OPERATION_NOTFULLFILLED = -1;

    private ArrayList<Player> players;
    private DreamTileCollection dreamTiles;
    private DreamTileBoard board;
    private ArrayList<DreamTile> market;
    private int currPlayerIndex;
    
    public RestingPhase(ArrayList<Player> players, DreamTileCollection dreamTiles, DreamTileBoard board){
        this.players = players;
        currPlayerIndex=0;
        this.dreamTiles = dreamTiles;
        this.board = board;
        createMarket();
    }

    public boolean setNextPlayer(){
        if (currPlayerIndex == players.size()-1){
            return false;
        }
        currPlayerIndex++;
        return true;
    }

    public boolean startPhase(){
        return false;
    }

    public boolean catchZzz(int location, int numZzzToken){
        if(!board.occupied(location)){
            return false;
        }
        if()
        
        return false;
    }

    
    
    /**
     * Helper method that create the market
     */
    private void createMarket(){
        market = new ArrayList<>();
        for (int i=0 ; i<4 ; i++){
            market.add(dreamTiles.takeTile());
        }
    }

    /**
     * Helper method that refill the market with new DreamTile
     * @throws IllegalStateException Since the maximum number of dreamtiles in market is 4, it throws an exception
     *         when the function is called even if market already contains 4 dreamtiles
     */
    private void fillMarket(){
        if(market.size() == 4){
            throw new IllegalStateException();
        }
        market.add(dreamTiles.takeTile());
    }
}
