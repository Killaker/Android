package android.support.v7.app;

import android.graphics.drawable.*;
import android.view.*;
import android.support.v7.widget.*;

public class TabImpl extends Tab
{
    private TabListener mCallback;
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    private int mPosition;
    private Object mTag;
    private CharSequence mText;
    
    public TabImpl() {
        this.mPosition = -1;
    }
    
    public TabListener getCallback() {
        return this.mCallback;
    }
    
    @Override
    public CharSequence getContentDescription() {
        return this.mContentDesc;
    }
    
    @Override
    public View getCustomView() {
        return this.mCustomView;
    }
    
    @Override
    public Drawable getIcon() {
        return this.mIcon;
    }
    
    @Override
    public int getPosition() {
        return this.mPosition;
    }
    
    @Override
    public Object getTag() {
        return this.mTag;
    }
    
    @Override
    public CharSequence getText() {
        return this.mText;
    }
    
    @Override
    public void select() {
        WindowDecorActionBar.this.selectTab(this);
    }
    
    @Override
    public Tab setContentDescription(final int n) {
        return this.setContentDescription(WindowDecorActionBar.access$1000(WindowDecorActionBar.this).getResources().getText(n));
    }
    
    @Override
    public Tab setContentDescription(final CharSequence mContentDesc) {
        this.mContentDesc = mContentDesc;
        if (this.mPosition >= 0) {
            WindowDecorActionBar.access$1100(WindowDecorActionBar.this).updateTab(this.mPosition);
        }
        return this;
    }
    
    @Override
    public Tab setCustomView(final int n) {
        return this.setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(n, (ViewGroup)null));
    }
    
    @Override
    public Tab setCustomView(final View mCustomView) {
        this.mCustomView = mCustomView;
        if (this.mPosition >= 0) {
            WindowDecorActionBar.access$1100(WindowDecorActionBar.this).updateTab(this.mPosition);
        }
        return this;
    }
    
    @Override
    public Tab setIcon(final int n) {
        return this.setIcon(AppCompatDrawableManager.get().getDrawable(WindowDecorActionBar.access$1000(WindowDecorActionBar.this), n));
    }
    
    @Override
    public Tab setIcon(final Drawable mIcon) {
        this.mIcon = mIcon;
        if (this.mPosition >= 0) {
            WindowDecorActionBar.access$1100(WindowDecorActionBar.this).updateTab(this.mPosition);
        }
        return this;
    }
    
    public void setPosition(final int mPosition) {
        this.mPosition = mPosition;
    }
    
    @Override
    public Tab setTabListener(final TabListener mCallback) {
        this.mCallback = mCallback;
        return this;
    }
    
    @Override
    public Tab setTag(final Object mTag) {
        this.mTag = mTag;
        return this;
    }
    
    @Override
    public Tab setText(final int n) {
        return this.setText(WindowDecorActionBar.access$1000(WindowDecorActionBar.this).getResources().getText(n));
    }
    
    @Override
    public Tab setText(final CharSequence mText) {
        this.mText = mText;
        if (this.mPosition >= 0) {
            WindowDecorActionBar.access$1100(WindowDecorActionBar.this).updateTab(this.mPosition);
        }
        return this;
    }
}
