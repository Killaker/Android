package com.facebook.share.widget;

public enum ObjectType
{
    public static ObjectType DEFAULT;
    
    OPEN_GRAPH("open_graph", 1), 
    PAGE("page", 2), 
    UNKNOWN("unknown", 0);
    
    private int intValue;
    private String stringValue;
    
    static {
        ObjectType.DEFAULT = ObjectType.UNKNOWN;
    }
    
    private ObjectType(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    public static ObjectType fromInt(final int n) {
        for (final ObjectType objectType : values()) {
            if (objectType.getValue() == n) {
                return objectType;
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
