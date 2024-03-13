import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RacingPhase {
    private ArrayList<Player> players;
    private Deck deck;
    private Nightmare nightmare;

    public RacingPhase(ArrayList<Player> players, Deck deck, Nightmare nightmare){
        this.players = players;
        this.deck = deck;
        this.nightmare = nightmare;
    }

    public void startPhase(){
        int playerCount = players.size();
        Player curr;
        Card picked;
        ArrayList<Integer> nightmareCardIndices = new ArrayList<>();
        CardViewer cardViewer;
        boolean nightmareFound;
        ArrayList<Card> usedCards = new ArrayList<>();

        while(!allPlayersAwake(players)){
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
    
                Scanner scanner = new Scanner(System.in); //maybe move all this to a turnviewer class?
    
                System.out.println("Input which card you would like to use from your hand (integer)");
                if(nightmareFound){
                    System.out.println("Uh oh! You have one or more nightmare cards in your hand! Use one of the following indices (or else!): " + Arrays.asList(nightmareCardIndices));
                }
    
                picked = hand.get(scanner.nextInt());
                usedCards.add(picked);
                hand.remove(picked);
                hand.add(deck.takeCard());

                CardPlayer cardPlayer = new CardPlayer();
                if(picked.isNightmare()){
                    cardPlayer.playNightmareCard(picked, nightmare, players);
                }
                else{
                    cardPlayer.playCard(picked, curr, nightmare);
                }

                BoardViewer.showBoard(players, nightmare);
            }
        }
        replaceUsedCards(usedCards, deck);
    }

    /**
     * Used to shuffle back in all the used cards.
     * 
     * @param used used cards to fill "toFill" deck
     * @param toFill deck to be filled by the used deck
     */
    public void replaceUsedCards(ArrayList<Card> used, Deck toFill){
        while(!used.isEmpty()){
            Card temp = used.getFirst();
            toFill.add(temp);
            used.remove(temp);
        }
        toFill.shuffle();
    }

    public boolean allPlayersAwake(ArrayList<Player> players){
        int awakeCount = 0;
        for(Player p : players){
            if(p.isAwake()){
                awakeCount++;
            }
        }
        return (awakeCount == players.size());
    }
}
