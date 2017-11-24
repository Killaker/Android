package timber.log;

import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import android.util.*;

public final class Timber
{
    private static final List<Tree> FOREST;
    private static final Tree TREE_OF_SOULS;
    
    static {
        FOREST = new CopyOnWriteArrayList<Tree>();
        TREE_OF_SOULS = (Tree)new Tree() {
            @Override
            public void d(final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).d(s, array);
                }
            }
            
            @Override
            public void d(final Throwable t, final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).d(t, s, array);
                }
            }
            
            @Override
            public void e(final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).e(s, array);
                }
            }
            
            @Override
            public void e(final Throwable t, final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).e(t, s, array);
                }
            }
            
            @Override
            public void i(final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).i(s, array);
                }
            }
            
            @Override
            public void i(final Throwable t, final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
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
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).v(s, array);
                }
            }
            
            @Override
            public void v(final Throwable t, final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).v(t, s, array);
                }
            }
            
            @Override
            public void w(final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).w(s, array);
                }
            }
            
            @Override
            public void w(final Throwable t, final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).w(t, s, array);
                }
            }
            
            @Override
            public void wtf(final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).wtf(s, array);
                }
            }
            
            @Override
            public void wtf(final Throwable t, final String s, final Object... array) {
                final List access$100 = Timber.FOREST;
                for (int i = 0; i < access$100.size(); ++i) {
                    access$100.get(i).wtf(t, s, array);
                }
            }
        };
    }
    
    private Timber() {
        throw new AssertionError((Object)"No instances.");
    }
    
    public static Tree asTree() {
        return Timber.TREE_OF_SOULS;
    }
    
    public static void d(final String s, final Object... array) {
        Timber.TREE_OF_SOULS.d(s, array);
    }
    
    public static void d(final Throwable t, final String s, final Object... array) {
        Timber.TREE_OF_SOULS.d(t, s, array);
    }
    
    public static void e(final String s, final Object... array) {
        Timber.TREE_OF_SOULS.e(s, array);
    }
    
    public static void e(final Throwable t, final String s, final Object... array) {
        Timber.TREE_OF_SOULS.e(t, s, array);
    }
    
    public static void i(final String s, final Object... array) {
        Timber.TREE_OF_SOULS.i(s, array);
    }
    
    public static void i(final Throwable t, final String s, final Object... array) {
        Timber.TREE_OF_SOULS.i(t, s, array);
    }
    
    public static void plant(final Tree tree) {
        if (tree == null) {
            throw new NullPointerException("tree == null");
        }
        if (tree == Timber.TREE_OF_SOULS) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        }
        Timber.FOREST.add(tree);
    }
    
    public static Tree tag(final String s) {
        final List<Tree> forest = Timber.FOREST;
        for (int i = 0; i < forest.size(); ++i) {
            forest.get(i).explicitTag.set(s);
        }
        return Timber.TREE_OF_SOULS;
    }
    
    public static void uproot(final Tree tree) {
        if (!Timber.FOREST.remove(tree)) {
            throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
        }
    }
    
    public static void uprootAll() {
        Timber.FOREST.clear();
    }
    
    public static void v(final String s, final Object... array) {
        Timber.TREE_OF_SOULS.v(s, array);
    }
    
    public static void v(final Throwable t, final String s, final Object... array) {
        Timber.TREE_OF_SOULS.v(t, s, array);
    }
    
    public static void w(final String s, final Object... array) {
        Timber.TREE_OF_SOULS.w(s, array);
    }
    
    public static void w(final Throwable t, final String s, final Object... array) {
        Timber.TREE_OF_SOULS.w(t, s, array);
    }
    
    public static void wtf(final String s, final Object... array) {
        Timber.TREE_OF_SOULS.wtf(s, array);
    }
    
    public static void wtf(final Throwable t, final String s, final Object... array) {
        Timber.TREE_OF_SOULS.wtf(t, s, array);
    }
    
    public static class DebugTree extends Tree
    {
        private static final Pattern ANONYMOUS_CLASS;
        private static final int CALL_STACK_INDEX = 5;
        private static final int MAX_LOG_LENGTH = 4000;
        
        static {
            ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
        }
        
        protected String createStackElementTag(final StackTraceElement stackTraceElement) {
            String s = stackTraceElement.getClassName();
            final Matcher matcher = DebugTree.ANONYMOUS_CLASS.matcher(s);
            if (matcher.find()) {
                s = matcher.replaceAll("");
            }
            return s.substring(1 + s.lastIndexOf(46));
        }
        
        @Override
        final String getTag() {
            final String tag = super.getTag();
            if (tag != null) {
                return tag;
            }
            final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length <= 5) {
                throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
            }
            return this.createStackElementTag(stackTrace[5]);
        }
        
        @Override
        protected void log(final int n, final String s, final String s2, final Throwable t) {
            if (s2.length() < 4000) {
                if (n != 7) {
                    Log.println(n, s, s2);
                    return;
                }
                Log.wtf(s, s2);
            }
            else {
                for (int i = 0, length = s2.length(); i < length; ++i) {
                    int index = s2.indexOf(10, i);
                    if (index == -1) {
                        index = length;
                    }
                    do {
                        final int min = Math.min(index, i + 4000);
                        final String substring = s2.substring(i, min);
                        if (n == 7) {
                            Log.wtf(s, substring);
                        }
                        else {
                            Log.println(n, s, substring);
                        }
                        i = min;
                    } while (i < index);
                }
            }
        }
    }
    
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
}
