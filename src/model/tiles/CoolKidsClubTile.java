package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class CoolKidsClubTile extends DreamTile{
    public CoolKidsClubTile(){
        super("Cool Kids Club", true, "Move your pillow down 1.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.getScoreboard().setPillowPos(player.getScoreboard().getPillowPos() - 1);
    }
}
