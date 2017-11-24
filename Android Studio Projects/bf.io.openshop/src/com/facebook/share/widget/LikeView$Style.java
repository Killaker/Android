package com.facebook.share.widget;

public enum Style
{
    BOX_COUNT("box_count", 2), 
    BUTTON("button", 1);
    
    static Style DEFAULT;
    
    STANDARD("standard", 0);
    
    private int intValue;
    private String stringValue;
    
    static {
        Style.DEFAULT = Style.STANDARD;
    }
    
    private Style(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    static Style fromInt(final int n) {
        for (final Style style : values()) {
            if (style.getValue() == n) {
                return style;
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
