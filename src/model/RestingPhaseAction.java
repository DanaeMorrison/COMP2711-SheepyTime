package model;

public class RestingPhaseAction {

    private final int OPERATION_SUCCEED = 1;
    private final int OPERATION_NOT_FULLFILLED = 0;

    private final int ERR_NOT_ENOUGH_Z_TOKEN = -1;
    private final int ERR_EMPTY_TILE = -2;
    private final int ERR_ALREADY_OCCUPIED = -3;
    private final int ERR_INVALID_INPUT = -4;

    public int getOperationSucceed() {
        return OPERATION_SUCCEED;
    }

    public int getOperationNotFulfilled() {
        return OPERATION_NOT_FULLFILLED;
    }

    public int getErrNotEnoughZToken() {
        return ERR_NOT_ENOUGH_Z_TOKEN;
    }

    public int getErrEmptyTile() {
        return ERR_EMPTY_TILE;
    }

    public int getErrAlreadyOccupied() {
        return ERR_ALREADY_OCCUPIED;
    }

    public int getErrInvalidInput(){
        return ERR_INVALID_INPUT;
    }

    

    public boolean isChoiceValid(int choice){
        return ((choice==0)||(choice==1));
    }

    public void helpCatchZzz(int location, int numZzzToken, boolean isInfinity, DreamTileBoard board, Player currPlayer) {
        DreamTile tile = board.getTile(location);
        for (int i = 0; i < numZzzToken; i++) {
            tile.addToken(currPlayer, isInfinity);
            currPlayer.setZtokens(currPlayer.getZtokens() - 1);
        }
    }


}
