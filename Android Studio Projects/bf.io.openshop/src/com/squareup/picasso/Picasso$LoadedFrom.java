package com.squareup.picasso;

public enum LoadedFrom
{
    DISK(-16776961), 
    MEMORY(-16711936), 
    NETWORK(-65536);
    
    final int debugColor;
    
    private LoadedFrom(final int debugColor) {
        this.debugColor = debugColor;
    }
}
