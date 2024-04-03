package model;
import java.util.ArrayList;

public class RacingPhase {
    public final int FIRST_CARD = 0;
    public final int SECOND_CARD = 1;

    private ArrayList<Player> players;
    private Deck deck;
    private Nightmare nightmare;
    // private ArrayList<ModelListenerRacingPhase> listeners = new ArrayList<>();
    private DreamTileBoard dreamTileBoard;
    // private CardPlayer cardPlayer;
    private int cardChoice = -1;

    public RacingPhase(ArrayList<Player> players, Deck deck, Nightmare nightmare, DreamTileBoard dreamTileBoard/**, /**CardPlayer cardPlayer*/){
        this.players = players;
        this.deck = deck;
        this.nightmare = nightmare;
        this.dreamTileBoard = dreamTileBoard;
        // this.cardPlayer = cardPlayer;
    }

    // getter and setter methods
    // setter methods will most likely contain the error handling
    public int getPlayerSize() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Nightmare getNightmare() {
        return nightmare;
    }

    public Deck getDeck() {
        return deck;
    }

    /**
    public int getCardChoice() {
        return cardChoice;
    }*/

    public DreamTileBoard getDreamTileBoard() {
        return dreamTileBoard;
    }

    public void setCardChoice(int cardChoice) {
        if (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
            //throw some error. should it be try and catch? 
        } else {
            this.cardChoice = cardChoice;
        }
    }

    public boolean isCardChoiceValid(int cardChoice) {
        boolean validChoice = true;
        if (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
            validChoice = false;
        }
        return validChoice;
    }
}