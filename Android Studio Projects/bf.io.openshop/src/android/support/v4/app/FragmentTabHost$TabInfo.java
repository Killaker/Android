package android.support.v4.app;

import android.os.*;

static final class TabInfo
{
    private final Bundle args;
    private final Class<?> clss;
    private Fragment fragment;
    private final String tag;
    
    TabInfo(final String tag, final Class<?> clss, final Bundle args) {
        this.tag = tag;
        this.clss = clss;
        this.args = args;
    }
}
