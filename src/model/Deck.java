package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private Random random;
    private int nextIndex;
    
    public Deck(){
        deck = new ArrayList<>();

    }
    
    /**
     * Shuffles the cards.
     */
    public void shuffle(){
        nextIndex = random.nextInt(deck.size());
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
        shuffle();
        Card card = deck.get(nextIndex);
        deck.remove(nextIndex);
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

    public int getSize(){
        return deck.size();
    }
}
