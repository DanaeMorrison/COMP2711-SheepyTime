import java.util.ArrayList;
import java.util.Collections;

public class DreamTileCollection{
    private ArrayList<DreamTile> tileCollection;

    public DreamTileCollection(){
        tileCollection = new ArrayList<>();
    }

    public void shuffle(){
        Collections.shuffle(tileCollection);
    }

    public DreamTile takeTile(){
        DreamTile tile = tileCollection.get(0);
        tileCollection.remove(0);
        return tile;
    }

    public void add(DreamTile tile){
        tileCollection.add(tile);
    }
}