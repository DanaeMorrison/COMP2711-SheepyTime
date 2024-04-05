package model;
import java.util.ArrayList;

public class Player{
    private String name;
    private int orderPosition;
    private ArrayList<Card> hand;
    private BoardInterface board;
    private int scaredStatus;
    private boolean isAwake;
    private boolean justCrossed;
    private int winks;
    private int zTokens;

    //TODO CodeSmell unussedvariable
    private int infiniteZTokens;

    private Score scoreboard;
    
    public Player(String name, int orderPosition){
        BoardFactory factory = new BoardFactory();
        board = factory.createBoard("Player");

        justCrossed = false;
        hand = new ArrayList<>();
        this.name = name;
        this.orderPosition = orderPosition;
        isAwake = false;
        justCrossed = false;
        winks = 0;
        zTokens = 0;
        scoreboard = new Score();
        setBrave();
    }

    public boolean isAwake(){
        return isAwake;
    }

    public boolean justCrossed(){
        return justCrossed;
    }

    public void setCrossed(boolean in){
        justCrossed = in;
    }

    public void setAwake(boolean isAwake){
        this.isAwake = isAwake;
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

    //TODO: Bad method name, this returns int, but looks like it returns boolean
    public int isScared(){
        return scaredStatus;
    }

    public void setScaredStatus(int status){
        scaredStatus = status;
    }

    public void setBrave(){
        scaredStatus = 0;
    }

    public void setScoreboard(Score in){
        scoreboard = in;
    }

    public PlayerBoard getBoard(){
        return (PlayerBoard) board;
    }

    public int getZtokens(){
        return zTokens;
    }

    public void setZtokens(int zTokens){
        this.zTokens = zTokens;
    }
}
