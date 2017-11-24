package com.google.android.gms.common.api;

public interface ApiOptions
{
    public interface HasOptions extends ApiOptions
    {
    }
    
    public static final class NoOptions implements NotRequiredOptions
    {
    }
    
    public interface NotRequiredOptions extends ApiOptions
    {
    }
    
    public interface Optional extends HasOptions, NotRequiredOptions
    {
    }
}
