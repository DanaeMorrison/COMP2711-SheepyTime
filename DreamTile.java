import java.util.ArrayList;

public class DreamTile{
    private ArrayList<ZToken> tokens;
    private String tileName;
    //private String rule;

    public DreamTile(String name){
        tokens = new ArrayList<>();
        tileName = name;
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

    public void removePlayerAccess(Player player){
        if(!tokens.isEmpty()){
            int index = -1;
            for(int i = 0; i < tokens.size(); i++){
                if(tokens.get(i).getOwner() == player){
                    index = i;
                }
            }
            if(index != -1){
                tokens.remove(index);
            } //throw some sort of error if no player is found?
        }
    }
    
    //todo: use method, player param and move/add whatever as required
}