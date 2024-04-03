package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.RestingPhaseController;

/**
 * Class for RestingPhase Controller
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseViewer {
    private RestingPhaseController controller;
    private Scanner scanner;
    private String instruction = "";
    private final String CATCH_Z_INSTRUCTION = "Catch 2 ZTokens on DreamTiles";
    private final String PUT_NEW_TILE_INSTRUCTION = "Add a new DreamTile to the Board";

    public RestingPhaseViewer(RestingPhaseController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
        instruction = "";
    }

    
    public void showErrorMessage(String errorMessage){
        System.out.println(errorMessage);
    }

    public void showOption() {
        System.out.print(instruction);
    }

    public void addCatchZInstruction(int optionNum){
        instruction += putInstruction(optionNum, CATCH_Z_INSTRUCTION);
    }

    public void addPutNewTileInstruction(int optionNum){
        instruction += putInstruction(optionNum, PUT_NEW_TILE_INSTRUCTION);
    }

    private String putInstruction(int optionNum, String instruction){
        return "Option "+optionNum+ ": "+ instruction + "\n";
    }

    public int askTileLocationToCatch(){
        System.out.print("Where Do you want to catch your Z Token? Please type from 1~10");
        return askIntegerInput();
    }

    public int askNumZTokenToCatch(){
        System.out.print("How many Z Tokens do you want to catch here? please type either 1 or 2");
        return askIntegerInput();
    }


    private int askIntegerInput(){
        boolean validInput = false;
        int userInput=0;
        do{
            try{
                userInput = scanner.nextInt();
            }
            catch( InputMismatchException ime ){
                System.out.print("\nWrong input format! Please Type integer");
            }
        }while(!validInput);
        return userInput;
    }

}
