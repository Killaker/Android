package android.support.design.widget;

import android.content.*;
import android.support.v7.widget.*;
import android.support.v4.view.*;
import android.support.annotation.*;
import android.graphics.drawable.*;
import android.text.*;
import android.support.v7.app.*;
import android.annotation.*;
import android.view.accessibility.*;
import android.widget.*;
import android.support.v4.widget.*;
import android.support.design.*;
import android.view.*;

class TabView extends LinearLayout implements View$OnLongClickListener
{
    private ImageView mCustomIconView;
    private TextView mCustomTextView;
    private View mCustomView;
    private int mDefaultMaxLines;
    private ImageView mIconView;
    private Tab mTab;
    private TextView mTextView;
    
    public TabView(final Context context) {
        super(context);
        this.mDefaultMaxLines = 2;
        if (TabLayout.access$900(TabLayout.this) != 0) {
            this.setBackgroundDrawable(AppCompatDrawableManager.get().getDrawable(context, TabLayout.access$900(TabLayout.this)));
        }
        ViewCompat.setPaddingRelative((View)this, TabLayout.access$1000(TabLayout.this), TabLayout.access$1100(TabLayout.this), TabLayout.access$1200(TabLayout.this), TabLayout.access$1300(TabLayout.this));
        this.setGravity(17);
        this.setOrientation(1);
        this.setClickable(true);
    }
    
    private float approximateLineWidth(final Layout layout, final int n, final float n2) {
        return layout.getLineWidth(n) * (n2 / layout.getPaint().getTextSize());
    }
    
    private void reset() {
        this.setTab(null);
        this.setSelected(false);
    }
    
    private void setTab(@Nullable final Tab mTab) {
        if (mTab != this.mTab) {
            this.mTab = mTab;
            this.update();
        }
    }
    
    private void updateTextAndIcon(@Nullable final TextView textView, @Nullable final ImageView imageView) {
        Drawable icon;
        if (this.mTab != null) {
            icon = this.mTab.getIcon();
        }
        else {
            icon = null;
        }
        CharSequence text;
        if (this.mTab != null) {
            text = this.mTab.getText();
        }
        else {
            text = null;
        }
        CharSequence contentDescription;
        if (this.mTab != null) {
            contentDescription = this.mTab.getContentDescription();
        }
        else {
            contentDescription = null;
        }
        if (imageView != null) {
            if (icon != null) {
                imageView.setImageDrawable(icon);
                imageView.setVisibility(0);
                this.setVisibility(0);
            }
            else {
                imageView.setVisibility(8);
                imageView.setImageDrawable((Drawable)null);
            }
            imageView.setContentDescription(contentDescription);
        }
        boolean b;
        if (!TextUtils.isEmpty(text)) {
            b = true;
        }
        else {
            b = false;
        }
        if (textView != null) {
            if (b) {
                textView.setText(text);
                textView.setVisibility(0);
                this.setVisibility(0);
            }
            else {
                textView.setVisibility(8);
                textView.setText((CharSequence)null);
            }
            textView.setContentDescription(contentDescription);
        }
        if (imageView != null) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)imageView.getLayoutParams();
            int access$2100 = 0;
            if (b) {
                final int visibility = imageView.getVisibility();
                access$2100 = 0;
                if (visibility == 0) {
                    access$2100 = TabLayout.access$2100(TabLayout.this, 8);
                }
            }
            if (access$2100 != viewGroup$MarginLayoutParams.bottomMargin) {
                viewGroup$MarginLayoutParams.bottomMargin = access$2100;
                imageView.requestLayout();
            }
        }
        if (!b && !TextUtils.isEmpty(contentDescription)) {
            this.setOnLongClickListener((View$OnLongClickListener)this);
            return;
        }
        this.setOnLongClickListener((View$OnLongClickListener)null);
        this.setLongClickable(false);
    }
    
    public Tab getTab() {
        return this.mTab;
    }
    
    @TargetApi(14)
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)ActionBar.Tab.class.getName());
    }
    
    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((CharSequence)ActionBar.Tab.class.getName());
    }
    
    public boolean onLongClick(final View view) {
        final int[] array = new int[2];
        this.getLocationOnScreen(array);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        final Toast text = Toast.makeText(context, this.mTab.getContentDescription(), 0);
        text.setGravity(49, array[0] + width / 2 - widthPixels / 2, height);
        text.show();
        return true;
    }
    
    public void onMeasure(final int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n);
        final int mode = View$MeasureSpec.getMode(n);
        final int access$1400 = TabLayout.access$1400(TabLayout.this);
        int measureSpec;
        if (access$1400 > 0 && (mode == 0 || size > access$1400)) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(TabLayout.access$1500(TabLayout.this), Integer.MIN_VALUE);
        }
        else {
            measureSpec = n;
        }
        super.onMeasure(measureSpec, n2);
        if (this.mTextView != null) {
            this.getResources();
            float n3 = TabLayout.access$1600(TabLayout.this);
            int mDefaultMaxLines = this.mDefaultMaxLines;
            if (this.mIconView != null && this.mIconView.getVisibility() == 0) {
                mDefaultMaxLines = 1;
            }
            else if (this.mTextView != null && this.mTextView.getLineCount() > 1) {
                n3 = TabLayout.access$1700(TabLayout.this);
            }
            final float textSize = this.mTextView.getTextSize();
            final int lineCount = this.mTextView.getLineCount();
            final int maxLines = TextViewCompat.getMaxLines(this.mTextView);
            if (n3 != textSize || (maxLines >= 0 && mDefaultMaxLines != maxLines)) {
                boolean b = true;
                if (TabLayout.access$1800(TabLayout.this) == 1 && n3 > textSize && lineCount == 1) {
                    final Layout layout = this.mTextView.getLayout();
                    if (layout == null || this.approximateLineWidth(layout, 0, n3) > layout.getWidth()) {
                        b = false;
                    }
                }
                if (b) {
                    this.mTextView.setTextSize(0, n3);
                    this.mTextView.setMaxLines(mDefaultMaxLines);
                    super.onMeasure(measureSpec, n2);
                }
            }
        }
    }
    
    public boolean performClick() {
        boolean performClick = super.performClick();
        if (this.mTab != null) {
            this.mTab.select();
            performClick = true;
        }
        return performClick;
    }
    
    public void setSelected(final boolean selected) {
        boolean b;
        if (this.isSelected() != selected) {
            b = true;
        }
        else {
            b = false;
        }
        super.setSelected(selected);
        if (b && selected) {
            this.sendAccessibilityEvent(4);
            if (this.mTextView != null) {
                this.mTextView.setSelected(selected);
            }
            if (this.mIconView != null) {
                this.mIconView.setSelected(selected);
            }
        }
    }
    
    final void update() {
        final Tab mTab = this.mTab;
        View customView;
        if (mTab != null) {
            customView = mTab.getCustomView();
        }
        else {
            customView = null;
        }
        if (customView != null) {
            final ViewParent parent = customView.getParent();
            if (parent != this) {
                if (parent != null) {
                    ((ViewGroup)parent).removeView(customView);
                }
                this.addView(customView);
            }
            this.mCustomView = customView;
            if (this.mTextView != null) {
                this.mTextView.setVisibility(8);
            }
            if (this.mIconView != null) {
                this.mIconView.setVisibility(8);
                this.mIconView.setImageDrawable((Drawable)null);
            }
            this.mCustomTextView = (TextView)customView.findViewById(16908308);
            if (this.mCustomTextView != null) {
                this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mCustomTextView);
            }
            this.mCustomIconView = (ImageView)customView.findViewById(16908294);
        }
        else {
            if (this.mCustomView != null) {
                this.removeView(this.mCustomView);
                this.mCustomView = null;
            }
            this.mCustomTextView = null;
            this.mCustomIconView = null;
        }
        if (this.mCustomView == null) {
            if (this.mIconView == null) {
                final ImageView mIconView = (ImageView)LayoutInflater.from(this.getContext()).inflate(R.layout.design_layout_tab_icon, (ViewGroup)this, false);
                this.addView((View)mIconView, 0);
                this.mIconView = mIconView;
            }
            if (this.mTextView == null) {
                final TextView mTextView = (TextView)LayoutInflater.from(this.getContext()).inflate(R.layout.design_layout_tab_text, (ViewGroup)this, false);
                this.addView((View)mTextView);
                this.mTextView = mTextView;
                this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mTextView);
            }
            this.mTextView.setTextAppearance(this.getContext(), TabLayout.access$1900(TabLayout.this));
            if (TabLayout.access$2000(TabLayout.this) != null) {
                this.mTextView.setTextColor(TabLayout.access$2000(TabLayout.this));
            }
            this.updateTextAndIcon(this.mTextView, this.mIconView);
        }
        else if (this.mCustomTextView != null || this.mCustomIconView != null) {
            this.updateTextAndIcon(this.mCustomTextView, this.mCustomIconView);
        }
    }
}
