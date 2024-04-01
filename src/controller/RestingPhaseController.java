package controller;

import org.junit.experimental.ParallelComputer;

import model.RestingPhase;
import view.DreamTileBoardViewer;
import view.RestingPhaseViewer;

/**
 * Class for RestingPhase Controller
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseController {
    private RestingPhaseViewer viewer;
    private RestingPhase phase;
    private DreamTileBoardViewer viewer2; 
    

    public RestingPhaseController(RestingPhaseViewer viewer, RestingPhase phase){
        this.phase = phase;
        this.viewer = viewer;
    }

    public void startPhase(){
        do{

        }while(phase.setNextPlayer());




        nextPhase();
    }

    public void setRacingPhaseController(RacingPhaseController controller){
        racingPhaseController = controller;
    }

    private void nextPhase(){
        racingPhaseController.startPhase();
    }

    /**
     * Method that tells the viewer to show the board Status
     */
    private void showBoardStatus(){
        viewer.showBoardStatus(phase.getBoardStatus());
    }
}
