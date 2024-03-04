public class PlayerBoard implements BoardInterface{

    private boolean[] board;

    public PlayerBoard(){
        board = new boolean[10];
        emptyBoard();
        board[0] = true;
    }

    public void emptyBoard(){
        for(int i = 0; i < board.length; i++){
            board[i] = false;
        }
        board[0] = true;
    }

    public boolean occupied(int i){
        return board[i];
    }

    public int getIndex(){
        for(int i = 0; i < board.length; i++){
            if(board[i]){
                return i;
            }
        }
        return -1;
    }

    public void advance(int n){
        int startingPos = getIndex();
        board[startingPos] = false;
        board[(startingPos + n) % 10] = true;
    }

    public void jump(int n){
        emptyBoard();
        board[(n % 10)] = true;
    }

    public boolean isCrossing(int n){
        if((getIndex() + n) >= 10){
            return true;
        }
        return false;
    }
}