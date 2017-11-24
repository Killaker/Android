package com.facebook.login.widget;

public enum ToolTipMode
{
    AUTOMATIC("automatic", 0);
    
    public static ToolTipMode DEFAULT;
    
    DISPLAY_ALWAYS("display_always", 1), 
    NEVER_DISPLAY("never_display", 2);
    
    private int intValue;
    private String stringValue;
    
    static {
        ToolTipMode.DEFAULT = ToolTipMode.AUTOMATIC;
    }
    
    private ToolTipMode(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    public static ToolTipMode fromInt(final int n) {
        for (final ToolTipMode toolTipMode : values()) {
            if (toolTipMode.getValue() == n) {
                return toolTipMode;
            }
        }
        return null;
    }
    
    public int getValue() {
        return this.intValue;
    }
    
    @Override
    public String toString() {
        return this.stringValue;
    }
}
