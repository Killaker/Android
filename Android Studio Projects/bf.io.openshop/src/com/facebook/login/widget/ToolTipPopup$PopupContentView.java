package com.facebook.login.widget;

import android.widget.*;
import android.content.*;
import com.facebook.*;
import android.view.*;

private class PopupContentView extends FrameLayout
{
    private View bodyFrame;
    private ImageView bottomArrow;
    private ImageView topArrow;
    private ImageView xOut;
    
    public PopupContentView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(R.layout.com_facebook_tooltip_bubble, (ViewGroup)this);
        this.topArrow = (ImageView)this.findViewById(R.id.com_facebook_tooltip_bubble_view_top_pointer);
        this.bottomArrow = (ImageView)this.findViewById(R.id.com_facebook_tooltip_bubble_view_bottom_pointer);
        this.bodyFrame = this.findViewById(R.id.com_facebook_body_frame);
        this.xOut = (ImageView)this.findViewById(R.id.com_facebook_button_xout);
    }
    
    public void showBottomArrow() {
        this.topArrow.setVisibility(4);
        this.bottomArrow.setVisibility(0);
    }
    
    public void showTopArrow() {
        this.topArrow.setVisibility(0);
        this.bottomArrow.setVisibility(4);
    }
}
