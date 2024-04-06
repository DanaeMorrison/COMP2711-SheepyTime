package controller;

import model.DreamTile;
import model.DreamTileBoard;
import model.DreamTileCollection;
import model.RestingPhase;
import model.RestingPhaseCatchZ;
import model.RestingPhasePutNewTile;
import model.exception.GameLogicViolationException;
import view.DreamTileBoardViewer;
import view.DreamTileViewer;
import view.RestingPhaseViewer;
import model.Player;

import java.util.ArrayList;

/**
 * Class for RestingPhase Controller
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseController {
    private RestingPhase phase;
    private RestingPhaseViewer phaseViewer;
    private DreamTileViewer tileViewer;
    private DreamTileBoardViewer boardViewer;
    private DreamTileBoard tileBoard;
    private DreamTileCollection dreamTiles;
    private RestingPhaseCatchZ actionCatchZ;
    private RestingPhasePutNewTile actionPutNewTile;
    private boolean isSolo = false;

    public RestingPhaseController(DreamTileBoardViewer boardViewer, ArrayList<Player> players,
        DreamTileCollection dreamTiles, DreamTileViewer tileViewer, RestingPhaseCatchZ actionCatchZ, RestingPhasePutNewTile actionPutNewTile) {
        phase = new RestingPhase(players, dreamTiles);
        if (players.size() == 1) {
            isSolo = true;
        }
        phaseViewer = new RestingPhaseViewer();
        this.boardViewer = boardViewer;
        this.tileViewer = tileViewer;
        this.dreamTiles = dreamTiles;

        this.actionCatchZ = actionCatchZ;
        this.actionPutNewTile = actionPutNewTile;
    }

    /**
     * Method that starts the phase
     */
    public DreamTileBoard startPhase(DreamTileBoard tileBoard) {
        setTileBoard(tileBoard);
        do {
            boolean canPutTile = canPutNewTile();
            int userChoice = askUserChoice(canPutTile);

            if (userChoice == 0) {
                catchZ();
            } else if (userChoice == 1 && canPutTile) {
                putNewTile();
                showMarket();
            }
            showBoardStatus();

        } while (phase.setNextPlayer());

        if (isSolo) {
            actionPutNewTile.putNewTileInSolo(dreamTiles.takeTile());
        }
        return actionPutNewTile.getBoard();
    }

    private void setTileBoard(DreamTileBoard tileBoard) {
        actionCatchZ.setBoard(tileBoard);
        actionPutNewTile.setBoard(tileBoard);
    }

    /**
     * Helper method that handles when the user decides to catch Z Token
     */
    private void catchZ() {
        int location;
        int numZToken;
        showBoardStatus();
        boolean actionTermination = false;
        do {
            if (playerDoesNotHaveZ()&&tileBoard.isFull()) {
                phaseViewer.showErrorMessage("Uh oh! You don't have ZToken anymore, and there is no empty space for another dreamTile in the board!\n"+ 
                                            "Unfortunately, there is no other option left for you...");
                return;
            }

            location = phaseViewer.askTileLocationToCatch();
            numZToken = phaseViewer.askNumZTokenToCatch();

            try {
                actionTermination = actionCatchZ.catchZ(location, numZToken);
            } catch (GameLogicViolationException glve) {
                phaseViewer.showErrorMessage(glve.getMessage());
                continue;
            }
        } while (!actionTermination);
    }

    /**
     * Helper method that checks whether player has Z Token in their supply
     * 
     * @return true if player has Z Token
     */
    private boolean playerDoesNotHaveZ() {
        return phase.getCurrentPlayer().getZtokens() == 0;
    }

    /**
     * Helper method that handles when the player decides to put a new DreamTile on
     * the board
     */
    private void putNewTile() {
        int tileNum;
        int location;
        boolean actionTermination = false;
        do {
            showMarket();
            tileNum = phaseViewer.askTileNumber();
            showBoardStatus();
            location = phaseViewer.askLocationToPut();

            try {
                actionTermination = actionPutNewTile.putNewTile(tileNum, location);
            } catch (GameLogicViolationException glve) {
                phaseViewer.showErrorMessage(glve.getMessage());
                continue;
            }
        } while (!actionTermination);

    }

    /**
     * Helper method that checks the validity of putting a new tile
     * 
     * @return
     */
    private boolean canPutNewTile() {
        boolean canPutTile = false;
        if (!tileBoard.isFull()) {
            canPutTile = true;
        }
        return canPutTile;
    }

    /**
     * Helper method that guarantee that the player chose the right option
     * 
     * @param canPutTile whether putting a new dreamtile valid or not
     * @return user choice
     */
    private int askUserChoice(boolean canPutTile) {
        boolean validInput = false;
        int userChoice;
        do {
            userChoice = phaseViewer.askChoice(canPutTile);
            validInput = phase.isChoiceValid(userChoice - 1, canPutTile);
        } while (!validInput);
        return userChoice;
    }

    /**
     * Method that tells the viewer to show the board Status
     */
    private void showBoardStatus() {
        boardViewer.showBoardStatus(tileBoard.getBoardStatus(phase.getCurrentPlayer()));
    }

    /**
     * Helper method that tell the DreamTileViewer to show the DreamTile in the
     * market
     */
    private void showMarket() {
        String[] tileNames = new String[phase.getMarketSize()];
        String[] tileRules = new String[phase.getMarketSize()];
        for (int i = 0; i < 4; i++) {
            if (getTileFromMarket(i) == null) {
                tileNames[i] = "(Empty)";
                tileRules[i] = "";
            } else {
                tileNames[i] = getTileFromMarket(i).getTileName();
                tileRules[i] = getTileFromMarket(i).getRule();
            }
        }
        tileViewer.printMarket(tileNames, tileRules);
    }

    /**
     * helper method that gives the DreamTile on the desired index
     * 
     * @param index index in the market
     * @return
     */
    private DreamTile getTileFromMarket(int index) {
        return phase.getMarket().get(index);
    }

}
