package android.support.v7.util;

public interface MainThreadCallback<T>
{
    void addTile(final int p0, final TileList.Tile<T> p1);
    
    void removeTile(final int p0, final int p1);
    
    void updateItemCount(final int p0, final int p1);
}
