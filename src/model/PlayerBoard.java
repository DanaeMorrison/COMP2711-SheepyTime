package model;

//TODO: Why do we have to use array? can we just update integer instance variable position?

public class PlayerBoard implements BoardInterface{

    private boolean[] board;

    public PlayerBoard(){
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
        board[0] = true;
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

    //TODO: Does sheep token jump?
    /**
     * Jumps character directly to a tile
     * 
     * @param n Tile to jump to
     */
    public void jump(int n){
        emptyBoard();
        board[(n % 10)] = true;
    }

    //TODO If this method is going to be called after advance(), this method never return true
    /**
     * Checks if the user will cross the fence if they were to advance n steps.
     * Doesn't actually move the player, just a check!
     * 
     * @param n Steps to be checked
     * @return true if crossing, false if not
     */
    public boolean isCrossing(int n){
        if((getIndex() + n) >= 10){
            return true;
        }
        return false;
    }
}