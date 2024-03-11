import java.util.ArrayList;

public class RacingPhase {
    private ArrayList<Player> players;

    public RacingPhase(ArrayList<Player> players){
        this.players = players;
    }

    public void startPhase(){
        Player curr;
        for(int i = 0; i < players.size(); i++){
            curr = players.get(i);
            ArrayList<Card> hand = curr.getHand();
            for(int j = 0; j < hand.size(); j++){
                System.out.println(hand.get(i).isNightmare());
            }
        }
    }
}
