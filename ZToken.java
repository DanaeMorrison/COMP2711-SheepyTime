public class ZToken {
    private boolean isInfinite;
    private Player owner;

    public ZToken(boolean isInf, Player owner){
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
