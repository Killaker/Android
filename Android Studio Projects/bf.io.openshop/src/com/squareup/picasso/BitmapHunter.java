package com.squareup.picasso;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import android.graphics.*;
import java.util.*;
import android.net.*;
import java.io.*;

class BitmapHunter implements Runnable
{
    private static final Object DECODE_LOCK;
    private static final RequestHandler ERRORING_HANDLER;
    private static final ThreadLocal<StringBuilder> NAME_BUILDER;
    private static final AtomicInteger SEQUENCE_GENERATOR;
    Action action;
    List<Action> actions;
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifRotation;
    Future<?> future;
    final String key;
    Picasso.LoadedFrom loadedFrom;
    final int memoryPolicy;
    int networkPolicy;
    final Picasso picasso;
    Picasso.Priority priority;
    final RequestHandler requestHandler;
    Bitmap result;
    int retryCount;
    final int sequence;
    final Stats stats;
    
    static {
        DECODE_LOCK = new Object();
        NAME_BUILDER = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder("Picasso-");
            }
        };
        SEQUENCE_GENERATOR = new AtomicInteger();
        ERRORING_HANDLER = new RequestHandler() {
            @Override
            public boolean canHandleRequest(final Request request) {
                return true;
            }
            
            @Override
            public Result load(final Request request, final int n) throws IOException {
                throw new IllegalStateException("Unrecognized type of request: " + request);
            }
        };
    }
    
    BitmapHunter(final Picasso picasso, final Dispatcher dispatcher, final Cache cache, final Stats stats, final Action action, final RequestHandler requestHandler) {
        this.sequence = BitmapHunter.SEQUENCE_GENERATOR.incrementAndGet();
        this.picasso = picasso;
        this.dispatcher = dispatcher;
        this.cache = cache;
        this.stats = stats;
        this.action = action;
        this.key = action.getKey();
        this.data = action.getRequest();
        this.priority = action.getPriority();
        this.memoryPolicy = action.getMemoryPolicy();
        this.networkPolicy = action.getNetworkPolicy();
        this.requestHandler = requestHandler;
        this.retryCount = requestHandler.getRetryCount();
    }
    
    static Bitmap applyCustomTransformations(final List<Transformation> list, Bitmap bitmap) {
        int i = 0;
        while (i < list.size()) {
            final Transformation transformation = list.get(i);
            StringBuilder append = null;
            Label_0150: {
                Bitmap transform = null;
                Label_0168: {
                    try {
                        transform = transformation.transform(bitmap);
                        if (transform == null) {
                            append = new StringBuilder().append("Transformation ").append(transformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                            final Iterator<Transformation> iterator = list.iterator();
                            while (iterator.hasNext()) {
                                append.append(iterator.next().key()).append('\n');
                            }
                            break Label_0150;
                        }
                        break Label_0168;
                    }
                    catch (RuntimeException ex) {
                        Picasso.HANDLER.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                throw new RuntimeException("Transformation " + transformation.key() + " crashed with exception.", ex);
                            }
                        });
                        bitmap = null;
                    }
                    break;
                }
                if (transform == bitmap && bitmap.isRecycled()) {
                    Picasso.HANDLER.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation.key() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                }
                if (transform != bitmap && !bitmap.isRecycled()) {
                    Picasso.HANDLER.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation.key() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                }
                bitmap = transform;
                ++i;
                continue;
            }
            Picasso.HANDLER.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    throw new NullPointerException(append.toString());
                }
            });
            return null;
        }
        return bitmap;
    }
    
    private Picasso.Priority computeNewPriority() {
        Enum<Picasso.Priority> enum1 = Picasso.Priority.LOW;
        boolean b;
        if (this.actions != null && !this.actions.isEmpty()) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (this.action != null || b) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            return (Picasso.Priority)enum1;
        }
        if (this.action != null) {
            enum1 = this.action.getPriority();
        }
        if (b) {
            for (int i = 0; i < this.actions.size(); ++i) {
                final Picasso.Priority priority = this.actions.get(i).getPriority();
                if (priority.ordinal() > enum1.ordinal()) {
                    enum1 = priority;
                }
            }
        }
        return (Picasso.Priority)enum1;
    }
    
    static Bitmap decodeStream(final InputStream inputStream, final Request request) throws IOException {
        final MarkableInputStream markableInputStream = new MarkableInputStream(inputStream);
        final long savePosition = markableInputStream.savePosition(65536);
        final BitmapFactory$Options bitmapOptions = RequestHandler.createBitmapOptions(request);
        final boolean requiresInSampleSize = RequestHandler.requiresInSampleSize(bitmapOptions);
        final boolean webPFile = Utils.isWebPFile(markableInputStream);
        markableInputStream.reset(savePosition);
        Bitmap bitmap;
        if (webPFile) {
            final byte[] byteArray = Utils.toByteArray(markableInputStream);
            if (requiresInSampleSize) {
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, bitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, bitmapOptions, request);
            }
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, bitmapOptions);
        }
        else {
            if (requiresInSampleSize) {
                BitmapFactory.decodeStream((InputStream)markableInputStream, (Rect)null, bitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, bitmapOptions, request);
                markableInputStream.reset(savePosition);
            }
            bitmap = BitmapFactory.decodeStream((InputStream)markableInputStream, (Rect)null, bitmapOptions);
            if (bitmap == null) {
                throw new IOException("Failed to decode stream.");
            }
        }
        return bitmap;
    }
    
    static BitmapHunter forRequest(final Picasso picasso, final Dispatcher dispatcher, final Cache cache, final Stats stats, final Action action) {
        final Request request = action.getRequest();
        final List<RequestHandler> requestHandlers = picasso.getRequestHandlers();
        for (int i = 0; i < requestHandlers.size(); ++i) {
            final RequestHandler requestHandler = requestHandlers.get(i);
            if (requestHandler.canHandleRequest(request)) {
                return new BitmapHunter(picasso, dispatcher, cache, stats, action, requestHandler);
            }
        }
        return new BitmapHunter(picasso, dispatcher, cache, stats, action, BitmapHunter.ERRORING_HANDLER);
    }
    
    private static boolean shouldResize(final boolean b, final int n, final int n2, final int n3, final int n4) {
        return !b || n > n3 || n2 > n4;
    }
    
    static Bitmap transformResult(final Request request, Bitmap bitmap, final int n) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final boolean onlyScaleDown = request.onlyScaleDown;
        int n2 = width;
        int n3 = height;
        final Matrix matrix = new Matrix();
        final boolean needsMatrixTransform = request.needsMatrixTransform();
        int n4 = 0;
        int n5 = 0;
        Label_0196: {
            if (needsMatrixTransform) {
                final int targetWidth = request.targetWidth;
                final int targetHeight = request.targetHeight;
                final float rotationDegrees = request.rotationDegrees;
                if (rotationDegrees != 0.0f) {
                    if (request.hasRotationPivot) {
                        matrix.setRotate(rotationDegrees, request.rotationPivotX, request.rotationPivotY);
                    }
                    else {
                        matrix.setRotate(rotationDegrees);
                    }
                }
                if (request.centerCrop) {
                    final float n6 = targetWidth / width;
                    final float n7 = targetHeight / height;
                    float n9;
                    float n10;
                    if (n6 > n7) {
                        final int n8 = (int)Math.ceil(height * (n7 / n6));
                        n5 = (height - n8) / 2;
                        n3 = n8;
                        n9 = n6;
                        n10 = targetHeight / n3;
                    }
                    else {
                        final int n11 = (int)Math.ceil(width * (n6 / n7));
                        n4 = (width - n11) / 2;
                        n2 = n11;
                        n9 = targetWidth / n2;
                        n10 = n7;
                        n5 = 0;
                    }
                    if (shouldResize(onlyScaleDown, width, height, targetWidth, targetHeight)) {
                        matrix.preScale(n9, n10);
                    }
                }
                else if (request.centerInside) {
                    final float n12 = targetWidth / width;
                    final float n13 = targetHeight / height;
                    float n14;
                    if (n12 < n13) {
                        n14 = n12;
                    }
                    else {
                        n14 = n13;
                    }
                    final boolean shouldResize = shouldResize(onlyScaleDown, width, height, targetWidth, targetHeight);
                    n4 = 0;
                    n5 = 0;
                    if (shouldResize) {
                        matrix.preScale(n14, n14);
                        n4 = 0;
                        n5 = 0;
                    }
                }
                else {
                    if (targetWidth == 0) {
                        n4 = 0;
                        n5 = 0;
                        if (targetHeight == 0) {
                            break Label_0196;
                        }
                    }
                    if (targetWidth == width) {
                        n4 = 0;
                        n5 = 0;
                        if (targetHeight == height) {
                            break Label_0196;
                        }
                    }
                    float n15;
                    if (targetWidth != 0) {
                        n15 = targetWidth / width;
                    }
                    else {
                        n15 = targetHeight / height;
                    }
                    float n16;
                    if (targetHeight != 0) {
                        n16 = targetHeight / height;
                    }
                    else {
                        n16 = targetWidth / width;
                    }
                    final boolean shouldResize2 = shouldResize(onlyScaleDown, width, height, targetWidth, targetHeight);
                    n4 = 0;
                    n5 = 0;
                    if (shouldResize2) {
                        matrix.preScale(n15, n16);
                        n4 = 0;
                        n5 = 0;
                    }
                }
            }
        }
        if (n != 0) {
            matrix.preRotate((float)n);
        }
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap, n4, n5, n2, n3, matrix, true);
        if (bitmap2 != bitmap) {
            bitmap.recycle();
            bitmap = bitmap2;
        }
        return bitmap;
    }
    
    static void updateThreadName(final Request request) {
        final String name = request.getName();
        final StringBuilder sb = BitmapHunter.NAME_BUILDER.get();
        sb.ensureCapacity("Picasso-".length() + name.length());
        sb.replace("Picasso-".length(), sb.length(), name);
        Thread.currentThread().setName(sb.toString());
    }
    
    void attach(final Action action) {
        final boolean loggingEnabled = this.picasso.loggingEnabled;
        final Request request = action.request;
        if (this.action == null) {
            this.action = action;
            if (loggingEnabled) {
                if (this.actions != null && !this.actions.isEmpty()) {
                    Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
                    return;
                }
                Utils.log("Hunter", "joined", request.logId(), "to empty hunter");
            }
        }
        else {
            if (this.actions == null) {
                this.actions = new ArrayList<Action>(3);
            }
            this.actions.add(action);
            if (loggingEnabled) {
                Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
            }
            final Picasso.Priority priority = action.getPriority();
            if (priority.ordinal() > this.priority.ordinal()) {
                this.priority = priority;
            }
        }
    }
    
    boolean cancel() {
        final Action action = this.action;
        boolean b = false;
        if (action == null) {
            if (this.actions != null) {
                final boolean empty = this.actions.isEmpty();
                b = false;
                if (!empty) {
                    return b;
                }
            }
            final Future<?> future = this.future;
            b = false;
            if (future != null) {
                final boolean cancel = this.future.cancel(false);
                b = false;
                if (cancel) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    void detach(final Action action) {
        boolean remove;
        if (this.action == action) {
            this.action = null;
            remove = true;
        }
        else {
            final List<Action> actions = this.actions;
            remove = false;
            if (actions != null) {
                remove = this.actions.remove(action);
            }
        }
        if (remove && action.getPriority() == this.priority) {
            this.priority = this.computeNewPriority();
        }
        if (this.picasso.loggingEnabled) {
            Utils.log("Hunter", "removed", action.request.logId(), Utils.getLogIdsForHunter(this, "from "));
        }
    }
    
    Action getAction() {
        return this.action;
    }
    
    List<Action> getActions() {
        return this.actions;
    }
    
    Request getData() {
        return this.data;
    }
    
    Exception getException() {
        return this.exception;
    }
    
    String getKey() {
        return this.key;
    }
    
    Picasso.LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }
    
    int getMemoryPolicy() {
        return this.memoryPolicy;
    }
    
    Picasso getPicasso() {
        return this.picasso;
    }
    
    Picasso.Priority getPriority() {
        return this.priority;
    }
    
    Bitmap getResult() {
        return this.result;
    }
    
    Bitmap hunt() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/squareup/picasso/BitmapHunter.memoryPolicy:I
        //     4: invokestatic    com/squareup/picasso/MemoryPolicy.shouldReadFromMemoryCache:(I)Z
        //     7: istore_1       
        //     8: aconst_null    
        //     9: astore_2       
        //    10: iload_1        
        //    11: ifeq            77
        //    14: aload_0        
        //    15: getfield        com/squareup/picasso/BitmapHunter.cache:Lcom/squareup/picasso/Cache;
        //    18: aload_0        
        //    19: getfield        com/squareup/picasso/BitmapHunter.key:Ljava/lang/String;
        //    22: invokeinterface com/squareup/picasso/Cache.get:(Ljava/lang/String;)Landroid/graphics/Bitmap;
        //    27: astore_2       
        //    28: aload_2        
        //    29: ifnull          77
        //    32: aload_0        
        //    33: getfield        com/squareup/picasso/BitmapHunter.stats:Lcom/squareup/picasso/Stats;
        //    36: invokevirtual   com/squareup/picasso/Stats.dispatchCacheHit:()V
        //    39: aload_0        
        //    40: getstatic       com/squareup/picasso/Picasso$LoadedFrom.MEMORY:Lcom/squareup/picasso/Picasso$LoadedFrom;
        //    43: putfield        com/squareup/picasso/BitmapHunter.loadedFrom:Lcom/squareup/picasso/Picasso$LoadedFrom;
        //    46: aload_0        
        //    47: getfield        com/squareup/picasso/BitmapHunter.picasso:Lcom/squareup/picasso/Picasso;
        //    50: getfield        com/squareup/picasso/Picasso.loggingEnabled:Z
        //    53: ifeq            75
        //    56: ldc_w           "Hunter"
        //    59: ldc_w           "decoded"
        //    62: aload_0        
        //    63: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //    66: invokevirtual   com/squareup/picasso/Request.logId:()Ljava/lang/String;
        //    69: ldc_w           "from cache"
        //    72: invokestatic    com/squareup/picasso/Utils.log:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    75: aload_2        
        //    76: areturn        
        //    77: aload_0        
        //    78: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //    81: astore_3       
        //    82: aload_0        
        //    83: getfield        com/squareup/picasso/BitmapHunter.retryCount:I
        //    86: ifne            366
        //    89: getstatic       com/squareup/picasso/NetworkPolicy.OFFLINE:Lcom/squareup/picasso/NetworkPolicy;
        //    92: getfield        com/squareup/picasso/NetworkPolicy.index:I
        //    95: istore          4
        //    97: aload_3        
        //    98: iload           4
        //   100: putfield        com/squareup/picasso/Request.networkPolicy:I
        //   103: aload_0        
        //   104: getfield        com/squareup/picasso/BitmapHunter.requestHandler:Lcom/squareup/picasso/RequestHandler;
        //   107: aload_0        
        //   108: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   111: aload_0        
        //   112: getfield        com/squareup/picasso/BitmapHunter.networkPolicy:I
        //   115: invokevirtual   com/squareup/picasso/RequestHandler.load:(Lcom/squareup/picasso/Request;I)Lcom/squareup/picasso/RequestHandler$Result;
        //   118: astore          5
        //   120: aload           5
        //   122: ifnull          179
        //   125: aload_0        
        //   126: aload           5
        //   128: invokevirtual   com/squareup/picasso/RequestHandler$Result.getLoadedFrom:()Lcom/squareup/picasso/Picasso$LoadedFrom;
        //   131: putfield        com/squareup/picasso/BitmapHunter.loadedFrom:Lcom/squareup/picasso/Picasso$LoadedFrom;
        //   134: aload_0        
        //   135: aload           5
        //   137: invokevirtual   com/squareup/picasso/RequestHandler$Result.getExifOrientation:()I
        //   140: putfield        com/squareup/picasso/BitmapHunter.exifRotation:I
        //   143: aload           5
        //   145: invokevirtual   com/squareup/picasso/RequestHandler$Result.getBitmap:()Landroid/graphics/Bitmap;
        //   148: astore_2       
        //   149: aload_2        
        //   150: ifnonnull       179
        //   153: aload           5
        //   155: invokevirtual   com/squareup/picasso/RequestHandler$Result.getStream:()Ljava/io/InputStream;
        //   158: astore          8
        //   160: aload           8
        //   162: aload_0        
        //   163: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   166: invokestatic    com/squareup/picasso/BitmapHunter.decodeStream:(Ljava/io/InputStream;Lcom/squareup/picasso/Request;)Landroid/graphics/Bitmap;
        //   169: astore          10
        //   171: aload           10
        //   173: astore_2       
        //   174: aload           8
        //   176: invokestatic    com/squareup/picasso/Utils.closeQuietly:(Ljava/io/InputStream;)V
        //   179: aload_2        
        //   180: ifnull          364
        //   183: aload_0        
        //   184: getfield        com/squareup/picasso/BitmapHunter.picasso:Lcom/squareup/picasso/Picasso;
        //   187: getfield        com/squareup/picasso/Picasso.loggingEnabled:Z
        //   190: ifeq            209
        //   193: ldc_w           "Hunter"
        //   196: ldc_w           "decoded"
        //   199: aload_0        
        //   200: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   203: invokevirtual   com/squareup/picasso/Request.logId:()Ljava/lang/String;
        //   206: invokestatic    com/squareup/picasso/Utils.log:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   209: aload_0        
        //   210: getfield        com/squareup/picasso/BitmapHunter.stats:Lcom/squareup/picasso/Stats;
        //   213: aload_2        
        //   214: invokevirtual   com/squareup/picasso/Stats.dispatchBitmapDecoded:(Landroid/graphics/Bitmap;)V
        //   217: aload_0        
        //   218: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   221: invokevirtual   com/squareup/picasso/Request.needsTransformation:()Z
        //   224: ifne            234
        //   227: aload_0        
        //   228: getfield        com/squareup/picasso/BitmapHunter.exifRotation:I
        //   231: ifeq            364
        //   234: getstatic       com/squareup/picasso/BitmapHunter.DECODE_LOCK:Ljava/lang/Object;
        //   237: astore          6
        //   239: aload           6
        //   241: monitorenter   
        //   242: aload_0        
        //   243: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   246: invokevirtual   com/squareup/picasso/Request.needsMatrixTransform:()Z
        //   249: ifne            259
        //   252: aload_0        
        //   253: getfield        com/squareup/picasso/BitmapHunter.exifRotation:I
        //   256: ifeq            298
        //   259: aload_0        
        //   260: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   263: aload_2        
        //   264: aload_0        
        //   265: getfield        com/squareup/picasso/BitmapHunter.exifRotation:I
        //   268: invokestatic    com/squareup/picasso/BitmapHunter.transformResult:(Lcom/squareup/picasso/Request;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
        //   271: astore_2       
        //   272: aload_0        
        //   273: getfield        com/squareup/picasso/BitmapHunter.picasso:Lcom/squareup/picasso/Picasso;
        //   276: getfield        com/squareup/picasso/Picasso.loggingEnabled:Z
        //   279: ifeq            298
        //   282: ldc_w           "Hunter"
        //   285: ldc_w           "transformed"
        //   288: aload_0        
        //   289: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   292: invokevirtual   com/squareup/picasso/Request.logId:()Ljava/lang/String;
        //   295: invokestatic    com/squareup/picasso/Utils.log:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   298: aload_0        
        //   299: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   302: invokevirtual   com/squareup/picasso/Request.hasCustomTransformations:()Z
        //   305: ifeq            349
        //   308: aload_0        
        //   309: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   312: getfield        com/squareup/picasso/Request.transformations:Ljava/util/List;
        //   315: aload_2        
        //   316: invokestatic    com/squareup/picasso/BitmapHunter.applyCustomTransformations:(Ljava/util/List;Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
        //   319: astore_2       
        //   320: aload_0        
        //   321: getfield        com/squareup/picasso/BitmapHunter.picasso:Lcom/squareup/picasso/Picasso;
        //   324: getfield        com/squareup/picasso/Picasso.loggingEnabled:Z
        //   327: ifeq            349
        //   330: ldc_w           "Hunter"
        //   333: ldc_w           "transformed"
        //   336: aload_0        
        //   337: getfield        com/squareup/picasso/BitmapHunter.data:Lcom/squareup/picasso/Request;
        //   340: invokevirtual   com/squareup/picasso/Request.logId:()Ljava/lang/String;
        //   343: ldc_w           "from custom transformations"
        //   346: invokestatic    com/squareup/picasso/Utils.log:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   349: aload           6
        //   351: monitorexit    
        //   352: aload_2        
        //   353: ifnull          364
        //   356: aload_0        
        //   357: getfield        com/squareup/picasso/BitmapHunter.stats:Lcom/squareup/picasso/Stats;
        //   360: aload_2        
        //   361: invokevirtual   com/squareup/picasso/Stats.dispatchBitmapTransformed:(Landroid/graphics/Bitmap;)V
        //   364: aload_2        
        //   365: areturn        
        //   366: aload_0        
        //   367: getfield        com/squareup/picasso/BitmapHunter.networkPolicy:I
        //   370: istore          4
        //   372: goto            97
        //   375: astore          9
        //   377: aload           8
        //   379: invokestatic    com/squareup/picasso/Utils.closeQuietly:(Ljava/io/InputStream;)V
        //   382: aload           9
        //   384: athrow         
        //   385: astore          7
        //   387: aload           6
        //   389: monitorexit    
        //   390: aload           7
        //   392: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  160    171    375    385    Any
        //  242    259    385    393    Any
        //  259    298    385    393    Any
        //  298    349    385    393    Any
        //  349    352    385    393    Any
        //  387    390    385    393    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    boolean isCancelled() {
        return this.future != null && this.future.isCancelled();
    }
    
    @Override
    public void run() {
        try {
            updateThreadName(this.data);
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "executing", Utils.getLogIdsForHunter(this));
            }
            this.result = this.hunt();
            if (this.result == null) {
                this.dispatcher.dispatchFailed(this);
            }
            else {
                this.dispatcher.dispatchComplete(this);
            }
        }
        catch (Downloader.ResponseException exception) {
            if (!exception.localCacheOnly || exception.responseCode != 504) {
                this.exception = exception;
            }
            this.dispatcher.dispatchFailed(this);
        }
        catch (NetworkRequestHandler.ContentLengthException exception2) {
            this.exception = exception2;
            this.dispatcher.dispatchRetry(this);
        }
        catch (IOException exception3) {
            this.exception = exception3;
            this.dispatcher.dispatchRetry(this);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            final StringWriter stringWriter = new StringWriter();
            this.stats.createSnapshot().dump(new PrintWriter(stringWriter));
            this.exception = new RuntimeException(stringWriter.toString(), outOfMemoryError);
            this.dispatcher.dispatchFailed(this);
        }
        catch (Exception exception4) {
            this.exception = exception4;
            this.dispatcher.dispatchFailed(this);
        }
        finally {
            Thread.currentThread().setName("Picasso-Idle");
        }
    }
    
    boolean shouldRetry(final boolean b, final NetworkInfo networkInfo) {
        int n;
        if (this.retryCount > 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            return false;
        }
        --this.retryCount;
        return this.requestHandler.shouldRetry(b, networkInfo);
    }
    
    boolean supportsReplay() {
        return this.requestHandler.supportsReplay();
    }
}
