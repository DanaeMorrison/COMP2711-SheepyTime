package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class LoneSheepTile extends DreamTile{
    public LoneSheepTile(){
        super("Lone Sheep", true, "If you have no Zzz on any other Dream Tiles within 2 spaces of this one, gain 3 winks.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        int index = player.getBoard().getIndex();
        boolean hasZ = false;
        for(int i = (index - 2) % 10; i < (index  + 2) % 10; i++){
            if(dreamTileBoard.getTile(i).canUse(player)){
                hasZ = true;
                break;
            }
        }
        if(!hasZ){
            player.setWinks(player.getWinks() + 3);
        }
    }
}
