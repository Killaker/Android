package com.google.gson.internal;

import java.io.*;

private static final class AppendableWriter extends Writer
{
    private final Appendable appendable;
    private final CurrentWrite currentWrite;
    
    private AppendableWriter(final Appendable appendable) {
        this.currentWrite = new CurrentWrite();
        this.appendable = appendable;
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public void write(final int n) throws IOException {
        this.appendable.append((char)n);
    }
    
    @Override
    public void write(final char[] chars, final int n, final int n2) throws IOException {
        this.currentWrite.chars = chars;
        this.appendable.append(this.currentWrite, n, n + n2);
    }
    
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
}
