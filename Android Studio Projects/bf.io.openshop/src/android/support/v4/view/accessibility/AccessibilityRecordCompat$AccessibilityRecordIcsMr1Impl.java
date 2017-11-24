package android.support.v4.view.accessibility;

static class AccessibilityRecordIcsMr1Impl extends AccessibilityRecordIcsImpl
{
    @Override
    public int getMaxScrollX(final Object o) {
        return AccessibilityRecordCompatIcsMr1.getMaxScrollX(o);
    }
    
    @Override
    public int getMaxScrollY(final Object o) {
        return AccessibilityRecordCompatIcsMr1.getMaxScrollY(o);
    }
    
    @Override
    public void setMaxScrollX(final Object o, final int n) {
        AccessibilityRecordCompatIcsMr1.setMaxScrollX(o, n);
    }
    
    @Override
    public void setMaxScrollY(final Object o, final int n) {
        AccessibilityRecordCompatIcsMr1.setMaxScrollY(o, n);
    }
}
