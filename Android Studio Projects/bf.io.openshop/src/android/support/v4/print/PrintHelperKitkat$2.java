package android.support.v4.print;

import android.graphics.*;
import android.net.*;
import java.io.*;
import android.os.*;
import android.print.*;

class PrintHelperKitkat$2 extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    Bitmap mBitmap = null;
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
    final /* synthetic */ OnPrintFinishCallback val$callback;
    final /* synthetic */ Uri val$imageFile;
    final /* synthetic */ String val$jobName;
    
    private void cancelLoad() {
        synchronized (PrintHelperKitkat.access$400(PrintHelperKitkat.this)) {
            if (PrintHelperKitkat.this.mDecodeOptions != null) {
                PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
                PrintHelperKitkat.this.mDecodeOptions = null;
            }
        }
    }
    
    public void onFinish() {
        super.onFinish();
        this.cancelLoad();
        if (this.mLoadBitmap != null) {
            this.mLoadBitmap.cancel(true);
        }
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
    }
    
    public void onLayout(final PrintAttributes printAttributes, final PrintAttributes mAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
        int n = 1;
        this.mAttributes = mAttributes;
        if (cancellationSignal.isCanceled()) {
            printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
            return;
        }
        if (this.mBitmap != null) {
            final PrintDocumentInfo build = new PrintDocumentInfo$Builder(this.val$jobName).setContentType(n).setPageCount(n).build();
            if (mAttributes.equals((Object)printAttributes)) {
                n = 0;
            }
            printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, (boolean)(n != 0));
            return;
        }
        this.mLoadBitmap = (AsyncTask<Uri, Boolean, Bitmap>)new AsyncTask<Uri, Boolean, Bitmap>() {
            protected Bitmap doInBackground(final Uri... array) {
                try {
                    return PrintHelperKitkat.access$300(PrintHelperKitkat.this, PrintDocumentAdapter.this.val$imageFile, 3500);
                }
                catch (FileNotFoundException ex) {
                    return null;
                }
            }
            
            protected void onCancelled(final Bitmap bitmap) {
                printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
                PrintDocumentAdapter.this.mLoadBitmap = null;
            }
            
            protected void onPostExecute(final Bitmap mBitmap) {
                int n = 1;
                super.onPostExecute((Object)mBitmap);
                PrintDocumentAdapter.this.mBitmap = mBitmap;
                if (mBitmap != null) {
                    final PrintDocumentInfo build = new PrintDocumentInfo$Builder(PrintDocumentAdapter.this.val$jobName).setContentType(n).setPageCount(n).build();
                    if (mAttributes.equals((Object)printAttributes)) {
                        n = 0;
                    }
                    printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, (boolean)(n != 0));
                }
                else {
                    printDocumentAdapter$LayoutResultCallback.onLayoutFailed((CharSequence)null);
                }
                PrintDocumentAdapter.this.mLoadBitmap = null;
            }
            
            protected void onPreExecute() {
                cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener)new CancellationSignal$OnCancelListener() {
                    public void onCancel() {
                        PrintDocumentAdapter.this.cancelLoad();
                        AsyncTask.this.cancel(false);
                    }
                });
            }
        }.execute((Object[])new Uri[0]);
    }
    
    public void onWrite(final PageRange[] p0, final ParcelFileDescriptor p1, final CancellationSignal p2, final PrintDocumentAdapter$WriteResultCallback p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Landroid/print/pdf/PrintedPdfDocument;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //     8: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
        //    11: aload_0        
        //    12: getfield        android/support/v4/print/PrintHelperKitkat$2.mAttributes:Landroid/print/PrintAttributes;
        //    15: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //    18: astore          5
        //    20: aload_0        
        //    21: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    24: aload_0        
        //    25: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //    28: aload_0        
        //    29: getfield        android/support/v4/print/PrintHelperKitkat$2.mAttributes:Landroid/print/PrintAttributes;
        //    32: invokevirtual   android/print/PrintAttributes.getColorMode:()I
        //    35: invokestatic    android/support/v4/print/PrintHelperKitkat.access$000:(Landroid/support/v4/print/PrintHelperKitkat;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
        //    38: astore          6
        //    40: aload           5
        //    42: iconst_1       
        //    43: invokevirtual   android/print/pdf/PrintedPdfDocument.startPage:(I)Landroid/graphics/pdf/PdfDocument$Page;
        //    46: astore          9
        //    48: new             Landroid/graphics/RectF;
        //    51: dup            
        //    52: aload           9
        //    54: invokevirtual   android/graphics/pdf/PdfDocument$Page.getInfo:()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //    57: invokevirtual   android/graphics/pdf/PdfDocument$PageInfo.getContentRect:()Landroid/graphics/Rect;
        //    60: invokespecial   android/graphics/RectF.<init>:(Landroid/graphics/Rect;)V
        //    63: astore          10
        //    65: aload_0        
        //    66: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    69: aload_0        
        //    70: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //    73: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //    76: aload_0        
        //    77: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //    80: invokevirtual   android/graphics/Bitmap.getHeight:()I
        //    83: aload           10
        //    85: aload_0        
        //    86: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fittingMode:I
        //    89: invokestatic    android/support/v4/print/PrintHelperKitkat.access$100:(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //    92: astore          11
        //    94: aload           9
        //    96: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
        //    99: aload           6
        //   101: aload           11
        //   103: aconst_null    
        //   104: invokevirtual   android/graphics/Canvas.drawBitmap:(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   107: aload           5
        //   109: aload           9
        //   111: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   114: aload           5
        //   116: new             Ljava/io/FileOutputStream;
        //   119: dup            
        //   120: aload_2        
        //   121: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //   124: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/FileDescriptor;)V
        //   127: invokevirtual   android/print/pdf/PrintedPdfDocument.writeTo:(Ljava/io/OutputStream;)V
        //   130: iconst_1       
        //   131: anewarray       Landroid/print/PageRange;
        //   134: astore          15
        //   136: aload           15
        //   138: iconst_0       
        //   139: getstatic       android/print/PageRange.ALL_PAGES:Landroid/print/PageRange;
        //   142: aastore        
        //   143: aload           4
        //   145: aload           15
        //   147: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFinished:([Landroid/print/PageRange;)V
        //   150: aload           5
        //   152: ifnull          160
        //   155: aload           5
        //   157: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   160: aload_2        
        //   161: ifnull          168
        //   164: aload_2        
        //   165: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   168: aload           6
        //   170: aload_0        
        //   171: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //   174: if_acmpeq       182
        //   177: aload           6
        //   179: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   182: return         
        //   183: astore          12
        //   185: ldc             "PrintHelperKitkat"
        //   187: ldc             "Error writing printed content"
        //   189: aload           12
        //   191: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   194: pop            
        //   195: aload           4
        //   197: aconst_null    
        //   198: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFailed:(Ljava/lang/CharSequence;)V
        //   201: goto            150
        //   204: astore          7
        //   206: aload           5
        //   208: ifnull          216
        //   211: aload           5
        //   213: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   216: aload_2        
        //   217: ifnull          224
        //   220: aload_2        
        //   221: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   224: aload           6
        //   226: aload_0        
        //   227: getfield        android/support/v4/print/PrintHelperKitkat$2.mBitmap:Landroid/graphics/Bitmap;
        //   230: if_acmpeq       238
        //   233: aload           6
        //   235: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   238: aload           7
        //   240: athrow         
        //   241: astore          14
        //   243: goto            168
        //   246: astore          8
        //   248: goto            224
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  40     114    204    241    Any
        //  114    150    183    204    Ljava/io/IOException;
        //  114    150    204    241    Any
        //  164    168    241    246    Ljava/io/IOException;
        //  185    201    204    241    Any
        //  220    224    246    251    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}