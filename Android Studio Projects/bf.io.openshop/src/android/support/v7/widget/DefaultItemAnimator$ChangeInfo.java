package android.support.v7.widget;

private static class ChangeInfo
{
    public int fromX;
    public int fromY;
    public ViewHolder newHolder;
    public ViewHolder oldHolder;
    public int toX;
    public int toY;
    
    private ChangeInfo(final ViewHolder oldHolder, final ViewHolder newHolder) {
        this.oldHolder = oldHolder;
        this.newHolder = newHolder;
    }
    
    private ChangeInfo(final ViewHolder viewHolder, final ViewHolder viewHolder2, final int fromX, final int fromY, final int toX, final int toY) {
        this(viewHolder, viewHolder2);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }
    
    @Override
    public String toString() {
        return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
    }
}
