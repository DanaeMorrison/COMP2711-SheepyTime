public class Nightmare{
    private String name;
    private BoardInterface board;

    public Nightmare(String name){
        this.name = name;
        board = new BoardFactory().createBoard("Nightmare");
    }

    public String getName(){
        return name;
    }

    public boolean isNightmare() {
        return true;
    }
}
