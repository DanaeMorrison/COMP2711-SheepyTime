package controller;

import java.util.ArrayList;

import model.Card;
import model.Player;
import model.Nightmare;
import model.CardPlayer;
import model.DreamTilePlayer;
import model.RacingPhase;
import model.RacingPhaseCatchZ;
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

    /**
     * Starts the racing phase and takes the game through all of its steps up until no players remain asleep
     * @param dreamTileBoard board that contains the tiles on the board
     * @return the dream tile board with any changes that were made during the phase
     */
    public DreamTileBoard startPhase(DreamTileBoard dreamTileBoard) {
        racingPhase.setDreamTileBoard(dreamTileBoard);
        ArrayList<Player> players = racingPhase.getPlayers();
        int playerCount = racingPhase.getPlayerSize();
        Nightmare nightmare = racingPhase.getNightmare();
        dreamTilePlayer = new DreamTilePlayer(dreamTileBoard);
        Deck deck = racingPhase.getDeck();

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

                playCard(picked, currPlayer, nightmare, dreamTileBoard);

                PlayerBoard board = currPlayer.getBoard();
                int playerPosition = board.getIndex();
                if (dreamTilePlayer.isUsableTilePresent(playerPosition, currPlayer)) {
                    DreamTile thisDreamTile = dreamTileBoard.getTile(playerPosition);
                    int useTileChoice = racingPhaseViewer.getUseTileChoice(thisDreamTile);

                    boolean validInput = dreamTilePlayer.isUseTileChoiceValid(useTileChoice);

                    while (!validInput){
                        useTileChoice = racingPhaseViewer.getUseTileChoice(thisDreamTile);
                        validInput = dreamTilePlayer.isUseTileChoiceValid(useTileChoice);
                    };

                    if (useTileChoice == 1) {
                        useDreamTile(currPlayer, players, nightmare, dreamTileBoard, thisDreamTile);
                    }
                }

                if (onePlayerAsleep(players)) {
                    Card card = deck.takeCard();
                    usedCards.add(card);
            
                    if(card.isNightmare()){
                        printCard(card, nightmare);
                        playNightmareCard(card, nightmare, players);
                        if(racingPhase.getNightmareHasCrossed()){
                            resetPlayerInfo(players);
                            return dreamTileBoard;
                        }
                    }
                }

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
            Card temp = used.get(0); // get()?
            toFill.add(temp);
            used.remove(temp);
        }
        toFill.shuffle();
    }

    public void fillHand(Player player, ArrayList<Player> players, ArrayList<Card> usedCards, Deck deck, Nightmare nightmare){
        
        for(int j = player.getHand().size(); j < 2; j++){
            Card card = deck.takeCard();
            usedCards.add(card);
            
            if(card.isNightmare()){
                printCard(card, nightmare);
                playNightmareCard(card, nightmare, players);
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
        tile.removePlayerToken(player);
        player.setZtokens(player.getZtokens() + 1);
        tile.useTile(player, players, nightmare, dreamTileBoard);
    }

    private int askCardChoice() {
        boolean validInput = false;
        int cardChoice;

        cardChoice = racingPhaseViewer.getCardChoice();
        validInput = racingPhase.isCardChoiceValid(cardChoice);


        while (!validInput){
            cardChoice = racingPhaseViewer.getCardChoiceOnError();
            validInput = racingPhase.isCardChoiceValid(cardChoice);
        }
        return cardChoice;
    }

    /**
     * Method that handles playing a sheep card by from getting input from the user
     * @param pickedCard the current sheep card being played
     * @param currentPlayer the player that picked the card being played
     * @param nightmare the nightmare for this game's instantiation
     * @param dreamTileBoard the board containing positions of the dreamtiles
     */
    private void playCard(Card pickedCard, Player currentPlayer, Nightmare nightmare, DreamTileBoard dreamTileBoard) {
        boolean validInput = false;
        int abilityChoice;
        int moveAmount;
        String response;

        int secondAbility = cardPlayer.getValidCardOptions(pickedCard);
        PlayerBoard board = currentPlayer.getBoard();

        if (!pickedCard.bothConditions()) {
            racingPhaseViewer.displayAbilityOptions(secondAbility);
            abilityChoice = racingPhaseViewer.getAbilityChoice();
            validInput = cardPlayer.isAbilityChoiceValid(abilityChoice, secondAbility);

            while (!validInput){
                abilityChoice = racingPhaseViewer.getAbilityChoiceOnError(secondAbility);
                validInput = cardPlayer.isAbilityChoiceValid(abilityChoice, secondAbility);
            }

            validInput = false;

            if (abilityChoice == 1) {
                int[] moves = pickedCard.getMoves();
                if (moves.length > 1) {
                    int selectedMove = racingPhaseViewer.getSpecificMove(moves);
                    validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);

                    while (!validInput){
                        selectedMove = racingPhaseViewer.getSpecificMoveOnError(moves);
                        validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);
                    }

                    validInput = false;
                    moveAmount = selectedMove;
                } else {
                    moveAmount = moves[0];
                }

                if (board.isCrossing(moveAmount)) {
                    int wakingUp = racingPhaseViewer.getPlayOrCallNight();
                    validInput = cardPlayer.isWakingUpValid(wakingUp);

                    while (!validInput){
                        wakingUp = racingPhaseViewer.getPlayOrCallNightOnError();
                        validInput = cardPlayer.isWakingUpValid(wakingUp);
                    }

                    cardPlayer.resolveFenceCrossing(currentPlayer, wakingUp);
                }

                response = cardPlayer.movePlayer(currentPlayer, nightmare, board, moveAmount);
            } else if (abilityChoice == 2) {
                response = cardPlayer.playCard(pickedCard, currentPlayer, nightmare, abilityChoice);
            } else {
                response = "";
                catchZ(dreamTileBoard, currentPlayer);
            }
            racingPhaseViewer.printCardPlayResponse(response);
        } else {
            int[] moves = pickedCard.getMoves();
            if (moves.length > 1) {
                int selectedMove = racingPhaseViewer.getSpecificMove(moves);
                validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);

                while (!validInput){
                    selectedMove = racingPhaseViewer.getSpecificMoveOnError(moves);
                    validInput = cardPlayer.isSpecificMoveValid(selectedMove, moves);
                }

                validInput = false;
                moveAmount = selectedMove;
            } else {
                moveAmount = moves[0];
            }

            if (board.isCrossing(moveAmount)) {
                int wakingUp = racingPhaseViewer.getPlayOrCallNight();
                validInput = cardPlayer.isWakingUpValid(wakingUp);

                while (!validInput){
                    wakingUp = racingPhaseViewer.getPlayOrCallNightOnError();
                    validInput = cardPlayer.isWakingUpValid(wakingUp);
                }

                cardPlayer.resolveFenceCrossing(currentPlayer, wakingUp);
            }

            response = cardPlayer.movePlayer(currentPlayer, nightmare, board, moveAmount);
            if(secondAbility ==2){
                response += cardPlayer.playCard(pickedCard, currentPlayer, nightmare, secondAbility);
            }
            else if(secondAbility == 3 ){
                catchZ(dreamTileBoard, currentPlayer);
            }
            racingPhaseViewer.printCardPlayResponse(response);
        }
    }
    /**
     * Method that plays a nightmare card if the nightmare movement does not result in crossing the fence
     * Handles any possible crossing that occurs when the nightmare
     * @param card the nightmare card to be played
     * @param nightmare the nightmare involved in this game's instantiation
     * @param players the list of players in the game
     */
    private void playNightmareCard(Card card, Nightmare nightmare, ArrayList<Player> players) {
        if (cardPlayer.isNightmareCrossing(card, nightmare)) {
            racingPhase.setNightmareHasCrossed(true);
        } else {
            String response = cardPlayer.playNightmareCard(card, nightmare, players);
            racingPhaseViewer.printCardPlayResponse(response);
        }
    }

    
    /**
     * Helper method that goes through all players that are currently asleep when a nightmare has crossed
     * the fence and sets them all to wake up, removing them from the board and reducing their winks to 0
     * @param players
     */
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
    private String catchZ(DreamTileBoard dreamTileBoard, Player currPlayer) {
        DreamTileBoardViewer dreamTileBoardViewer = new DreamTileBoardViewer();
        RacingPhaseCatchZ catchZ = new RacingPhaseCatchZ(dreamTileBoard, currPlayer);
        int location;
        int numZToken;
        String response="";
        showBoardStatus(dreamTileBoardViewer, dreamTileBoard, currPlayer);
        boolean actionTermination = false;
        do {
            if (currPlayer.getZtokens()==0 && dreamTileBoard.isFull()) {
                racingPhaseViewer.showErrorMessage(
                        "Uh oh! You don't have ZToken anymore, and there is no empty space for another dreamTile in the board!\n"
                                +
                                "Unfortunately, there is no other option left for you...");
                break;
            }

            location = racingPhaseViewer.askTileLocationToCatch();
            numZToken = racingPhaseViewer.askNumZTokenToCatch();

            try {
                actionTermination = catchZ.catchZ(location, numZToken);
                response += (currPlayer.getName() + " catched " + numZToken + "Z Token(s)\n");
            } catch (GameLogicViolationException glve) {
                racingPhaseViewer.showErrorMessage(glve.getMessage());
                continue;
            }
        } while (!actionTermination);

        return response;
    }

    /**
     * Method that tells the viewer to show the board Status
     */
    private void showBoardStatus(DreamTileBoardViewer dreamTileBoardViewer, DreamTileBoard dreamTileBoard, Player currPlayer) {
        dreamTileBoardViewer.showBoardStatus(dreamTileBoard.getBoardStatus(currPlayer));
    }
}