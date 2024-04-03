package model;


public class PlayerBoard implements BoardInterface{

    private int position;

    public PlayerBoard(){
        position = -1;
    }

    /**
     * Empties out the board, and resets the player position to 0.
     */
    public void emptyBoard(){
        position = -1;
    }

    /**
     * Returns true if occupied at given index.
     * 
     * @param index index
     * @return true if occupied, false otherwise.
     */
    public boolean occupied(int index){
        return index == position; 
    }

    /**
     * Returns the location of the character on the board.
     * 
     * @return location of character on board
     */
    public int getIndex(){
        return position;
    }

    /**
     * Advances the character forwards by n steps, looping around to 0 if needed.
     * 
     * @param steps Steps to advance
     */
    public void advance(int steps){
        position = (position+steps) % 10;
    }

    /**
     * Jumps character directly to a tile
     * 
     * @param n Tile to jump to
     */
    public void jump(int n){
        this.position = position%10;
    }

    /**
     * Checks if the user will cross the fence if they were to advance n steps.
     * Doesn't actually move the player, just a check!
     * 
     * @param steps Steps to be checked
     * @return true if crossing, false if not
     */
    public boolean isCrossing(int steps){
        if((getIndex() + steps) >= 10){
            return true;
        }
        return false;
    }
}