package model;
import java.util.ArrayList;

public abstract class DreamTile{
    private ArrayList<ZToken> tokens;
    private String tileName;
    private boolean infiniteBonus;
    private String rule;

    public DreamTile(String name, boolean infiteBonus, String rule){
        tokens = new ArrayList<>();
        tileName = name;
        this.infiniteBonus = infiteBonus;
        this.rule = rule;
    }

    public String getRule(){
        return rule;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean canUse(Player player){
        for(ZToken t : tokens){
            if(t.getOwner() == player){
                return true;
            }
        }
        return false;
    }

    public int getTokenCount(Player player){
        int count = 0;
        for(ZToken z : tokens){
            if(z.getOwner() == player){
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a z token to the tile.
     * 
     * @param token token to be added to tile
     */
    public void addToken(ZToken token){
        tokens.add(token);
    }

    public void addToken(Player player, boolean isInfinite){
        tokens.add(new ZToken(isInfinite, player));
    }

    public void removePlayerToken(Player player){
        if(!tokens.isEmpty()){
            int index = -1;
            for(int i = 0; i < tokens.size(); i++){
                if(tokens.get(i).getOwner() == player && !tokens.get(i).isInfinite()){
                    index = i;
                }
            }
            if(index != -1){
                tokens.remove(index);
            } //throw some sort of error if no player is found?
        }
    }

    public boolean hasInfinite(Player player){
        for(ZToken z : tokens){
            if(z.getOwner() == player && z.isInfinite()){
                return true;
            }
        }
        return false;
    }

    public boolean isInfiniteBonus(){
        return infiniteBonus;
    }

    public ArrayList<ZToken> getTokens(){
        return tokens;
    }

    public void useTile(Player player, ArrayList<Player> players, Nightmare nightmare, DreamTileBoard dreamTileBoard){};

    //todo: use method, player param and move/add whatever as required
}