package model;
public class NightmareBoard implements BoardInterface{
    private boolean[] board;

    public NightmareBoard(){
        board = new boolean[10];
        emptyBoard();
        board[0] = true;
    }

    /**
     * Empties out the board, and resets the player position to 0.
     */
    public void emptyBoard(){
        for(int i = 0; i < board.length; i++){
            board[i] = false;
        }
    }

    /**
     * Returns true if occupied at given index.
     * 
     * @param i index
     * @return true if occupied, false otherwise.
     */
    public boolean occupied(int i){
        return board[i];
    }

    /**
     * Returns the location of the character on the board.
     * 
     * @return location of character on board
     */
    public int getIndex(){
        for(int i = 0; i < board.length; i++){
            if(board[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * Advances the character forwards by n steps, looping around to 0 if needed.
     * 
     * @param n Steps to advance
     */
    public void advance(int n){
        int startingPos = getIndex();
        board[startingPos] = false;
        board[(startingPos + n) % 10] = true;
    }

    /**
     * Returns the traveled path of a character if they were to move n steps.
     * Note: this doesn't actually move the character!
     * 
     * @param n steps to record
     * @return steps travelled
     */
    public int[] traveledSpaces(int n){
        int[] traveledSpaces = new int[n];
        int startingPos = getIndex();
        for(int i = 0; i < n; i++){
            traveledSpaces[i] = (startingPos + i) % 10;
        }
        return traveledSpaces;
    }

    /**
     * Jumps character directly to a tile
     * 
     * @param n Tile to jump to
     */
    public void jump(int n){
        emptyBoard();
        board[(n % 10)] = true;
    }
}