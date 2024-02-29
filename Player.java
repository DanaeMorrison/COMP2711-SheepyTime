public class Player implements Character{
    private String name;
    private Board board;
    private boolean isScared;
    private int winks;

    public Player(String name){
        BoardFactory factory = new BoardFactory();
        BoardInterface board = factory.createBoard("Player");
        this.name = name;
        winks = 0;
        calm();
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

    public void scare(){
        isScared = true;
    }

    public void calm(){
        isScared = false;
    }
}
