package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class ActionHeroTile extends DreamTile{
    public ActionHeroTile(){
        super("Action Hero", true, "If you are scared, gain 3 winks.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        if(player.isScared() >= 1){
            player.setWinks(player.getWinks() + 3);
        }
    }
}
