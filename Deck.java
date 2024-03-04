import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<CardInterface> deck;
    
    public Deck(){
        deck = new ArrayList<>();
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }

    public CardInterface takeCard(){
        CardInterface card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public void addCard(CardInterface card){
        deck.add(card);
    }
}
