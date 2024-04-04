package controller;

import model.scorelogic.DuoLogicFactory;
import model.scorelogic.MultiPlayerLogicFactory;
import model.scorelogic.ScoreLogic;
import model.scorelogic.SoloLogic;
import model.scorelogic.SquadLogicFactory;
import model.scorelogic.TrioLogicFactory;
import model.Player;
import java.util.ArrayList;

public class ScoreController {

    private ScoreLogic scoreLogic;

    public ScoreController(ArrayList<Player> players){
        if(players.size()==1){
            scoreLogic = new SoloLogic(players.get(0));
        }
        else if(players.size()==2){
            MultiPlayerLogicFactory factory = new DuoLogicFactory();
            scoreLogic = factory.getScoreLogic(players);
        }
        else if(players.size()==3){
            MultiPlayerLogicFactory factory = new TrioLogicFactory();
            scoreLogic = factory.getScoreLogic(players);
        }
        else if(players.size()==4){
            MultiPlayerLogicFactory factory = new SquadLogicFactory();
            scoreLogic = factory.getScoreLogic(players);
        }
    }
}
