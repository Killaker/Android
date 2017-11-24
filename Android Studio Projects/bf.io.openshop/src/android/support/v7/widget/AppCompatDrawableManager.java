package android.support.v7.widget;

import java.util.*;
import android.content.*;
import java.lang.ref.*;
import android.support.v7.appcompat.*;
import android.support.v4.graphics.*;
import android.os.*;
import org.xmlpull.v1.*;
import android.util.*;
import android.graphics.*;
import android.support.v4.graphics.drawable.*;
import android.graphics.drawable.*;
import android.support.v4.content.*;
import android.content.res.*;
import android.support.annotation.*;
import android.support.graphics.drawable.*;
import android.support.v4.util.*;

public final class AppCompatDrawableManager
{
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final boolean DEBUG = false;
    private static final PorterDuff$Mode DEFAULT_MODE;
    private static AppCompatDrawableManager INSTANCE;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManager";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private final Object mDelegateDrawableCacheLock;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable$ConstantState>>> mDelegateDrawableCaches;
    private ArrayMap<String, InflateDelegate> mDelegates;
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;
    
    static {
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
        TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_ab_back_mtrl_am_alpha, R.drawable.abc_ic_go_search_api_mtrl_alpha, R.drawable.abc_ic_search_api_mtrl_alpha, R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_ic_clear_mtrl_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R.drawable.abc_ic_voice_search_api_mtrl_alpha };
        COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material };
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
        TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_edit_text_material, R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material, R.drawable.abc_spinner_mtrl_am_alpha, R.drawable.abc_spinner_textfield_background_material, R.drawable.abc_ratingbar_full_material, R.drawable.abc_switch_track_mtrl_alpha, R.drawable.abc_switch_thumb_material, R.drawable.abc_btn_default_mtrl_shape, R.drawable.abc_btn_borderless_material };
        TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
    }
    
    public AppCompatDrawableManager() {
        this.mDelegateDrawableCacheLock = new Object();
        this.mDelegateDrawableCaches = new WeakHashMap<Context, LongSparseArray<WeakReference<Drawable$ConstantState>>>(0);
    }
    
    private boolean addCachedDelegateDrawable(@NonNull final Context context, final long n, @NonNull final Drawable drawable) {
        final Drawable$ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            synchronized (this.mDelegateDrawableCacheLock) {
                LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray = this.mDelegateDrawableCaches.get(context);
                if (longSparseArray == null) {
                    longSparseArray = new LongSparseArray<WeakReference<Drawable$ConstantState>>();
                    this.mDelegateDrawableCaches.put(context, longSparseArray);
                }
                longSparseArray.put(n, new WeakReference<Drawable$ConstantState>(constantState));
                return true;
            }
        }
        return false;
    }
    
    private void addDelegate(@NonNull final String s, @NonNull final InflateDelegate inflateDelegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap<String, InflateDelegate>();
        }
        this.mDelegates.put(s, inflateDelegate);
    }
    
    private void addTintListToCache(@NonNull final Context context, @DrawableRes final int n, @NonNull final ColorStateList list) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap<Context, SparseArray<ColorStateList>>();
        }
        SparseArray sparseArray = this.mTintLists.get(context);
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            this.mTintLists.put(context, (SparseArray<ColorStateList>)sparseArray);
        }
        sparseArray.append(n, (Object)list);
    }
    
    private static boolean arrayContains(final int[] array, final int n) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private ColorStateList createButtonColorStateList(final Context context, final int n) {
        final int[][] array = new int[4][];
        final int[] array2 = new int[4];
        final int themeAttrColor = ThemeUtils.getThemeAttrColor(context, n);
        final int themeAttrColor2 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
        final int n2 = 0 + 1;
        array[n2] = ThemeUtils.PRESSED_STATE_SET;
        array2[n2] = ColorUtils.compositeColors(themeAttrColor2, themeAttrColor);
        final int n3 = n2 + 1;
        array[n3] = ThemeUtils.FOCUSED_STATE_SET;
        array2[n3] = ColorUtils.compositeColors(themeAttrColor2, themeAttrColor);
        final int n4 = n3 + 1;
        array[n4] = ThemeUtils.EMPTY_STATE_SET;
        array2[n4] = themeAttrColor;
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createCheckableButtonColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.CHECKED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createColoredButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, R.attr.colorAccent);
    }
    
    private ColorStateList createDefaultButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, R.attr.colorButtonNormal);
    }
    
    private ColorStateList createDefaultColorStateList(final Context context) {
        final int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        final int themeAttrColor2 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        final int[][] array = new int[7][];
        final int[] array2 = new int[7];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.FOCUSED_STATE_SET;
        array2[n] = themeAttrColor2;
        final int n2 = n + 1;
        array[n2] = ThemeUtils.ACTIVATED_STATE_SET;
        array2[n2] = themeAttrColor2;
        final int n3 = n2 + 1;
        array[n3] = ThemeUtils.PRESSED_STATE_SET;
        array2[n3] = themeAttrColor2;
        final int n4 = n3 + 1;
        array[n4] = ThemeUtils.CHECKED_STATE_SET;
        array2[n4] = themeAttrColor2;
        final int n5 = n4 + 1;
        array[n5] = ThemeUtils.SELECTED_STATE_SET;
        array2[n5] = themeAttrColor2;
        final int n6 = n5 + 1;
        array[n6] = ThemeUtils.EMPTY_STATE_SET;
        array2[n6] = themeAttrColor;
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createEditTextColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSeekbarThumbColorStateList(final Context context) {
        final int[][] array = new int[2][];
        final int[] array2 = new int[2];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlActivated);
        final int n = 0 + 1;
        array[n] = ThemeUtils.EMPTY_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSpinnerColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSwitchThumbColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        final ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = themeAttrColorStateList.getColorForState(array[0], 0);
            final int n = 0 + 1;
            array[n] = ThemeUtils.CHECKED_STATE_SET;
            array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            final int n2 = n + 1;
            array[n2] = ThemeUtils.EMPTY_STATE_SET;
            array2[n2] = themeAttrColorStateList.getDefaultColor();
        }
        else {
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
            final int n3 = 0 + 1;
            array[n3] = ThemeUtils.CHECKED_STATE_SET;
            array2[n3] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            final int n4 = n3 + 1;
            array[n4] = ThemeUtils.EMPTY_STATE_SET;
            array2[n4] = ThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
        }
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSwitchTrackColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getThemeAttrColor(context, 16842800, 0.1f);
        final int n = 0 + 1;
        array[n] = ThemeUtils.CHECKED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated, 0.3f);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, 16842800, 0.3f);
        return new ColorStateList(array, array2);
    }
    
    private static PorterDuffColorFilter createTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int[] array) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(list.getColorForState(array, 0), porterDuff$Mode);
    }
    
    public static AppCompatDrawableManager get() {
        if (AppCompatDrawableManager.INSTANCE == null) {
            installDefaultInflateDelegates(AppCompatDrawableManager.INSTANCE = new AppCompatDrawableManager());
        }
        return AppCompatDrawableManager.INSTANCE;
    }
    
    private Drawable getCachedDelegateDrawable(@NonNull final Context context, final long n) {
        Label_0094: {
            final LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray;
            synchronized (this.mDelegateDrawableCacheLock) {
                longSparseArray = this.mDelegateDrawableCaches.get(context);
                if (longSparseArray == null) {
                    return null;
                }
                final WeakReference<Drawable$ConstantState> weakReference = longSparseArray.get(n);
                if (weakReference == null) {
                    break Label_0094;
                }
                final Drawable$ConstantState drawable$ConstantState = weakReference.get();
                if (drawable$ConstantState != null) {
                    return drawable$ConstantState.newDrawable(context.getResources());
                }
            }
            longSparseArray.delete(n);
        }
        // monitorexit(o)
        return null;
    }
    
    public static PorterDuffColorFilter getPorterDuffColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuffColorFilter value = AppCompatDrawableManager.COLOR_FILTER_CACHE.get(n, porterDuff$Mode);
        if (value == null) {
            value = new PorterDuffColorFilter(n, porterDuff$Mode);
            AppCompatDrawableManager.COLOR_FILTER_CACHE.put(n, porterDuff$Mode, value);
        }
        return value;
    }
    
    private ColorStateList getTintListFromCache(@NonNull final Context context, @DrawableRes final int n) {
        final WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists = this.mTintLists;
        ColorStateList list = null;
        if (mTintLists != null) {
            final SparseArray<ColorStateList> sparseArray = this.mTintLists.get(context);
            list = null;
            if (sparseArray != null) {
                list = (ColorStateList)sparseArray.get(n);
            }
        }
        return list;
    }
    
    private static void installDefaultInflateDelegates(@NonNull final AppCompatDrawableManager appCompatDrawableManager) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT < 21) {
            appCompatDrawableManager.addDelegate("vector", (InflateDelegate)new VdcInflateDelegate());
            if (sdk_INT >= 11) {
                appCompatDrawableManager.addDelegate("animated-vector", (InflateDelegate)new AvdcInflateDelegate());
            }
        }
    }
    
    private static boolean isVectorDrawable(@NonNull final Drawable drawable) {
        return drawable instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
    
    private Drawable loadDrawableFromDelegates(@NonNull final Context context, @DrawableRes final int n) {
        if (this.mDelegates == null || this.mDelegates.isEmpty()) {
            return null;
        }
        Label_0081: {
            if (this.mKnownDrawableIdTags == null) {
                this.mKnownDrawableIdTags = (SparseArray<String>)new SparseArray();
                break Label_0081;
            }
            final String s = (String)this.mKnownDrawableIdTags.get(n);
            if (!"appcompat_skip_skip".equals(s) && (s == null || this.mDelegates.get(s) != null)) {
                break Label_0081;
            }
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        final TypedValue mTypedValue = this.mTypedValue;
        final Resources resources = context.getResources();
        resources.getValue(n, mTypedValue, true);
        final long n2 = mTypedValue.assetCookie << 32 | mTypedValue.data;
        Drawable drawable = this.getCachedDelegateDrawable(context, n2);
        if (drawable != null) {
            return drawable;
        }
        Label_0239: {
            if (mTypedValue.string != null && mTypedValue.string.toString().endsWith(".xml")) {
                XmlResourceParser xml = null;
                AttributeSet attributeSet = null;
                Label_0257: {
                    try {
                        xml = resources.getXml(n);
                        attributeSet = Xml.asAttributeSet((XmlPullParser)xml);
                        int next;
                        do {
                            next = ((XmlPullParser)xml).next();
                        } while (next != 2 && next != 1);
                        if (next != 2) {
                            throw new XmlPullParserException("No start tag found");
                        }
                        break Label_0257;
                    }
                    catch (Exception ex) {
                        Log.e("AppCompatDrawableManager", "Exception while inflating drawable", (Throwable)ex);
                    }
                    break Label_0239;
                }
                final String name = ((XmlPullParser)xml).getName();
                this.mKnownDrawableIdTags.append(n, (Object)name);
                final InflateDelegate inflateDelegate = this.mDelegates.get(name);
                if (inflateDelegate != null) {
                    drawable = inflateDelegate.createFromXmlInner(context, (XmlPullParser)xml, attributeSet, context.getTheme());
                }
                if (drawable != null) {
                    drawable.setChangingConfigurations(mTypedValue.changingConfigurations);
                    if (this.addCachedDelegateDrawable(context, n2, drawable)) {}
                }
            }
        }
        if (drawable == null) {
            this.mKnownDrawableIdTags.append(n, (Object)"appcompat_skip_skip");
            return drawable;
        }
        return drawable;
    }
    
    private void removeDelegate(@NonNull final String s, @NonNull final InflateDelegate inflateDelegate) {
        if (this.mDelegates != null && this.mDelegates.get(s) == inflateDelegate) {
            this.mDelegates.remove(s);
        }
    }
    
    private static void setPorterDuffColorFilter(Drawable mutate, final int n, PorterDuff$Mode default_MODE) {
        if (DrawableUtils.canSafelyMutateDrawable(mutate)) {
            mutate = mutate.mutate();
        }
        if (default_MODE == null) {
            default_MODE = AppCompatDrawableManager.DEFAULT_MODE;
        }
        mutate.setColorFilter((ColorFilter)getPorterDuffColorFilter(n, default_MODE));
    }
    
    private Drawable tintDrawable(@NonNull final Context context, @DrawableRes final int n, final boolean b, @NonNull Drawable drawable) {
        final ColorStateList tintList = this.getTintList(context, n);
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(drawable, tintList);
            final PorterDuff$Mode tintMode = this.getTintMode(n);
            if (tintMode != null) {
                DrawableCompat.setTintMode(drawable, tintMode);
            }
        }
        else {
            if (n == R.drawable.abc_cab_background_top_material) {
                return (Drawable)new LayerDrawable(new Drawable[] { this.getDrawable(context, R.drawable.abc_cab_background_internal_bg), this.getDrawable(context, R.drawable.abc_cab_background_top_mtrl_alpha) });
            }
            if (n == R.drawable.abc_seekbar_track_material) {
                final LayerDrawable layerDrawable = (LayerDrawable)drawable;
                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
            }
            else if (n == R.drawable.abc_ratingbar_indicator_material || n == R.drawable.abc_ratingbar_small_material) {
                final LayerDrawable layerDrawable2 = (LayerDrawable)drawable;
                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
            }
            else if (!tintDrawableUsingColorFilter(context, n, drawable) && b) {
                drawable = null;
            }
        }
        return drawable;
    }
    
    public static void tintDrawable(final Drawable drawable, final TintInfo tintInfo, final int[] array) {
        if (DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable) {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
        }
        else {
            if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                ColorStateList mTintList;
                if (tintInfo.mHasTintList) {
                    mTintList = tintInfo.mTintList;
                }
                else {
                    mTintList = null;
                }
                PorterDuff$Mode porterDuff$Mode;
                if (tintInfo.mHasTintMode) {
                    porterDuff$Mode = tintInfo.mTintMode;
                }
                else {
                    porterDuff$Mode = AppCompatDrawableManager.DEFAULT_MODE;
                }
                drawable.setColorFilter((ColorFilter)createTintFilter(mTintList, porterDuff$Mode, array));
            }
            else {
                drawable.clearColorFilter();
            }
            if (Build$VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }
    
    private static boolean tintDrawableUsingColorFilter(@NonNull final Context context, @DrawableRes final int n, @NonNull Drawable mutate) {
        PorterDuff$Mode porterDuff$Mode = AppCompatDrawableManager.DEFAULT_MODE;
        int round = -1;
        int n2;
        int n3;
        if (arrayContains(AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n)) {
            n2 = R.attr.colorControlNormal;
            n3 = 1;
        }
        else if (arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, n)) {
            n2 = R.attr.colorControlActivated;
            n3 = 1;
        }
        else if (arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n)) {
            n2 = 16842801;
            n3 = 1;
            porterDuff$Mode = PorterDuff$Mode.MULTIPLY;
        }
        else {
            final int abc_list_divider_mtrl_alpha = R.drawable.abc_list_divider_mtrl_alpha;
            n2 = 0;
            n3 = 0;
            if (n == abc_list_divider_mtrl_alpha) {
                n2 = 16842800;
                n3 = 1;
                round = Math.round(40.8f);
            }
        }
        if (n3 != 0) {
            if (DrawableUtils.canSafelyMutateDrawable(mutate)) {
                mutate = mutate.mutate();
            }
            mutate.setColorFilter((ColorFilter)getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, n2), porterDuff$Mode));
            if (round != -1) {
                mutate.setAlpha(round);
            }
            return true;
        }
        return false;
    }
    
    public Drawable getDrawable(@NonNull final Context context, @DrawableRes final int n) {
        return this.getDrawable(context, n, false);
    }
    
    public Drawable getDrawable(@NonNull final Context context, @DrawableRes final int n, final boolean b) {
        Drawable drawable = this.loadDrawableFromDelegates(context, n);
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(context, n);
        }
        if (drawable != null) {
            drawable = this.tintDrawable(context, n, b, drawable);
        }
        if (drawable != null) {
            DrawableUtils.fixDrawable(drawable);
        }
        return drawable;
    }
    
    public final ColorStateList getTintList(@NonNull final Context context, @DrawableRes final int n) {
        ColorStateList list = this.getTintListFromCache(context, n);
        if (list == null) {
            if (n == R.drawable.abc_edit_text_material) {
                list = this.createEditTextColorStateList(context);
            }
            else if (n == R.drawable.abc_switch_track_mtrl_alpha) {
                list = this.createSwitchTrackColorStateList(context);
            }
            else if (n == R.drawable.abc_switch_thumb_material) {
                list = this.createSwitchThumbColorStateList(context);
            }
            else if (n == R.drawable.abc_btn_default_mtrl_shape || n == R.drawable.abc_btn_borderless_material) {
                list = this.createDefaultButtonColorStateList(context);
            }
            else if (n == R.drawable.abc_btn_colored_material) {
                list = this.createColoredButtonColorStateList(context);
            }
            else if (n == R.drawable.abc_spinner_mtrl_am_alpha || n == R.drawable.abc_spinner_textfield_background_material) {
                list = this.createSpinnerColorStateList(context);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL, n)) {
                list = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST, n)) {
                list = this.createDefaultColorStateList(context);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST, n)) {
                list = this.createCheckableButtonColorStateList(context);
            }
            else if (n == R.drawable.abc_seekbar_thumb_material) {
                list = this.createSeekbarThumbColorStateList(context);
            }
            if (list != null) {
                this.addTintListToCache(context, n, list);
            }
        }
        return list;
    }
    
    final PorterDuff$Mode getTintMode(final int n) {
        final int abc_switch_thumb_material = R.drawable.abc_switch_thumb_material;
        PorterDuff$Mode multiply = null;
        if (n == abc_switch_thumb_material) {
            multiply = PorterDuff$Mode.MULTIPLY;
        }
        return multiply;
    }
    
    public final Drawable onDrawableLoadedFromResources(@NonNull final Context context, @NonNull final TintResources tintResources, @DrawableRes final int n) {
        Drawable drawable = this.loadDrawableFromDelegates(context, n);
        if (drawable == null) {
            drawable = tintResources.superGetDrawable(n);
        }
        if (drawable != null) {
            return this.tintDrawable(context, n, false, drawable);
        }
        return null;
    }
    
    private static class AvdcInflateDelegate implements InflateDelegate
    {
        @Override
        public Drawable createFromXmlInner(@NonNull final Context context, @NonNull final XmlPullParser xmlPullParser, @NonNull final AttributeSet set, @Nullable final Resources$Theme resources$Theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, set, resources$Theme);
            }
            catch (Exception ex) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", (Throwable)ex);
                return null;
            }
        }
    }
    
    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter>
    {
        public ColorFilterLruCache(final int n) {
            super(n);
        }
        
        private static int generateCacheKey(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return 31 * (n + 31) + porterDuff$Mode.hashCode();
        }
        
        PorterDuffColorFilter get(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return this.get(generateCacheKey(n, porterDuff$Mode));
        }
        
        PorterDuffColorFilter put(final int n, final PorterDuff$Mode porterDuff$Mode, final PorterDuffColorFilter porterDuffColorFilter) {
            return this.put(generateCacheKey(n, porterDuff$Mode), porterDuffColorFilter);
        }
    }
    
    private interface InflateDelegate
    {
        Drawable createFromXmlInner(@NonNull final Context p0, @NonNull final XmlPullParser p1, @NonNull final AttributeSet p2, @Nullable final Resources$Theme p3);
    }
    
    private static class VdcInflateDelegate implements InflateDelegate
    {
        @Override
        public Drawable createFromXmlInner(@NonNull final Context context, @NonNull final XmlPullParser xmlPullParser, @NonNull final AttributeSet set, @Nullable final Resources$Theme resources$Theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, set, resources$Theme);
            }
            catch (Exception ex) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", (Throwable)ex);
                return null;
            }
        }
    }
}
