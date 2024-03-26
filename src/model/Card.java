package model;
/**
 * @author Danae Morrison
 */

import java.util.ArrayList;

public class Card {
    /**
     * This class allows for cards to be built that cater to specific conditions and types of cards. These
     * are to be used in the game to facilitate movement of players and a given nightmare
     */
    private final boolean bothConditions;
    private final boolean isNightmare;

    private int[] moves;
    private int Ztokens;
    private int winks;
    private int jumpPos;
    private int spiderMove;

    /**
     * This constructor is used by the Builder class within Card to
     * make a Card object with required parameters.
     * @param bothConditions says whether a card requires both conditions to be done i.e., an AND card
     * @param isNightmare says whether a card is a nightmare or not
     */

    private Card(boolean bothConditions, boolean isNightmare) {
        this.bothConditions = bothConditions;
        this.isNightmare = isNightmare;
    }
    /**
     * This constructor is accessible to classes outside and inside of the Card class to 
     * build a Card object with various attributes
     * @param bothConditions says whether a card requires both conditions to be done i.e., an AND card
     * @param isNightmare says whether a card is a nightmare or not
     * @param moves contains the number of positions a character (player or wolf nightmare)
     *              should be moved on the board
     * @param Ztokens contains the number of Ztokens a card potentially says can be caught by the player
     * @param winks contains the number of winks a card potentially says can be gained by the player
     * @param jumpPos contains the position on a board a bump nightmare should jump to
     * @param spiderMove contains the number of positions a spider nightmare's web token should be
     *                      moved forward if the spider is already on the token or if the token is
     *                      not currently on the board
     */
    public Card (boolean bothConditions, boolean isNightmare, int[] moves, int Ztokens, int winks, int jumpPos, int spiderMove) {
        this.bothConditions = bothConditions;
        this.isNightmare = isNightmare;
        this.moves = moves;
        this.Ztokens = Ztokens;
        this.winks = winks;
        this.jumpPos = jumpPos;
        this.spiderMove = spiderMove;
    }
    /**
     * This method allows a card to be initialized with a number of moves that a player or wolf nightmare can take.
     * Players can sometimes choose between what number of spaces they want to move, making an int[] struture
     * necessary to capture the options
     * @param moves contains the number of spaces a player or wolf nightmare should traverse on the board
     */
    public void setMoves(int[] moves) {
        this.moves = moves;
    }
    /**
     * This method allows a card to be initialized with a number of Ztokens that a player can catch
     * @param Ztokens contains the number of Ztokens that a player can catch if a card contains the option
     */
    public void setZtokens(int Ztokens) {
        this.Ztokens = Ztokens;
    }
    /**
     * This method allows a card to be initialized with a number of winks that a player can gain
     * @param winks contains the number of winks that a player can gain if a card contains the option
     */
    public void setWinks(int winks) {
        this.winks = winks;
    }
    /**
     * This method allows a card to be initialized with the board position a bump nightmare should jump to
     * @param jumpPos contains the board position a bump nightmare will jump to
     */
    public void setJumpPos(int jumpPos) {
        this.jumpPos = jumpPos;
    }
    /**
     * This method allows a card to be initialized with the number of positions a spider nightmare's 
     * web token should be moved forward if the spider is already on the token or if the token is
     * not currently on the board
     * @param spiderMove contains the number of positions a spider nightmare's web token would be moved forward
     */
    public void setSpiderMove(int spiderMove) {
        this.spiderMove = spiderMove;
    }
    /**
     * This method gives the number of cards that are assigned to a card
     * @return int[] containing the moves stored in a card for either a player or a wolf nightmare
     */
    public int[] getMoves() {
        return moves;
    }
    /**
     * This method gives the number of Ztokens that are assigned to a card
     * @return int containing the Ztokens stored in a card for a player
     */
    public int getZtokens() {
        return Ztokens;
    }
    /**
     * This method gives the number of winks that are assigned to a card
     * @return int containing the winks stored in a card for a player
     */
    public int getWinks() {
        return winks;
    }
    /**
     * This method gives the board position that is assigned to a card
     * @return int containing the board position stored in a card for a bump nightmare
     */
    public int getJumpPos() {
        return jumpPos;
    }
    /**
     * This method gives the number of moves for a spider nightmare's web token that is assigned to a card
     * @return int containing the spider nightmare's web token moves stored in a card
     */
    public int getSpiderMove() {
        return spiderMove;
    }
    /**
     * This method says whether or not a card is a nightmare card
     * @return false for a player card; true for a nightmare card
     */
    public boolean isNightmare() {
        return isNightmare;
    }
    /**
     * This method says whether or not a player card is an AND card where both conditions must be carried out
     * @return false for an OR card; true for an AND card
     */
    public boolean bothConditions() {
        return bothConditions;
    }

    public static class Builder {
        /**
         * This class enables Card objects to be built separately with only the elements they need to satisfy the
         * conditions on their card
         */
        private final boolean bothConditions;
        private final boolean isNightmare;

        private int[] moves;
        private int Ztokens = 0;
        private int winks = 0;
        private int jumpPos = 0;
        private int spiderMove = 0;

        /**
         * This constructor creates a Builder object to be used to get the values that are required for a
         * Card object to be built
         * @param bothConditions says whether a card requires both conditions to be done i.e., an AND card
         * @param isNightmare says whether a card is a nightmare or not
         */
        public Builder (boolean bothConditions, boolean isNightmare) {
            this.bothConditions = bothConditions;
            this.isNightmare = isNightmare;
        }

        /**
         * This method allows a value other than the default one to be set for the variable containing the moves
         * associated with a card so that a controller can know whether or not a card should be displayed
         * with options to move
         * @param moves contains the number of spaces a player or wolf nightmare should traverse on the board
         * @return the Builder object that contains the moves to be associated with a card
         */
        public Builder withMoves(int[] moves) {
            this.moves = moves;
            return this;
        }
        /**
         * This method allows a value other than the default one to be set for the variable containing the Ztokens
         * associated with a card so that a controller can know whether or not a card should be displayed
         * with options to catch tokens
         * @param Ztokens contains the number of Ztokens that a player can catch if a card contains the option
         * @return
         */
        public Builder withZtokens(int Ztokens) {
            this.Ztokens = Ztokens;
            return this;
        }

        /**
         * This method allows a value other than the default one to be set for the variable containing the winks
         * associated with a card so that a controller can know whether or not a card should be displayed
         * with options to gain winks
         * @param winks contains the number of winks that a player can gain if a card contains the option
         * @return
         */
        public Builder withWinks(int winks) {
            this.winks = winks;
            return this;
        }
        /**
         * This method allows a value other than the default one to be set for the variable containing the jump position
         * associated with a card so that a controller can know whether or not a card should be displayed
         * with details of a bump nightmare
         * @param jumpPos contains the board position a bump nightmare will jump to
         * @return
         */
        public Builder withJumpPos(int jumpPos) {
            this.jumpPos = jumpPos;
            return this;
        }

        /**
         * This method allows a value other than the default one to be set for the variable containing the moves
         * associated with a spider nightmare so that a controller can know whether or not a card should be displayed
         * with options to move
         * @param spiderMove contains the number of positions a spider nightmare's web token would be moved forward
         * @return
         */
        public Builder withSpiderMove(int spiderMove) {
            this.spiderMove = spiderMove;
            return this;
        }

        /**
         * This method handles the building of the Card object with all of the variables that are associated with a card
         * object
         * @return a Card built with specific features
         */
        public Card build() {
            Card card = new Card(bothConditions, isNightmare);
            card.setMoves(moves);
            card.setZtokens(Ztokens);
            card.setWinks(winks);
            card.setJumpPos(jumpPos);
            card.setSpiderMove(spiderMove);
            return card;
        }
    }
}
