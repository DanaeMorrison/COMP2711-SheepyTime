package model.scorelogic;

import model.Player;
import model.Score;

/**
 * Game Logic for solo
 * 
 * @author Dylan Kim
 * @version 1.0
 */
public class SoloLogic implements ScoreLogic{

    private final int WAKE_UP = 0;

    private final int SCOING_RATIO = 5;

    private Player player;
    private Score scoreBoard;
    private int numRound=0;

    public SoloLogic(Player player) {
        this.player = player;
        scoreBoard = player.getScoreboard();
    }

    public void updateScore() {
        int scoreAmount;
        if(player.isScared()==2){
            scoreAmount = WAKE_UP;
        }else{
            scoreAmount = scoreBoard.getWinkPos() / SCOING_RATIO;
        }
        getPillowScore(scoreAmount);
    }

    private void getPillowScore(int score){
        scoreBoard.setPillowPos(scoreBoard.getPillowPos() -score);
    }

    public String getWinner(){
        if(scoreBoard.getDistance() <=0){
            int finalScore = scoreBoard.getWinkPos();
            finalScore += 2*(scoreBoard.getPillowPos() / 5);
            finalScore -= 5*numRound;
            return getResult(finalScore);
        }
        return null;
    }

    public void setNumRound(int numRound){
        this.numRound = numRound;
    }

    private String getResult(int finalScore){
        String result="";
        if(finalScore <10){
            result = "9 or less points: Restless Sleeper...";
        }
        else if (finalScore <20){
            result = "10-19 points: Solid Snooze";
        }
        else if (finalScore <30){
            result = "20-29 points: Amazing Dreaming!";
        }
        else{
            result = "30 or more points: The Dreamiest Sheep!!!";
        }
        return result;
    }
}
