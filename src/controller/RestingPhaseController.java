package controller;

import model.RestingPhase;
import view.RestingPhaseViewer;

/**
 * Class for RestingPhase Controller
 * @author Dylan Kim
 * @version 1.0
 */

public class RestingPhaseController {
    private RestingPhaseViewer viewer;
    private RestingPhase phase;

    public RestingPhaseController(RestingPhaseViewer viewer, RestingPhase phase){
        this.phase = phase;
        this.viewer = viewer;
    }

    public void startPhase(){
        do{
            
        }while(phase.setNextPlayer());

    }
}
