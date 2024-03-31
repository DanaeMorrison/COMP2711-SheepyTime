package controller;

import model.Card;

import model.CardPlayer;
import model.RacingPhase;
import view.PlayerCardDecision;
import view.PlayerDecision;

public class RacingPhaseController implements ModelListenerRacingPhase, ModelListenerCardPlayer {
    private RacingPhase racingPhase;
    private CardPlayer cardPlayer;

    public RacingPhaseController(RacingPhase racingPhase, CardPlayer cardPlayer) {
        this.racingPhase = racingPhase;
        this.cardPlayer = cardPlayer;
    }
    @Override
    public void onRequestPrintCard(Card card, int nightmare) {
        CardViewer cardViewer = new CardViewer(card, nightmare);
        cardViewer.rulePrint(card.getMoves(), card.getJumpPos(), card.getSpiderMove(), card.getWinks(), card.getZtokens(), nightmare, card.isNightmare(), card.bothConditions());
    }

    @Override
    public int onRequestCardChoice() {
        PlayerDecision playerDecision = new PlayerDecision();
        int cardChoice = playerDecision.getCardChoice();
        return cardChoice;

    }

    @Override
    public void onRequestDisplayAbilityOptions(int secondAbility) {
        // TODO Auto-generated method stub
        
        PlayDecision playerDecision = new PlayerDecision();
        playerDecision.displayAbilityOptions(secondAbility);
        
    }

    @Override
    public int onRequestAskAbility(int secondAbility) {
        // TODO Auto-generated method stub
        PlayerDecision playerDecision = new PlayerDecision();
        return playerDecision.getAbilityChoice(secondAbility);
    }
    @Override
    public int onRequestSpecificMove(int[] moves) {
        // TODO Auto-generated method stub
        PlayerDecision playerDecision = new PlayerDecision();
        return playerDecision.getSpecificMove(moves);
    }
    @Override
    public int onRequestResolveFenceCrossing() {
        // TODO Auto-generated method stub
        PlayerDecision playerDecision = new PlayerDecision();
        return playerDecision.getPlayOrCallNight();
    }

    
   


    
}