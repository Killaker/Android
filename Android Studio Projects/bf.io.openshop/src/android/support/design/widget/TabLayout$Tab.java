package android.support.design.widget;

import android.graphics.drawable.*;
import android.view.*;
import android.support.annotation.*;
import android.support.v7.widget.*;

public static final class Tab
{
    public static final int INVALID_POSITION = -1;
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    private TabLayout mParent;
    private int mPosition;
    private Object mTag;
    private CharSequence mText;
    private TabView mView;
    
    private Tab() {
        this.mPosition = -1;
    }
    
    private void reset() {
        this.mParent = null;
        this.mView = null;
        this.mTag = null;
        this.mIcon = null;
        this.mText = null;
        this.mContentDesc = null;
        this.mPosition = -1;
        this.mCustomView = null;
    }
    
    private void updateView() {
        if (this.mView != null) {
            this.mView.update();
        }
    }
    
    @Nullable
    public CharSequence getContentDescription() {
        return this.mContentDesc;
    }
    
    @Nullable
    public View getCustomView() {
        return this.mCustomView;
    }
    
    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }
    
    public int getPosition() {
        return this.mPosition;
    }
    
    @Nullable
    public Object getTag() {
        return this.mTag;
    }
    
    @Nullable
    public CharSequence getText() {
        return this.mText;
    }
    
    public boolean isSelected() {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.mParent.getSelectedTabPosition() == this.mPosition;
    }
    
    public void select() {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        this.mParent.selectTab(this);
    }
    
    @NonNull
    public Tab setContentDescription(@StringRes final int n) {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.setContentDescription(this.mParent.getResources().getText(n));
    }
    
    @NonNull
    public Tab setContentDescription(@Nullable final CharSequence mContentDesc) {
        this.mContentDesc = mContentDesc;
        this.updateView();
        return this;
    }
    
    @NonNull
    public Tab setCustomView(@LayoutRes final int n) {
        return this.setCustomView(LayoutInflater.from(this.mView.getContext()).inflate(n, (ViewGroup)this.mView, false));
    }
    
    @NonNull
    public Tab setCustomView(@Nullable final View mCustomView) {
        this.mCustomView = mCustomView;
        this.updateView();
        return this;
    }
    
    @NonNull
    public Tab setIcon(@DrawableRes final int n) {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.setIcon(AppCompatDrawableManager.get().getDrawable(this.mParent.getContext(), n));
    }
    
    @NonNull
    public Tab setIcon(@Nullable final Drawable mIcon) {
        this.mIcon = mIcon;
        this.updateView();
        return this;
    }
    
    void setPosition(final int mPosition) {
        this.mPosition = mPosition;
    }
    
    @NonNull
    public Tab setTag(@Nullable final Object mTag) {
        this.mTag = mTag;
        return this;
    }
    
    @NonNull
    public Tab setText(@StringRes final int n) {
        if (this.mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return this.setText(this.mParent.getResources().getText(n));
    }
    
    @NonNull
    public Tab setText(@Nullable final CharSequence mText) {
        this.mText = mText;
        this.updateView();
        return this;
    }
}
