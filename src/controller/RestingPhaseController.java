package controller;

import model.DreamTile;
import model.DreamTileBoard;
import model.DreamTileCollection;
import model.RestingPhase;
import model.RestingPhaseAction;
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
    private RestingPhaseCatchZ actionCatchZ;
    private RestingPhasePutNewTile actionPutNewTile;
    

    public RestingPhaseController(DreamTileBoard tileBoard, DreamTileBoardViewer boardViewer, ArrayList<Player> player,
    DreamTileCollection dreamTiles, DreamTileViewer tileViewer) {
        phase = new RestingPhase(player, dreamTiles);
        
        phaseViewer = new RestingPhaseViewer(this);
        this.boardViewer = boardViewer;
        this.tileViewer = tileViewer;
        
        this.tileBoard = tileBoard;
        
        actionCatchZ = new RestingPhaseCatchZ(phase, tileBoard);
        actionPutNewTile = new RestingPhasePutNewTile(phase, tileBoard);
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
                putNewTile();
                showMarket();
            }
            showBoardStatus();
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
        boolean actionTermination = false;
        do{
            showMarket();
            tileNum = phaseViewer.askTileNumber();
            showBoardStatus();
            location = phaseViewer.askLocationToPut();

            try{
                actionTermination = actionPutNewTile.putNewTile(tileNum, location);
            }
            catch(GameLogicViolationException glve) {
                phaseViewer.showErrorMessage(glve.getMessage());
                continue;
            }  
        }while(!actionTermination);

    }


    private int showChoiceList() {
        int numOption = 0;
        phaseViewer.addCatchZInstruction(numOption);
        if (!tileBoard.isFull()) {
            numOption++;
            phaseViewer.addPutNewTileInstruction(numOption);
        }
        phaseViewer.showOption();
        return numOption;
    }

    /**
     * Helper method that guarantee that the player chose the right option
     * @param numOption
     * @return
     */
    private int askUserChoice(int numOption) {
        boolean validInput = false;
        int userChoice;
        do {
            userChoice = phaseViewer.askChoice(numOption);
            validInput = phase.isChoiceValid(userChoice-1, numOption);
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
     * Helper method that tell the DreamTileViewer to show the DreamTile in the market
     */
    private void showMarket(){
        for(int i=0 ; i<4 ; i++){
            if(getTile(i)==null){
                tileViewer.printDreamTile("(Empty)", "");
            }
            else{
                tileViewer.printDreamTile(getTile(i).getTileName(), getTile(i).getDescription());
            }
        }
    }

    /**
     * helper method that gives the DreamTile on the desired index
     * @param index index in the market
     * @return
     */
    private DreamTile getTile(int index){
        return phase.getMarket().get(index);
    }

    // public void setRacingPhaseController(RacingPhaseController controller){
    // racingPhaseController = controller;
    // }

    // private void nextPhase(){
    // racingPhaseController.startPhase();
    // }
}
