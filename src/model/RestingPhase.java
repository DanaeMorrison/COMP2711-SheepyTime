package model;

/**
 * Class for Resting Phase
 * @author Dylan Kim
 * @version 1.0
 */

import java.util.ArrayList;

public class RestingPhase {

    private ArrayList<Player> players;
    private DreamTileCollection dreamTiles;
    private ArrayList<DreamTile> market;
    private int currPlayerIndex;

    public RestingPhase(ArrayList<Player> players, DreamTileCollection dreamTiles) {
        this.players = players;
        currPlayerIndex = 0;
        this.dreamTiles = dreamTiles;
        createMarket();
    }


    public boolean isChoiceValid(int userChoice, int numOption) {
        return ((userChoice >= 0) && (userChoice < numOption));
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
