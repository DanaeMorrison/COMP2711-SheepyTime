import java.util.ArrayList;
import java.util.Scanner;

import model.Deck;
import model.DeckGenerator;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;
import model.RacingPhase;
import model.tiles.DreamJournalTile;


public class Initializer {

    /**
     * This method generates n new players, and prompts the users to get a name.
     * 
     * @param n Player count.
     * @return ArrayList of newly generated players.
     */
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

    /**
     * Prompts the user for how many players they'd like.
     * 
     * @return amount of players to be generated.
     */
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
        DreamTileBoard dreamTileBoard = new DreamTileBoard();

        Nightmare nightmare = new Nightmare("Wolf", 1); //eventually ask for nightmare

        Deck deck = new Deck();
        
        init.generateCards(deck, players, nightmare);

        RacingPhase racingPhase = new RacingPhase(players, deck, nightmare); //need dreamtileboard in constructor here after nightmare

        racingPhase.startPhase();
        init.resetScaredStatus(players);
    }

    /**
     * Initializes a given deck and assigns players appropriate cards for their hand.
     * 
     * @param deck Deck that cards will be taken from.
     * @param players Players to initialize a hand.
     * @param nightmare Nightmare to generate appropriate nightmare cards.
     */
    public void generateCards(Deck deck, ArrayList<Player> players, Nightmare nightmare){
        int playerCount = players.size();
        DeckGenerator deckGenerator = new DeckGenerator(deck, playerCount, nightmare.getType());
        deckGenerator.makeDeck();
        deck.shuffle();

        Player curr;
        for(int i = 0; i < playerCount; i++){
            curr = players.get(i);
            for(int j = 0; j < 2; j++){
                curr.getHand().add(deck.takeCard());
            }
        }
    }

    /**
     * Resets all players' scared status and awake-ness to default (0 and false).
     * 
     * @param players players to reset
     */
    public void resetScaredStatus(ArrayList<Player> players){
        for(Player p : players){
            p.setAwake(false);
            p.setScaredStatus(0);
        }
    }
}
