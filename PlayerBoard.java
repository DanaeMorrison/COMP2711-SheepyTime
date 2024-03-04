public class PlayerBoard implements BoardInterface{

    private boolean[] board;

    public PlayerBoard(){
        emptyBoard();
        board[0] = true;
        board = new boolean[10];
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

    public int[] advance(int n){
        int[] traveledSpaces = new int[n];
        int startingPos = getIndex();
        board[startingPos] = false;
        for(int i = 0; i < n; i++){
            traveledSpaces[i] = (startingPos + i) % 10;
        }
        board[(startingPos + n) % 10] = true;
        return traveledSpaces;
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