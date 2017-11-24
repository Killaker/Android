package android.support.v4.view.accessibility;

public static class CollectionInfoCompat
{
    public static final int SELECTION_MODE_MULTIPLE = 2;
    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_SINGLE = 1;
    final Object mInfo;
    
    private CollectionInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    public static CollectionInfoCompat obtain(final int n, final int n2, final boolean b, final int n3) {
        return new CollectionInfoCompat(AccessibilityNodeInfoCompat.access$000().obtainCollectionInfo(n, n2, b, n3));
    }
    
    public int getColumnCount() {
        return AccessibilityNodeInfoCompat.access$000().getCollectionInfoColumnCount(this.mInfo);
    }
    
    public int getRowCount() {
        return AccessibilityNodeInfoCompat.access$000().getCollectionInfoRowCount(this.mInfo);
    }
    
    public boolean isHierarchical() {
        return AccessibilityNodeInfoCompat.access$000().isCollectionInfoHierarchical(this.mInfo);
    }
}
