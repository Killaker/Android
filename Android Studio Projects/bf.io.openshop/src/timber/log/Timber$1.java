package timber.log;

import java.util.*;

static final class Timber$1 extends Tree {
    @Override
    public void d(final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).d(s, array);
        }
    }
    
    @Override
    public void d(final Throwable t, final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).d(t, s, array);
        }
    }
    
    @Override
    public void e(final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).e(s, array);
        }
    }
    
    @Override
    public void e(final Throwable t, final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).e(t, s, array);
        }
    }
    
    @Override
    public void i(final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).i(s, array);
        }
    }
    
    @Override
    public void i(final Throwable t, final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).i(t, s, array);
        }
    }
    
    @Override
    protected void log(final int n, final String s, final String s2, final Throwable t) {
        throw new AssertionError((Object)"Missing override for log method.");
    }
    
    @Override
    public void v(final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).v(s, array);
        }
    }
    
    @Override
    public void v(final Throwable t, final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).v(t, s, array);
        }
    }
    
    @Override
    public void w(final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).w(s, array);
        }
    }
    
    @Override
    public void w(final Throwable t, final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).w(t, s, array);
        }
    }
    
    @Override
    public void wtf(final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).wtf(s, array);
        }
    }
    
    @Override
    public void wtf(final Throwable t, final String s, final Object... array) {
        final List access$100 = Timber.access$100();
        for (int i = 0; i < access$100.size(); ++i) {
            access$100.get(i).wtf(t, s, array);
        }
    }
}