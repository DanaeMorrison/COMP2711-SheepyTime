package controller;

import java.util.ArrayList;

import model.Card;
import model.Player;
import model.Nightmare;
import model.CardPlayer;
import model.DreamTilePlayer;
import model.RacingPhase;
import model.Deck;
import model.DreamTile;
import model.DreamTileBoard;
import model.PlayerBoard;

import view.BoardViewer;
import view.CardViewer;
import view.RacingPhaseViewer;

/**
 * RacingPhase Model Class
 * 
 * @author Danae Morrison
 * @version 1.0
 */

public class RacingPhaseController /**implements ModelListenerRacingPhase, ModelListenerCardPlayer*/ {
    public final int FIRST_CARD = 0;
    public final int SECOND_CARD = 1;
    
    private RacingPhase racingPhase;
    CardPlayer cardPlayer = new CardPlayer();
    DreamTilePlayer dreamTilePlayer;
    CardViewer cardViewer = new CardViewer();
    RacingPhaseViewer racingPhaseViewer = new RacingPhaseViewer();

    public RacingPhaseController(RacingPhase racingPhase/*, CardPlayer cardPlayer*/) {
        this.racingPhase = racingPhase;
        //this.cardPlayer = cardPlayer;
    }

    public DreamTileBoard startPhase(DreamTileBoard dreamTileBoard) {
        //TODO Update
        racingPhase.setDreamTileBoard(dreamTileBoard);
        // racingPhase.startPhase();
        ArrayList<Player> players = racingPhase.getPlayers();
        int playerCount = racingPhase.getPlayerSize();
        Nightmare nightmare = racingPhase.getNightmare();
        // TODO Update: DreamTileBoard dreamTileBoard = racingPhase.getDreamTileBoard();
        dreamTilePlayer = new DreamTilePlayer(dreamTileBoard);
        Deck deck = racingPhase.getDeck();
        // boolean nightmareHasCrossed = racingPhase.getNightmareHasCrossed();
        // boolean nightmareHasCrossed = false;
        // boolean oneSleepingPlayer;

        Player curr;
        Card picked;
        int cardChoice;
        ArrayList<Card> usedCards = new ArrayList<>();


        while(!allPlayersAwake(players)){
            for(int i = 0; i < playerCount; i++){
                curr = players.get(i);
                if(curr.isAwake()){
                    continue;
                }

                fillHand(curr, players, usedCards, deck, nightmare);

                if(racingPhase.getNightmareHasCrossed()){
                    resetPlayerInfo(players);
                    return dreamTileBoard; 
                }

                ArrayList<Card> hand = curr.getHand();
    
                for(int j = 0; j < hand.size(); j++){
                    Card currCard = curr.getHand().get(j);
                    racingPhaseViewer.printCardInHand(curr.getName(), j);
                    cardViewer.rulePrint(currCard.getMoves(), currCard.getJumpMove(), currCard.getSpiderMove(), currCard.getWinks(), currCard.getZtokens(), nightmare.getType(), currCard.isNightmare(), currCard.bothConditions());
                }
                cardChoice = askCardChoice();

                picked = hand.get(cardChoice);
                usedCards.add(picked);
                hand.remove(cardChoice);

                playCard(picked, curr, nightmare);

                // DreamTile Section
                PlayerBoard board = curr.getBoard();
                int playerPosition = board.getIndex();
                if (dreamTilePlayer.isUsableTilePresent(playerPosition, curr)) {
                    DreamTile thisDreamTile = dreamTileBoard.getTile(playerPosition);
                    int useTileChoice = racingPhaseViewer.getUseTileChoice(thisDreamTile);

                    boolean validInput = dreamTilePlayer.isUseTileChoiceValid(useTileChoice);

                    do {
                        useTileChoice = racingPhaseViewer.getUseTileChoice(thisDreamTile);
                        validInput = dreamTilePlayer.isUseTileChoiceValid(useTileChoice);
                    } while (!validInput);

                    if (useTileChoice == 1) {
                        useDreamTile(curr, players, nightmare, dreamTileBoard, thisDreamTile);
                    }
                }
                
                // Check to see if there is currently only one player asleep in the phase
                // This is to satisfy the rule that a card has to be played
                if (onePlayerAsleep(players)) {
                    // TODO: check if deck is empty. if empty, refill with replaceUsedCards. setter method
                    // may need to be used to access the new Deck after fillHand is complete
                    Card card = deck.takeCard();
                    usedCards.add(card);
            
                    if(card.isNightmare()){
                        // print that a nightmare card has been drawn
                        cardViewer.rulePrint(card.getMoves(), card.getJumpMove(), card.getSpiderMove(), card.getWinks(), card.getZtokens(), nightmare.getType(), card.isNightmare(), card.bothConditions());
                        /*if(cardPlayer.playNightmareCard(card, nightmare, players)){ //ugly syntactically, but it's playing the card *and* returning a true boolean if the nightmare is crossing.
                            racingPhase.setNightmareHasCrossed(true);
                        }*/
                        playNightmareCard(card, nightmare, players);
                        if(racingPhase.getNightmareHasCrossed()){
                            resetPlayerInfo(players);
                            return dreamTileBoard;
                        }
                    }

                // refills a player's hand at the end of their turn
                // code smell: put the loops below this together in another function
                // to run at the same time
                fillHand(curr, players, usedCards, deck, nightmare);

                if(racingPhase.getNightmareHasCrossed()){
                    resetPlayerInfo(players);
                    return dreamTileBoard;
                }

                for(int j = 0; j < hand.size(); j++){
                    Card currCard = curr.getHand().get(j);
                    racingPhaseViewer.printCardInHand(curr.getName(), j);
                    cardViewer.rulePrint(currCard.getMoves(), currCard.getJumpMove(), currCard.getSpiderMove(), currCard.getWinks(), currCard.getZtokens(), nightmare.getType(), currCard.isNightmare(), currCard.bothConditions());
                }

                BoardViewer.showBoard(players, nightmare);
            }
        }
        replaceUsedCards(usedCards, deck);
        return dreamTileBoard;
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
            // TODO: check if deck is empty. if empty, refill with replaceUsedCards. setter method
            //          may need to be used to access the new Deck after fillHand is complete
            Card card = deck.takeCard();
            usedCards.add(card);
            
            if(card.isNightmare()){
                // print that a nightmare card has been pulled
                cardViewer.rulePrint(card.getMoves(), card.getJumpMove(), card.getSpiderMove(), card.getWinks(), card.getZtokens(), nightmare.getType(), card.isNightmare(), card.bothConditions());
                /*if(cardPlayer.playNightmareCard(card, nightmare, players)){ //ugly syntactically, but it's playing the card *and* returning a true boolean if the nightmare is crossing.
                    racingPhase.setNightmareHasCrossed(true);
                    return;
                }*/
                playNightmareCard(card, nightmare, players);
                // check if nightmarehascrossed == true to return
                if (racingPhase.getNightmareHasCrossed()) {
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

    private void useDreamTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard, DreamTile tile){
        // PlayerBoard board = player.getBoard();
        // int playerPos = board.getIndex();
        // DreamTile tile = dreamTileBoard.getTile(playerPos);
        tile.removePlayerToken(player);
        player.setZtokens(player.getZtokens() + 1);
        tile.useTile(player, players, nightmare, dreamTileBoard);
        // make DreamTilePlayer throw exceptions for different scenarios of bad things that can occur?
        // go to the useTile function in each of the tiles to 
    }

    private int askCardChoice() {
        boolean validInput = false;
        int cardChoice;

        cardChoice = racingPhaseViewer.getCardChoice();
        validInput = racingPhase.isCardChoiceValid(cardChoice);
        // follow something like "success = racingPhase.resolveCard(cardChoice);"

        do {
            cardChoice = racingPhaseViewer.getCardChoiceOnError();
            validInput = racingPhase.isCardChoiceValid(cardChoice);
        } while (!validInput);
        return cardChoice;
    }

    private void playCard(Card pickedCard, Player currentPlayer, Nightmare nightmare) {
        boolean validInput = false;
        int abilityChoice;
        int moveAmount;
        String response;

        // cardPlayer.playCard(picked, curr, nightmare);

        int secondAbility = cardPlayer.getValidCardOptions(pickedCard);
        PlayerBoard board = currentPlayer.getBoard();

        if(!pickedCard.bothConditions()) {
            racingPhaseViewer.displayAbilityOptions(secondAbility);
            abilityChoice = racingPhaseViewer.getAbilityChoice(secondAbility);
            validInput = cardPlayer.isAbilityChoiceValid(abilityChoice, secondAbility);

            do {
                abilityChoice = racingPhaseViewer.getAbilityChoiceOnError(secondAbility);
                validInput = cardPlayer.isAbilityChoiceValid(abilityChoice, secondAbility);
            } while (!validInput);

            validInput = false;

            // if ability choice == 1 and getMoveAmount > 1 then need to ask for the length that the user wants to travel
            // then this info could be passed to a different playCard method
            if (abilityChoice == 1) {
                int[] moves = pickedCard.getMoves();
                if (cardPlayer.getMovesLength(moves) > 1) {
                    int selectedMove = racingPhaseViewer.getSpecificMove(moves);
                    validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);

                    do {
                        selectedMove = racingPhaseViewer.getSpecificMoveOnError(moves);
                        validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);
                    } while (!validInput);

                    validInput = false;
                    moveAmount = selectedMove;
                } else {
                    moveAmount = moves[0];
                }

                if (board.isCrossing(moveAmount)) {
                    int wakingUp = racingPhaseViewer.getPlayOrCallNight();
                    validInput = cardPlayer.isWakingUpValid(wakingUp);

                    do {
                        wakingUp = racingPhaseViewer.getPlayOrCallNightOnError();
                        validInput = cardPlayer.isWakingUpValid(wakingUp);
                    } while (!validInput);

                    cardPlayer.resolveFenceCrossing(currentPlayer, wakingUp);
                }

                response = cardPlayer.movePlayer(currentPlayer, nightmare, board, moveAmount);
            } else {
                // otherwise, this method can be played
                response = cardPlayer.playCard(pickedCard, currentPlayer, nightmare, abilityChoice);
            }
        } else {
            // make a racingphaseviewer method to print "All abilities on the card will be played"
            int[] moves = pickedCard.getMoves();
                if (cardPlayer.getMovesLength(moves) > 1) {
                    int selectedMove = racingPhaseViewer.getSpecificMove(moves);
                    validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);

                    do {
                        selectedMove = racingPhaseViewer.getSpecificMoveOnError(moves);
                        validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);
                    } while (!validInput);

                    validInput = false;
                    moveAmount = selectedMove;
                } else {
                    moveAmount = moves[0];
                }

                if (board.isCrossing(moveAmount)) {
                    int wakingUp = racingPhaseViewer.getPlayOrCallNight();
                    validInput = cardPlayer.isWakingUpValid(wakingUp);

                    do {
                        wakingUp = racingPhaseViewer.getPlayOrCallNightOnError();
                        validInput = cardPlayer.isWakingUpValid(wakingUp);
                    } while (!validInput);

                    cardPlayer.resolveFenceCrossing(currentPlayer, wakingUp);
                }

                response = cardPlayer.movePlayer(currentPlayer, nightmare, board, moveAmount);
                response += cardPlayer.playCard(pickedCard, currentPlayer, nightmare, secondAbility);
                racingPhaseViewer.printCardPlayResponse(response);
        }
    }

    private void playNightmareCard(Card card, Nightmare nightmare, ArrayList<Player> players) {
        if (cardPlayer.isNightmareCrossing(card, nightmare)) {
            racingPhase.setNightmareHasCrossed(true);
        } else {
            String response = cardPlayer.playNightmareCard(card, nightmare, players);
            racingPhaseViewer.printCardPlayResponse(response);
        }
    }

    //make local method to check if nightmare has crossed to modify player info and return dreamtileboard
    private void resetPlayerInfo(ArrayList<Player> players) {
        String response = "";
        for(Player p : players){
            if(!p.isAwake()){
                p.setWinks(0);
                p.setScaredStatus(2);
                p.setAwake(true);
                p.getBoard().emptyBoard();
                response += p.getName() + "got scared awake!\n";
            }
        }
        racingPhaseViewer.printNightmareHasCrossed(response);
    }
}