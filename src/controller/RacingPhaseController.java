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

public class RacingPhaseController {

    private RacingPhase racingPhase;
    CardPlayer cardPlayer = new CardPlayer();
    DreamTilePlayer dreamTilePlayer;
    CardViewer cardViewer = new CardViewer();
    RacingPhaseViewer racingPhaseViewer = new RacingPhaseViewer();

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
        boolean nightmareHasCrossed = racingPhase.getNightmareHasCrossed();
        // boolean nightmareHasCrossed = false;
        // boolean oneSleepingPlayer;

        Player currPlayer;
        Card picked;
        int cardChoice;
        ArrayList<Card> usedCards = new ArrayList<>();

        while (!allPlayersAwake(players)) {
            for (int i = 0; i < playerCount; i++) {
                currPlayer = players.get(i);
                if (currPlayer.isAwake()) {
                    continue;
                }

                fillHand(currPlayer, players, usedCards, deck, nightmare);

                nightmareCrossFence(nightmareHasCrossed, players);

                ArrayList<Card> hand = currPlayer.getHand();

                showHand(hand, currPlayer, nightmare);

                // should the model be updated to store the currently chosen card?
                // cardChoice = racingPhaseViewer.getCardChoice();
                cardChoice = askCardChoice();
                // IGNORE
                // instead: make a handle card method that will go through taking in input,
                // choosing a card from the hand based on the input, and playing the card.
                // if the player's hand and the usedCards are handed to the method, then the
                // picked card can be added to the usedCards and removed from the hand within
                // that method. Otherwise:
                // make the method return the integer that was picked so that the matching card
                // can be added to used cards and so it can be removed from the player's hand
                //
                // cardChoice = notifyListenersAskCardChoice();

                // make a version that says if thrown an error, will ask for new input
                // will replace this one
                /**
                 * while (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
                 * cardChoice = racingPhaseViewer.getCardChoiceOnError();
                 * }
                 */

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
                    Card card = deck.takeCard();
                    usedCards.add(card);

                    if (card.isNightmare()) {
                        printCard(card, nightmare);
                        if (cardPlayer.playNightmareCard(card, nightmare, players)) { // ugly syntactically, but it's
                                                                                      // playing the card *and*
                                                                                      // returning a true boolean if the
                                                                                      // nightmare is crossing.
                            racingPhase.setNightmareHasCrossed(true);
                        }
                    }
                }

                // refills a player's hand at the end of their turn
                // code smell: put the loops below this together in another function
                // to run at the same time
                fillHand(currPlayer, players, usedCards, deck, nightmare);

                nightmareCrossFence(nightmareHasCrossed, players);

                showHand(hand, currPlayer, nightmare);

                BoardViewer.showBoard(players, nightmare);
            }
        }
        replaceUsedCards(usedCards, deck);
        // here is where more code should be written to update RacingPhase before the
        // startPhase method closes
        // e.g. racingPhase.setDreamTileBoard(dreamTileBoard); This method hasn't been
        // created yet.
        // loop through all players and access their boards to reset their positions to
        // -1. Same with nightmare

        return dreamTileBoard;
    }

    private void showHand(ArrayList<Card> hand, Player currPlayer, Nightmare nightmare) {
        for (int j = 0; j < hand.size(); j++) {
            Card currCard = currPlayer.getHand().get(j);
            racingPhaseViewer.printCardInHand(currPlayer.getName(), j);
            printCard(currCard, nightmare);

        }
    }

    private void nightmareCrossFence(boolean nightmareHasCrossed, ArrayList<Player> players) {
        if (nightmareHasCrossed) {
            // make method to print out that the nightmare has jumped the fence (in
            // RacingPhaseViewer)
            for (Player p : players) {
                if (!p.isAwake()) {
                    p.setWinks(0);
                    p.setScaredStatus(2);
                    p.setAwake(true);
                    p.getBoard().emptyBoard();
                    // make method to print "PlayerName got scared awake!"
                }
            }
            racingPhaseViewer.nightmareCrossFence();
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

    public void fillHand(Player player, ArrayList<Player> players, ArrayList<Card> usedCards, Deck deck,
            Nightmare nightmare) {

        for (int j = player.getHand().size(); j < 2; j++) {
            Card card = deck.takeCard();
            usedCards.add(card);

            if (card.isNightmare()) {
                printCard(card, nightmare);
                if (cardPlayer.playNightmareCard(card, nightmare, players)) { // ugly syntactically, but it's playing
                                                                              // the card *and* returning a true boolean
                                                                              // if the nightmare is crossing.
                    racingPhase.setNightmareHasCrossed(true);
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
            abilityChoice = racingPhaseViewer.getAbilityChoice(secondAbility);
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
            // racingPhaseViewer.printCardPlayResponse(response);

            // both of these should be made into booleans so we can know if the player
            // hopped the fence
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
            response += cardPlayer.playCard(pickedCard, currentPlayer, nightmare, secondAbility, board);
            racingPhaseViewer.printCardPlayResponse(response);
        }
    }
}