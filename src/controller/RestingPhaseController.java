package controller;

import model.DreamTileBoard;
import model.DreamTileCollection;
import model.RestingPhase;
import model.RestingPhaseAction;
import model.RestingPhaseCatchZ;
import model.RestingPhasePutNewTile;
import model.exception.GameLogicViolationException;
import view.DreamTileBoardViewer;
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
    private RestingPhaseViewer phaseViewer;
    private RestingPhase phase;
    private DreamTileBoardViewer boardViewer;
    private DreamTileBoard tileBoard;
    private RestingPhaseCatchZ actionCatchZ;
    private RestingPhasePutNewTile actionPutNewTile;

    public RestingPhaseController(DreamTileBoard tileBoard, DreamTileBoardViewer boardViewer, ArrayList<Player> player,
        DreamTileCollection dreamTiles) {
        phaseViewer = new RestingPhaseViewer(this);
        phase = new RestingPhase(player, dreamTiles);
        actionCatchZ = new RestingPhaseCatchZ(phase, tileBoard);
        actionPutNewTile = new RestingPhasePutNewTile(phase, tileBoard);
        this.boardViewer = boardViewer;
        this.tileBoard = tileBoard;
    }

    public void startPhase(){
        do{
            int numOption = showChoiceList();
            int userChoice = askUserChoice(numOption);

            //get choice from user + check whether input is valid
            if(userChoice == 0){
                catchZ();
            }
            else if (userChoice == 1 && numOption == 1){
                //Put New Tile

                //get input from user based on the desired choice

                //call proper function 
            }

            //Show Update
            
        }while(phase.setNextPlayer());
        //Move to Racing Phase
    }

    private void catchZ(){
        int location;
        int numZToken;
        showBoardStatus();
        boolean actionTermination = false;
        do{
            if(playerHasZ()){
                phaseViewer.showErrorMessage("Uh oh! You don't have ZToken anymore!");
                return;
            }
            location = phaseViewer.askTileLocationToCatch();
            numZToken = phaseViewer.askNumZTokenToCatch();
            try{
                actionTermination = actionCatchZ.catchZ(location, numZToken);
            }
            catch(GameLogicViolationException glve) {
                phaseViewer.showErrorMessage(glve.getMessage());
                continue;
            }  
        }while(!actionTermination);
    }

    private boolean playerHasZ(){
        return phase.getCurrentPlayer().getZtokens()==0;
    }

    private void putNewTile(){
        int tileNum;
        int location;

    }


    private int showChoiceList() {
        int numOption = 0;
        phaseViewer.addPutNewTileInstruction(numOption);
        if (!tileBoard.isFull()) {
            numOption++;
            phaseViewer.addPutNewTileInstruction(numOption);
        }
        phaseViewer.showOption();
        return numOption;
    }

    private int askUserChoice(int numOption) {
        boolean validInput = false;
        int userChoice;
        do {
            userChoice = phaseViewer.askIntegerInput();
            validInput = phase.isChoiceValid(userChoice, numOption);
        } while (!validInput);
        return userChoice;
    }

    /**
     * Method that tells the viewer to show the board Status
     */
    private void showBoardStatus() {
        boardViewer.showBoardStatus(tileBoard.getBoardStatus(phase.getCurrentPlayer()));
    }

    private void showMarket(){
        
    }

    // public void setRacingPhaseController(RacingPhaseController controller){
    // racingPhaseController = controller;
    // }

    // private void nextPhase(){
    // racingPhaseController.startPhase();
    // }
}
