import java.util.ArrayList;
import java.util.Arrays;

import javax.smartcardio.Card;

public class DeckGenerator {
    private static final int NIGHTMARELEN = 10;
    private static final int PLAYERTWOLEN = 30;
    
    private Deck deck;
    private int players;
    private int nightmare;

    //Solo and 2 player information
    private ArrayList<Boolean> isNightmarePlayer2 = new ArrayList<>();
    private ArrayList<Boolean> bothConditionsPlayer2 = new ArrayList<>();
    private int[] movesListPlayerTwo = {{2}, {3}, {4}, {4}, {1, 2}, {1, 2}, {6}, {5}, {4}, {4}, {4}, {4}, {6}, {1}, {4}, {6}, {3}, {3}, {3}, {2}, {2}, 
                                    {3}, {3}, {1, 5}, {1, 5}, {7}, {7}, {3}, {5}, {1}};
    private ArrayList<Integer> movesPlayer2 = new ArrayList<Integer>(Arrays.asList(movesListPlayerTwo));
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
    private ArrayList<Integer> movesWolf = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 2, 2, 2, 2, 10, 10));
    

    //Bump
    private ArrayList<Integer> jumpPosBump = new ArrayList<Integer>(Arrays.asList(3, 3, 2, 2, 2, 2, 1, -1, -1, -2));


    //Spider
    private ArrayList<Integer> spiderMove = new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 3, 3, 2, 2, 2, 2));
    
    public DeckGenerator (Deck deck, int players, int nightmare) {
        this.deck = deck;
        this.players = players;
        this.nightmare = nightmare;
    }

    //Solo and 2 player information
    public ArrayList<Boolean> fillFalse(ArrayList<Boolean> playerCards) {
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
    public ArrayList<Integer> fillZeroPlayerTwo(ArrayList<Integer> cards) {
        for (int i = 0; i < PLAYERTWOLEN; i++) {
            cards.add(0);
        }
        return cards;
    }

    //Nightmare information
    public ArrayList<Boolean> fillTrue(ArrayList<Boolean> nightmareCards) {
        for (int i = 0; i < NIGHTMARELEN; i++) {
            nightmareCards.add(true);
        }
        return nightmareCards;
    }

    public ArrayList<Boolean> fillFalseNightmare(ArrayList<Boolean> cards) {
        for (int i = 0; i < NIGHTMARELEN; i++) {
            cards.add(false);
        }
        return cards;
    }

    public ArrayList<Integer> fillZeroNightmare(ArrayList<Integer> cards) {
        for (int i = 0; i < NIGHTMARELEN; i++) {
            cards.add(0);
        }
        return cards;
    }


    public void makeDeck() {
        //private ArrayList<Boolean> nightmarePlayer2 = new ArrayList<>();
        isNightmarePlayer2 = fillFalse(isNightmarePlayer2);
        bothConditionsPlayer2 = fillFalse(bothConditionsPlayer2);
        //jumpPosPlayer2 = fillZeroPlayerTwo(jumpPosPlayer2);
        //spiderMovePlayer2 = fillZeroPlayerTwo(spiderMovePlayer2);

        isNightmareReal = fillTrue(isNightmareReal);
        bothConditionsFalse = fillFalse(bothConditionsFalse);

        for (int i = 0; i < PLAYERTWOLEN; i++) {
            Card newCard = new Card.Builder(isNightmarePlayer2.get(i), bothConditionsPlayer2.get(i))
                .withMoves(movesPlayerTwo.get(i))
                .withZtokens(ZtokensPlayer2.get(i))
                .withWinks(winksPlayer2.get(i))
                .build();
            deck.add(newCard);
        }

        if (nightmare == 1) {
            for (int i = 0; i < NIGHTMARELEN; i++) {
                Card newCard = new Card.Builder(isNightmareReal.get(i), bothConditionsFalse.get(i))
                    .withMoves(movesWolf.get(i))
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
    }

}
