 package controller;

import model.DreamTileBoard;

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
//DreamTileBoard
//PhaseShiftController -> RacingPhaseController getDreamTileBoard()

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
