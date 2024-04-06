package model;
import java.util.ArrayList;

/**
 * RacingPhase Model Class
 * 
 * @author Julien Ouellette
 * @version 1.0
 * @author Danae Morrison
 * @version 2.0- MVC compliant
 */

public class RacingPhase {
    public final int FIRST_CARD = 0;
    public final int SECOND_CARD = 1;

    private boolean nightmareHasCrossed;
    private ArrayList<Player> players;
    private Deck deck;
    private Nightmare nightmare;
    // private ArrayList<ModelListenerRacingPhase> listeners = new ArrayList<>();
    private DreamTileBoard dreamTileBoard;
    // private CardPlayer cardPlayer;
    // private int cardChoice = -1;


    public RacingPhase(ArrayList<Player> players, Deck deck, Nightmare nightmare){
        this.players = players;
        this.deck = deck;
        this.nightmare = nightmare;
        
        //this.dreamTileBoard = dreamTileBoard;
        
        // this.cardPlayer = cardPlayer;
        nightmareHasCrossed = false;
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

    public void setDreamTileBoard(DreamTileBoard dreamTileBoard) {
        this.dreamTileBoard = dreamTileBoard;
    }

    public boolean getNightmareHasCrossed() {
        return nightmareHasCrossed;
    }

    public void setNightmareHasCrossed(boolean nightmareHasCrossed) {
        this.nightmareHasCrossed = nightmareHasCrossed;
    }

    /**
    public void setCardChoice(int cardChoice) {
        if (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
            //throw some error. should it be try and catch? 
        } else {
            this.cardChoice = cardChoice;
        }
    }*/
    
    // rename resolveCard. IGNORE for now
    public boolean isCardChoiceValid(int cardChoice) {
        boolean validChoice = true;
        if (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
            validChoice = false;
          }
        /** if (validChoice) {
            // do card player
        }*/
        return validChoice;
    }
}