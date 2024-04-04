package model.tiles;
import model.DreamTile;
import model.Player;

public class RunTile extends DreamTile{
    public RunTile(){
        super("Ruuuuuun!", true, "Move forward to the space in front of the nightmare.");
    }

    @Override
    public void useTile(Player player){
        int HAS_TO_GET_DONE; //need to get nightmare position somehow
    }
}
