package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RestingPhaseController;

/**
 * Class for RestingPhase Controller
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseViewer {
    private RestingPhaseController controller;
    private Scanner scanner;

    public RestingPhaseViewer(RestingPhaseController controller){
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public int showChoice(ArrayList<Integer> choiceList){
        for(int i=0 ; i<choiceList.size() ; i++){
            if(choiceList.get(i) == 11)
        }
    }

    public int getChoice(){
        
    }

}
