package controller;
import model.Card;

public interface ModelListenerRacingPhase {
    public void onRequestPrintHandCard(String playerName, int cardInHand);
    public void onRequestPrintCard(Card card, int nightmare);
    public int onRequestCardChoice();
    public int onRequestRepeatCardChoice();
}