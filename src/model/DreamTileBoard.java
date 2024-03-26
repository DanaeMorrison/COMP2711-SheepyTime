package model;
public class DreamTileBoard /*implements BoardInterface*/{
    private DreamTile[] board;
    public DreamTileBoard(){
        board = new DreamTile[10];
    }

    public DreamTile getTile(int n){
        return board[n % 10];
    }

    public boolean occupied(int n){
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
}
