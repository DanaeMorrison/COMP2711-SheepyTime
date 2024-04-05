package view;

/**
 * @author Dylan Kim
 * @version 1.0
 */
public class ScoreViewer {

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

    public void showContinue(){
        System.out.println("Hmm.. It seems that there is winner..  Then, let's move on to the Resting Phase!");
    }
}
