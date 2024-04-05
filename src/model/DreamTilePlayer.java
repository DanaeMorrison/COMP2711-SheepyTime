package model;

import java.util.ArrayList;
/**
 * DreamTilePlayer Model Class
 * 
 * @author Danae Morrison
 * @version 1.0
 */

public class DreamTilePlayer {
    private DreamTileBoard dreamTileBoard;

    public DreamTilePlayer (DreamTileBoard dreamTileBoard) {
        this.dreamTileBoard = dreamTileBoard;
    }

    public boolean isUsableTilePresent(int playerPosition, Player player) {
        if (dreamTileBoard.occupied(playerPosition)) {
            DreamTile dreamTile = dreamTileBoard.getTile(playerPosition);
            ArrayList<ZToken> zTokens = dreamTile.getTokens();
            for (int i = 0; i < zTokens.size(); i++) {
                if (zTokens.get(i).getOwner().equals(player)) {
                    return true;
                }
            }
        }
        return false;
    }
}