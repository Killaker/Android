package android.support.v4.widget;

import android.view.*;
import android.content.*;

static class SearchViewCompatStubImpl implements SearchViewCompatImpl
{
    @Override
    public CharSequence getQuery(final View view) {
        return null;
    }
    
    @Override
    public boolean isIconified(final View view) {
        return true;
    }
    
    @Override
    public boolean isQueryRefinementEnabled(final View view) {
        return false;
    }
    
    @Override
    public boolean isSubmitButtonEnabled(final View view) {
        return false;
    }
    
    @Override
    public Object newOnCloseListener(final OnCloseListenerCompat onCloseListenerCompat) {
        return null;
    }
    
    @Override
    public Object newOnQueryTextListener(final OnQueryTextListenerCompat onQueryTextListenerCompat) {
        return null;
    }
    
    @Override
    public View newSearchView(final Context context) {
        return null;
    }
    
    @Override
    public void setIconified(final View view, final boolean b) {
    }
    
    @Override
    public void setImeOptions(final View view, final int n) {
    }
    
    @Override
    public void setInputType(final View view, final int n) {
    }
    
    @Override
    public void setMaxWidth(final View view, final int n) {
    }
    
    @Override
    public void setOnCloseListener(final Object o, final Object o2) {
    }
    
    @Override
    public void setOnQueryTextListener(final Object o, final Object o2) {
    }
    
    @Override
    public void setQuery(final View view, final CharSequence charSequence, final boolean b) {
    }
    
    @Override
    public void setQueryHint(final View view, final CharSequence charSequence) {
    }
    
    @Override
    public void setQueryRefinementEnabled(final View view, final boolean b) {
    }
    
    @Override
    public void setSearchableInfo(final View view, final ComponentName componentName) {
    }
    
    @Override
    public void setSubmitButtonEnabled(final View view, final boolean b) {
    }
}
