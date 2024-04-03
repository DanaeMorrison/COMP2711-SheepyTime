
package view;
// import model.Card;
/**
 * @author Danae Morrison
 */
 //TODO: what should we do with CardViewerInterface?

public class CardViewer{
    /**
     * This class prints off the details of a given card
     */
    // private Card card;
    // private int nightmare;

    /**
     * This constructor creates a CardViewer that takes in a card and the int associated with the chosen nightmare.
     * This allows all cards of a certain nightmare type to be printed with the same name
     * @param card a specific card that is drawn from a deck or in a player's hand
     * @param nightmare the int associated with the chosen nightmare for a game
     */
    /** public CardViewer (Card card, int nightmare) {
        this.card = card;
        this.nightmare = nightmare;
    } */
    /**
     * This method prints the information on a card to be displayed to the user
     */
    public void rulePrint(int[] moves, int jumpPos, int spiderMove, int winks, int Ztokens, int nightmare, boolean isNightmare, boolean bothConditions) {
        System.out.println("            ");
        System.out.println("____________");
        System.out.println("            ");
        
        if (isNightmare) {
            if (nightmare == 1) {
                System.out.println("    WOLF    ");
                System.out.println("            ");
                System.out.println("            ");

                if (moves[0] != 10) {
                    System.out.println("Move nightmare by " + String.valueOf(moves[0]) + " spaces");
                } else {
                    System.out.println("All players on and adjacent to the nightmare get scared");
                }
            }

            if (nightmare == 2) {
                System.out.println("    BUMP    ");
                System.out.println("            ");
                System.out.println("            ");

                if (jumpPos > 1) {
                    System.out.println("Jump nightmare " + String.valueOf(jumpPos) + " spaces forward");
                } else if (jumpPos == 1) {
                    System.out.println("Jump nightmare 1 space forward");
                } else if (jumpPos == -2) {
                    System.out.println("Jump nightmare 2 spaces backward");
                } else {
                    System.out.println("Jump nightmare 1 space backward");
                }
            }

            if (nightmare == 3) {
                System.out.println("   SPIDER   ");
                System.out.println("            ");
                System.out.println("            ");

                System.out.println("Jump nightmare to web token");
                System.out.println("If nightmare doesn't move, move web token forward by " + String.valueOf(spiderMove) + " spaces");
            }

        } else {
            System.out.println("    SLEEP   ");
            System.out.println("            ");
            System.out.println("            ");

            if (bothConditions == false) {
                String condition1 = "Move " + String.valueOf(moves[0]);
                if (moves.length > 1) {
                    condition1 += " OR " + String.valueOf(moves[1]);
                }
                condition1 += " spaces";
                System.out.println(condition1);
            } //add functionality for AND

            if (winks != 0) {
                System.out.println("    OR      ");
                System.out.println("Gain " + String.valueOf(winks) + " Wink");
            }
           
            if (Ztokens != 0) {
                System.out.println("    OR      ");
                System.out.println("Catch " + String.valueOf(Ztokens) + " Zzz");
            }
            
        }
        
        System.out.println("            ");
        System.out.println("            ");
        System.out.println("____________");
    }

}
