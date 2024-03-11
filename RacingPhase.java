import java.util.ArrayList;
import java.util.Scanner;

public class RacingPhase {
    private ArrayList<Player> players;

    public RacingPhase(ArrayList<Player> players){
        this.players = players;
    }

    public void startPhase(){
        Player curr;
        Card picked;
        for(int i = 0; i < players.size(); i++){
            curr = players.get(i);
            ArrayList<Card> hand = curr.getHand();
            for(int j = 0; j < hand.size(); j++){
                System.out.println(hand.get(i).isNightmare()); //to be replaced with a working card viewer
            }

            Scanner scanner = new Scanner(System.in); //maybe move all this to a turnviewer class? idk, mvc smells
            System.out.println("Input which card you would like to use from your hand (integer)");
            picked = hand.get(scanner.nextInt());
        }
    }
}
