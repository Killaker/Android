package android.support.v7.widget;

public interface ActionBarVisibilityCallback
{
    void enableContentAnimations(final boolean p0);
    
    void hideForSystem();
    
    void onContentScrollStarted();
    
    void onContentScrollStopped();
    
    void onWindowVisibilityChanged(final int p0);
    
    void showForSystem();
}
