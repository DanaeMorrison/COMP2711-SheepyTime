package model;
/**
 * @author Danae Morrison
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.Arrays;

public class DeckGenerator {
    /**
     * This class is responsible for generating the cards that are to be added to the deck for an instantiation of
     * the game according to the number of players and the chosen nightmare.
     * It contains lists that store specific numbers associated with different cards for the same area of information,
     * e.g., the number of moves associated with all two player cards, so that loops can be used to add the information
     * to a specific card and add it to a deck. 
     */
    private static final int NIGHTMARELEN = 10;
    private static final int PLAYERTWOLEN = 30;
    
    private int players;
    private int nightmare;

    //Solo and 2 player information
    private ArrayList<Boolean> isNightmarePlayer2 = new ArrayList<>();
    private ArrayList<Boolean> bothConditionsPlayer2 = new ArrayList<>();
    private int[][] movesPlayer2 = {{2}, {3}, {4}, {4}, {1, 2}, {1, 2}, {6}, {5}, {4}, {4}, {4}, {4}, {6}, {1}, {4}, {6}, {3}, {3}, {3}, {2}, {2}, 
                                    {3}, {3}, {1, 5}, {1, 5}, {7}, {7}, {3}, {5}, {1}};
    //private ArrayList<Integer> movesPlayer2 = new ArrayList<Integer>(Arrays.asList(movesListPlayerTwo));
    private ArrayList<Integer> ZtokensPlayer2 = new ArrayList<Integer>(Arrays.asList(2, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 
                                                                                0, 1, 1, 1, 2, 2, 1, 1, 0, 0, 0, 0, 1, 0, 1));
    private ArrayList<Integer> winksPlayer2 = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 
                                                                                0, 0, 0, 0, 0, 0, 0, 2, 0));
    //private ArrayList<Integer> jumpPosPlayer2 = new ArrayList<Integer>();
    //private ArrayList<Integer> spiderMovePlayer2 = new ArrayList<Integer>();

    //Nightmare information
    private ArrayList<Boolean> isNightmareReal = new ArrayList<>();
    private ArrayList<Boolean> bothConditionsFalse = new ArrayList<>();
    //private ArrayList<Integer> ZtokensZero = new ArrayList<Integer>();
    //private ArrayList<Integer> winksZero = new ArrayList<Integer>();

    //private ArrayList<Integer> movesZero = new ArrayList<Integer>();
    //private ArrayList<Integer> jumpPosZero = new ArrayList<Integer>();
    //private ArrayList<Integer> spiderMoveZero = new ArrayList<Integer>();
    
    //Wolf
    private int[][] movesWolf = {{1}, {1}, {1}, {1}, {2}, {2}, {2}, {2}, {10}, {10}};
    

    //Bump
    private ArrayList<Integer> jumpPosBump = new ArrayList<Integer>(Arrays.asList(3, 3, 2, 2, 2, 2, 1, -1, -1, -2));


    //Spider
    private ArrayList<Integer> spiderMove = new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2, 2, 2));
    
    /**
     * This constructor creates a DeckGenerator that will be used to make the cards needed for an instantiation of
     * a game and add them to the deck for the game
     * @param deck stores the cards (player and nightmare) that will be used in a game
     * @param players stores the number of players for a given game
     * @param nightmare stores the int associated with the chosen nightmare for a game
     */
    public DeckGenerator (int players, int nightmare) {
        this.players = players;
        this.nightmare = nightmare;
    }

    //Solo and 2 player information
    private ArrayList<Boolean> fillFalse(ArrayList<Boolean> playerCards) {
        for (int i = 0; i < PLAYERTWOLEN; i++) {
            playerCards.add(false);
        }
        return playerCards;
    }

    /*public void fillBothConditions() {
        for (int i = 0; i < 30; i++) {
            bothConditions.add(false);
        }
    }
    */
    private ArrayList<Integer> fillZeroPlayerTwo(ArrayList<Integer> cards) {
        for (int i = 0; i < PLAYERTWOLEN; i++) {
            cards.add(0);
        }
        return cards;
    }

    //Nightmare information
    private ArrayList<Boolean> fillTrue(ArrayList<Boolean> nightmareCards) {
        for (int i = 0; i < NIGHTMARELEN; i++) {
            nightmareCards.add(true);
        }
        return nightmareCards;
    }

    private ArrayList<Boolean> fillFalseNightmare(ArrayList<Boolean> cards) {
        for (int i = 0; i < NIGHTMARELEN; i++) {
            cards.add(false);
        }
        return cards;
    }

    private ArrayList<Integer> fillZeroNightmare(ArrayList<Integer> cards) {
        for (int i = 0; i < NIGHTMARELEN; i++) {
            cards.add(0);
        }
        return cards;
    }

    /**
     * This method cretaes and add cards to the deck for the game
     */
    public Deck makeDeck(Deck deck) {
        //private ArrayList<Boolean> nightmarePlayer2 = new ArrayList<>();

        // TODO: this could be simplified. the sleep cards for 2 players will always be false for
        // nightmare and both conditions, so the card can be constructed in the loop
        // with just false passed in those positions
        isNightmarePlayer2 = fillFalse(isNightmarePlayer2);
        bothConditionsPlayer2 = fillFalse(bothConditionsPlayer2);
        //jumpPosPlayer2 = fillZeroPlayerTwo(jumpPosPlayer2);
        //spiderMovePlayer2 = fillZeroPlayerTwo(spiderMovePlayer2);

        isNightmareReal = fillTrue(isNightmareReal);
        bothConditionsFalse = fillFalse(bothConditionsFalse);

        for (int i = 0; i < PLAYERTWOLEN; i++) {
            Card newCard = new Card.Builder(isNightmarePlayer2.get(i), bothConditionsPlayer2.get(i))
                .withMoves(movesPlayer2[i])
                .withZtokens(ZtokensPlayer2.get(i))
                .withWinks(winksPlayer2.get(i))
                .build();
            deck.add(newCard);
        }

        if (nightmare == 1) {
            for (int i = 0; i < NIGHTMARELEN; i++) {
                Card newCard = new Card.Builder(isNightmareReal.get(i), bothConditionsFalse.get(i))
                    .withMoves(movesWolf[i])
                    .build();
                deck.add(newCard);
            }
        }

        if (nightmare == 2) {
            for (int i = 0; i < NIGHTMARELEN; i++) {
                Card newCard = new Card.Builder(isNightmareReal.get(i), bothConditionsFalse.get(i))
                    .withJumpPos(jumpPosBump.get(i))
                    .build();
                deck.add(newCard);
            }
        }

        if (nightmare == 3) {
            for (int i = 0; i < NIGHTMARELEN; i++) {
                Card newCard = new Card.Builder(isNightmareReal.get(i), bothConditionsFalse.get(i))
                    .withSpiderMove(spiderMove.get(i))
                    .build();
                deck.add(newCard);
            }
        }

        // isn't just one loop needed to check values for a given index for one card?
        return deck;
    }

}
