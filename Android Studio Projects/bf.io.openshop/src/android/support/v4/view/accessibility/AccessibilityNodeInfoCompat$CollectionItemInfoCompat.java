package android.support.v4.view.accessibility;

public static class CollectionItemInfoCompat
{
    private final Object mInfo;
    
    private CollectionItemInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    public static CollectionItemInfoCompat obtain(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.access$000().obtainCollectionItemInfo(n, n2, n3, n4, b, b2));
    }
    
    public int getColumnIndex() {
        return AccessibilityNodeInfoCompat.access$000().getCollectionItemColumnIndex(this.mInfo);
    }
    
    public int getColumnSpan() {
        return AccessibilityNodeInfoCompat.access$000().getCollectionItemColumnSpan(this.mInfo);
    }
    
    public int getRowIndex() {
        return AccessibilityNodeInfoCompat.access$000().getCollectionItemRowIndex(this.mInfo);
    }
    
    public int getRowSpan() {
        return AccessibilityNodeInfoCompat.access$000().getCollectionItemRowSpan(this.mInfo);
    }
    
    public boolean isHeading() {
        return AccessibilityNodeInfoCompat.access$000().isCollectionItemHeading(this.mInfo);
    }
    
    public boolean isSelected() {
        return AccessibilityNodeInfoCompat.access$000().isCollectionItemSelected(this.mInfo);
    }
}
