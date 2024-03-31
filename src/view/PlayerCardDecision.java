package view;

import java.util.Scanner;

public class PlayerCardDecision {
    
    Scanner scanner = new Scanner(System.in); //maybe move all this to a turnviewer class?
    
    
    public int getCardChoice() {
        int cardChoice = -1;
        System.out.print("Input which card you would like to choose from your hand. 0 or 1: ");
        cardChoice = scanner.nextInt();
        System.out.println("");

        while (cardChoice != 0 && cardChoice != 1) {
            System.out.print("You did not enter a valid number. Please type in either 0 or 1");
            cardChoice = scanner.nextInt();
            System.out.println("");
        }

        return cardChoice;
    }
    
}