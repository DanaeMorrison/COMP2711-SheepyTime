package controller;

import java.util.ArrayList;

import model.scorelogic.DuoLogicFactory;
import model.scorelogic.MultiPlayerLogicFactory;
import model.scorelogic.ScoreLogic;
import model.scorelogic.SoloLogic;
import model.scorelogic.SquadLogicFactory;
import model.scorelogic.TrioLogicFactory;
import view.ScoreViewer;
import model.Player;

/**
 * Controller class that manages the scoring process after each racing phase
 * @author Dylan Kim
 * @version 1.0
 */
public class ScoreController {

    private ScoreLogic scoreLogic;
    private boolean isSolo = false;
    private ScoreViewer scoreViewer;

    public ScoreController(ArrayList<Player> players) {

        scoreViewer = new ScoreViewer();

        if (players.size() == 1) {
            scoreLogic = new SoloLogic(players.get(0));
            isSolo = true;
        } else if (players.size() == 2) {
            MultiPlayerLogicFactory factory = new DuoLogicFactory();
            scoreLogic = factory.getScoreLogic(players);
        } else if (players.size() == 3) {
            MultiPlayerLogicFactory factory = new TrioLogicFactory();
            scoreLogic = factory.getScoreLogic(players);
        } else if (players.size() == 4) {
            MultiPlayerLogicFactory factory = new SquadLogicFactory();
            scoreLogic = factory.getScoreLogic(players);
        }
    }

    /**
     * Method that updates the score of each player, and then returns whether there is any winner or not
     * @return true if there is a winner, false otherwise
     */
    public boolean checkWinner() {
        scoreLogic.updateScore();
        if(scoreLogic.getWinner().equals("")){
            scoreViewer.showContinue();
            return false;
        }
        scoreViewer.showWinner(scoreLogic.getWinner(), isSolo);
        return true;
    }
}
