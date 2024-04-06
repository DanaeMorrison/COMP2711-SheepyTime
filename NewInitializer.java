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

public class NewInitializer {
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

        return playerCount;
    }

    public static void main(String[] args) {
        ArrayList<Player> players = generatePlayers();
        Nightmare nightmare = new Nightmare("wolf", 1);
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
