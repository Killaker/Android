package android.support.v7.widget;

import android.graphics.*;
import android.content.*;
import android.util.*;
import android.support.v4.view.*;
import android.graphics.drawable.*;
import android.widget.*;
import android.view.*;

private class DropdownPopup extends ListPopupWindow
{
    private ListAdapter mAdapter;
    private CharSequence mHintText;
    private final Rect mVisibleRect;
    
    public DropdownPopup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mVisibleRect = new Rect();
        this.setAnchorView((View)AppCompatSpinner.this);
        this.setModal(true);
        this.setPromptPosition(0);
        this.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
                AppCompatSpinner.this.setSelection(selection);
                if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                    AppCompatSpinner.this.performItemClick(view, selection, DropdownPopup.this.mAdapter.getItemId(selection));
                }
                DropdownPopup.this.dismiss();
            }
        });
    }
    
    private boolean isVisibleToUser(final View view) {
        return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
    }
    
    void computeContentWidth() {
        final Drawable background = this.getBackground();
        int right;
        if (background != null) {
            background.getPadding(AppCompatSpinner.access$300(AppCompatSpinner.this));
            if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
                right = AppCompatSpinner.access$300(AppCompatSpinner.this).right;
            }
            else {
                right = -AppCompatSpinner.access$300(AppCompatSpinner.this).left;
            }
        }
        else {
            final Rect access$300 = AppCompatSpinner.access$300(AppCompatSpinner.this);
            AppCompatSpinner.access$300(AppCompatSpinner.this).right = 0;
            access$300.left = 0;
            right = 0;
        }
        final int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
        final int paddingRight = AppCompatSpinner.this.getPaddingRight();
        final int width = AppCompatSpinner.this.getWidth();
        if (AppCompatSpinner.access$400(AppCompatSpinner.this) == -2) {
            int access$301 = AppCompatSpinner.access$500(AppCompatSpinner.this, (SpinnerAdapter)this.mAdapter, this.getBackground());
            final int n = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.access$300(AppCompatSpinner.this).left - AppCompatSpinner.access$300(AppCompatSpinner.this).right;
            if (access$301 > n) {
                access$301 = n;
            }
            this.setContentWidth(Math.max(access$301, width - paddingLeft - paddingRight));
        }
        else if (AppCompatSpinner.access$400(AppCompatSpinner.this) == -1) {
            this.setContentWidth(width - paddingLeft - paddingRight);
        }
        else {
            this.setContentWidth(AppCompatSpinner.access$400(AppCompatSpinner.this));
        }
        int horizontalOffset;
        if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
            horizontalOffset = right + (width - paddingRight - this.getWidth());
        }
        else {
            horizontalOffset = right + paddingLeft;
        }
        this.setHorizontalOffset(horizontalOffset);
    }
    
    public CharSequence getHintText() {
        return this.mHintText;
    }
    
    @Override
    public void setAdapter(final ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.mAdapter = listAdapter;
    }
    
    public void setPromptText(final CharSequence mHintText) {
        this.mHintText = mHintText;
    }
    
    @Override
    public void show() {
        final boolean showing = this.isShowing();
        this.computeContentWidth();
        this.setInputMethodMode(2);
        super.show();
        this.getListView().setChoiceMode(1);
        this.setSelection(AppCompatSpinner.this.getSelectedItemPosition());
        if (!showing) {
            final ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if (!DropdownPopup.this.isVisibleToUser((View)AppCompatSpinner.this)) {
                            DropdownPopup.this.dismiss();
                            return;
                        }
                        DropdownPopup.this.computeContentWidth();
                        DropdownPopup.this.show();
                    }
                };
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)viewTreeObserver$OnGlobalLayoutListener);
                this.setOnDismissListener((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener() {
                    public void onDismiss() {
                        final ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                        if (viewTreeObserver != null) {
                            viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
                        }
                    }
                });
            }
        }
    }
}
