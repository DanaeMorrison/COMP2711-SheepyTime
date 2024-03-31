package controller;

public interface ModelListenerCardPlayer {
    public void onRequestDisplayAbilityOptions (int[] validCardOptions);
    public int onRequestAskAbility(int[] validCardOptions);
    public int onRequestSpecificMove(int[] moves);
}