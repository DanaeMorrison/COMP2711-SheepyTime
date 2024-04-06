package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.DreamTile;

public class RacingPhaseViewer {
    private final int WINK_ABILITY = 2;
    // private final int KEEP_PLAY = 0;
    // private final int CALL_NIGHT = 1;

    private Scanner scanner = new Scanner(System.in); // maybe move all this to a turnviewer class?

    public int getCardChoice() {
        System.out.print("Input which card you would like to choose from your hand. 0 or 1: ");
        return getCardChoice();
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
        return getIntegerInput();
    }

    /**
     * Method that asks the user to choose the number of Z Token to catch 
     * @return user input
     */
    public int askNumZTokenToCatch() {
        System.out.print("How many Z Tokens do you want to catch here? please type either 1 or 2");
        return getIntegerInput();
    }

    private int getIntegerInput() {
        int userInput = 0;
        boolean valid = false;
        System.out.print("Your Choice: ");
        do {
            try {
                userInput = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException ime) {
                System.out.print("\nInvalid Input: Please enter integer value only: ");
            }
        } while (!valid);
        return userInput;
    }

    public int getCardChoiceOnError() {
        System.out.print("You did not enter a valid number. Please type in either 0 or 1: ");
        return getIntegerInput();
    }

    public void displayAbilityOptions(int secondAbility) {
        System.out.println("You've picked an OR card. Which ability do you want to use?");
        System.out.println("Enter 1 to Move");

        if (secondAbility == WINK_ABILITY) {
            System.out.println("Enter 2 to Catch Winks");
        } else {
            System.out.println("Enter 3 to Get ZTokens");
        }
        System.out.println("");
    }

    public int getAbilityChoice() {
        return getIntegerInput();
    }

    public int getAbilityChoiceOnError(int secondAbility) {
        System.out.println(
                "You did not enter a valid number. Please type in either 1 or " + String.valueOf(secondAbility));
        return getIntegerInput();
    }

    public int getSpecificMove(int[] moves) {

        System.out.println(
                "How many steps would you like to move? Input the corresponding number to the amount of steps.");
        for (int i = 0; i < moves.length; i++) {
            System.out.println(i + " - " + moves[i] + "steps");
        }

        return getIntegerInput();
    }

    public int getSpecificMoveOnError(int[] moves) {
        int firstMove = moves[0];
        int secondMove = moves[1];

        System.out.print("You did not enter a valid number. Please type in either " + String.valueOf(firstMove) + " or "
                + String.valueOf(secondMove));
        return getIntegerInput();
    }

    public int getPlayOrCallNight() {
        System.out.println("You've crossed the fence. Would you like to call it a night, or keep playing?");
        System.out.println("Enter 0 to Keep playing");
        System.out.println("Enter 1 to Call it a night");
        return getIntegerInput();
    }

    public int getPlayOrCallNightOnError() {
        System.out.println("You did not enter a valid number. Please type in either 0 or 1");
        System.out.println("Enter 0 to Keep playing");
        System.out.println("Enter 1 to Call it a night");
        return getIntegerInput();
    }

    public int getUseTileChoice(DreamTile dreamTile) {
        System.out.println("This position has a dream tile");
        System.out.println("");
        System.out.println(dreamTile.getTileName() + ": " + dreamTile.getRule());
        System.out.println("You have a Ztoken on this dream tile. Would you like to use it?");
        System.out.println("Enter 0 for No");
        System.out.println("Enter 1 for Yes");
        return getIntegerInput();
    }

    public int getUseTileChoiceOnError(DreamTile dreamTile) {
        System.out.println("You did not enter a valid number. Please type in either 0 or 1");
        System.out.println("");
        System.out.println("Enter 0 for No");
        System.out.println("Enter 1 for Yes");
        return getIntegerInput();
    }

    public void printCardInHand(String playerName, int cardInHand) {
        System.out.println(playerName + "'s card #" + String.valueOf(cardInHand));
    }

    public void printCardPlayResponse(String response) {
        System.out.println(response);
    }

    public void printNightmareHasCrossed(String response) {
        response += "The nightmare has jumped over the fence!\n";
        System.out.println(response);
    }
    
}