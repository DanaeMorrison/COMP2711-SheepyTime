package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class RecurringDreamTile extends DreamTile{
    public RecurringDreamTile(){
        super("Recurring Dream", true, "Gain 2 winks for each dream tile you have 3 or more Zzz on.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        int threeOrMore = 0;
        for(int i = 0; i < 10; i++){
            DreamTile tile = dreamTileBoard.getTile(i);
            if(tile.getTokenCount(player) >= 3){
                threeOrMore++;
            }
        }
        player.setWinks(player.getWinks() + (threeOrMore * 2));
    }
}
