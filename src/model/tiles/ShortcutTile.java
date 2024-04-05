package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class ShortcutTile extends DreamTile{
    public ShortcutTile(){
        super("Shortcut", true, "Move to the next space with a dream tile that has 3 or more Zzz from any one player.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        for(int i = 0; i < 10; i++){
            for(Player p : players){
                if(dreamTileBoard.getTile(p.getBoard().getIndex() + i).getTokenCount(p) >= 3){
                    player.getBoard().advance(i);
                    return;
                }
            }
        }
    }
}
