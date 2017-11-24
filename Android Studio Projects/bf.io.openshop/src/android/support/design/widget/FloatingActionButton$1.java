package android.support.design.widget;

class FloatingActionButton$1 implements InternalVisibilityChangedListener {
    final /* synthetic */ OnVisibilityChangedListener val$listener;
    
    @Override
    public void onHidden() {
        this.val$listener.onHidden(FloatingActionButton.this);
    }
    
    @Override
    public void onShown() {
        this.val$listener.onShown(FloatingActionButton.this);
    }
}