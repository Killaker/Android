package com.facebook.share.widget;

public enum HorizontalAlignment
{
    CENTER("center", 0);
    
    static HorizontalAlignment DEFAULT;
    
    LEFT("left", 1), 
    RIGHT("right", 2);
    
    private int intValue;
    private String stringValue;
    
    static {
        HorizontalAlignment.DEFAULT = HorizontalAlignment.CENTER;
    }
    
    private HorizontalAlignment(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    static HorizontalAlignment fromInt(final int n) {
        for (final HorizontalAlignment horizontalAlignment : values()) {
            if (horizontalAlignment.getValue() == n) {
                return horizontalAlignment;
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
