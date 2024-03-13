import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    
    public Deck(){
        deck = new ArrayList<>();
    }
    
    /**
     * Shuffles the cards.
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    /**
     * Returns the top card in deck.
     * 
     * @return top card in deck.
     */
    public Card takeCard(){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    /**
     * Adds a card to deck.
     * 
     * @param card Card to be added.
     */
    public void add(Card card){
        deck.add(card);
    }
}
