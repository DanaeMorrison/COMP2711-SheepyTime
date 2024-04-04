package model.tiles;
import model.DreamTile;
import model.Player;

public class IntenseDreamsTile extends DreamTile{
    public IntenseDreamsTile(){
        super("Intense Dreams", true, "Become Scared to gain 4 winks.");
    }

    @Override
    public void useTile(Player player){
        player.setScaredStatus(1);
        player.setWinks(player.getWinks() + 4);
    }
}
