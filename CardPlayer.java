import java.util.Arrays;
import java.util.Scanner;

public class CardPlayer {
    public void playCard(Card card, Player player){
        Scanner scanner = new Scanner(System.in);
        if(card.isNightmare()){

        }
        else{
            if(!card.bothConditions()){ //"OR" card
                System.out.println("You've picked an OR card. Which ability do you want to use? (Don't lie or this'll break...)");
                System.out.println("1 - Move");
                System.out.println("2 - Catch Winks");
                System.out.println("3 - Get ZTokens");
                int ability = scanner.nextInt();
            }
            else{ //"AND" card or single-ability card
                PlayerBoard board = player.getBoard();
                int moveAmount = card.getMoves()[0];
                if(card.getMoves().length > 1){
                    System.out.println("How many steps do you want to move?");
                    for(int i = 0; i < card.getMoves().length; i++){
                        System.out.println(i + " - " + card.getMoves()[i] + " spaces");
                    }
                    moveAmount = scanner.nextInt();
                }
                
                if(board.isCrossing(moveAmount)){
                    player.setWinks(player.getWinks() + 5);
                }
                else{
                    board.advance(moveAmount);
                }
                
                player.setWinks(player.getWinks() + card.getWinks());
                player.setZtokens(player.getZtokens() + card.getZtokens());
            }
        }
        
    }
}