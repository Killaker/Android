package android.support.v7.widget;

private static class MoveInfo
{
    public int fromX;
    public int fromY;
    public ViewHolder holder;
    public int toX;
    public int toY;
    
    private MoveInfo(final ViewHolder holder, final int fromX, final int fromY, final int toX, final int toY) {
        this.holder = holder;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }
}
