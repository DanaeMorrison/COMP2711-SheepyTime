package model;
public class NightmareBoard implements BoardInterface{
    private int position;

    public NightmareBoard(){
        position = -1;
    }

    /**
     * Empties out the board, and resets the player position to 0.
     */
    @Override
    public void emptyBoard(){
        position = -1;
    }

    /**
     * Returns true if occupied at given index.
     * 
     * @param index index
     * @return true if occupied, false otherwise.
     */
    @Override
    public boolean occupied(int index) {
        return index == position; 
    }

    /**
     * Returns the location of the character on the board.
     * 
     * @return location of character on board
     */
    @Override
    public int getIndex(){
        return position;
    }

    /**
     * Advances the character forwards by n steps, looping around to 0 if needed.
     * 
     * @param n Steps to advance
     */
    @Override
    public void advance(int steps) {
        position = (position+steps) % 10;
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
     * @param position Tile to jump to
     */
    @Override
    public void jump(int position){
        this.position = position%10;
    }

    /**
     * Checks if the user will cross the fence if they were to advance n steps.
     * Doesn't actually move the player, just a check!
     * 
     * @param steps Steps to be checked
     * @return true if crossing, false if not
     */
    @Override
    public boolean isCrossing(int steps){
        if((getIndex() + steps) >= 10){
            return true;
        }
        return false;
    }
}