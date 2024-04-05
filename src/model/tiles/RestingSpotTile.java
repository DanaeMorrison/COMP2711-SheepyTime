package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class RestingSpotTile extends DreamTile{
    public RestingSpotTile(){
        super("Resting Spot", true, "Catch 1 Zzz. You may become Brave.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setWinks(player.getWinks() + 1);
        player.setBrave();
    }
}
