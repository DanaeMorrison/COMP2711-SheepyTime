package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class IntenseDreamsTile extends DreamTile{
    public IntenseDreamsTile(){
        super("Intense Dreams", true, "Become Scared to gain 4 winks.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setScaredStatus(1);
        player.setWinks(player.getWinks() + 4);
    }
}
