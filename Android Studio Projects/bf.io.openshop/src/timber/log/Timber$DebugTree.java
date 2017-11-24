package timber.log;

import java.util.regex.*;
import android.util.*;

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
