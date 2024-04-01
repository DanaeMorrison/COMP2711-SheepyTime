package controller;

public interface ModelListenerRacing {
    public void onRequestPrintHandCard(String playerName, int cardInHand);
    public void onRequestPrintCard(Card card, int nightmare);
    public int onRequestCardChoice(int[] validCardOptions);
}