
package model;

import model.exception.BoardIndexOutOfBoundsException;
import model.exception.EmptyBoardIndexException;
import model.exception.IllegalZTokenAmountException;
import model.exception.NotEnoughZTokenInSupplyException;

public class RacingPhaseCatchZ {

    private DreamTileBoard tileBoard;
    private Player currPlayer;

    public RacingPhaseCatchZ(DreamTileBoard tileBoard, Player currPlayer){
        this.tileBoard = tileBoard;
        this.currPlayer = currPlayer;
    }   

   /**
     * Method to catch a desired amount of Z Token on the desired DreamTile
     * 
     * @param location  index number of the desired DreamTile
     * @param numZToken desired amount of Z Token
     * 
     * @throw BoardIndexOutOfBoundsException If the player input is out of boundary
     *        [1,10]
     * @throw EmptyBoardIndexException If there is no dreamtile on the board at
     *        desired index
     * @throw IllegalZTokenCatchException If player is trying to catch more than 2
     *        ZToken
     * 
     * @return false if the player catches only one Z Token, true if player catches
     *         all 2 Z Tokens
     */
    public boolean catchZ(int location, int numZToken) {
        if (location < 1 || location > 10) {
            throw new BoardIndexOutOfBoundsException("Please type from 1 to 10!");
        } else if (!tileBoard.occupied(location)) {
            throw new EmptyBoardIndexException(
                    "There is no Dream Tile on this location, please choose other location!");
        } else if (numZToken < 1 || numZToken > 2) {
            throw new IllegalZTokenAmountException("You can put either 1 or 2 Z Tokens!");
        }

        catchZ(location - 1, numZToken, false);

        if (numZToken == 1) {
            return false;
        }
        return true;
    }

    /**
     * Method that catches Z token(either infinity Z Token or original) on the
     * desired DreamTile with desired amount
     * 
     * @param location    desired location of the dream tile
     * @param numZzzToken desired number of Z Token to catch
     * @param isInfinity  Whether the Z Token should be infinity Z Token or not
     */
    void catchZ(int location, int numZzzToken, boolean isInfinity) {
        if (notEnoughZ(numZzzToken)) {
            throw new NotEnoughZTokenInSupplyException("You don't have that many Z Token in your supply!");
        }

        DreamTile tile = tileBoard.getTile(location);
        for (int i = 0; i < numZzzToken; i++) {
            tile.addToken(currPlayer, isInfinity);
            removeZTokenFromSupply();
        }
    }

    /**
     * Helper method that reduce one Z Token in Player's Supply(inventory)
     */
    private void removeZTokenFromSupply() {
        currPlayer.setZtokens(currPlayer.getZtokens() - 1);
    }

    /**
     * Helper method that checks whether the current player has enough Zzztoken or
     * not
     * 
     * @param numZToken: required amount of Z Token
     * @return true if the current player has any Zzztoken, false otherwise
     */
    private boolean notEnoughZ(int numZToken) {
        return (currPlayer.getZtokens() >= numZToken);
    }

}
