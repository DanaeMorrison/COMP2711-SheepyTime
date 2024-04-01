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
    private RestingPhaseViewer viewer;
    private RestingPhase phase;
    private DreamTileBoardViewer boardViewer; 
    private DreamTileBoard tileBoard;
    

    public RestingPhaseController(DreamTileBoard tileBoard, DreamTileBoardViewer boardViewer, ArrayList<Player> player, DreamTileCollection dreamTiles){
        viewer = new RestingPhaseViewer(this);
        phase = new RestingPhase(player, dreamTiles);
        this.boardViewer = boardViewer;
        this.tileBoard = tileBoard;
    }

    public void startPhase(){
        do{

        }while(phase.setNextPlayer());

    }

    // public void setRacingPhaseController(RacingPhaseController controller){
    //     racingPhaseController = controller;
    // }

    // private void nextPhase(){
    //     racingPhaseController.startPhase();
    // }

    /**
     * Method that tells the viewer to show the board Status
     */
    private void showBoardStatus(){
        viewer.showBoardStatus(tileBoard.getBoardStatus(phase.getCurrentPlayer()));
    }
}
