package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import controller.ModelListenerRacing;
import view.BoardViewer;

public class RacingPhase {
    private ArrayList<Player> players;
    private Deck deck;
    private Nightmare nightmare;
    private List<ModelListenerRacingPhase> listeners = new ArrayList<>();

    public RacingPhase(ArrayList<Player> players, Deck deck, Nightmare nightmare){
        this.players = players;
        this.deck = deck;
        this.nightmare = nightmare;
    }

    public void addListener(ModelListenerRacingPhase listener) {
        listeners.add(listener);
    }

    public void startPhase(){
        int playerCount = players.size();
        Player curr;
        Card picked;
        int cardChoice;
        ArrayList<Integer> nightmareCardIndices = new ArrayList<>();
        boolean nightmareFound;
        ArrayList<Card> usedCards = new ArrayList<>();

        while(!allPlayersAwake(players)){
            for(int i = 0; i < playerCount; i++){
                nightmareCardIndices.clear();
                nightmareFound = false;
    
                curr = players.get(i);
                ArrayList<Card> hand = curr.getHand();
    
                for(int j = 0; j < hand.size(); j++){
                    System.out.println(curr.getName() + "'s card #" + j);
    
                    if(curr.getHand().get(i).isNightmare()){
                        nightmareFound = true;
                        nightmareCardIndices.add(j);
                    }
                    
                    notifyListenersPrintCard(curr.getHand().get(j), nightmare);
                }
                
                cardChoice = notifyListenersAskCardChoice();

                picked = hand.get(cardChoice);
                usedCards.add(picked);
                hand.remove(cardChoice);

                // If CardPlayer is instantiated outside of this class and passed to the controller
                // then an indication is needed here to alert the controller to play the card based
                // on that object outside the class, passing the picked card, the current player, and
                // and the nightmare to the controller method through the notify method 

                CardPlayer cardPlayer = new CardPlayer();
                if(picked.isNightmare()){
                    cardPlayer.playNightmareCard(picked, nightmare, players);
                }
                else{
                    cardPlayer.playCard(picked, curr, nightmare);
                }
                // maybe make this a helper function- fillHand
                while (hand.size() < 2) {
                    hand.add(deck.takeCard());
                }

                BoardViewer.showBoard(players, nightmare);
            }
        }
        replaceUsedCards(usedCards, deck);
    }

    /**
     * Used to shuffle back in all the used cards.
     * 
     * @param used used cards to fill "toFill" deck
     * @param toFill deck to be filled by the used deck
     */
    public void replaceUsedCards(ArrayList<Card> used, Deck toFill){
        while(!used.isEmpty()){
            Card temp = used.getFirst(); //get()?
            toFill.add(temp);
            used.remove(temp);
        }
        toFill.shuffle(); //shouldn't be needed anymore with new style of getting a random card from deck
    }

    public boolean allPlayersAwake(ArrayList<Player> players){
        int awakeCount = 0;
        for(Player p : players){
            if(p.isAwake()){
                awakeCount++;
            }
        }
        return (awakeCount == players.size());
    }

    private void notifyListenersPrintCard(Card card, int nightmare) {
        for (ModelListenerRacingPhase listener: listeners) {
            listener.onRequestPrintCard(card, nightmare);
        }
    }

    private int notifyListenersAskCardChoice() {
        int cardChoice;
        for (ModelListenerRacingPhase listener: listeners) {
            cardChoice = listener.onRequestCardChoice();
        }
        return cardChoice;
    }
}
