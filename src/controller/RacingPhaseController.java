package controller;

import model.Card;

import model.CardPlayer;
import model.RacingPhase;
import view.PlayerCardDecision;

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
        PlayerCardDecision playerCardDecision = new PlayerCardDecision();
        int cardChoice = playerCardDecision.getCardChoice();
        return cardChoice;

    }

    @Override
    public void onRequestDisplayAbilityOptions(int secondAbility) {
        // TODO Auto-generated method stub
        
        PlayerCardDecision playerCardDecision = new PlayerCardDecision();
        playerCardDecision.displayAbilityOptions(secondAbility);
        
    }

    @Override
    public int onRequestAskAbility(int secondAbility) {
        // TODO Auto-generated method stub
        PlayerCardDecision playerCardDecision = new PlayerCardDecision();
        return playerCardDecision.getAbilityChoice(secondAbility);
    }
    @Override
    public int onRequestSpecificMove(int[] moves) {
        // TODO Auto-generated method stub
        PlayerCardDecision playerCardDecision = new PlayerCardDecision();
        return playerCardDecision.getSpecificMove(moves);
    }
    @Override
    public int onRequestResolveFenceCrossing(Player player) {
        // TODO Auto-generated method stub
        return 0;
    }

    
   


    
}