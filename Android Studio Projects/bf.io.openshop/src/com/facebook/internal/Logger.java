package com.facebook.internal;

import com.facebook.*;
import android.util.*;
import java.util.*;

public class Logger
{
    public static final String LOG_TAG_BASE = "FacebookSDK.";
    private static final HashMap<String, String> stringsToReplace;
    private final LoggingBehavior behavior;
    private StringBuilder contents;
    private int priority;
    private final String tag;
    
    static {
        stringsToReplace = new HashMap<String, String>();
    }
    
    public Logger(final LoggingBehavior behavior, final String s) {
        this.priority = 3;
        Validate.notNullOrEmpty(s, "tag");
        this.behavior = behavior;
        this.tag = "FacebookSDK." + s;
        this.contents = new StringBuilder();
    }
    
    public static void log(final LoggingBehavior loggingBehavior, final int n, String string, final String s) {
        if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
            final String replaceStrings = replaceStrings(s);
            if (!string.startsWith("FacebookSDK.")) {
                string = "FacebookSDK." + string;
            }
            Log.println(n, string, replaceStrings);
            if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }
    
    public static void log(final LoggingBehavior loggingBehavior, final int n, final String s, final String s2, final Object... array) {
        if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
            log(loggingBehavior, n, s, String.format(s2, array));
        }
    }
    
    public static void log(final LoggingBehavior loggingBehavior, final String s, final String s2) {
        log(loggingBehavior, 3, s, s2);
    }
    
    public static void log(final LoggingBehavior loggingBehavior, final String s, final String s2, final Object... array) {
        if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
            log(loggingBehavior, 3, s, String.format(s2, array));
        }
    }
    
    public static void registerAccessToken(final String s) {
        synchronized (Logger.class) {
            if (!FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
                registerStringToReplace(s, "ACCESS_TOKEN_REMOVED");
            }
        }
    }
    
    public static void registerStringToReplace(final String s, final String s2) {
        synchronized (Logger.class) {
            Logger.stringsToReplace.put(s, s2);
        }
    }
    
    private static String replaceStrings(String replace) {
        synchronized (Logger.class) {
            for (final Map.Entry<String, String> entry : Logger.stringsToReplace.entrySet()) {
                replace = replace.replace(entry.getKey(), entry.getValue());
            }
            return replace;
        }
    }
    
    private boolean shouldLog() {
        return FacebookSdk.isLoggingBehaviorEnabled(this.behavior);
    }
    
    public void append(final String s) {
        if (this.shouldLog()) {
            this.contents.append(s);
        }
    }
    
    public void append(final String s, final Object... array) {
        if (this.shouldLog()) {
            this.contents.append(String.format(s, array));
        }
    }
    
    public void append(final StringBuilder sb) {
        if (this.shouldLog()) {
            this.contents.append((CharSequence)sb);
        }
    }
    
    public void appendKeyValue(final String s, final Object o) {
        this.append("  %s:\t%s\n", s, o);
    }
    
    public String getContents() {
        return replaceStrings(this.contents.toString());
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void log() {
        this.logString(this.contents.toString());
        this.contents = new StringBuilder();
    }
    
    public void logString(final String s) {
        log(this.behavior, this.priority, this.tag, s);
    }
    
    public void setPriority(final int priority) {
        Validate.oneOf(priority, "value", 7, 3, 6, 4, 2, 5);
        this.priority = priority;
    }
}
