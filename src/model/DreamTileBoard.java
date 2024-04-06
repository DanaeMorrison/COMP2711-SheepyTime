package model;

import java.util.ArrayList;
public class DreamTileBoard /*implements BoardInterface*/{
    private DreamTile[] board;
    public DreamTileBoard(){
        board = new DreamTile[10];
    }

    public DreamTile getTile(int n){
        return board[n % 10];
    }

    public boolean occupied(int n){
        if(n < 0){
            n = 0;
        }
        return (board[n] != null);
    }

    public void emptyBoard(){
        for(int i = 0; i < board.length; i++){
            board[i] = null;
        }
    }

    public boolean addTile(int index, DreamTile tile){
        if(!occupied(index)){
            board[index] = tile;
            return true;
        }
        return false;
    }

    /**
     * method that check if there is a empty spot in the board
     * @return true if there is, otherwise false
     */
    public boolean isFull() {
        for (int i = 0; i < 10; i++) {
            if (!occupied(i)) {
                return true;
            }
        }
        return false;
    }

    public String[] getBoardStatus(Player currPlayer) {
        String[] boardStatus = new String[10];
        for (int i = 0; i < 10; i++) {
            if (occupied(i)) {
                boardStatus[i] = "O";
            } else {
                boardStatus[i] = "X";
            }
            boardStatus[i] = boardStatus[i] += printZToken(i, currPlayer);
        }
        return boardStatus;
    }

    private String printZToken(int location, Player currPlayer) {
        DreamTile tile = getTile(location);
        if(tile == null){
            return "No Tile!";
        }
        ArrayList<ZToken> zTokens = tile.getTokens();
        String result = "";
        for (int i = 0; i < zTokens.size(); i++) {
            if (zTokens.get(i).getOwner().equals(currPlayer)) {
                result += "*";
            }
        }
        return result;
    }

}
