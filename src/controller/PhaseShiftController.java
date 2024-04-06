 package controller;

import model.DreamTileBoard;

/**
 * Controller that manages the sequence in RacingPhase, update the score, and then RestingPhase
 * @author Dylan Kim
 * @version 1.0
 */
public class PhaseShiftController {

    private RacingPhaseController racingPhaseCon;
    private ScoreController scoreCon;
    private RestingPhaseController restingPhaseCon;
    private DreamTileBoard tileBoard;

    public PhaseShiftController(RacingPhaseController racingPhaseCon, ScoreController ScoreCon,
            RestingPhaseController RestingPhaseCon, DreamTileBoard tileBoard) {
        this.racingPhaseCon = racingPhaseCon;
        this.scoreCon = ScoreCon;
        this.restingPhaseCon = RestingPhaseCon;
        this.tileBoard = tileBoard;
    }


    /**
     * Method that let the game will loop until the winner is determined
     */
    public void loopRound(){
        boolean noWinner = true;
        do{
            tileBoard = racingPhaseCon.startPhase(tileBoard);
            if(scoreCon.checkWinner()){
                noWinner = false;
            }
            else{
                tileBoard = restingPhaseCon.startPhase(tileBoard);
            }
        }while(noWinner);
    }
}
