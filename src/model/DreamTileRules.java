package model;

/**
 * @author Danae Morrison
 */

public class DreamTileRules {
    /**
     * This class carries out the rules of a dreamtile upon being chosen by a player
     * who is eligible to use it
     */
    private String tileName;
    private Player curPlayer;
    // private Score curPlayerScore;

    public DreamTileRules(String tileName, Player curPlayer/* , Score curPlayerScore */) {
        this.tileName = tileName;
        this.curPlayer = curPlayer;
        // this.curPlayerScore = curPlayerScore;
    }

    public void getRule() {
        if (tileName.equals("action hero")) {
            // System.out.println("if you are scared, gain 3 winks");

            if (curPlayer.isScared() >= 1) {
                curPlayer.getScoreboard().setWinkPos(curPlayer.getScoreboard().getWinkPos() + 3);
                curPlayer.setWinks(curPlayer.getScoreboard().getWinkPos());
            }
        }

        if (tileName.equals("final sprint")) {
            // ystem.out.println("if you are scared, move forward 7 spaces");

            if (curPlayer.isScared() >= 1) {
                curPlayer.getBoard().advance(7);
            }
        }

        if (tileName.equals("cool kids club")) {
            // System.out.println("Move your pillow down 1");
            curPlayer.getScoreboard().setPillowPos(curPlayer.getScoreboard().getPillowPos() - 1);
        }

        if (tileName.equals("bounce ahead")) {
            // System.out.println("Move forward 1 space");
            curPlayer.getBoard().advance(1);
        }
    }
}