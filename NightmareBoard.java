public class NightmareBoard implements BoardInterface{
    private boolean[] board;

    public NightmareBoard(){
        board = new boolean[10];
        emptyBoard();
        board[0] = true;
    }

    public void emptyBoard(){
        for(int i = 0; i < board.length; i++){
            board[i] = false;
        }
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

    public int[] traveledSpaces(int n){
        int[] traveledSpaces = new int[n];
        int startingPos = getIndex();
        for(int i = 0; i < n; i++){
            traveledSpaces[i] = (startingPos + i) % 10;
        }
        return traveledSpaces;
    }

    public void jump(int n){
        emptyBoard();
        board[(n % 10)] = true;
    }
}