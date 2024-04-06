import java.util.ArrayList;
import java.util.Scanner;

import model.Player;

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
    }

}
