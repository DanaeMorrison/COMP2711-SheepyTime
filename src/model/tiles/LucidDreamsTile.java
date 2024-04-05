package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class LucidDreamsTile extends DreamTile{
    public LucidDreamsTile(){
        super("Lucid Dreams", true, "If you have Zzz on 2 or fewer dream tiles, gain 4 winks.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        int zCount = 0;
        for(int i = 0; i < 10; i++){
            if(dreamTileBoard.getTile(i).canUse(player)){
                zCount++;
            }
        }
        if(zCount <= 2){
            player.setWinks(player.getWinks() + 4);
        }
    }
}
