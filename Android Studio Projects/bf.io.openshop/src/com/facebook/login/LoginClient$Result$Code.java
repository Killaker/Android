package com.facebook.login;

enum Code
{
    CANCEL("cancel"), 
    ERROR("error"), 
    SUCCESS("success");
    
    private final String loggingValue;
    
    private Code(final String loggingValue) {
        this.loggingValue = loggingValue;
    }
    
    String getLoggingValue() {
        return this.loggingValue;
    }
}
