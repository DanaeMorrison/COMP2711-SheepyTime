package model;
/**
 * @author Danae Morrison
 */

public interface CardInterface {
    /**
     * This interface is the layout for what all card implentations should contain, providing a layer of abstraction
     * @return
     */
    // stores the number of moves of a card
    //private int moves;

    // method that asks if a card is a nightmare card
    public boolean isNightmare();

    // method that returns the number of spaces associated with
    // the "move" option of a card
    public int getMoves();

    public int getZtokens();

    public int getWinks();

    public boolean getConditions();

    public int getJumpPos();

}