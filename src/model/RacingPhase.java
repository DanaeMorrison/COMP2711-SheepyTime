package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import view.BoardViewer;
import view.CardViewer;

public class RacingPhase {
    private ArrayList<Player> players;
    private Deck deck;
    private Nightmare nightmare;
    private DreamTileBoard dreamTileBoard;

    public RacingPhase(ArrayList<Player> players, Deck deck, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        this.players = players;
        this.deck = deck;
        this.nightmare = nightmare;
        this.dreamTileBoard = dreamTileBoard;
    }

    public void startPhase(){
        int playerCount = players.size();
        Player curr;
        Card picked;
        CardViewer cardViewer;
        CardPlayer cardPlayer = new CardPlayer();
        ArrayList<Card> usedCards = new ArrayList<>();

        while(!allPlayersAwake(players)){
            for(int i = 0; i < playerCount; i++){
                curr = players.get(i);
                if(curr.isAwake()){
                    continue;
                }

                fillHand(curr, players, usedCards);

                ArrayList<Card> hand = curr.getHand();
    
                for(int j = 0; j < hand.size(); j++){
                    System.out.println(curr.getName() + "'s card #" + j);
                    cardViewer = new CardViewer(curr.getHand().get(j), 1); //TODO: currently cardviewer can't actually change the card and nightmare type, should probably change
                    cardViewer.rulePrint();
                }
    
                Scanner scanner = new Scanner(System.in); //maybe move all this to a turnviewer class?
    
                System.out.println("Input which card you would like to use from your hand (integer)");
    
                picked = hand.get(scanner.nextInt());
                usedCards.add(picked);
                hand.remove(picked);

                cardPlayer.playCard(picked, curr, nightmare);

                //TODO: here, the dreamtile is always being used if it can be used. we need to ask the player if they want to use it or not.
                dreamTileUser(curr);

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

    public void fillHand(Player player, ArrayList<Player> players, ArrayList<Card> usedCards){
        for(int j = player.getHand().size(); j < 2; j++){
            Card card = deck.takeCard();
            usedCards.add(card);
            
            if(card.isNightmare()){
                new CardViewer(card, nightmare.getType()).rulePrint();
                new CardPlayer().playNightmareCard(card, nightmare, players);
                j--;
                continue;
            }

            player.getHand().add(card);
        }
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

    private void dreamTileUser(Player player){
        PlayerBoard board = player.getBoard();
        int playerPos = board.getIndex();
        DreamTile tile =dreamTileBoard.getTile(playerPos);
        if(tile.canUse(player)){
            tile.useTile(player);
        }
        tile.removePlayerToken(player);
    }
}
