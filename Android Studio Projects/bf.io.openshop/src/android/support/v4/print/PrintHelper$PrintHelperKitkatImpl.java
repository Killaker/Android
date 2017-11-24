package android.support.v4.print;

import android.content.*;
import android.graphics.*;
import android.net.*;
import java.io.*;

private static final class PrintHelperKitkatImpl implements PrintHelperVersionImpl
{
    private final PrintHelperKitkat mPrintHelper;
    
    PrintHelperKitkatImpl(final Context context) {
        this.mPrintHelper = new PrintHelperKitkat(context);
    }
    
    @Override
    public int getColorMode() {
        return this.mPrintHelper.getColorMode();
    }
    
    @Override
    public int getOrientation() {
        return this.mPrintHelper.getOrientation();
    }
    
    @Override
    public int getScaleMode() {
        return this.mPrintHelper.getScaleMode();
    }
    
    @Override
    public void printBitmap(final String s, final Bitmap bitmap, final OnPrintFinishCallback onPrintFinishCallback) {
        Object o = null;
        if (onPrintFinishCallback != null) {
            o = new PrintHelperKitkat.OnPrintFinishCallback() {
                @Override
                public void onFinish() {
                    onPrintFinishCallback.onFinish();
                }
            };
        }
        this.mPrintHelper.printBitmap(s, bitmap, (PrintHelperKitkat.OnPrintFinishCallback)o);
    }
    
    @Override
    public void printBitmap(final String s, final Uri uri, final OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        Object o = null;
        if (onPrintFinishCallback != null) {
            o = new PrintHelperKitkat.OnPrintFinishCallback() {
                @Override
                public void onFinish() {
                    onPrintFinishCallback.onFinish();
                }
            };
        }
        this.mPrintHelper.printBitmap(s, uri, (PrintHelperKitkat.OnPrintFinishCallback)o);
    }
    
    @Override
    public void setColorMode(final int colorMode) {
        this.mPrintHelper.setColorMode(colorMode);
    }
    
    @Override
    public void setOrientation(final int orientation) {
        this.mPrintHelper.setOrientation(orientation);
    }
    
    @Override
    public void setScaleMode(final int scaleMode) {
        this.mPrintHelper.setScaleMode(scaleMode);
    }
}
