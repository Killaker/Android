package android.support.v4.print;

import android.graphics.*;
import android.os.*;
import android.print.*;

class PrintHelperKitkat$1 extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    final /* synthetic */ OnPrintFinishCallback val$callback;
    final /* synthetic */ String val$jobName;
    
    public void onFinish() {
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
    }
    
    public void onLayout(final PrintAttributes printAttributes, final PrintAttributes mAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
        int n = 1;
        this.mAttributes = mAttributes;
        final PrintDocumentInfo build = new PrintDocumentInfo$Builder(this.val$jobName).setContentType(n).setPageCount(n).build();
        if (mAttributes.equals((Object)printAttributes)) {
            n = 0;
        }
        printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, (boolean)(n != 0));
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
        //     5: getfield        android/support/v4/print/PrintHelperKitkat$1.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //     8: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
        //    11: aload_0        
        //    12: getfield        android/support/v4/print/PrintHelperKitkat$1.mAttributes:Landroid/print/PrintAttributes;
        //    15: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //    18: astore          5
        //    20: aload_0        
        //    21: getfield        android/support/v4/print/PrintHelperKitkat$1.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    24: aload_0        
        //    25: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
        //    28: aload_0        
        //    29: getfield        android/support/v4/print/PrintHelperKitkat$1.mAttributes:Landroid/print/PrintAttributes;
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
        //    66: getfield        android/support/v4/print/PrintHelperKitkat$1.this$0:Landroid/support/v4/print/PrintHelperKitkat;
        //    69: aload           6
        //    71: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //    74: aload           6
        //    76: invokevirtual   android/graphics/Bitmap.getHeight:()I
        //    79: aload           10
        //    81: aload_0        
        //    82: getfield        android/support/v4/print/PrintHelperKitkat$1.val$fittingMode:I
        //    85: invokestatic    android/support/v4/print/PrintHelperKitkat.access$100:(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //    88: astore          11
        //    90: aload           9
        //    92: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
        //    95: aload           6
        //    97: aload           11
        //    99: aconst_null    
        //   100: invokevirtual   android/graphics/Canvas.drawBitmap:(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   103: aload           5
        //   105: aload           9
        //   107: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   110: aload           5
        //   112: new             Ljava/io/FileOutputStream;
        //   115: dup            
        //   116: aload_2        
        //   117: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //   120: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/FileDescriptor;)V
        //   123: invokevirtual   android/print/pdf/PrintedPdfDocument.writeTo:(Ljava/io/OutputStream;)V
        //   126: iconst_1       
        //   127: anewarray       Landroid/print/PageRange;
        //   130: astore          15
        //   132: aload           15
        //   134: iconst_0       
        //   135: getstatic       android/print/PageRange.ALL_PAGES:Landroid/print/PageRange;
        //   138: aastore        
        //   139: aload           4
        //   141: aload           15
        //   143: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFinished:([Landroid/print/PageRange;)V
        //   146: aload           5
        //   148: ifnull          156
        //   151: aload           5
        //   153: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   156: aload_2        
        //   157: ifnull          164
        //   160: aload_2        
        //   161: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   164: aload           6
        //   166: aload_0        
        //   167: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
        //   170: if_acmpeq       178
        //   173: aload           6
        //   175: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   178: return         
        //   179: astore          12
        //   181: ldc             "PrintHelperKitkat"
        //   183: ldc             "Error writing printed content"
        //   185: aload           12
        //   187: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   190: pop            
        //   191: aload           4
        //   193: aconst_null    
        //   194: invokevirtual   android/print/PrintDocumentAdapter$WriteResultCallback.onWriteFailed:(Ljava/lang/CharSequence;)V
        //   197: goto            146
        //   200: astore          7
        //   202: aload           5
        //   204: ifnull          212
        //   207: aload           5
        //   209: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
        //   212: aload_2        
        //   213: ifnull          220
        //   216: aload_2        
        //   217: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   220: aload           6
        //   222: aload_0        
        //   223: getfield        android/support/v4/print/PrintHelperKitkat$1.val$bitmap:Landroid/graphics/Bitmap;
        //   226: if_acmpeq       234
        //   229: aload           6
        //   231: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   234: aload           7
        //   236: athrow         
        //   237: astore          14
        //   239: goto            164
        //   242: astore          8
        //   244: goto            220
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  40     110    200    237    Any
        //  110    146    179    200    Ljava/io/IOException;
        //  110    146    200    237    Any
        //  160    164    237    242    Ljava/io/IOException;
        //  181    197    200    237    Any
        //  216    220    242    247    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}