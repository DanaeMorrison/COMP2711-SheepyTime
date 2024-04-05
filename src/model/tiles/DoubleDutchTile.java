package model.tiles;
import java.util.ArrayList;

import model.CardPlayer;
import model.DreamTile;
import model.DreamTileBoard;
import model.Nightmare;
import model.Player;

public class DoubleDutchTile extends DreamTile{
    public DoubleDutchTile(){
        super("Double Dutch", false, "Play the other card from your hand.");
    }

    @Override
    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){
        CardPlayer cardPlayer = new CardPlayer();
        cardPlayer.playCard(player.getHand().getFirst(), player, nightmare);
    }
}
