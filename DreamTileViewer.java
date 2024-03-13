/**
 * @author Danae Morrison
 */

public class DreamTileViewer {
    
    public void printRule(String tileName) {

        System.out.println("");
        System.out.println("DREAM TILE");
        System.out.println("");

        if (tileName.equals("action hero")) {
            System.out.println("ACTION HERO: If you are scared, gain 3 winks");
        }

        if (tileName.equals("final sprint")) {
            System.out.println("FINAL SPRINT: if you are scared, move forward 7 spaces");
        }

        if (tileName.equals("cool kids club")) {
            System.out.println("COOL KIDS CLUB: Move your pillow down 1");
            }

        if (tileName.equals("bounce ahead")) {
            System.out.println("BOUNCE AHEAD: Move forward 1 space");
        }

        System.out.println("");
    }
}
