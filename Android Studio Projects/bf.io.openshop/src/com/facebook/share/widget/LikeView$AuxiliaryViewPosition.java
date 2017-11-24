package com.facebook.share.widget;

public enum AuxiliaryViewPosition
{
    BOTTOM("bottom", 0);
    
    static AuxiliaryViewPosition DEFAULT;
    
    INLINE("inline", 1), 
    TOP("top", 2);
    
    private int intValue;
    private String stringValue;
    
    static {
        AuxiliaryViewPosition.DEFAULT = AuxiliaryViewPosition.BOTTOM;
    }
    
    private AuxiliaryViewPosition(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    static AuxiliaryViewPosition fromInt(final int n) {
        for (final AuxiliaryViewPosition auxiliaryViewPosition : values()) {
            if (auxiliaryViewPosition.getValue() == n) {
                return auxiliaryViewPosition;
            }
        }
        return null;
    }
    
    private int getValue() {
        return this.intValue;
    }
    
    @Override
    public String toString() {
        return this.stringValue;
    }
}
