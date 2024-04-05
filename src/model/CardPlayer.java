package model;
import java.util.ArrayList;

import controller.ModelListenerCardPlayer;

/**
 * @author Julien Ouellette
 * @version 1.0
 * @author Danae Morrison
 * @version 2.0- MVC compliant
 */

public class CardPlayer {
    public final int MOVE_ABILITY = 1;
    public final int KEEP_PLAY = 0;
    public final int CALL_NIGHT = 1;

    private ArrayList<ModelListenerCardPlayer> listeners = new ArrayList<>();

    public void addListener(ModelListenerCardPlayer listener) {
        listeners.add(listener);
    }
    //public final int MAX_ABILITY_OPTIONS = 3;
    /**
     * Plays a card and performs the appropriate operations specified by a card for the given player.
     * 
     * @param card Card to be played.
     * @param player Player playing the card.
     */
    
    // maybe have the method below return a string with a message saying what action was taken.
    // both action and if a player got scared or scared awake

    public String playCard(Card card, Player player, Nightmare nightmare, int ability, PlayerBoard board) {
        String response = player.getName();
        switch(ability){
            case 2:
                player.setWinks(player.getWinks() + card.getWinks());
                response += " has gained " + String.valueOf(player.getWinks()) + " winks";
            case 3:
                //add ztokens and infinite ztokens, no dreamtile functionality yet
        }
        return response; 
    }

    public String movePlayer(Player player, Nightmare nightmare, PlayerBoard board, int moveAmount) {
        board.advance(moveAmount);
        String response = player.getName() + " has moved by " + String.valueOf(moveAmount) + " spaces\n";
        if(board.getIndex() == nightmare.getBoard().getIndex()){
            response += nightmareCollision(player);
        }
        return response;
    }

    /**
     * Handles "collisions" between a Nightmare and Player, and scares the player appropriately.
     * 
     * @param player Player to get scared!
     */

    /** public void nightmareCollision(Player player){
        player.setScaredStatus(player.isScared() + 1);
        if(player.isScared() > 1){
            player.setAwake(true);
        }
    }*/

    public String nightmareCollision(Player player) {
        String status;
        player.setScaredStatus(player.isScared() + 1);
        if(player.isScared() > 1){
            player.setAwake(true);
            status = player.getName() + " got scared awake!\n";
            // functionality to reduce winks to 0
            player.setWinks(0);
            return status;
        }
        status = player.getName() + " got scared!\n";
        return status;
    }

    /**
     * Deals with playing Nightmare cards, and executes all the given moves from given card.
     * 
     * @param card nightmare card
     * @param nightmare nightmare
     * @param players players to scare
     */
    public boolean playNightmareCard(Card card, Nightmare nightmare, ArrayList<Player> players) {
        // should rename "moves" to "move"
        PlayerBoard playerBoard;
        NightmareBoard nightmareBoard = nightmare.getBoard();

        int moves = card.getMoves()[0]; //nightmare cards only have 1 move option
        boolean nightmareCrossed = false;

        if(moves > 0 && moves != 10) {

            int[] path = nightmareBoard.traveledSpaces(moves);

            int end = path.length;

            if(nightmare.getBoard().isCrossing(moves)){
                end = getMaxIndex(path) + 1;
                nightmareCrossed = true;
            }

            for(Player p : players){
                playerBoard = p.getBoard();
                for(int i = 0; i < end; i++){
                    if(playerBoard.occupied(path[i])){
                        nightmareCollision(p);
                    }
                }
            }
            nightmare.getBoard().advance(moves);
        }

        if (moves == 10) {
            for(Player p : players) {
                playerBoard = p.getBoard();
                if (playerBoard.getIndex() == ((nightmareBoard.getIndex() - 1) % 10) || playerBoard.getIndex() == nightmareBoard.getIndex() || playerBoard.getIndex() == ((nightmareBoard.getIndex() + 1) % 10)) {
                    nightmareCollision(p);
                }
            }
        }

        nightmare.getBoard().jump(card.getJumpPos());
        
        return nightmareCrossed;

        //TODO: something something spider token for different nightmares?
        // spider nightmare uses spiderMove
        // Bump nightmare uses jumpPost
        
    }

    private static int getMaxIndex(int[] in){
        int maxIndex = 0;
        for(int i = 0; i < in.length; i++){
            if(in[i] > in[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
    /*
     * Returns the amount of moves from a given int[] taken from a card, depending on user input.
     * 
     * @param moves Array of moves from a card.
     * @return Returns the appropriate amount of moves depending on user input.
     *
    private int multiMoveOptions(int[] moves){
        int firstMove = moves[0];
        int secondMove = moves[1];
        int selectedMove = notifyListenersRequestSpecificMove(moves);

        while (selectedMove != firstMove && selectedMove != secondMove) {
            //make another function of this for controller and model to print out error message and ask for choice
            selectedMove = notifyListenersRepeatRequestSpecificMove(moves);
        }
        return selectedMove;
    }

    /*
     * Deals with and resolves a player crossing the fence by adding 5 winks to the player, and allows the player to wake up if desired.
     * 
     * @param player Player crossing fence
     
    }*/

    public void resolveFenceCrossing(Player player, int wakingUp){
        player.setWinks(player.getWinks() + 5);
        
        if(wakingUp == 1){
            player.setAwake(true);
        }

        player.setCrossed(true);
    }

    /**
     * Gets a move amount from a user depending on the card's specifications.
     * 
     * @param moves Move array from card
     * @return Length of array containing moves
     *
    }*/

    public int getMovesLength(int[] moves){
            return moves.length;
    }

    public int getValidCardOptions(Card card) {
        int secondAbility = 0;

        if (card.getWinks() != 0) {
            secondAbility = 2;
        } else {
            secondAbility = 3;
        }
        return secondAbility;
    }

    public boolean isSpecificMoveValid(int selectedMove, int[] moves) {
        int firstMove = moves[0];
        int secondMove = moves[1];

        if (selectedMove != firstMove && selectedMove != secondMove) {
            return false;
        }
        return true;
    }

    public boolean isAbilityChoiceValid(int abilityChoice, int secondAbility) {
        boolean validOption = true;
        if (abilityChoice != MOVE_ABILITY && abilityChoice != secondAbility) {
            validOption = false;
        }
        return validOption;
    }

    public boolean isWakingUpValid(int wakingUp) {
        boolean validOption = true;
        if (wakingUp != KEEP_PLAY && wakingUp != CALL_NIGHT) {
            validOption = false;
        }
        return validOption;
    }
}