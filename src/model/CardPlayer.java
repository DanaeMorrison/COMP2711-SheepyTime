package model;

import java.util.ArrayList;

import model.exception.IllegalMovementException;

/**
 * @author Julien Ouellette
 * @version 1.0
 * @author Danae Morrison
 * @version 2.0- MVC compliant
 */

public class CardPlayer {
    private final int MOVE_ABILITY = 1;
    private final int KEEP_PLAY = 0;
    private final int CALL_NIGHT = 1;

    // private final int MAX_ABILITY_OPTIONS = 3;
    /**
     * Plays a card and performs the appropriate operations specified by a card for
     * the given player.
     * 
     * @param card   Card to be played.
     * @param player Player playing the card.
     */

    // maybe have the method below return a string with a message saying what action
    // was taken.
    // both action and if a player got scared or scared awake

    public String playCard(Card card, Player player, Nightmare nightmare, int ability) {
        String response = player.getName();
        switch (ability) {
            case 2:
                player.setWinks(player.getWinks() + card.getWinks());
                response += " has gained " + String.valueOf(player.getWinks()) + " winks";
            case 3:
                // add ztokens and infinite ztokens, no dreamtile functionality yet
        }
        return response;
    }

    private String

    public String movePlayer(Player player, Nightmare nightmare, PlayerBoard board, int moveAmount) {
        board.advance(moveAmount);
        String response = player.getName() + " has moved by " + String.valueOf(moveAmount) + " spaces\n";
        if (board.getIndex() == nightmare.getBoard().getIndex()) {
            response += nightmareCollision(player);
        }
        return response;
    }

    /**
     * Handles "collisions" between a Nightmare and Player, and scares the player
     * appropriately.
     * 
     * @param player Player to get scared!
     */
    public String nightmareCollision(Player player) {
        String status;
        player.setScaredStatus(player.isScared() + 1);
        if (player.isScared() > 1) {
            player.setAwake(true);
            status = player.getName() + " got scared awake!\n";
            // functionality to reduce winks to 0
            player.setWinks(0);
            // functionality to reset position to -1
            return status;
        }
        status = player.getName() + " got scared!\n";
        return status;
    }

    /**
     * Deals with playing Nightmare cards, and executes all the given moves from
     * given card.
     * 
     * @param card      nightmare card
     * @param nightmare nightmare
     * @param players   players to scare
     */
    /** public boolean playNightmareCard(Card card, Nightmare nightmare, ArrayList<Player> players) {
        // should rename "moves" to "move"
        PlayerBoard playerBoard;
        NightmareBoard nightmareBoard = nightmare.getBoard();

        int moves = card.getMoves()[0]; // nightmare cards only have 1 move option
        boolean nightmareCrossed = false;

        // boolean nightmareCrossed = false;

        if (moves < 0 || moves > 10) {
            throw new IllegalMovementException("BackEnd Error: value should be in 0 and 10");
        }

        if(moves > 0 && moves != 10) {

            int[] path = nightmareBoard.traveledSpaces(moves);

            int end = path.length;

            if(nightmareBoard.isCrossing(moves)){
                // end = getMaxIndex(path) + 1;
                // nightmareCrossed = true;
                return true;
            }

            for(Player p : players){
                // specify that this should be for players that are currently not awake
                playerBoard = p.getBoard();
                for(int i = 0; i < end; i++){
                    if(playerBoard.occupied(path[i])){
                        nightmareCollision(p);
                    }
                }
            }
            nightmare.getBoard().advance(moves);
        

        if (moves == 10) {
            for (Player p : players) {
                playerBoard = p.getBoard();
                if (isAdjacent(playerBoard.getIndex(), nightmareBoard.getIndex())) {
                    nightmareCollision(p);
                }
            }
            return false;
        }

        if (jumpPos != 0) {
            if(nightmareBoard.isCrossing(jumpPos)){
                // end = getMaxIndex(path) + 1;
                //nightmareCrossed = true;
                return true;
            }

            for(Player p : players) {
                playerBoard = p.getBoard();
                if (playerBoard.getIndex() == nightmareBoard.getIndex()) {
                    nightmareCollision(p);
                }
            }

            nightmare.getBoard().advance(moves);

        }

        for (Player p : players) {
            // specify that this should be for players that are currently not awake
            playerBoard = p.getBoard();
            for (int i = 0; i < end; i++) {
                if (playerBoard.occupied(path[i])) {
                    nightmareCollision(p);
                }
            }
        }
        nightmare.getBoard().advance(moves);

        nightmare.getBoard().jump(card.getJumpMove());

        return nightmareCrossed;

        // TODO: something something spider token for different nightmares?
        // spider nightmare uses spiderMove
        // Bump nightmare uses jumpPos
        
    }*/
    /**
     * Determines if playing a nightmare card will result in the nightmare jumping the fence
     * @param card contains the information of the nightmare's movement
     * @param nightmare contains the nightmare board to determine the nightmare's position
     * @return true if the nightmare would cross the fence as a result of their movement,
     *          false if the nightmare will not cross the fence as a result of their movement
     */
    public boolean isNightmareCrossing(Card card, Nightmare nightmare) {
        NightmareBoard nightmareBoard = nightmare.getBoard();

        int moves = card.getMoves()[0];
        int jumpPos = card.getJumpMove();

        if(nightmareBoard.isCrossing(moves)){
            return true;
        }

        if(nightmareBoard.isCrossing(jumpPos)){
            return true;
        }

        return false;
    }

    public String playNightmareCard(Card card, Nightmare nightmare, ArrayList<Player> players) {
        // should rename "moves" to "move"
        String response = "";
        PlayerBoard playerBoard;
        NightmareBoard nightmareBoard = nightmare.getBoard();

        int moves = card.getMoves()[0]; //nightmare cards only have 1 move option
        int jumpPos = card.getJumpMove();

        // boolean nightmareCrossed = false;

        if (moves < 0 || moves > 10) {
            throw new IllegalMovementException("BackEnd Error: value should be in 0 and 10");
        }

        if(moves > 0 && moves != 10) {

            int[] path = nightmareBoard.traveledSpaces(moves);

            int end = path.length;

            for(Player p : players){
                // specify that this should be for players that are currently not awake
                playerBoard = p.getBoard();
                for(int i = 0; i < end; i++){
                    if(playerBoard.occupied(path[i])){
                        response += nightmareCollision(p);
                    }
                }
            }
            nightmare.getBoard().advance(moves);
        }

        if (moves == 10) {
            for(Player p : players) {
                playerBoard = p.getBoard();
                if (playerBoard.getIndex() == ((nightmareBoard.getIndex() - 1) % 10) || playerBoard.getIndex() == nightmareBoard.getIndex() || playerBoard.getIndex() == ((nightmareBoard.getIndex() + 1) % 10)) {
                    response += nightmareCollision(p);
                }
            }
        }

        if (jumpPos != 0) {
            for(Player p : players) {
                playerBoard = p.getBoard();
                if (playerBoard.getIndex() == nightmareBoard.getIndex()) {
                    response += nightmareCollision(p);
                }
            }

            nightmare.getBoard().advance(moves);
        }

        return response;
    }

    
    /* private static int getMaxIndex(int[] in) {
        int maxIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] > in[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }*/

    public void resolveFenceCrossing(Player player, int wakingUp) {
        player.setWinks(player.getWinks() + 5);

        if (wakingUp == 1) {
            player.setAwake(true);
            player.getBoard().emptyBoard();
        }

        player.setCrossed(true);
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