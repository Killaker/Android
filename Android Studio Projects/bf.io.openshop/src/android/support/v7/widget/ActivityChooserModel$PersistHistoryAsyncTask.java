package android.support.v7.widget;

import android.os.*;

private final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void>
{
    public Void doInBackground(final Object... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: iconst_0       
        //     2: aaload         
        //     3: checkcast       Ljava/util/List;
        //     6: astore_2       
        //     7: aload_1        
        //     8: iconst_1       
        //     9: aaload         
        //    10: checkcast       Ljava/lang/String;
        //    13: astore_3       
        //    14: aload_0        
        //    15: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //    18: invokestatic    android/support/v7/widget/ActivityChooserModel.access$200:(Landroid/support/v7/widget/ActivityChooserModel;)Landroid/content/Context;
        //    21: aload_3        
        //    22: iconst_0       
        //    23: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    26: astore          6
        //    28: invokestatic    android/util/Xml.newSerializer:()Lorg/xmlpull/v1/XmlSerializer;
        //    31: astore          7
        //    33: aload           7
        //    35: aload           6
        //    37: aconst_null    
        //    38: invokeinterface org/xmlpull/v1/XmlSerializer.setOutput:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //    43: aload           7
        //    45: ldc             "UTF-8"
        //    47: iconst_1       
        //    48: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    51: invokeinterface org/xmlpull/v1/XmlSerializer.startDocument:(Ljava/lang/String;Ljava/lang/Boolean;)V
        //    56: aload           7
        //    58: aconst_null    
        //    59: ldc             "historical-records"
        //    61: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //    66: pop            
        //    67: aload_2        
        //    68: invokeinterface java/util/List.size:()I
        //    73: istore          24
        //    75: iconst_0       
        //    76: istore          25
        //    78: iload           25
        //    80: iload           24
        //    82: if_icmpge       214
        //    85: aload_2        
        //    86: iconst_0       
        //    87: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //    92: checkcast       Landroid/support/v7/widget/ActivityChooserModel$HistoricalRecord;
        //    95: astore          26
        //    97: aload           7
        //    99: aconst_null    
        //   100: ldc             "historical-record"
        //   102: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   107: pop            
        //   108: aload           7
        //   110: aconst_null    
        //   111: ldc             "activity"
        //   113: aload           26
        //   115: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.activity:Landroid/content/ComponentName;
        //   118: invokevirtual   android/content/ComponentName.flattenToString:()Ljava/lang/String;
        //   121: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   126: pop            
        //   127: aload           7
        //   129: aconst_null    
        //   130: ldc             "time"
        //   132: aload           26
        //   134: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.time:J
        //   137: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   140: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   145: pop            
        //   146: aload           7
        //   148: aconst_null    
        //   149: ldc             "weight"
        //   151: aload           26
        //   153: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.weight:F
        //   156: invokestatic    java/lang/String.valueOf:(F)Ljava/lang/String;
        //   159: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   164: pop            
        //   165: aload           7
        //   167: aconst_null    
        //   168: ldc             "historical-record"
        //   170: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   175: pop            
        //   176: iinc            25, 1
        //   179: goto            78
        //   182: astore          4
        //   184: invokestatic    android/support/v7/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   187: new             Ljava/lang/StringBuilder;
        //   190: dup            
        //   191: invokespecial   java/lang/StringBuilder.<init>:()V
        //   194: ldc             "Error writing historical recrod file: "
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: aload_3        
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   206: aload           4
        //   208: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   211: pop            
        //   212: aconst_null    
        //   213: areturn        
        //   214: aload           7
        //   216: aconst_null    
        //   217: ldc             "historical-records"
        //   219: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
        //   224: pop            
        //   225: aload           7
        //   227: invokeinterface org/xmlpull/v1/XmlSerializer.endDocument:()V
        //   232: aload_0        
        //   233: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   236: iconst_1       
        //   237: invokestatic    android/support/v7/widget/ActivityChooserModel.access$502:(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
        //   240: pop            
        //   241: aload           6
        //   243: ifnull          251
        //   246: aload           6
        //   248: invokevirtual   java/io/FileOutputStream.close:()V
        //   251: aconst_null    
        //   252: areturn        
        //   253: astore          19
        //   255: invokestatic    android/support/v7/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   258: new             Ljava/lang/StringBuilder;
        //   261: dup            
        //   262: invokespecial   java/lang/StringBuilder.<init>:()V
        //   265: ldc             "Error writing historical recrod file: "
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload_0        
        //   271: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   274: invokestatic    android/support/v7/widget/ActivityChooserModel.access$400:(Landroid/support/v7/widget/ActivityChooserModel;)Ljava/lang/String;
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   283: aload           19
        //   285: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   288: pop            
        //   289: aload_0        
        //   290: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   293: iconst_1       
        //   294: invokestatic    android/support/v7/widget/ActivityChooserModel.access$502:(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
        //   297: pop            
        //   298: aload           6
        //   300: ifnull          251
        //   303: aload           6
        //   305: invokevirtual   java/io/FileOutputStream.close:()V
        //   308: goto            251
        //   311: astore          22
        //   313: goto            251
        //   316: astore          15
        //   318: invokestatic    android/support/v7/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   321: new             Ljava/lang/StringBuilder;
        //   324: dup            
        //   325: invokespecial   java/lang/StringBuilder.<init>:()V
        //   328: ldc             "Error writing historical recrod file: "
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: aload_0        
        //   334: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   337: invokestatic    android/support/v7/widget/ActivityChooserModel.access$400:(Landroid/support/v7/widget/ActivityChooserModel;)Ljava/lang/String;
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   346: aload           15
        //   348: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   351: pop            
        //   352: aload_0        
        //   353: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   356: iconst_1       
        //   357: invokestatic    android/support/v7/widget/ActivityChooserModel.access$502:(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
        //   360: pop            
        //   361: aload           6
        //   363: ifnull          251
        //   366: aload           6
        //   368: invokevirtual   java/io/FileOutputStream.close:()V
        //   371: goto            251
        //   374: astore          18
        //   376: goto            251
        //   379: astore          11
        //   381: invokestatic    android/support/v7/widget/ActivityChooserModel.access$300:()Ljava/lang/String;
        //   384: new             Ljava/lang/StringBuilder;
        //   387: dup            
        //   388: invokespecial   java/lang/StringBuilder.<init>:()V
        //   391: ldc             "Error writing historical recrod file: "
        //   393: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   396: aload_0        
        //   397: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   400: invokestatic    android/support/v7/widget/ActivityChooserModel.access$400:(Landroid/support/v7/widget/ActivityChooserModel;)Ljava/lang/String;
        //   403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   406: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   409: aload           11
        //   411: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   414: pop            
        //   415: aload_0        
        //   416: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   419: iconst_1       
        //   420: invokestatic    android/support/v7/widget/ActivityChooserModel.access$502:(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
        //   423: pop            
        //   424: aload           6
        //   426: ifnull          251
        //   429: aload           6
        //   431: invokevirtual   java/io/FileOutputStream.close:()V
        //   434: goto            251
        //   437: astore          14
        //   439: goto            251
        //   442: astore          8
        //   444: aload_0        
        //   445: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
        //   448: iconst_1       
        //   449: invokestatic    android/support/v7/widget/ActivityChooserModel.access$502:(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
        //   452: pop            
        //   453: aload           6
        //   455: ifnull          463
        //   458: aload           6
        //   460: invokevirtual   java/io/FileOutputStream.close:()V
        //   463: aload           8
        //   465: athrow         
        //   466: astore          34
        //   468: goto            251
        //   471: astore          10
        //   473: goto            463
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     28     182    214    Ljava/io/FileNotFoundException;
        //  33     75     253    316    Ljava/lang/IllegalArgumentException;
        //  33     75     316    379    Ljava/lang/IllegalStateException;
        //  33     75     379    442    Ljava/io/IOException;
        //  33     75     442    466    Any
        //  85     176    253    316    Ljava/lang/IllegalArgumentException;
        //  85     176    316    379    Ljava/lang/IllegalStateException;
        //  85     176    379    442    Ljava/io/IOException;
        //  85     176    442    466    Any
        //  214    232    253    316    Ljava/lang/IllegalArgumentException;
        //  214    232    316    379    Ljava/lang/IllegalStateException;
        //  214    232    379    442    Ljava/io/IOException;
        //  214    232    442    466    Any
        //  246    251    466    471    Ljava/io/IOException;
        //  255    289    442    466    Any
        //  303    308    311    316    Ljava/io/IOException;
        //  318    352    442    466    Any
        //  366    371    374    379    Ljava/io/IOException;
        //  381    415    442    466    Any
        //  429    434    437    442    Ljava/io/IOException;
        //  458    463    471    476    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
