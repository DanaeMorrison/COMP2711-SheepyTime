package model;

public class RestingPhaseCatchZ extends RestingPhaseAction{

    private RestingPhase phase;
    private DreamTileBoard board;

    public RestingPhaseCatchZ(RestingPhase phase, DreamTileBoard board){
        this.phase = phase;
        this.board = board;
    }

    public int catchZ(int location, int numZzzToken) {
        try {
            if (!board.occupied(location)) {
                return getErrEmptyTile();
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            return getErrInvalidInput();
        }

        if (!haveEnoughZ()) {
            return getErrNotEnoughZToken();
        }
        helpCatchZzz(location, numZzzToken, false, board, phase.getCurrentPlayer());

        if(numZzzToken==1){
            return getOperationNotFulfilled();
        }
        else if (numZzzToken==2) {
            return getOperationSucceed();
        }
        throw new IllegalArgumentException("Invalid input for numZzzToken: Should be either 1 or 2");
    }

    /**
     * Helper method that checks whether the current player has any Zzztoken or not
     * 
     * @return true if the current player has any Zzztoken, false otherwise
     */
    private boolean haveEnoughZ() {
        return (phase.getCurrentPlayer().getZtokens() > 0);
    }

}
