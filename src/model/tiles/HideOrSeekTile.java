package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class HideOrSeekTile extends DreamTile{
    public HideOrSeekTile(){
        super("Hide or Seek", true, "If the nightmare is on an odd-numbered space, gain 2 winks.\n If it's on an even-numbered space, move 2 spaces.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        if(nightmare.getBoard().getIndex() % 2 == 0){
            player.getBoard().advance(2);
        }
        else{
            player.setWinks(player.getWinks() + 2);
        }
    }
}
