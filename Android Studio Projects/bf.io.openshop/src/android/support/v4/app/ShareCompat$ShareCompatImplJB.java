package android.support.v4.app;

import android.view.*;

static class ShareCompatImplJB extends ShareCompatImplICS
{
    @Override
    public String escapeHtml(final CharSequence charSequence) {
        return ShareCompatJB.escapeHtml(charSequence);
    }
    
    @Override
    boolean shouldAddChooserIntent(final MenuItem menuItem) {
        return false;
    }
}
