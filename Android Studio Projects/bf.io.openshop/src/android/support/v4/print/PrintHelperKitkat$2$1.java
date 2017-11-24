package android.support.v4.print;

import android.net.*;
import android.graphics.*;
import java.io.*;
import android.print.*;
import android.os.*;

class PrintHelperKitkat$2$1 extends AsyncTask<Uri, Boolean, Bitmap> {
    final /* synthetic */ CancellationSignal val$cancellationSignal;
    final /* synthetic */ PrintDocumentAdapter$LayoutResultCallback val$layoutResultCallback;
    final /* synthetic */ PrintAttributes val$newPrintAttributes;
    final /* synthetic */ PrintAttributes val$oldPrintAttributes;
    
    protected Bitmap doInBackground(final Uri... array) {
        try {
            return PrintHelperKitkat.access$300(PrintDocumentAdapter.this.this$0, PrintDocumentAdapter.this.val$imageFile, 3500);
        }
        catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    protected void onCancelled(final Bitmap bitmap) {
        this.val$layoutResultCallback.onLayoutCancelled();
        PrintDocumentAdapter.this.mLoadBitmap = null;
    }
    
    protected void onPostExecute(final Bitmap mBitmap) {
        int n = 1;
        super.onPostExecute((Object)mBitmap);
        PrintDocumentAdapter.this.mBitmap = mBitmap;
        if (mBitmap != null) {
            final PrintDocumentInfo build = new PrintDocumentInfo$Builder(PrintDocumentAdapter.this.val$jobName).setContentType(n).setPageCount(n).build();
            if (this.val$newPrintAttributes.equals((Object)this.val$oldPrintAttributes)) {
                n = 0;
            }
            this.val$layoutResultCallback.onLayoutFinished(build, (boolean)(n != 0));
        }
        else {
            this.val$layoutResultCallback.onLayoutFailed((CharSequence)null);
        }
        PrintDocumentAdapter.this.mLoadBitmap = null;
    }
    
    protected void onPreExecute() {
        this.val$cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener)new CancellationSignal$OnCancelListener() {
            public void onCancel() {
                PrintHelperKitkat$2.access$200(PrintDocumentAdapter.this);
                AsyncTask.this.cancel(false);
            }
        });
    }
}