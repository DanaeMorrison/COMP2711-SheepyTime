package model;
public class ZToken {
    private boolean isInfinite;
    private Player owner;

    public ZToken(boolean isInfinite, Player owner){
        this.isInfinite = isInfinite;
        this.owner = owner;
    }

    public Player getOwner(){
        return owner;
    }

    public boolean isInfinite(){
        return isInfinite;
    }
}
