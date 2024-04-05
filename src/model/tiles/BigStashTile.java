package model.tiles;
import java.util.ArrayList;

import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class BigStashTile extends DreamTile{
    public BigStashTile(){
        super("Big Stash", false, "Gain 2 winks for each Zzz you have here, then remove all your Zzz from here.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        player.setWinks(player.getWinks() + (getTokenCount(player) * 2));
        for(int i = 0; i < getTokenCount(player); i++){
            removePlayerToken(player);
        }
    }
}
