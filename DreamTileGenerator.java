public class DreamTileGenerator {
    private String[] tileNames = {"action hero", "final sprint", "cool kids club", "bounce ahead"};
    //private DreamTileCollection tileCollection;

    public void makeDreamTiles(DreamTileCollection tileCollection) {
        for (int i = 0; i < tileNames.length; i++) {
            DreamTile tile = new DreamTile(tileNames[i]);
            tileCollection.add(tile);
        }
    }
}
