package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class RushAheadTile extends DreamTile{
    public RushAheadTile(){
        super("Rush Ahead", true, "Gain 1 wink. Play the top card of the deck.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        int HAS_TO_GET_DONE;
    }
}
