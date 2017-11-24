package android.support.v4.view;

import android.view.*;

class PagerTabStrip$2 implements View$OnClickListener {
    public void onClick(final View view) {
        PagerTabStrip.this.mPager.setCurrentItem(1 + PagerTabStrip.this.mPager.getCurrentItem());
    }
}