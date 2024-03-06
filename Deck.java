import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    
    public Deck(){
        deck = new ArrayList<>();
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card takeCard(){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public void add(Card card){
        deck.add(card);
    }
}
