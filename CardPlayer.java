import java.util.Arrays;
import java.util.Scanner;

public class CardPlayer {
    /**
     * Plays a card and performs the appropriate operations specified by a card for the given player.
     * 
     * @param card Card to be played.
     * @param player Player playing the card.
     */
    public void playCard(Card card, Player player){
        Scanner scanner = new Scanner(System.in);
        PlayerBoard board = player.getBoard();
        if(!card.bothConditions()){ //"OR" card
            System.out.println("You've picked an OR card. Which ability do you want to use? (Don't lie or this'll break...)");
            System.out.println("1 - Move");
            System.out.println("2 - Catch Winks");
            System.out.println("3 - Get ZTokens");
            int ability = scanner.nextInt();

            switch(ability){
                case 1:
                    int moveAmount = getMoveAmount(card.getMoves());
                    if(board.isCrossing(moveAmount)){
                        resolveFenceCrossing(player);
                    }
                    board.advance(getMoveAmount(card.getMoves()));
                case 2:
                    player.setWinks(player.getWinks() + card.getWinks());
                case 3:
                    //add ztokens and infinite ztokens, no dreamtile functionality yet
            }
        }
        else{ //"AND" card or single-ability card
            int moveAmount = getMoveAmount(card.getMoves());

            if(board.isCrossing(moveAmount)){
                resolveFenceCrossing(player);
            }
            board.advance(moveAmount);
            
            player.setWinks(player.getWinks() + card.getWinks());
            //player.setZtokens(player.getZtokens() + card.getZtokens()); need to add proper ztoken functionality still
        }
    }

    public void playNightmareCard(Card card, Nightmare nightmare){
        //
    }

    /**
     * Returns the amount of moves from a given int[] taken from a card, depending on user input.
     * 
     * @param moves Array of moves from a card.
     * @return Returns the appropriate amount of moves depending on user input.
     */
    private int multiMoveOptions(int[] moves){
        System.out.println("How many steps would you like to move? Input the corresponding number to the amount of steps.");
        for(int i = 0; i < moves.length; i++){
            System.out.println(i + " - " + moves[i] + "steps");
        }
        Scanner scanner = new Scanner(System.in);
        int selectedMoves = scanner.nextInt();
        return selectedMoves;
    }

    /**
     * Deals with and resolves a player crossing the fence by adding 5 winks to the player, and allows the player to wake up if desired.
     * 
     * @param player Player crossing fence
     */
    public void resolveFenceCrossing(Player player){
        player.setWinks(player.getWinks() + 5);
        System.out.println("You've crossed the fence. Would you like to call it a night, or keep playing?");
        System.out.println("0: Keep playing -- 1: Call it a night");
        int wakingUp = new Scanner(System.in).nextInt();
        if(wakingUp == 1){
            player.setAwake(true);
        }
    }

    public int getMoveAmount(int[] in){
        if(in.length > 1){
            return multiMoveOptions(in);
        }
        else{
            return in[0];
        }
    }
}