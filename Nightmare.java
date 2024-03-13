public class Nightmare{
    private String name;
    private int type;
    private NightmareBoard board;

    public Nightmare(String name, int type){
        this.name = name;
        this.type = type;
        board = (NightmareBoard) new BoardFactory().createBoard("Nightmare");
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public boolean isNightmare() {
        return true;
    }

    public NightmareBoard getBoard(){
        return board;
    }
}
