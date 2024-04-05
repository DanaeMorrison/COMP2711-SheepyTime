import controller.RacingPhaseController;
import controller.RestingPhaseController;
import controller.ScoreController;

public class PhaseShifter {

    RacingPhaseController racingPhaseCon;
    ScoreController scoreCon;
    RestingPhaseController restingPhaseCon;

    public PhaseShifter(RacingPhaseController racingPhaseCon, ScoreController ScoreCon,
            RestingPhaseController RestingPhaseCon) {
        this.racingPhaseCon = racingPhaseCon;
        this.scoreCon = ScoreCon;
        this.restingPhaseCon = RestingPhaseCon;
    }

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
