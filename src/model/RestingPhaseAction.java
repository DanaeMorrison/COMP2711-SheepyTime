package model;

import model.exception.NotEnoughZTokenInSupplyException;

/**
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseAction {

    private RestingPhase phase;
    private DreamTileBoard getBoard;

    public RestingPhaseAction(RestingPhase phase, DreamTileBoard tileBoard) {
        this.phase = phase;
        this.getBoard = tileBoard;
    }

    // Getter Methods
    public DreamTileBoard getBoard() { return getBoard; }

    public RestingPhase getRestingPhase() { return phase; }

    /**
     * Method that catches Z token(either infinity Z Token or original) on the desired DreamTile with desired amount
     * 
     * @param location desired location of the dream tile
     * @param numZzzToken desired number of Z Token to catch
     * @param isInfinity Whether the Z Token should be infinity Z Token or not
     */
     void catchZ(int location, int numZzzToken, boolean isInfinity) {
        if (notEnoughZ(numZzzToken)) {
            throw new NotEnoughZTokenInSupplyException("You don't have that many Z Token in your supply!");
        }

        DreamTile tile = getBoard.getTile(location);
        for (int i = 0; i < numZzzToken; i++) {
            tile.addToken(phase.getCurrentPlayer(), isInfinity);
            removeZTokenFromSupply();
        }
    }

    /**
     * Helper method that reduce one Z Token in Player's Supply(inventory)
     */
    private void removeZTokenFromSupply(){
        phase.getCurrentPlayer().setZtokens(phase.getCurrentPlayer().getZtokens() - 1);
    }

    /**
     * Helper method that checks whether the current player has enough Zzztoken or
     * not
     * 
     * @param numZToken: required amount of Z Token
     * @return true if the current player has any Zzztoken, false otherwise
     */
    private boolean notEnoughZ(int numZToken) {
        return (phase.getCurrentPlayer().getZtokens() >= numZToken);
    }

}
