package com.android.volley;

private static class Marker
{
    public final String name;
    public final long thread;
    public final long time;
    
    public Marker(final String name, final long thread, final long time) {
        this.name = name;
        this.thread = thread;
        this.time = time;
    }
}
