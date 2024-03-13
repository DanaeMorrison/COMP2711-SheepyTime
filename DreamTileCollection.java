import java.util.ArrayList;
import java.util.Collections;

public class DreamTileCollection{
    private ArrayList<DreamTile> tileCollection;

    public DreamTileCollection(){
        tileCollection = new ArrayList<>();
    }

    /**
     * Shuffles the tiles.
     */
    public void shuffle(){
        Collections.shuffle(tileCollection);
    }

    /**
     * Returns the top tile in the list.
     * 
     * @return top dreamtile in collection.
     */
    public DreamTile takeTile(){
        DreamTile tile = tileCollection.get(0);
        tileCollection.remove(0);
        return tile;
    }

    /**
     * Adds tile to collection.
     * 
     * @param tile tile to be added
     */
    public void add(DreamTile tile){
        tileCollection.add(tile);
    }
}