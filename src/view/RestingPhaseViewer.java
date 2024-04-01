package view;

import controller.RestingPhaseController;

/**
 * Class for RestingPhase Controller
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseViewer {
    private RestingPhaseController controller;

    public RestingPhaseViewer(RestingPhaseController controller){
        this.controller = controller;
    }

    /**
     * Method that prints status of board whether dreamtile is placed or not
     * @param boardStatus boardStatus from model 
     */
    public void showBoardStatus(String[] boardStatus){
        for(int i=0; i<10;i++){
            System.out.println((i+1)+": "+boardStatus[i]);
        }
        System.out.println("--<Fence>--");
    }

}
