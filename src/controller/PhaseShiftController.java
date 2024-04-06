 package controller;

public class PhaseShiftController {

    RacingPhaseController racingPhaseCon;
    ScoreController scoreCon;
    RestingPhaseController restingPhaseCon;

    public PhaseShiftController(RacingPhaseController racingPhaseCon, ScoreController ScoreCon,
            RestingPhaseController RestingPhaseCon) {
        this.racingPhaseCon = racingPhaseCon;
        this.scoreCon = ScoreCon;
        this.restingPhaseCon = RestingPhaseCon;
    }
//DreamTileBoard
//PhaseShiftController -> RacingPhaseController getDreamTileBoard()

    public void loopRound(){
        boolean noWinner = true;
        do{
            racingPhaseCon.startPhase();
            if(scoreCon.checkWinner()){
                noWinner = false;
            }
            else{
                restingPhaseCon.startPhase();
            }
        }while(noWinner);
    }
}
