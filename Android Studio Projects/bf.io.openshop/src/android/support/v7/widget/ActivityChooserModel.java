package android.support.v7.widget;

import android.database.*;
import android.text.*;
import android.content.pm.*;
import android.support.v4.os.*;
import android.os.*;
import java.util.*;
import android.content.*;
import java.math.*;

class ActivityChooserModel extends DataSetObservable
{
    private static final String ATTRIBUTE_ACTIVITY = "activity";
    private static final String ATTRIBUTE_TIME = "time";
    private static final String ATTRIBUTE_WEIGHT = "weight";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    private static final String LOG_TAG;
    private static final String TAG_HISTORICAL_RECORD = "historical-record";
    private static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityResolveInfo> mActivities;
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    private boolean mCanReadHistoricalData;
    private final Context mContext;
    private final List<HistoricalRecord> mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    private final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;
    
    static {
        LOG_TAG = ActivityChooserModel.class.getSimpleName();
        sRegistryLock = new Object();
        sDataModelRegistry = new HashMap<String, ActivityChooserModel>();
    }
    
    private ActivityChooserModel(final Context context, final String mHistoryFileName) {
        this.mInstanceLock = new Object();
        this.mActivities = new ArrayList<ActivityResolveInfo>();
        this.mHistoricalRecords = new ArrayList<HistoricalRecord>();
        this.mActivitySorter = (ActivitySorter)new DefaultSorter();
        this.mHistoryMaxSize = 50;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = false;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = false;
        this.mContext = context.getApplicationContext();
        if (!TextUtils.isEmpty((CharSequence)mHistoryFileName) && !mHistoryFileName.endsWith(".xml")) {
            this.mHistoryFileName = mHistoryFileName + ".xml";
            return;
        }
        this.mHistoryFileName = mHistoryFileName;
    }
    
    private boolean addHisoricalRecord(final HistoricalRecord historicalRecord) {
        final boolean add = this.mHistoricalRecords.add(historicalRecord);
        if (add) {
            this.mHistoricalRecordsChanged = true;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            this.persistHistoricalDataIfNeeded();
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
        return add;
    }
    
    private void ensureConsistentState() {
        final boolean b = this.loadActivitiesIfNeeded() | this.readHistoricalDataIfNeeded();
        this.pruneExcessiveHistoricalRecordsIfNeeded();
        if (b) {
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
    }
    
    public static ActivityChooserModel get(final Context context, final String s) {
        synchronized (ActivityChooserModel.sRegistryLock) {
            ActivityChooserModel activityChooserModel = ActivityChooserModel.sDataModelRegistry.get(s);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, s);
                ActivityChooserModel.sDataModelRegistry.put(s, activityChooserModel);
            }
            return activityChooserModel;
        }
    }
    
    private boolean loadActivitiesIfNeeded() {
        final boolean mReloadActivities = this.mReloadActivities;
        boolean b = false;
        if (mReloadActivities) {
            final Intent mIntent = this.mIntent;
            b = false;
            if (mIntent != null) {
                this.mReloadActivities = false;
                this.mActivities.clear();
                final List queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
                for (int size = queryIntentActivities.size(), i = 0; i < size; ++i) {
                    this.mActivities.add(new ActivityResolveInfo(queryIntentActivities.get(i)));
                }
                b = true;
            }
        }
        return b;
    }
    
    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if (!TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
                AsyncTaskCompat.executeParallel((android.os.AsyncTask<Object, Object, Object>)new PersistHistoryAsyncTask(), new ArrayList(this.mHistoricalRecords), this.mHistoryFileName);
            }
        }
    }
    
    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        final int n = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (n > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < n; ++i) {
                final HistoricalRecord historicalRecord = this.mHistoricalRecords.remove(0);
            }
        }
    }
    
    private boolean readHistoricalDataIfNeeded() {
        if (this.mCanReadHistoricalData && this.mHistoricalRecordsChanged && !TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
            this.mCanReadHistoricalData = false;
            this.mReadShareHistoryCalled = true;
            this.readHistoricalDataImpl();
            return true;
        }
        return false;
    }
    
    private void readHistoricalDataImpl() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v7/widget/ActivityChooserModel.mContext:Landroid/content/Context;
        //     4: aload_0        
        //     5: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore_2       
        //    12: invokestatic    android/util/Xml.newPullParser:()Lorg/xmlpull/v1/XmlPullParser;
        //    15: astore          11
        //    17: aload           11
        //    19: aload_2        
        //    20: ldc_w           "UTF-8"
        //    23: invokeinterface org/xmlpull/v1/XmlPullParser.setInput:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    28: iconst_0       
        //    29: istore          12
        //    31: iload           12
        //    33: iconst_1       
        //    34: if_icmpeq       55
        //    37: iload           12
        //    39: iconst_2       
        //    40: if_icmpeq       55
        //    43: aload           11
        //    45: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //    50: istore          12
        //    52: goto            31
        //    55: ldc             "historical-records"
        //    57: aload           11
        //    59: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //    64: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    67: ifne            127
        //    70: new             Lorg/xmlpull/v1/XmlPullParserException;
        //    73: dup            
        //    74: ldc_w           "Share records file does not start with historical-records tag."
        //    77: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //    80: athrow         
        //    81: astore          8
        //    83: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: invokespecial   java/lang/StringBuilder.<init>:()V
        //    93: ldc_w           "Error reading historical recrod file: "
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: aload_0        
        //   100: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   109: aload           8
        //   111: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   114: pop            
        //   115: aload_2        
        //   116: ifnull          322
        //   119: aload_2        
        //   120: invokevirtual   java/io/FileInputStream.close:()V
        //   123: return         
        //   124: astore          10
        //   126: return         
        //   127: aload_0        
        //   128: getfield        android/support/v7/widget/ActivityChooserModel.mHistoricalRecords:Ljava/util/List;
        //   131: astore          13
        //   133: aload           13
        //   135: invokeinterface java/util/List.clear:()V
        //   140: aload           11
        //   142: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //   147: istore          14
        //   149: iload           14
        //   151: iconst_1       
        //   152: if_icmpne       167
        //   155: aload_2        
        //   156: ifnull          322
        //   159: aload_2        
        //   160: invokevirtual   java/io/FileInputStream.close:()V
        //   163: return         
        //   164: astore          16
        //   166: return         
        //   167: iload           14
        //   169: iconst_3       
        //   170: if_icmpeq       140
        //   173: iload           14
        //   175: iconst_4       
        //   176: if_icmpeq       140
        //   179: ldc             "historical-record"
        //   181: aload           11
        //   183: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //   188: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   191: ifne            251
        //   194: new             Lorg/xmlpull/v1/XmlPullParserException;
        //   197: dup            
        //   198: ldc_w           "Share records file not well-formed."
        //   201: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //   204: athrow         
        //   205: astore          5
        //   207: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   210: new             Ljava/lang/StringBuilder;
        //   213: dup            
        //   214: invokespecial   java/lang/StringBuilder.<init>:()V
        //   217: ldc_w           "Error reading historical recrod file: "
        //   220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: aload_0        
        //   224: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   233: aload           5
        //   235: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   238: pop            
        //   239: aload_2        
        //   240: ifnull          322
        //   243: aload_2        
        //   244: invokevirtual   java/io/FileInputStream.close:()V
        //   247: return         
        //   248: astore          7
        //   250: return         
        //   251: aload           13
        //   253: new             Landroid/support/v7/widget/ActivityChooserModel$HistoricalRecord;
        //   256: dup            
        //   257: aload           11
        //   259: aconst_null    
        //   260: ldc             "activity"
        //   262: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   267: aload           11
        //   269: aconst_null    
        //   270: ldc             "time"
        //   272: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   277: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   280: aload           11
        //   282: aconst_null    
        //   283: ldc             "weight"
        //   285: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   290: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   293: invokespecial   android/support/v7/widget/ActivityChooserModel$HistoricalRecord.<init>:(Ljava/lang/String;JF)V
        //   296: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   301: pop            
        //   302: goto            140
        //   305: astore_3       
        //   306: aload_2        
        //   307: ifnull          314
        //   310: aload_2        
        //   311: invokevirtual   java/io/FileInputStream.close:()V
        //   314: aload_3        
        //   315: athrow         
        //   316: astore          4
        //   318: goto            314
        //   321: astore_1       
        //   322: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  0      12     321    322    Ljava/io/FileNotFoundException;
        //  12     28     81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  12     28     205    251    Ljava/io/IOException;
        //  12     28     305    321    Any
        //  43     52     81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  43     52     205    251    Ljava/io/IOException;
        //  43     52     305    321    Any
        //  55     81     81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  55     81     205    251    Ljava/io/IOException;
        //  55     81     305    321    Any
        //  83     115    305    321    Any
        //  119    123    124    127    Ljava/io/IOException;
        //  127    140    81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  127    140    205    251    Ljava/io/IOException;
        //  127    140    305    321    Any
        //  140    149    81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  140    149    205    251    Ljava/io/IOException;
        //  140    149    305    321    Any
        //  159    163    164    167    Ljava/io/IOException;
        //  179    205    81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  179    205    205    251    Ljava/io/IOException;
        //  179    205    305    321    Any
        //  207    239    305    321    Any
        //  243    247    248    251    Ljava/io/IOException;
        //  251    302    81     127    Lorg/xmlpull/v1/XmlPullParserException;
        //  251    302    205    251    Ljava/io/IOException;
        //  251    302    305    321    Any
        //  310    314    316    321    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
            this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList((List<? extends HistoricalRecord>)this.mHistoricalRecords));
            return true;
        }
        return false;
    }
    
    public Intent chooseActivity(final int n) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == null) {
                return null;
            }
            this.ensureConsistentState();
            final ActivityResolveInfo activityResolveInfo = this.mActivities.get(n);
            final ComponentName component = new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name);
            final Intent intent = new Intent(this.mIntent);
            intent.setComponent(component);
            if (this.mActivityChoserModelPolicy != null && this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                return null;
            }
            this.addHisoricalRecord(new HistoricalRecord(component, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }
    
    public ResolveInfo getActivity(final int n) {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mActivities.get(n).resolveInfo;
        }
    }
    
    public int getActivityCount() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mActivities.size();
        }
    }
    
    public int getActivityIndex(final ResolveInfo resolveInfo) {
        while (true) {
            while (true) {
                int n;
                synchronized (this.mInstanceLock) {
                    this.ensureConsistentState();
                    final List<ActivityResolveInfo> mActivities = this.mActivities;
                    final int size = mActivities.size();
                    n = 0;
                    if (n >= size) {
                        return -1;
                    }
                    if (mActivities.get(n).resolveInfo == resolveInfo) {
                        return n;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public ResolveInfo getDefaultActivity() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            if (!this.mActivities.isEmpty()) {
                return this.mActivities.get(0).resolveInfo;
            }
            return null;
        }
    }
    
    public int getHistoryMaxSize() {
        synchronized (this.mInstanceLock) {
            return this.mHistoryMaxSize;
        }
    }
    
    public int getHistorySize() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mHistoricalRecords.size();
        }
    }
    
    public Intent getIntent() {
        synchronized (this.mInstanceLock) {
            return this.mIntent;
        }
    }
    
    public void setActivitySorter(final ActivitySorter mActivitySorter) {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter == mActivitySorter) {
                return;
            }
            this.mActivitySorter = mActivitySorter;
            if (this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
        }
    }
    
    public void setDefaultActivity(final int n) {
        while (true) {
            while (true) {
                synchronized (this.mInstanceLock) {
                    this.ensureConsistentState();
                    final ActivityResolveInfo activityResolveInfo = this.mActivities.get(n);
                    final ActivityResolveInfo activityResolveInfo2 = this.mActivities.get(0);
                    if (activityResolveInfo2 != null) {
                        final float n2 = 5.0f + (activityResolveInfo2.weight - activityResolveInfo.weight);
                        this.addHisoricalRecord(new HistoricalRecord(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), n2));
                        return;
                    }
                }
                final float n2 = 1.0f;
                continue;
            }
        }
    }
    
    public void setHistoryMaxSize(final int mHistoryMaxSize) {
        synchronized (this.mInstanceLock) {
            if (this.mHistoryMaxSize == mHistoryMaxSize) {
                return;
            }
            this.mHistoryMaxSize = mHistoryMaxSize;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            if (this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
        }
    }
    
    public void setIntent(final Intent mIntent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == mIntent) {
                return;
            }
            this.mIntent = mIntent;
            this.mReloadActivities = true;
            this.ensureConsistentState();
        }
    }
    
    public void setOnChooseActivityListener(final OnChooseActivityListener mActivityChoserModelPolicy) {
        synchronized (this.mInstanceLock) {
            this.mActivityChoserModelPolicy = mActivityChoserModelPolicy;
        }
    }
    
    public interface ActivityChooserModelClient
    {
        void setActivityChooserModel(final ActivityChooserModel p0);
    }
    
    public final class ActivityResolveInfo implements Comparable<ActivityResolveInfo>
    {
        public final ResolveInfo resolveInfo;
        public float weight;
        
        public ActivityResolveInfo(final ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }
        
        @Override
        public int compareTo(final ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o == null) {
                    return false;
                }
                if (this.getClass() != o.getClass()) {
                    return false;
                }
                if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityResolveInfo)o).weight)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return 31 + Float.floatToIntBits(this.weight);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("resolveInfo:").append(this.resolveInfo.toString());
            sb.append("; weight:").append(new BigDecimal(this.weight));
            sb.append("]");
            return sb.toString();
        }
    }
    
    public interface ActivitySorter
    {
        void sort(final Intent p0, final List<ActivityResolveInfo> p1, final List<HistoricalRecord> p2);
    }
    
    private final class DefaultSorter implements ActivitySorter
    {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap;
        
        private DefaultSorter() {
            this.mPackageNameToActivityMap = new HashMap<ComponentName, ActivityResolveInfo>();
        }
        
        @Override
        public void sort(final Intent intent, final List<ActivityResolveInfo> list, final List<HistoricalRecord> list2) {
            final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap = this.mPackageNameToActivityMap;
            mPackageNameToActivityMap.clear();
            for (int size = list.size(), i = 0; i < size; ++i) {
                final ActivityResolveInfo activityResolveInfo = list.get(i);
                activityResolveInfo.weight = 0.0f;
                mPackageNameToActivityMap.put(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), activityResolveInfo);
            }
            final int n = -1 + list2.size();
            float n2 = 1.0f;
            for (int j = n; j >= 0; --j) {
                final HistoricalRecord historicalRecord = list2.get(j);
                final ActivityResolveInfo activityResolveInfo2 = mPackageNameToActivityMap.get(historicalRecord.activity);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight += n2 * historicalRecord.weight;
                    n2 *= 0.95f;
                }
            }
            Collections.sort((List<Comparable>)list);
        }
    }
    
    public static final class HistoricalRecord
    {
        public final ComponentName activity;
        public final long time;
        public final float weight;
        
        public HistoricalRecord(final ComponentName activity, final long time, final float weight) {
            this.activity = activity;
            this.time = time;
            this.weight = weight;
        }
        
        public HistoricalRecord(final String s, final long n, final float n2) {
            this(ComponentName.unflattenFromString(s), n, n2);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o == null) {
                    return false;
                }
                if (this.getClass() != o.getClass()) {
                    return false;
                }
                final HistoricalRecord historicalRecord = (HistoricalRecord)o;
                if (this.activity == null) {
                    if (historicalRecord.activity != null) {
                        return false;
                    }
                }
                else if (!this.activity.equals((Object)historicalRecord.activity)) {
                    return false;
                }
                if (this.time != historicalRecord.time) {
                    return false;
                }
                if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(historicalRecord.weight)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.activity == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.activity.hashCode();
            }
            return 31 * (31 * (hashCode + 31) + (int)(this.time ^ this.time >>> 32)) + Float.floatToIntBits(this.weight);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("; activity:").append(this.activity);
            sb.append("; time:").append(this.time);
            sb.append("; weight:").append(new BigDecimal(this.weight));
            sb.append("]");
            return sb.toString();
        }
    }
    
    public interface OnChooseActivityListener
    {
        boolean onChooseActivity(final ActivityChooserModel p0, final Intent p1);
    }
    
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
}
