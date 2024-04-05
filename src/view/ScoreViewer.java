package view;

/**
 * Viewer class that informs either the final result at the end of the game, or there is no winner yet, thus proceed to resting phase
 * @author Dylan Kim
 * @version 1.0
 */
public class ScoreViewer {

    /**
     * Method that show the result when there is a winner
     * @param result the result: who are the winners / solo Result
     * @param isSolo true if the game is soloPlayer, false otherwise
     */
    public void showWinner(String result, boolean isSolo){
        System.out.println("The Game is Over!");
        if (isSolo){
            System.out.println("Your result is " + result);
        }
        else{
            System.out.println("Winner(s): "+result);
        }
        System.out.println("Congratulations!");
    }

    /**
     * Method that tells the user there is no winner, thus move on to the RestingPhase
     */
    public void showContinue(){
        System.out.println("Hmm.. It seems that there is winner..  Then, let's move on to the Resting Phase!");
    }
}
