package model.tiles;
import model.DreamTile;
import model.Player;

public class PipeDreamTile extends DreamTile{
    public PipeDreamTile(){
        super("Pipe Dream", false, "If you moved 8 or more spaces before landing here this turn, move your pillow down 3.");
    }

    @Override
    public void useTile(Player player){
        int HAS_TO_GET_DONE; //need instance var in player to know how many spaces moved? maybe also in dreamtile itself to know where it's placed, idk
    }
}
