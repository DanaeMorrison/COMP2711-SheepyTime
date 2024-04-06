package model;

/**
 * Class for Resting Phase. It is responsible for determining the current player and the market
 * @author Dylan Kim
 * @version 1.0
 */

import java.util.ArrayList;

public class RestingPhase {

    private final int MARKET_SIZE = 4;
    private ArrayList<Player> players;
    private DreamTileCollection dreamTiles;
    private ArrayList<DreamTile> market;
    private int currPlayerIndex;

    public RestingPhase(ArrayList<Player> players, DreamTileCollection dreamTiles) {
        this.players = players;
        currPlayerIndex = 0;
        this.dreamTiles = dreamTiles;
        DreamTileGenerator generator = new DreamTileGenerator();
        generator.makeDreamTiles(dreamTiles);
        createMarket();
    }

    /**
     * Method that checks whether user's input for choosing behaviour(catchZ or putNewTile) is valid or not
     * @param userChoice user's choice
     * @param canPutTile whether user is allowed to put new tile or not
     * @return whether the user's choice is valid or not
     */
    public boolean isChoiceValid(int userChoice, boolean canPutTile) {
        if(canPutTile){
            return (userChoice ==1)||(userChoice == 2);
        }
        return userChoice == 1;
    }

    /**
     * Getter method for current Player
     * @return current player
     */
    public Player getCurrentPlayer() {
        return players.get(currPlayerIndex);
    }

    /**
     * Methods that updates the current player from previous current player to the next one
     * @return if the current player isn't last player, then updates to the next player and return true, and return false if the current player is the last player
     */
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
        for (int i = 0; i < MARKET_SIZE; i++) {
            market.add(dreamTiles.takeTile());
        }
    }

    /**
     * Method that refill the market with new DreamTile if there is an empty space in the market
     * 
     */
    public void fillMarket() {
        if (market.size() == MARKET_SIZE) {
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

    /**
     * getter method for market size
     * @return market size = 4
     */
    public int getMarketSize(){
        return MARKET_SIZE;
    }
           
}
