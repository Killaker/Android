package android.support.v7.util;

public interface BackgroundCallback<T>
{
    void loadTile(final int p0, final int p1);
    
    void recycleTile(final TileList.Tile<T> p0);
    
    void refresh(final int p0);
    
    void updateRange(final int p0, final int p1, final int p2, final int p3, final int p4);
}
