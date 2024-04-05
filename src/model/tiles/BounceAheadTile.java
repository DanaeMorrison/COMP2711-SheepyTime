package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class BounceAheadTile extends DreamTile{
    public BounceAheadTile(){
        super("Bounce Ahead", true, "Move forward 1 space.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.getBoard().advance(1);
    }
}
