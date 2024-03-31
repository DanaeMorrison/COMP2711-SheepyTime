package controller;

public interface ModelListenerCardPlayer {
    public void onRequestDisplayCardOptions (int[] validCardOptions);
    public int onRequestAskAbility(int[] validCardOptions);
}