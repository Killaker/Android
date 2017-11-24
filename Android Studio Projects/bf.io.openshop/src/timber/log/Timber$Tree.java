package timber.log;

import android.util.*;

public abstract static class Tree
{
    private final ThreadLocal<String> explicitTag;
    
    public Tree() {
        this.explicitTag = new ThreadLocal<String>();
    }
    
    private void prepareLog(final int n, final Throwable t, String s, final Object... array) {
        if (this.isLoggable(n)) {
            if (s != null && s.length() == 0) {
                s = null;
            }
            if (s == null) {
                if (t == null) {
                    return;
                }
                s = Log.getStackTraceString(t);
            }
            else {
                if (array.length > 0) {
                    s = String.format(s, array);
                }
                if (t != null) {
                    s = s + "\n" + Log.getStackTraceString(t);
                }
            }
            this.log(n, this.getTag(), s, t);
        }
    }
    
    public void d(final String s, final Object... array) {
        this.prepareLog(3, null, s, array);
    }
    
    public void d(final Throwable t, final String s, final Object... array) {
        this.prepareLog(3, t, s, array);
    }
    
    public void e(final String s, final Object... array) {
        this.prepareLog(6, null, s, array);
    }
    
    public void e(final Throwable t, final String s, final Object... array) {
        this.prepareLog(6, t, s, array);
    }
    
    String getTag() {
        final String s = this.explicitTag.get();
        if (s != null) {
            this.explicitTag.remove();
        }
        return s;
    }
    
    public void i(final String s, final Object... array) {
        this.prepareLog(4, null, s, array);
    }
    
    public void i(final Throwable t, final String s, final Object... array) {
        this.prepareLog(4, t, s, array);
    }
    
    protected boolean isLoggable(final int n) {
        return true;
    }
    
    protected abstract void log(final int p0, final String p1, final String p2, final Throwable p3);
    
    public void v(final String s, final Object... array) {
        this.prepareLog(2, null, s, array);
    }
    
    public void v(final Throwable t, final String s, final Object... array) {
        this.prepareLog(2, t, s, array);
    }
    
    public void w(final String s, final Object... array) {
        this.prepareLog(5, null, s, array);
    }
    
    public void w(final Throwable t, final String s, final Object... array) {
        this.prepareLog(5, t, s, array);
    }
    
    public void wtf(final String s, final Object... array) {
        this.prepareLog(7, null, s, array);
    }
    
    public void wtf(final Throwable t, final String s, final Object... array) {
        this.prepareLog(7, t, s, array);
    }
}
