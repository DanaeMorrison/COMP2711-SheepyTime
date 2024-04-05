package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class DeepSleepTile extends DreamTile{
    public DeepSleepTile(){
        super("Deep Sleep", true, "Double the number of Zzz you have on a single dream tile. Gain 1 wink.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setWinks(player.getWinks() + 1);
        int HAS_TO_GET_DONE;
    }
}
