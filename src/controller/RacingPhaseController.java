package controller;

import java.util.ArrayList;

import model.Card;
import model.Player;
import model.Nightmare;
import model.CardPlayer;
import model.RacingPhase;
import model.Deck;
import model.DreamTile;
import model.DreamTileBoard;
import model.PlayerBoard;

import view.BoardViewer;
import view.CardViewer;
import view.RacingPhaseViewer;

public class RacingPhaseController /**implements ModelListenerRacingPhase, ModelListenerCardPlayer*/ {
    public final int FIRST_CARD = 0;
    public final int SECOND_CARD = 1;
    
    private RacingPhase racingPhase;
    CardPlayer cardPlayer = new CardPlayer();
    CardViewer cardViewer = new CardViewer();
    RacingPhaseViewer racingPhaseViewer = new RacingPhaseViewer();

    public RacingPhaseController(RacingPhase racingPhase/*, CardPlayer cardPlayer*/) {
        this.racingPhase = racingPhase;
        //this.cardPlayer = cardPlayer;
    }

    public void startPhase() {
        // racingPhase.startPhase();
        ArrayList<Player> players = racingPhase.getPlayers();
        int playerCount = racingPhase.getPlayerSize();
        Nightmare nightmare = racingPhase.getNightmare();
        DreamTileBoard dreamTileBoard = racingPhase.getDreamTileBoard();
        Deck deck = racingPhase.getDeck();
        // boolean nightmareHasCrossed = racingPhase.getNightmareHasCrossed();
        boolean nightmareHasCrossed = false;
        // boolean oneSleepingPlayer;

        Player curr;
        Card picked;
        int cardChoice;
        ArrayList<Card> usedCards = new ArrayList<>();


        while(!allPlayersAwake(players)){
            for(int i = 0; i < playerCount; i++){
                // should curr (current player) be stored in the model?
                curr = players.get(i);
                if(curr.isAwake()){
                    continue;
                }

                fillHand(curr, players, usedCards, deck, nightmare);

                if(nightmareHasCrossed){
                    for(Player p : players){
                        if(!p.isAwake()){
                            p.setWinks(0);
                            p.setAwake(true);
                        }
                    }
                    return; // racing phase done if nightmare has crossed. Perhaps some details should be returned with it/updated in racing phase, such as the setting the phase's dreamtileboard to be equal to the one
                            // present at the end of the phase. we need to make sure that whenever a racing phase begins again, appropriate information is stored in RacingPhase to facilitate continuing the game
                            // from the start of a new racing phase, but with updated details (the new dreamtile board from the resting phase, for example. This means that the resting phase has to be able to access the 
                            // final )
                }

                ArrayList<Card> hand = curr.getHand();
    
                for(int j = 0; j < hand.size(); j++){
                    Card currCard = curr.getHand().get(j);
                    racingPhaseViewer.printCardInHand(curr.getName(), j);
                    cardViewer.rulePrint(currCard.getMoves(), currCard.getJumpPos(), currCard.getSpiderMove(), currCard.getWinks(), currCard.getZtokens(), nightmare.getType(), currCard.isNightmare(), currCard.bothConditions());
                }
                // should the model be updated to store the currently chosen card?
                //cardChoice = racingPhaseViewer.getCardChoice();
                cardChoice = askCardChoice();
                // cardChoice = notifyListenersAskCardChoice();

                // make a version that says if thrown an error, will ask for new input
                // will replace this one
                /** while (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
                    cardChoice = racingPhaseViewer.getCardChoiceOnError();
                }*/

                picked = hand.get(cardChoice);
                usedCards.add(picked);
                hand.remove(cardChoice);

                cardPlayer.playCard(picked, curr, nightmare);

                //TODO: here, the dreamtile is always being used if it can be used. we need to ask the player if they want to use it or not.
                // will put in it's own class like CardPlayer
                dreamTileUser(curr, dreamTileBoard);

                if (onePlayerAsleep(players)) {
                    Card card = deck.takeCard();
                    usedCards.add(card);
            
                    if(card.isNightmare()){
                        // new CardViewer(card, nightmare.getType()).rulePrint();
                        cardViewer.rulePrint(card.getMoves(), card.getJumpPos(), card.getSpiderMove(), card.getWinks(), card.getZtokens(), nightmare.getType(), card.isNightmare(), card.bothConditions());
                        if(cardPlayer.playNightmareCard(card, nightmare, players)){ //ugly syntactically, but it's playing the card *and* returning a true boolean if the nightmare is crossing.
                            racingPhase.setNightmareHasCrossed(true);
                        }
                    }
                }

                // refills a player's hand at the end of their turn
                // code smell: put fillHand and the loop below it together in another function
                // to run at the same time
                fillHand(curr, players, usedCards, deck, nightmare);

                if(nightmareHasCrossed){
                    for(Player p : players){
                        if(!p.isAwake()){
                            p.setWinks(0);
                            p.setScaredStatus(2);
                            p.setAwake(true);
                        }
                    }
                    // make method to print out that the nightmare has jumped the fence (in RacingPhaseViewer)
                    return; //racing phase done if nightmare has crossed
                }

                for(int j = 0; j < hand.size(); j++){
                    Card currCard = curr.getHand().get(j);
                    racingPhaseViewer.printCardInHand(curr.getName(), j);
                    cardViewer.rulePrint(currCard.getMoves(), currCard.getJumpPos(), currCard.getSpiderMove(), currCard.getWinks(), currCard.getZtokens(), nightmare.getType(), currCard.isNightmare(), currCard.bothConditions());
                }

                BoardViewer.showBoard(players, nightmare);
            }
        }
        replaceUsedCards(usedCards, deck);
        // here is where more code should be written to update RacingPhase before the startPhase method closes
        // e.g. racingPhase.setDreamTileBoard(dreamTileBoard); This method hasn't been created yet.
        // loop through all players and access their boards to reset their positions to -1. Same with nightmare 
    }

    /**
     * Used to shuffle back in all the used cards.
     * 
     * @param used used cards to fill "toFill" deck
     * @param toFill deck to be filled by the used deck
     */
    public void replaceUsedCards(ArrayList<Card> used, Deck toFill){
        while(!used.isEmpty()){
            Card temp = used.get(1); //get()?
            toFill.add(temp);
            used.remove(temp);
        }
        toFill.shuffle(); //shouldn't be needed anymore with new style of getting a random card from deck
    }

    public void fillHand(Player player, ArrayList<Player> players, ArrayList<Card> usedCards, Deck deck, Nightmare nightmare){
        
        for(int j = player.getHand().size(); j < 2; j++){
            Card card = deck.takeCard();
            usedCards.add(card);
            
            if(card.isNightmare()){
                // new CardViewer(card, nightmare.getType()).rulePrint();
                cardViewer.rulePrint(card.getMoves(), card.getJumpPos(), card.getSpiderMove(), card.getWinks(), card.getZtokens(), nightmare.getType(), card.isNightmare(), card.bothConditions());
                if(cardPlayer.playNightmareCard(card, nightmare, players)){ //ugly syntactically, but it's playing the card *and* returning a true boolean if the nightmare is crossing.
                    racingPhase.setNightmareHasCrossed(true);
                    return;
                }
                j--;
                continue;
            }

            player.getHand().add(card);
        }
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

    public boolean onePlayerAsleep(ArrayList<Player> players){
        int asleepCount = 0;
        for(Player p : players){
            if(!p.isAwake()){
                asleepCount++;
            }
        }
        return (asleepCount == 1);
    }

    private void dreamTileUser(Player player, DreamTileBoard dreamTileBoard){
        PlayerBoard board = player.getBoard();
        int playerPos = board.getIndex();
        DreamTile tile = dreamTileBoard.getTile(playerPos);
        if(tile.canUse(player)){
            tile.useTile(player);
        }
        tile.removePlayerToken(player);
    }

    private int askCardChoice() {
        boolean validInput = false;
        int cardChoice;
        do {
            cardChoice = racingPhaseViewer.getCardChoiceOnError();
            validInput = racingPhase.isCardChoiceValid(cardChoice);
        } while (!validInput);
        return cardChoice;
    }
}