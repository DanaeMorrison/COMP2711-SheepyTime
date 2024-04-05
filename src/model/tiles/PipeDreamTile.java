package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class PipeDreamTile extends DreamTile{
    public PipeDreamTile(){
        super("Pipe Dream", false, "If you moved 8 or more spaces before landing here this turn, move your pillow down 3.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        int HAS_TO_GET_DONE; //need instance var in player to know how many spaces moved? maybe also in dreamtile itself to know where it's placed, idk
    }
}
