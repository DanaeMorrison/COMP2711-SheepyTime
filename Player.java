public class Player{
    private String name;
    private BoardInterface board;
    private boolean isScared;
    private int winks;

    public Player(String name){
        BoardFactory factory = new BoardFactory();
        board = factory.createBoard("Player");
        this.name = name;
        winks = 0;
        setBrave();
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

    public void setScare(){
        isScared = true;
    }

    public void setBrave(){
        isScared = false;
    }

    public BoardInterface getBoard(){
        return board;
    }
}
