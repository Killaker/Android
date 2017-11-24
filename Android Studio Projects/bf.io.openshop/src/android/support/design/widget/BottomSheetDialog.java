package android.support.design.widget;

import android.support.v7.app.*;
import android.content.*;
import android.util.*;
import android.support.design.*;
import android.widget.*;
import android.view.*;
import android.os.*;
import android.support.annotation.*;

public class BottomSheetDialog extends AppCompatDialog
{
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback;
    
    public BottomSheetDialog(@NonNull final Context context) {
        this(context, 0);
    }
    
    public BottomSheetDialog(@NonNull final Context context, @StyleRes final int n) {
        super(context, getThemeResId(context, n));
        this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onSlide(@NonNull final View view, final float n) {
            }
            
            @Override
            public void onStateChanged(@NonNull final View view, final int n) {
                if (n == 5) {
                    BottomSheetDialog.this.dismiss();
                }
            }
        };
        this.supportRequestWindowFeature(1);
    }
    
    protected BottomSheetDialog(@NonNull final Context context, final boolean b, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        super(context, b, dialogInterface$OnCancelListener);
        this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onSlide(@NonNull final View view, final float n) {
            }
            
            @Override
            public void onStateChanged(@NonNull final View view, final int n) {
                if (n == 5) {
                    BottomSheetDialog.this.dismiss();
                }
            }
        };
        this.supportRequestWindowFeature(1);
    }
    
    private static int getThemeResId(final Context context, int resourceId) {
        if (resourceId == 0) {
            final TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, typedValue, true)) {
                return R.style.Theme_Design_Light_BottomSheetDialog;
            }
            resourceId = typedValue.resourceId;
        }
        return resourceId;
    }
    
    private boolean shouldWindowCloseOnTouchOutside() {
        if (Build$VERSION.SDK_INT >= 11) {
            final TypedValue typedValue = new TypedValue();
            if (!this.getContext().getTheme().resolveAttribute(16843611, typedValue, true)) {
                return false;
            }
            if (typedValue.data == 0) {
                return false;
            }
        }
        return true;
    }
    
    private View wrapInBottomSheet(final int n, View inflate, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)View.inflate(this.getContext(), R.layout.design_bottom_sheet_dialog, (ViewGroup)null);
        if (n != 0 && inflate == null) {
            inflate = this.getLayoutInflater().inflate(n, (ViewGroup)coordinatorLayout, false);
        }
        final FrameLayout frameLayout = (FrameLayout)coordinatorLayout.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(frameLayout).setBottomSheetCallback(this.mBottomSheetCallback);
        if (viewGroup$LayoutParams == null) {
            frameLayout.addView(inflate);
        }
        else {
            frameLayout.addView(inflate, viewGroup$LayoutParams);
        }
        if (this.shouldWindowCloseOnTouchOutside()) {
            coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (BottomSheetDialog.this.isShowing()) {
                        BottomSheetDialog.this.cancel();
                    }
                }
            });
        }
        return (View)coordinatorLayout;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().setLayout(-1, -1);
    }
    
    @Override
    public void setContentView(@LayoutRes final int n) {
        super.setContentView(this.wrapInBottomSheet(n, null, null));
    }
    
    @Override
    public void setContentView(final View view) {
        super.setContentView(this.wrapInBottomSheet(0, view, null));
    }
    
    @Override
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(this.wrapInBottomSheet(0, view, viewGroup$LayoutParams));
    }
}
