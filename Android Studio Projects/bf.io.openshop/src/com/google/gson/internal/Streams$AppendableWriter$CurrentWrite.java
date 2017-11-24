package com.google.gson.internal;

static class CurrentWrite implements CharSequence
{
    char[] chars;
    
    @Override
    public char charAt(final int n) {
        return this.chars[n];
    }
    
    @Override
    public int length() {
        return this.chars.length;
    }
    
    @Override
    public CharSequence subSequence(final int n, final int n2) {
        return new String(this.chars, n, n2 - n);
    }
}
