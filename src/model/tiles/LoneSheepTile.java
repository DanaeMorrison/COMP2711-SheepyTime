package model.tiles;
import model.DreamTile;
import model.Player;

public class LoneSheepTile extends DreamTile{
    public LoneSheepTile(){
        super("Lone Sheep", true, "If you have no Zzz on any other Dream Tiles within 2 spaces of this one, gain 3 winks.");
    }

    @Override
    public void useTile(Player player){
        //TODO: ?????
    }
}
