package view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Viewer class that shows the overall procecss of the resting phase
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class RestingPhaseViewer {
    private Scanner scanner;


    public RestingPhaseViewer() {
        scanner = new Scanner(System.in);
    }

    /**
     * Method that prints received error messages
     * 
     * @param errorMessage
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Method that asks the user to choose the position of the tile
     * @return user input
     */
    public int askTileLocationToCatch() {
        System.out.print("Where Do you want to catch your Z Token? Please type from 1~10");
        return askIntegerInput();
    }

    /**
     * Method that asks the user to choose the number of Z Token to catch 
     * @return user input
     */
    public int askNumZTokenToCatch() {
        System.out.print("How many Z Tokens do you want to catch here? please type either 1 or 2");
        return askIntegerInput();
    }

    /**
     * Method that asks the user input
     * @param numOption number of options
     * @return
     */
    public int askChoice(boolean canPutTile) {
        System.out.println("Choose your behavior and type the corresponing option number (either 1 or 2)" );
        System.out.println("Option 1: Catch 2 Z Tokens");
        if (canPutTile) {
            System.out.println("Option 2: Choose one DreamTile from the market and put it on the Board");
        }
        System.out.print("=>");
        return askIntegerInput();
    }

    public int askTileNumber() {
        System.out.print("Which tile do you want to place?");
        return askIntegerInput();
    }

    public int askLocationToPut() {
        System.out.println("Where do you want to place?");
        return askIntegerInput();
    }

    private int askIntegerInput() {
        boolean validInput = false;
        int userInput = 0;
        do {
            try {
                userInput = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException ime) {
                System.out.print("\nWrong input format! Please Type integer");
            }
        } while (!validInput);
        return userInput;
    }

}
