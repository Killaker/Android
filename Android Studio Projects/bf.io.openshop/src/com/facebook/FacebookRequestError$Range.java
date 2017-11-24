package com.facebook;

private static class Range
{
    private final int end;
    private final int start;
    
    private Range(final int start, final int end) {
        this.start = start;
        this.end = end;
    }
    
    boolean contains(final int n) {
        return this.start <= n && n <= this.end;
    }
}
