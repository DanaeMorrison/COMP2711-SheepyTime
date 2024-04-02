package model;

/**
 * @author Danae Morrison
 */

public class DreamTileGenerator {
    private String[] tileNames = {"action hero", "final sprint", "cool kids club", "bounce ahead"};
    //private DreamTileCollection tileCollection;

    public void makeDreamTiles(DreamTileCollection tileCollection) {
        DreamTileFactory factory = new DreamTileFactory();
        for (int i = 0; i < tileNames.length; i++) {
            DreamTile tile = factory.createDreamTile(tileNames[i]);
            tileCollection.add(tile);
        }
    }

    public class DreamTileFactory{
        public DreamTile createDreamTile(String tileType){
            if(tileType.equals("action hero")){
                //return new ActionHeroTile();
            }
            else if(tileType.equals("final sprint")){
                //return new FinalSprintTile();
            }
            else{
                throw new IllegalArgumentException("No such DreamTile exists!");
            }
        }
    }
}
