public class Player{
    private String name;
    private BoardInterface board;
    private boolean isScared;
    private int winks;
    private Score scoreboard;

    public Player(String name){
        BoardFactory factory = new BoardFactory();
        board = factory.createBoard("Player");
        this.name = name;
        winks = 0;
        scoreboard = new Score(this);
        setBrave();
    }

    public Score getScoreboard(){
        return scoreboard;
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

    public BoardInterface getBoard(){
        return board;
    }
}
