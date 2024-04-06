package model;
import model.tiles.*;

/**
 * @author Danae Morrison
 */

public class DreamTileGenerator {
    private String[] tileNames = {"ActionHero", "HideOrSeek", "Moonwalk", "RestingSpot",  
                                    "IntenseDreams", "PerfectLanding", "Run", "SnoozeMovesBounceAhead", 
                                    "LoneSheep", "StepBackCoolKidsClub", 
                                     "LucidDreams", "RecurringDream", "SecondWind"};

    //private DreamTileCollection tileCollection;

    public void makeDreamTiles(DreamTileCollection tileCollection) {
        DreamTileFactory factory = new DreamTileFactory();
        for (int i = 0; i < tileNames.length; i++) {
            try{
                DreamTile tile = factory.createDreamTile(tileNames[i]);
                tileCollection.add(tile);
            }
            catch (IllegalArgumentException iae) {
                continue;
            }
        }
    }

    public class DreamTileFactory{
        public DreamTile createDreamTile(String tileType){
            if(tileType.equals("ActionHero")){ return new ActionHeroTile(); }
            
            else if(tileType.equals("HideOrSeek")){ return new HideOrSeekTile(); }
            
            else if(tileType.equals("Moonwalk")){ return new MoonwalkTile(); }
            
            else if(tileType.equals("RestingSpot")){ return new RestingSpotTile(); }
            
            else if(tileType.equals("Shortcut")){ return new ShortcutTile(); }

            else if(tileType.equals("BigStash")){ return new BigStashTile(); }
            
            else if(tileType.equals("IntenseDreams")){ return new IntenseDreamsTile(); }
            
            else if(tileType.equals("PerfectLanding")){ return new PerfectLandingTile(); }
            
            else if(tileType.equals("Run")){ return new RunTile(); }
            
            else if(tileType.equals("SnoozeMoves")){ return new SnoozeMovesTile(); }

            else if(tileType.equals("BounceAhead")){ return new BounceAheadTile(); }
            
            else if(tileType.equals("LoneSheep")){ return new LoneSheepTile(); }
            
            else if(tileType.equals("StepBack")){ return new StepBackTile(); }

            else if(tileType.equals("CoolKidsClub")){ return new CoolKidsClubTile(); }
            
            else if(tileType.equals("LucidDreams")){ return new LucidDreamsTile(); }
            
            else if(tileType.equals("RecurringDream")){ return new RecurringDreamTile(); }
            
            else if(tileType.equals("SecondWind")){ return new SecondWindTile(); }
            
            else{
                throw new IllegalArgumentException("No such DreamTile exists!");
            }
        }
    }
}
