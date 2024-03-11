public class CardViewer implements CardViewerInterface {
    private Card card;
    private int nightmare;

    public CardViewer (Card card, int nightmare) {
        this.card = card;
        this.nightmare = nightmare;
    }

    public void rulePrint() {
        System.out.println("            ");
        System.out.println("____________");
        System.out.println("            ");
        
        if (card.isNightmare()) {
            if (nightmare == 1) {
                System.out.println("    WOLF    ");
                System.out.println("            ");
                System.out.println("            ");

                if (card.getMoves()[0] != 10) {
                    System.out.println("Move nightmare by " + String.valueOf(card.getMoves()[0]) + " spaces");
                } else {
                    System.out.println("All players on and adjacent to the nightmare get scared");
                }
            }

            if (nightmare == 2) {
                System.out.println("    BUMP    ");
                System.out.println("            ");
                System.out.println("            ");

                if (card.getJumpPos() > 1) {
                    System.out.println("Jump nightmare " + String.valueOf(card.getJumpPos()) + " spaces forward");
                } else if (card.getJumpPos() == 1) {
                    System.out.println("Jump nightmare 1 space forward");
                } else if (card.getJumpPos() == -2) {
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
                System.out.println("If nightmare doesn't move, move web token forward by " + String.valueOf(card.getSpiderMove()) + " spaces");
            }

        } else {
            System.out.println("    SLEEP   ");
            System.out.println("            ");
            System.out.println("            ");

            if (card.bothConditions() == false) {
                String condition1 = "Move " + String.valueOf(card.getMoves()[0]);
                if (card.getMoves().length > 1) {
                    condition1 += " OR " + String.valueOf(card.getMoves()[1]);
                }
                condition1 += " spaces";
                System.out.println(condition1);
            }

            if (card.getWinks() != 0) {
                System.out.println("    OR      ");
                System.out.println("Gain " + String.valueOf(card.getWinks()) + " Wink");
            }
           
            if (card.getZtokens() != 0) {
                System.out.println("    OR      ");
                System.out.println("Catch " + String.valueOf(card.getZtokens()) + " Zzz");
            }
            
        }
        
        System.out.println("            ");
        System.out.println("            ");
        System.out.println("____________");
    }

}
