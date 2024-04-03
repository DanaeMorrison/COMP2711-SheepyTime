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
        Player curr;
        Card picked;
        int cardChoice;
        ArrayList<Card> usedCards = new ArrayList<>();

        while(!allPlayersAwake(players)){
            for(int i = 0; i < playerCount; i++){
                // should curr (current plaeyer) be stored in the model?
                curr = players.get(i);
                if(curr.isAwake()){
                    continue;
                }

                fillHand(curr, players, usedCards, deck, nightmare);

                ArrayList<Card> hand = curr.getHand();
    
                for(int j = 0; j < hand.size(); j++){
                    Card currCard = curr.getHand().get(j);
                    racingPhaseViewer.printCardInHand(curr.getName(), j);
                    cardViewer.rulePrint(currCard.getMoves(), currCard.getJumpPos(), currCard.getSpiderMove(), currCard.getWinks(), currCard.getZtokens(), nightmare.getType(), currCard.isNightmare(), currCard.bothConditions());
                }
                // should the model be updated to store the currently chosen card?
                cardChoice = racingPhaseViewer.getCardChoice();
                // cardChoice = notifyListenersAskCardChoice();

                // make a version that says if thrown an error, will ask for new input
                // will replace this one
                while (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
                    cardChoice = racingPhaseViewer.getCardChoiceOnError();
                }

                picked = hand.get(cardChoice);
                usedCards.add(picked);
                hand.remove(cardChoice);

                cardPlayer.playCard(picked, curr, nightmare);

                //TODO: here, the dreamtile is always being used if it can be used. we need to ask the player if they want to use it or not.
                dreamTileUser(curr, dreamTileBoard);

                // refills a player's hand at the end of their turn
                // code smell: put fillHand and the loop below it together in another function
                // to run at the same time
                fillHand(curr, players, usedCards, deck, nightmare);

                for(int j = 0; j < hand.size(); j++){
                    Card currCard = curr.getHand().get(j);
                    racingPhaseViewer.printCardInHand(curr.getName(), j);
                    cardViewer.rulePrint(currCard.getMoves(), currCard.getJumpPos(), currCard.getSpiderMove(), currCard.getWinks(), currCard.getZtokens(), nightmare.getType(), currCard.isNightmare(), currCard.bothConditions());
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
                cardPlayer.playNightmareCard(card, nightmare, players);
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

    private void dreamTileUser(Player player, DreamTileBoard dreamTileBoard){
        PlayerBoard board = player.getBoard();
        int playerPos = board.getIndex();
        DreamTile tile = dreamTileBoard.getTile(playerPos);
        if(tile.canUse(player)){
            tile.useTile(player);
        }
        tile.removePlayerToken(player);
    }
}