package controller;

import org.junit.experimental.ParallelComputer;

import model.DreamTileBoard;
import model.DreamTileCollection;
import model.RestingPhase;
import view.DreamTileBoardViewer;
import view.RestingPhaseViewer;
import model.Player;

import java.util.ArrayList;

/**
 * Class for RestingPhase Controller
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseController {
    private RestingPhaseViewer phaseViewer;
    private RestingPhase phase;
    private DreamTileBoardViewer boardViewer; 
    private DreamTileBoard tileBoard;
    

    public RestingPhaseController(DreamTileBoard tileBoard, DreamTileBoardViewer boardViewer, ArrayList<Player> player, DreamTileCollection dreamTiles){
        phaseViewer = new RestingPhaseViewer(this);
        phase = new RestingPhase(player, dreamTiles, tileBoard);
        this.boardViewer = boardViewer;
        this.tileBoard = tileBoard;
    }

    public void startPhase(){
        do{
            //Show Choice
            phaseViewer.showChoice(getChoiceList());

            //get choice from user + check whether input is valid

            //get input from user based on the desired choice

            //call proper function

            //Show Update
        }while(phase.setNextPlayer());
        //Move to Racing Phase
    }

    private boolean isPuttingTileAvaiable(){
        return !tileBoard.isFull();
    }

    private boolean isCatchZAvailable(){
        return phase.getCurrentPlayer().getZtokens() >0;
    }

    private ArrayList<Integer> getChoiceList(){
        ArrayList<Integer> choiceList = new ArrayList<>();
        if(isPuttingTileAvaiable()){
            choiceList.add(phase.getOperationPlaceNewTile());
        }
        if(isCatchZAvailable()){
            choiceList.add(phase.getOperationCatchZ());
        }
        else{
            choiceList.add(phase.getOperationMoveZ());
        }
        return choiceList;
    } 


    /**
     * Method that tells the viewer to show the board Status
     */
    private void showBoardStatus(){
        boardViewer.showBoardStatus(tileBoard.getBoardStatus(phase.getCurrentPlayer()));
    }

    // public void setRacingPhaseController(RacingPhaseController controller){
    //     racingPhaseController = controller;
    // }

    // private void nextPhase(){
    //     racingPhaseController.startPhase();
    // }
}
