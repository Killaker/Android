package android.support.v4.widget;

import android.view.*;
import android.content.*;

interface SearchViewCompatImpl
{
    CharSequence getQuery(final View p0);
    
    boolean isIconified(final View p0);
    
    boolean isQueryRefinementEnabled(final View p0);
    
    boolean isSubmitButtonEnabled(final View p0);
    
    Object newOnCloseListener(final OnCloseListenerCompat p0);
    
    Object newOnQueryTextListener(final OnQueryTextListenerCompat p0);
    
    View newSearchView(final Context p0);
    
    void setIconified(final View p0, final boolean p1);
    
    void setImeOptions(final View p0, final int p1);
    
    void setInputType(final View p0, final int p1);
    
    void setMaxWidth(final View p0, final int p1);
    
    void setOnCloseListener(final Object p0, final Object p1);
    
    void setOnQueryTextListener(final Object p0, final Object p1);
    
    void setQuery(final View p0, final CharSequence p1, final boolean p2);
    
    void setQueryHint(final View p0, final CharSequence p1);
    
    void setQueryRefinementEnabled(final View p0, final boolean p1);
    
    void setSearchableInfo(final View p0, final ComponentName p1);
    
    void setSubmitButtonEnabled(final View p0, final boolean p1);
}
