package view;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Class for RestingPhase Controller
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseViewer {
    private Scanner scanner;
    private String instruction = "";
    private final String CATCH_Z_INSTRUCTION = "Catch 2 ZTokens on DreamTiles";
    private final String PUT_NEW_TILE_INSTRUCTION = "Add a new DreamTile to the Board";

    public RestingPhaseViewer() {
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

    public int askChoice(int numOption){
        System.out.println("Choose your behavior and type the corresponing number");
        System.out.println("Option 1: Catch 2 Z Tokens");
        if(numOption == 1){
            System.out.println("Option 2: Choose one DreamTile from the market and put it on the Board");
        }
        System.out.print("=>");
        return askIntegerInput();
    }

    public int askTileNumber(){
        System.out.print("Which tile do you want to place?");
        return askIntegerInput();
    }

    public int askLocationToPut(){
        System.out.println("Where do you want to place?");
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
