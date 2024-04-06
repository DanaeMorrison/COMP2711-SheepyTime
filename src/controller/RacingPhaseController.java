package controller;

import java.util.ArrayList;

import model.Card;
import model.Player;
import model.Nightmare;
import model.CardPlayer;
import model.DreamTilePlayer;
import model.RacingPhase;
import model.exception.GameLogicViolationException;
import model.Deck;
import model.DreamTile;
import model.DreamTileBoard;
import model.PlayerBoard;

import view.BoardViewer;
import view.CardViewer;
import view.DreamTileBoardViewer;
import view.RacingPhaseViewer;

/**
 * RacingPhase Model Class
 * 
 * @author Danae Morrison
 * @version 1.0
 */

public class RacingPhaseController {

    private RacingPhase racingPhase;
    private CardPlayer cardPlayer = new CardPlayer();
    private DreamTilePlayer dreamTilePlayer;
    private CardViewer cardViewer = new CardViewer();
    private RacingPhaseViewer racingPhaseViewer = new RacingPhaseViewer();

    public RacingPhaseController(RacingPhase racingPhase) {
        this.racingPhase = racingPhase;
    }

    public DreamTileBoard startPhase(DreamTileBoard dreamTileBoard) {
        racingPhase.setDreamTileBoard(dreamTileBoard);
        ArrayList<Player> players = racingPhase.getPlayers();
        int playerCount = racingPhase.getPlayerSize();
        Nightmare nightmare = racingPhase.getNightmare();
        dreamTilePlayer = new DreamTilePlayer(dreamTileBoard);
        Deck deck = racingPhase.getDeck();
        // boolean nightmareHasCrossed = racingPhase.getNightmareHasCrossed();
        // boolean nightmareHasCrossed = false;
        // boolean oneSleepingPlayer;

        Player currPlayer;
        Card picked;
        int cardChoice;
        ArrayList<Card> usedCards = new ArrayList<>();

        while(!allPlayersAwake(players)){
            for(int i = 0; i < playerCount; i++){
                currPlayer = players.get(i);
                if(currPlayer.isAwake()){
                    continue;
                }

                fillHand(currPlayer, players, usedCards, deck, nightmare);

                if(racingPhase.getNightmareHasCrossed()){
                    resetPlayerInfo(players);
                    return dreamTileBoard;
                }

                ArrayList<Card> hand = currPlayer.getHand();
                
                showHand(hand, currPlayer, nightmare);
                
                cardChoice = askCardChoice();

                picked = hand.get(cardChoice);
                usedCards.add(picked);
                hand.remove(cardChoice);

                playCard(picked, currPlayer, nightmare);

                // DreamTile Section
                PlayerBoard board = currPlayer.getBoard();
                int playerPosition = board.getIndex();
                if (dreamTilePlayer.isUsableTilePresent(playerPosition, currPlayer)) {
                    DreamTile thisDreamTile = dreamTileBoard.getTile(playerPosition);
                    int useTileChoice = racingPhaseViewer.getUseTileChoice(thisDreamTile);

                    boolean validInput = dreamTilePlayer.isUseTileChoiceValid(useTileChoice);

                    do {
                        useTileChoice = racingPhaseViewer.getUseTileChoice(thisDreamTile);
                        validInput = dreamTilePlayer.isUseTileChoiceValid(useTileChoice);
                    } while (!validInput);

                    if (useTileChoice == 1) {
                        useDreamTile(currPlayer, players, nightmare, dreamTileBoard, thisDreamTile);
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
                        printCard(card, nightmare);
                        // print that a nightmare card has been drawn
                        playNightmareCard(card, nightmare, players);
                        if(racingPhase.getNightmareHasCrossed()){
                            resetPlayerInfo(players);
                            return dreamTileBoard;
                        }
                    }
                }
                // refills a player's hand at the end of their turn
                // code smell: put the loops below this together in another function
                // to run at the same time
                fillHand(currPlayer, players, usedCards, deck, nightmare);

                if(racingPhase.getNightmareHasCrossed()){
                    resetPlayerInfo(players);
                    return dreamTileBoard;
                }

                showHand(hand, currPlayer, nightmare);
                BoardViewer.showBoard(players, nightmare);
            }
        }
        
        replaceUsedCards(usedCards, deck);
        return dreamTileBoard;
    }

    private void showHand(ArrayList<Card> hand, Player currPlayer, Nightmare nightmare) {
        for (int j = 0; j < hand.size(); j++) {
            Card currCard = currPlayer.getHand().get(j);
            racingPhaseViewer.printCardInHand(currPlayer.getName(), j);
            printCard(currCard, nightmare);

        }
    }

    /**
     * Used to shuffle back in all the used cards.
     * 
     * @param used   used cards to fill "toFill" deck
     * @param toFill deck to be filled by the used deck
     */
    public void replaceUsedCards(ArrayList<Card> used, Deck toFill) {
        while (!used.isEmpty()) {
            Card temp = used.get(1); // get()?
            toFill.add(temp);
            used.remove(temp);
        }
        toFill.shuffle(); // shouldn't be needed anymore with new style of getting a random card from deck
    }

    public void fillHand(Player player, ArrayList<Player> players, ArrayList<Card> usedCards, Deck deck, Nightmare nightmare){
        
        for(int j = player.getHand().size(); j < 2; j++){
            // TODO: check if deck is empty. if empty, refill with replaceUsedCards. setter method
            //          may need to be used to access the new Deck after fillHand is complete
            Card card = deck.takeCard();
            usedCards.add(card);
            
            if(card.isNightmare()){
                // print that a nightmare card has been pulled
                printCard(card, nightmare);
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


    private void printCard(Card card, Nightmare nightmare) {
        cardViewer.rulePrint(card.getMoves(), card.getJumpMove(), card.getSpiderMove(), card.getWinks(),
                card.getZtokens(), nightmare.getType(), card.isNightmare(), card.bothConditions());
    }

    public boolean allPlayersAwake(ArrayList<Player> players) {
        int awakeCount = 0;
        for (Player p : players) {
            if (p.isAwake()) {
                awakeCount++;
            }
        }
        return (awakeCount == players.size());
    }

    public boolean onePlayerAsleep(ArrayList<Player> players) {
        int asleepCount = 0;
        for (Player p : players) {
            if (!p.isAwake()) {
                asleepCount++;
            }
        }
        return (asleepCount == 1);
    }

    private void useDreamTile(Player player, ArrayList<Player> players, Nightmare nightmare,
            DreamTileBoard dreamTileBoard, DreamTile tile) {
        // PlayerBoard board = player.getBoard();
        // int playerPos = board.getIndex();
        // DreamTile tile = dreamTileBoard.getTile(playerPos);
        tile.removePlayerToken(player);
        player.setZtokens(player.getZtokens() + 1);
        tile.useTile(player, players, nightmare, dreamTileBoard);
        // make DreamTilePlayer throw exceptions for different scenarios of bad things
        // that can occur?
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

        if (!pickedCard.bothConditions()) {
            racingPhaseViewer.displayAbilityOptions(secondAbility);
            abilityChoice = racingPhaseViewer.getAbilityChoice();
            validInput = cardPlayer.isAbilityChoiceValid(abilityChoice, secondAbility);

            do {
                abilityChoice = racingPhaseViewer.getAbilityChoiceOnError(secondAbility);
                validInput = cardPlayer.isAbilityChoiceValid(abilityChoice, secondAbility);
            } while (!validInput);

            validInput = false;

            // if ability choice == 1 and getMoveAmount > 1 then need to ask for the length
            // that the user wants to travel
            // then this info could be passed to a different playCard method
            if (abilityChoice == 1) {
                int[] moves = pickedCard.getMoves();
                if (moves.length > 1) {
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
            // make a racingphaseviewer method to print "All abilities on the card will be
            // played"
            int[] moves = pickedCard.getMoves();
            if (moves.length > 1) {
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
            if(secondAbility ==2){
                response += cardPlayer.playCard(pickedCard, currentPlayer, nightmare, secondAbility);
            }
            else if(secondAbility == 3 ){
                catchZ();
            }
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

    /**
     * Helper method that handles when the user decides to catch Z Token
     */
    private String catchZ(DreamTileBoard dreamTileBoard) {
        DreamTileBoardViewer dreamTileBoardViewer = new DreamTileBoardViewer();
        int location;
        int numZToken;
        String responese;
        showBoardStatus();
        boolean actionTermination = false;
        do {
            if (playerDoesNotHaveZ() && dreamTileBoard.isFull()) {
                racingPhaseViewer.showErrorMessage(
                        "Uh oh! You don't have ZToken anymore, and there is no empty space for another dreamTile in the board!\n"
                                +
                                "Unfortunately, there is no other option left for you...");
                return;
            }

            location = racingPhaseViewer.askTileLocationToCatch();
            numZToken = racingPhaseViewer.askNumZTokenToCatch();

            try {
                actionTermination = actionCatchZ.catchZ(location, numZToken);
            } catch (GameLogicViolationException glve) {
                phaseViewer.showErrorMessage(glve.getMessage());
                continue;
            }
        } while (!actionTermination);
    }
}