package android.support.v4.app;

import android.graphics.*;
import android.app.*;
import android.os.*;
import java.util.*;

public static final class WearableExtender implements NotificationCompat.Extender
{
    private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    private static final int DEFAULT_FLAGS = 1;
    private static final int DEFAULT_GRAVITY = 80;
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    private static final int FLAG_HINT_HIDE_ICON = 2;
    private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    private static final int FLAG_START_SCROLL_BOTTOM = 8;
    private static final String KEY_ACTIONS = "actions";
    private static final String KEY_BACKGROUND = "background";
    private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    private static final String KEY_CONTENT_ICON = "contentIcon";
    private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    private static final String KEY_DISPLAY_INTENT = "displayIntent";
    private static final String KEY_FLAGS = "flags";
    private static final String KEY_GRAVITY = "gravity";
    private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    private static final String KEY_PAGES = "pages";
    public static final int SCREEN_TIMEOUT_LONG = -1;
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    public static final int SIZE_DEFAULT = 0;
    public static final int SIZE_FULL_SCREEN = 5;
    public static final int SIZE_LARGE = 4;
    public static final int SIZE_MEDIUM = 3;
    public static final int SIZE_SMALL = 2;
    public static final int SIZE_XSMALL = 1;
    public static final int UNSET_ACTION_INDEX = -1;
    private ArrayList<Action> mActions;
    private Bitmap mBackground;
    private int mContentActionIndex;
    private int mContentIcon;
    private int mContentIconGravity;
    private int mCustomContentHeight;
    private int mCustomSizePreset;
    private PendingIntent mDisplayIntent;
    private int mFlags;
    private int mGravity;
    private int mHintScreenTimeout;
    private ArrayList<Notification> mPages;
    
    public WearableExtender() {
        this.mActions = new ArrayList<Action>();
        this.mFlags = 1;
        this.mPages = new ArrayList<Notification>();
        this.mContentIconGravity = 8388613;
        this.mContentActionIndex = -1;
        this.mCustomSizePreset = 0;
        this.mGravity = 80;
    }
    
    public WearableExtender(final Notification notification) {
        this.mActions = new ArrayList<Action>();
        this.mFlags = 1;
        this.mPages = new ArrayList<Notification>();
        this.mContentIconGravity = 8388613;
        this.mContentActionIndex = -1;
        this.mCustomSizePreset = 0;
        this.mGravity = 80;
        final Bundle extras = NotificationCompat.getExtras(notification);
        Bundle bundle;
        if (extras != null) {
            bundle = extras.getBundle("android.wearable.EXTENSIONS");
        }
        else {
            bundle = null;
        }
        if (bundle != null) {
            final Action[] actionsFromParcelableArrayList = NotificationCompat.access$200().getActionsFromParcelableArrayList(bundle.getParcelableArrayList("actions"));
            if (actionsFromParcelableArrayList != null) {
                Collections.addAll(this.mActions, actionsFromParcelableArrayList);
            }
            this.mFlags = bundle.getInt("flags", 1);
            this.mDisplayIntent = (PendingIntent)bundle.getParcelable("displayIntent");
            final Notification[] access$500 = NotificationCompat.access$500(bundle, "pages");
            if (access$500 != null) {
                Collections.addAll(this.mPages, access$500);
            }
            this.mBackground = (Bitmap)bundle.getParcelable("background");
            this.mContentIcon = bundle.getInt("contentIcon");
            this.mContentIconGravity = bundle.getInt("contentIconGravity", 8388613);
            this.mContentActionIndex = bundle.getInt("contentActionIndex", -1);
            this.mCustomSizePreset = bundle.getInt("customSizePreset", 0);
            this.mCustomContentHeight = bundle.getInt("customContentHeight");
            this.mGravity = bundle.getInt("gravity", 80);
            this.mHintScreenTimeout = bundle.getInt("hintScreenTimeout");
        }
    }
    
    private void setFlag(final int n, final boolean b) {
        if (b) {
            this.mFlags |= n;
            return;
        }
        this.mFlags &= ~n;
    }
    
    public WearableExtender addAction(final Action action) {
        this.mActions.add(action);
        return this;
    }
    
    public WearableExtender addActions(final List<Action> list) {
        this.mActions.addAll(list);
        return this;
    }
    
    public WearableExtender addPage(final Notification notification) {
        this.mPages.add(notification);
        return this;
    }
    
    public WearableExtender addPages(final List<Notification> list) {
        this.mPages.addAll(list);
        return this;
    }
    
    public WearableExtender clearActions() {
        this.mActions.clear();
        return this;
    }
    
    public WearableExtender clearPages() {
        this.mPages.clear();
        return this;
    }
    
    public WearableExtender clone() {
        final WearableExtender wearableExtender = new WearableExtender();
        wearableExtender.mActions = new ArrayList<Action>(this.mActions);
        wearableExtender.mFlags = this.mFlags;
        wearableExtender.mDisplayIntent = this.mDisplayIntent;
        wearableExtender.mPages = new ArrayList<Notification>(this.mPages);
        wearableExtender.mBackground = this.mBackground;
        wearableExtender.mContentIcon = this.mContentIcon;
        wearableExtender.mContentIconGravity = this.mContentIconGravity;
        wearableExtender.mContentActionIndex = this.mContentActionIndex;
        wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
        wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
        wearableExtender.mGravity = this.mGravity;
        wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
        return wearableExtender;
    }
    
    @Override
    public NotificationCompat.Builder extend(final NotificationCompat.Builder builder) {
        final Bundle bundle = new Bundle();
        if (!this.mActions.isEmpty()) {
            bundle.putParcelableArrayList("actions", (ArrayList)NotificationCompat.access$200().getParcelableArrayListForActions(this.mActions.toArray(new Action[this.mActions.size()])));
        }
        if (this.mFlags != 1) {
            bundle.putInt("flags", this.mFlags);
        }
        if (this.mDisplayIntent != null) {
            bundle.putParcelable("displayIntent", (Parcelable)this.mDisplayIntent);
        }
        if (!this.mPages.isEmpty()) {
            bundle.putParcelableArray("pages", (Parcelable[])this.mPages.toArray((Parcelable[])new Notification[this.mPages.size()]));
        }
        if (this.mBackground != null) {
            bundle.putParcelable("background", (Parcelable)this.mBackground);
        }
        if (this.mContentIcon != 0) {
            bundle.putInt("contentIcon", this.mContentIcon);
        }
        if (this.mContentIconGravity != 8388613) {
            bundle.putInt("contentIconGravity", this.mContentIconGravity);
        }
        if (this.mContentActionIndex != -1) {
            bundle.putInt("contentActionIndex", this.mContentActionIndex);
        }
        if (this.mCustomSizePreset != 0) {
            bundle.putInt("customSizePreset", this.mCustomSizePreset);
        }
        if (this.mCustomContentHeight != 0) {
            bundle.putInt("customContentHeight", this.mCustomContentHeight);
        }
        if (this.mGravity != 80) {
            bundle.putInt("gravity", this.mGravity);
        }
        if (this.mHintScreenTimeout != 0) {
            bundle.putInt("hintScreenTimeout", this.mHintScreenTimeout);
        }
        builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
        return builder;
    }
    
    public List<Action> getActions() {
        return this.mActions;
    }
    
    public Bitmap getBackground() {
        return this.mBackground;
    }
    
    public int getContentAction() {
        return this.mContentActionIndex;
    }
    
    public int getContentIcon() {
        return this.mContentIcon;
    }
    
    public int getContentIconGravity() {
        return this.mContentIconGravity;
    }
    
    public boolean getContentIntentAvailableOffline() {
        return (0x1 & this.mFlags) != 0x0;
    }
    
    public int getCustomContentHeight() {
        return this.mCustomContentHeight;
    }
    
    public int getCustomSizePreset() {
        return this.mCustomSizePreset;
    }
    
    public PendingIntent getDisplayIntent() {
        return this.mDisplayIntent;
    }
    
    public int getGravity() {
        return this.mGravity;
    }
    
    public boolean getHintAvoidBackgroundClipping() {
        return (0x10 & this.mFlags) != 0x0;
    }
    
    public boolean getHintHideIcon() {
        return (0x2 & this.mFlags) != 0x0;
    }
    
    public int getHintScreenTimeout() {
        return this.mHintScreenTimeout;
    }
    
    public boolean getHintShowBackgroundOnly() {
        return (0x4 & this.mFlags) != 0x0;
    }
    
    public List<Notification> getPages() {
        return this.mPages;
    }
    
    public boolean getStartScrollBottom() {
        return (0x8 & this.mFlags) != 0x0;
    }
    
    public WearableExtender setBackground(final Bitmap mBackground) {
        this.mBackground = mBackground;
        return this;
    }
    
    public WearableExtender setContentAction(final int mContentActionIndex) {
        this.mContentActionIndex = mContentActionIndex;
        return this;
    }
    
    public WearableExtender setContentIcon(final int mContentIcon) {
        this.mContentIcon = mContentIcon;
        return this;
    }
    
    public WearableExtender setContentIconGravity(final int mContentIconGravity) {
        this.mContentIconGravity = mContentIconGravity;
        return this;
    }
    
    public WearableExtender setContentIntentAvailableOffline(final boolean b) {
        this.setFlag(1, b);
        return this;
    }
    
    public WearableExtender setCustomContentHeight(final int mCustomContentHeight) {
        this.mCustomContentHeight = mCustomContentHeight;
        return this;
    }
    
    public WearableExtender setCustomSizePreset(final int mCustomSizePreset) {
        this.mCustomSizePreset = mCustomSizePreset;
        return this;
    }
    
    public WearableExtender setDisplayIntent(final PendingIntent mDisplayIntent) {
        this.mDisplayIntent = mDisplayIntent;
        return this;
    }
    
    public WearableExtender setGravity(final int mGravity) {
        this.mGravity = mGravity;
        return this;
    }
    
    public WearableExtender setHintAvoidBackgroundClipping(final boolean b) {
        this.setFlag(16, b);
        return this;
    }
    
    public WearableExtender setHintHideIcon(final boolean b) {
        this.setFlag(2, b);
        return this;
    }
    
    public WearableExtender setHintScreenTimeout(final int mHintScreenTimeout) {
        this.mHintScreenTimeout = mHintScreenTimeout;
        return this;
    }
    
    public WearableExtender setHintShowBackgroundOnly(final boolean b) {
        this.setFlag(4, b);
        return this;
    }
    
    public WearableExtender setStartScrollBottom(final boolean b) {
        this.setFlag(8, b);
        return this;
    }
}
