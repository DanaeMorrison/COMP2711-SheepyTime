package model;
public interface BoardInterface {
    public boolean occupied(int n);
    public void emptyBoard();
    public int getIndex();
    public void advance(int steps);
    public void jump(int position);
    public boolean isCrossing(int steps);
}
