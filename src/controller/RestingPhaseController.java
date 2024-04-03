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
    private RestingPhaseCatchZ catchZ;
    private RestingPhasePutNewTile putNewTile;

    public RestingPhaseController(DreamTileBoard tileBoard, DreamTileBoardViewer boardViewer, ArrayList<Player> player,
            DreamTileCollection dreamTiles) {
        phaseViewer = new RestingPhaseViewer(this);
        phase = new RestingPhase(player, dreamTiles);
        catchZ = new RestingPhaseCatchZ(phase, tileBoard);
        putNewTile = new RestingPhasePutNewTile(phase, tileBoard);
        this.boardViewer = boardViewer;
        this.tileBoard = tileBoard;
    }

    public void startPhase(){
        do{
            int numOption = showChoiceList();
            int userChoice = askUserChoice(numOption);

            int integerParam1 = phaseViewer.askIntegerInput();
            int integerParam2 = phaseViewer.askIntegerInput();

            //get choice from user + check whether input is valid
            if(userChoice == 0){
                //catchZ
                
                //get input from user based on the desired choice

                //call proper function
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
            location = phaseViewer.askTileLocationToCatch();
            numZToken = phaseViewer.askNumZTokenToCatch();
            try{
                actionTermination = catchZ.catchZ(location, numZToken);
            }
            catch(GameLogicViolationException glve) {
                phaseViewer.showErrorMessage(glve.getMessage());
            }
            
        }while(!actionTermination);

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

    // public void setRacingPhaseController(RacingPhaseController controller){
    // racingPhaseController = controller;
    // }

    // private void nextPhase(){
    // racingPhaseController.startPhase();
    // }
}
