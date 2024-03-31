package view;

import java.util.Scanner;

public class PlayerDecision {
    public final int FIRST_CARD = 0;
    public final int SECOND_CARD = 1;
    public final int MOVE_ABILITY = 1;
    public final int WINK_ABILITY = 2;
    public final int KEEP_PLAY = 0;
    public final int CALL_NIGHT = 1;
    
    Scanner scanner = new Scanner(System.in); //maybe move all this to a turnviewer class?
    
    
    public int getCardChoice() {
        int cardChoice = -1;
        System.out.print("Input which card you would like to choose from your hand. 0 or 1: ");
        cardChoice = scanner.nextInt();
        System.out.println("");

        while (cardChoice != FIRST_CARD && cardChoice != SECOND_CARD) {
            System.out.print("You did not enter a valid number. Please type in either 0 or 1");
            cardChoice = scanner.nextInt();
            System.out.println("");
        }

        return cardChoice;
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

    public int getAbilityChoice(int secondAbility) {
        int abilityChoice = 0;
        System.out.print("Your choice: ");
        abilityChoice = scanner.nextInt();
        System.out.println("");

        while (abilityChoice != MOVE_ABILITY && abilityChoice != secondAbility) {
            System.out.println("You did not enter a valid number. Please type in either 1 or " + String.valueOf(secondAbility));
            System.out.print("Your choice: ");
            abilityChoice = scanner.nextInt();
            System.out.println("");
        }

        return abilityChoice;
    }

    public int getSpecificMove(int[] moves) {
        int firstMove = moves[0];
        int secondMove = moves[1];

        System.out.println("How many steps would you like to move? Input the corresponding number to the amount of steps.");
        for(int i = 0; i < moves.length; i++){
            System.out.println(i + " - " + moves[i] + "steps");
        }

        System.out.print("Your choice: ");
        int selectedMove = scanner.nextInt();
        System.out.println("");
        
        while (selectedMove != firstMove && selectedMove != secondMove) {
            System.out.print("You did not enter a valid number. Please type in either " + String.valueOf(firstMove) + " or " + String.valueOf(secondMove));
            System.out.print("Your choice: ");
            selectedMove = scanner.nextInt();
            System.out.println("");
        }

        return selectedMove;
    }

    public int getPlayOrCallNight() {
        System.out.println("You've crossed the fence. Would you like to call it a night, or keep playing?");
        System.out.println("0: Keep playing");
        System.out.println("1: Call it a night");
        System.out.print("Your choice: ");
        int playOrCallNight = scanner.nextInt();

        while (playOrCallNight != KEEP_PLAY && playOrCallNight != CALL_NIGHT) {
            System.out.println("You did not enter a valid number. Please type in either 0 or 1");
            System.out.print("Your choice: ");
            playOrCallNight = scanner.nextInt();
            System.out.println("");
        }

        return playOrCallNight;
    }
}