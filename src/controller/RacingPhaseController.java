package controller;

import model.Card;

//import model.CardPlayer;
import model.RacingPhase;
import view.CardViewer;
import view.PlayerDecision;

public class RacingPhaseController implements ModelListenerRacingPhase, ModelListenerCardPlayer {
    private RacingPhase racingPhase;
    //private CardPlayer cardPlayer;
    CardViewer cardViewer = new CardViewer();
    PlayerDecision playerDecision = new PlayerDecision();

    public RacingPhaseController(RacingPhase racingPhase/*, CardPlayer cardPlayer*/) {
        this.racingPhase = racingPhase;
        //this.cardPlayer = cardPlayer;
    }

    public void startPhase() {
        racingPhase.startPhase();
    }

    @Override
    public void onRequestPrintHandCard(String playerName, int cardInHand) {
        playerDecision.printCardInHand(playerName, cardInHand);
    }

    @Override
    public void onRequestPrintCard(Card card, int nightmare) {
        cardViewer.rulePrint(card.getMoves(), card.getJumpPos(), card.getSpiderMove(), card.getWinks(), card.getZtokens(), nightmare, card.isNightmare(), card.bothConditions());
    }

    @Override
    public int onRequestCardChoice() {
        int cardChoice = playerDecision.getCardChoice();
        return cardChoice;

    }

    @Override
    public void onRequestDisplayAbilityOptions(int secondAbility) {
        // TODO Auto-generated method stub
        playerDecision.displayAbilityOptions(secondAbility);
    }

    @Override
    public int onRequestAskAbility(int secondAbility) {
        // TODO Auto-generated method stub
        return playerDecision.getAbilityChoice(secondAbility);
    }
    @Override
    public int onRequestSpecificMove(int[] moves) {
        // TODO Auto-generated method stub
        return playerDecision.getSpecificMove(moves);
    }
    @Override
    public int onRequestResolveFenceCrossing() {
        // TODO Auto-generated method stub
        return playerDecision.getPlayOrCallNight();
    }

    
   


    
}