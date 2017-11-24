package android.support.v4.print;

import android.graphics.*;
import android.net.*;

private static final class PrintHelperStubImpl implements PrintHelperVersionImpl
{
    int mColorMode;
    int mOrientation;
    int mScaleMode;
    
    private PrintHelperStubImpl() {
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mOrientation = 1;
    }
    
    @Override
    public int getColorMode() {
        return this.mColorMode;
    }
    
    @Override
    public int getOrientation() {
        return this.mOrientation;
    }
    
    @Override
    public int getScaleMode() {
        return this.mScaleMode;
    }
    
    @Override
    public void printBitmap(final String s, final Bitmap bitmap, final OnPrintFinishCallback onPrintFinishCallback) {
    }
    
    @Override
    public void printBitmap(final String s, final Uri uri, final OnPrintFinishCallback onPrintFinishCallback) {
    }
    
    @Override
    public void setColorMode(final int mColorMode) {
        this.mColorMode = mColorMode;
    }
    
    @Override
    public void setOrientation(final int mOrientation) {
        this.mOrientation = mOrientation;
    }
    
    @Override
    public void setScaleMode(final int mScaleMode) {
        this.mScaleMode = mScaleMode;
    }
}
