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
        ArrayList<Player> players = init.generatePlayers(playerCount);
        
        Nightmare nightmare = new Nightmare("Wolf", 1); //eventually ask for nightmare

        Deck deck = new Deck();
        
        init.generateCards(deck, players, nightmare);

        RacingPhase racingPhase = new RacingPhase(players, deck);
        racingPhase.startPhase();
    }

    public void generateCards(Deck deck, ArrayList<Player> players, Nightmare nightmare){
        int playerCount = players.size();
        DeckGenerator deckGenerator = new DeckGenerator(deck, playerCount, nightmare.getType());
        deckGenerator.makeDeck();
        deck.shuffle();

        Player curr;
        for(int i = 0; i < playerCount; i++){
            curr = players.get(i);
            for(int j = 0; j < 3; j++){
                curr.getHand().add(deck.takeCard());
            }
        }
    }
}
