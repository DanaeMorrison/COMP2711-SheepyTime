import java.util.ArrayList;
import java.util.Arrays;

public class DeckGenerator {
    private Deck deck;
    private int players;
    private int nightmare;

    //Solo and 2 player information
    private ArrayList<Boolean> isNightmarePlayer2 = new ArrayList<>();
    private ArrayList<Boolean> bothConditionsPlayer2 = new ArrayList<>();
    //private ArrayList<Integer> moves = new ArrayList<Integer>(Arrays.asList());
    private int[] movesList = {{2}, {3}, {4}, {4}, {1, 2}, {1, 2}, {6}, {5}, {4}, {4}, {4}, {4}, {6}, {1}, {4}, {6}, {3}, {3}, {3}, {2}, {2}, 
                                    {3}, {3}, {1, 5}, {1, 5}, {7}, {7}, {3}, {5}, {1}};
    private ArrayList<Integer> ZtokensPlayer2 = new ArrayList<Integer>(Arrays.asList(2, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 
                                                                                0, 1, 1, 1, 2, 2, 1, 1, 0, 0, 0, 0, 1, 0, 1));
    private ArrayList<Integer> winksPlayer2 = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 
                                                                                0, 0, 0, 0, 0, 0, 0, 2, 0));
    private ArrayList<Integer> jumpPosPlayer2 = new ArrayList<Integer>();

    //Nightmare information
    private ArrayList<Boolean> isNightmareReal = new ArrayList<>();
    private ArrayList<Boolean> bothConditionsFalse = new ArrayList<>();
    private ArrayList<Integer> ZtokensFalse = new ArrayList<Integer>();
    private ArrayList<Integer> winksFalse = new ArrayList<Integer>();

    //add a no moves

    //Wolf
    private ArrayList<Integer> jumpPosFalse = new ArrayList<Integer>();
    //add moves

    //Bump
    private ArrayList<Integer> jumpPosBump = new ArrayList<Integer>();


    //Spider
    private ArrayList<Integer> jumpPosSpider = new ArrayList<Integer>();
    
    public DeckGenerator (Deck deck, int players, int nightmare) {
        this.deck = deck;
        this.players = players;
        this.nightmare = nightmare;
    }

    //Solo and 2 player information
    public ArrayList<Boolean> fillFalse(ArrayList<Boolean> playerCards) {
        for (int i = 0; i < 30; i++) {
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
    public ArrayList<Integer> fillZero(ArrayList<Integer> cards) {
        for (int i = 0; i < 30; i++) {
            cards.add(0);
        }
        return cards;
    }

    //Nightmare information
    public ArrayList<Boolean> fillTrue(ArrayList<Boolean> nightmareCards) {
        for (int i = 0; i < 10; i++) {
            nightmareCards.add(false);
        }
        return nightmareCards;
    }

    public ArrayList<Boolean> fillFalseNightmare(ArrayList<Boolean> cards) {
        for (int i = 0; i < 10; i++) {
            cards.add(false);
        }
        return cards;
    }

    public ArrayList<Integer> fillZeroNightmare(ArrayList<Integer> cards) {
        for (int i = 0; i < 10; i++) {
            cards.add(0);
        }
        return cards;
    }


    public void makeDeck() {
        //for i
        // isn't just one loop needed to check values for a given index for one card?
    }

}
