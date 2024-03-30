package controller;

import model.Card;

import model.CardPlayer;
import model.RacingPhase;

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
    public int onRequestCardChoice(int[] validCardOptions) {

    }

    @Override
    public int onRequestAskAbility(int[] validCardOptions) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void onRequestDisplayCardOptions(int[] validCardOptions) {
        // TODO Auto-generated method stub
        
    }


    
}