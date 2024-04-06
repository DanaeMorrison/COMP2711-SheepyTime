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
    private final int FIRST_CARD = 0;
    private final int SECOND_CARD = 1;
    private ArrayList<Player> players;
    private Deck deck;
    private Nightmare nightmare;
    // private ArrayList<ModelListenerRacingPhase> listeners = new ArrayList<>();
    private DreamTileBoard dreamTileBoard;
    // private CardPlayer cardPlayer;
    // private int cardChoice = -1;
    private boolean nightmareHasCrossed;

    public RacingPhase(ArrayList<Player> players, Deck deck, Nightmare nightmare) {
        this.players = players;
        this.deck = deck;
        this.nightmare = nightmare;
        DeckGenerator generator = new DeckGenerator(players.size(), nightmare.getType());
        deck = generator.makeDeck(deck);
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
     * public int getCardChoice() {
     * return cardChoice;
     * }
     */

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

    // rename resolveCard. IGNORE for now
    public boolean isCardChoiceValid(int cardChoice) {
        boolean validChoice = true;
        if (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
            validChoice = false;
        }
        return validChoice;
    }
}