package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class PerfectLandingTile extends DreamTile{
    public PerfectLandingTile(){
        super("Perfect Landing", true, "Catch 1 Zzz. You may become Brave.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        if(player.justCrossed()){
            player.setWinks(player.getWinks() + player.getBoard().getIndex() + 1);
        }
    }
}
