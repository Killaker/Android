package com.facebook;

private static class Attachment
{
    private final GraphRequest request;
    private final Object value;
    
    public Attachment(final GraphRequest request, final Object value) {
        this.request = request;
        this.value = value;
    }
    
    public GraphRequest getRequest() {
        return this.request;
    }
    
    public Object getValue() {
        return this.value;
    }
}
