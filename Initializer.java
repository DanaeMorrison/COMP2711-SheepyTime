import java.util.ArrayList;
import java.util.Scanner;

public class Initializer {

    public ArrayList<Player> generatePlayers(int n){
        ArrayList<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String name;
        for(int i = 0; i < n; i++){
            System.out.println("Input player " + (i + 1) + "'s name.");
            name = scanner.next();
            players.add(new Player(name, i));
        }

        //scanner.close();
        return players;
    }

    public int generateParams(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players would you like?");
        int playerCount = scanner.nextInt();

        //scanner.close(); closing the scanner causes weird errors!
        return playerCount;
    }

    public static void main(String[] args) {
        Initializer init = new Initializer();
        int playerCount = init.generateParams();
        System.out.println(playerCount);
        ArrayList<Player> players = init.generatePlayers(playerCount);
    }
}
