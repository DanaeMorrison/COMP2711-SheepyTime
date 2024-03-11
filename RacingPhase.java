import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RacingPhase {
    private ArrayList<Player> players;
    private Deck deck;

    public RacingPhase(ArrayList<Player> players, Deck deck){
        this.players = players;
        this.deck = deck;
    }

    public void startPhase(){
        int playerCount = players.size();

        Player curr;
        Card picked;
        ArrayList<Integer> nightmareCardIndices = new ArrayList<>();
        CardViewer cardViewer;
        boolean nightmareFound;
        for(int i = 0; i < playerCount; i++){
            nightmareCardIndices.clear();
            nightmareFound = false;

            curr = players.get(i);
            ArrayList<Card> hand = curr.getHand();
            
            for(int j = 0; j < hand.size(); j++){
                System.out.println(curr.getName() + "'s card #" + j);

                if(curr.getHand().get(i).isNightmare()){
                    nightmareFound = true;
                    nightmareCardIndices.add(j);
                }

                cardViewer = new CardViewer(curr.getHand().get(j), 1);
                cardViewer.rulePrint();
            }

            Scanner scanner = new Scanner(System.in); //maybe move all this to a turnviewer class? idk, mvc smells
            System.out.println("Input which card you would like to use from your hand (integer)");
            if(nightmareFound){
                System.out.println("Uh oh! You have one or more nightmare cards in your hand! Use one of the following indices: " + Arrays.asList(nightmareCardIndices));
            }
            picked = hand.get(scanner.nextInt());
        }
    }
}
