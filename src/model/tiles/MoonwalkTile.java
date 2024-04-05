package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class MoonwalkTile extends DreamTile{
    public MoonwalkTile(){
        super("Moonwalk", true, "Gain 2 winks. Move backward 2 spaces.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setWinks(player.getWinks() + 2);
        player.getBoard().advance(-2);
    }
}
