import java.util.ArrayList;
import java.util.Scanner;

import controller.PhaseShiftController;
import controller.RacingPhaseController;
import controller.RestingPhaseController;
import controller.ScoreController;
import model.Deck;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;
import model.RacingPhase;
import view.DreamTileBoardViewer;
import view.DreamTileViewer;


/**
 * @author Dylan Kim
 * @version 2.0
 */
public class Initializer {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * This method generates n new players, and prompts the users to get a name.
     * 
     * @param n Player count.
     * @return ArrayList of newly generated players.
     */
    private static ArrayList<Player> generatePlayers() {
        int numPlayer = generateParams();
        ArrayList<Player> players = new ArrayList<>();
        String name;
        for (int i = 0; i < numPlayer; i++) {
            System.out.println("Input player " + (i + 1) + "'s name.");
            name = scanner.next();
            players.add(new Player(name, i));
        }
        return players;
    }

    /**
     * Prompts the user for how many players they'd like.
     * 
     * @return amount of players to be generated.
     */
    private static int generateParams(){
        System.out.println("How many players would you like?");
        int playerCount = scanner.nextInt();

        do {
            System.out.println("The game is currently designed to only be solo player. Please enter 1");
            playerCount = scanner.nextInt();
        } while (playerCount != 1);

        return playerCount;
    }

    /**
     * Uses the chosen nightmare integer from the user to create the corresponding nightmare
     * @return a nightmare object corresponding to the one chosen by the user
     */
    private static Nightmare generateNightmare() {
        int nightmareChoice = generateNightmareParams();
        
        if (nightmareChoice == 1) {
            return new Nightmare("wolf", 1);
        }
        return new Nightmare("bump", 2);
    }

    /**
     * Prompts the user for which nightmare they'd like to play the game with
     * @return integer representing the chosen nightmare
     */
    private static int generateNightmareParams(){
        System.out.println("Which nightmare would you like?");
        System.out.println("Enter 1 for Wolf nightmare");
        System.out.println("Enter 2 for Bump in the Night nightmare");
        int nightmareChoice = scanner.nextInt();
        
        do {
            System.out.println("You did not enter a valid input. Please enter 1 or 2");
            nightmareChoice = scanner.nextInt();
        } while (nightmareChoice != 1 && nightmareChoice != 2);

        return nightmareChoice;
    }

    public static void main(String[] args) {
        ArrayList<Player> players = generatePlayers();
        Nightmare nightmare = generateNightmare();
        DreamTileBoard dreamTileBoard = new DreamTileBoard();
        Deck deck  = new Deck();
        RacingPhase racingPhase = new RacingPhase(players, deck, nightmare);

        DreamTileBoardViewer boardViewer = new DreamTileBoardViewer();
        DreamTileViewer tileViewer = new DreamTileViewer();
        
        ScoreController scoreController = new ScoreController(players);
        RacingPhaseController racingPhaseController = new RacingPhaseController(racingPhase);
        RestingPhaseController restingPhaseController = new RestingPhaseController(boardViewer, players, tileViewer);
    
        PhaseShiftController phaseShifter = new PhaseShiftController(racingPhaseController, scoreController, restingPhaseController, dreamTileBoard);
        phaseShifter.loopRound();
    }

}
