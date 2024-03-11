import java.util.ArrayList;

public class Player{
    private String name;
    private int orderPosition;
    private ArrayList<Card> hand;
    private BoardInterface board;
    private boolean isScared;
    private int winks;
    private Score scoreboard;

    public Player(String name, int orderPosition){
        BoardFactory factory = new BoardFactory();
        board = factory.createBoard("Player");

        hand = new ArrayList<>();
        this.name = name;
        this.orderPosition = orderPosition;
        winks = 0;
        scoreboard = new Score(this);
        setBrave();
    }

    public int getOrderPosition(){
        return orderPosition;
    }

    public Score getScoreboard(){
        return scoreboard;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void setHand(ArrayList<Card> inHand){
        hand = inHand;
    }

    public String getName(){
        return name;
    }

    public int getWinks(){
        return winks;
    }

    public void setWinks(int in){
        winks = in;
    }

    public boolean isScared(){
        return isScared;
    }

    public void setScared(){
        isScared = true;
    }

    public void setBrave(){
        isScared = false;
    }

    public void setScoreboard(Score in){
        scoreboard = in;
    }

    public PlayerBoard getBoard(){
        return (PlayerBoard) board;
    }
}
