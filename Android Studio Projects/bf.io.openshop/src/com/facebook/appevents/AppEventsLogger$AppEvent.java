package com.facebook.appevents;

import java.io.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;
import org.json.*;
import java.util.*;

static class AppEvent implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final HashSet<String> validatedIdentifiers;
    private boolean isImplicit;
    private JSONObject jsonObject;
    private String name;
    
    static {
        validatedIdentifiers = new HashSet<String>();
    }
    
    public AppEvent(final String s, final String name, final Double n, final Bundle bundle, final boolean isImplicit) {
        try {
            this.validateIdentifier(name);
            this.name = name;
            this.isImplicit = isImplicit;
            (this.jsonObject = new JSONObject()).put("_eventName", (Object)name);
            this.jsonObject.put("_logTime", System.currentTimeMillis() / 1000L);
            this.jsonObject.put("_ui", (Object)s);
            if (n != null) {
                this.jsonObject.put("_valueToSum", (double)n);
            }
            if (this.isImplicit) {
                this.jsonObject.put("_implicitlyLogged", (Object)"1");
            }
            if (bundle != null) {
                final Iterator<String> iterator = bundle.keySet().iterator();
                if (iterator.hasNext()) {
                    final String s2 = iterator.next();
                    this.validateIdentifier(s2);
                    final Object value = bundle.get(s2);
                    if (!(value instanceof String) && !(value instanceof Number)) {
                        throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", value, s2));
                    }
                    goto Label_0243;
                }
            }
        }
        catch (JSONException ex) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", ex.toString());
            this.jsonObject = null;
        }
        catch (FacebookException ex2) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", ex2.toString());
            this.jsonObject = null;
            return;
        }
        if (!this.isImplicit) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", this.jsonObject.toString());
            return;
        }
        goto Label_0242;
    }
    
    private AppEvent(final String s, final boolean isImplicit) throws JSONException {
        this.jsonObject = new JSONObject(s);
        this.isImplicit = isImplicit;
    }
    
    private void validateIdentifier(final String p0) throws FacebookException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          20
        //     4: aload_1        
        //     5: invokevirtual   java/lang/String.length:()I
        //     8: ifeq            20
        //    11: aload_1        
        //    12: invokevirtual   java/lang/String.length:()I
        //    15: bipush          40
        //    17: if_icmple       63
        //    20: aload_1        
        //    21: ifnonnull       27
        //    24: ldc             "<None Provided>"
        //    26: astore_1       
        //    27: getstatic       java/util/Locale.ROOT:Ljava/util/Locale;
        //    30: astore_2       
        //    31: iconst_2       
        //    32: anewarray       Ljava/lang/Object;
        //    35: astore_3       
        //    36: aload_3        
        //    37: iconst_0       
        //    38: aload_1        
        //    39: aastore        
        //    40: aload_3        
        //    41: iconst_1       
        //    42: bipush          40
        //    44: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    47: aastore        
        //    48: new             Lcom/facebook/FacebookException;
        //    51: dup            
        //    52: aload_2        
        //    53: ldc             "Identifier '%s' must be less than %d characters"
        //    55: aload_3        
        //    56: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    59: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //    62: athrow         
        //    63: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    66: astore          4
        //    68: aload           4
        //    70: monitorenter   
        //    71: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    74: aload_1        
        //    75: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
        //    78: istore          6
        //    80: aload           4
        //    82: monitorexit    
        //    83: iload           6
        //    85: ifne            116
        //    88: aload_1        
        //    89: ldc             "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$"
        //    91: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    94: ifeq            133
        //    97: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //   100: astore          7
        //   102: aload           7
        //   104: monitorenter   
        //   105: getstatic       com/facebook/appevents/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //   108: aload_1        
        //   109: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   112: pop            
        //   113: aload           7
        //   115: monitorexit    
        //   116: return         
        //   117: astore          5
        //   119: aload           4
        //   121: monitorexit    
        //   122: aload           5
        //   124: athrow         
        //   125: astore          8
        //   127: aload           7
        //   129: monitorexit    
        //   130: aload           8
        //   132: athrow         
        //   133: new             Lcom/facebook/FacebookException;
        //   136: dup            
        //   137: ldc             "Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen."
        //   139: iconst_1       
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: iconst_0       
        //   145: aload_1        
        //   146: aastore        
        //   147: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   150: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //   153: athrow         
        //    Exceptions:
        //  throws com.facebook.FacebookException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  71     83     117    125    Any
        //  105    116    125    133    Any
        //  119    122    117    125    Any
        //  127    130    125    133    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private Object writeReplace() {
        return new SerializationProxyV1(this.jsonObject.toString(), this.isImplicit);
    }
    
    public boolean getIsImplicit() {
        return this.isImplicit;
    }
    
    public JSONObject getJSONObject() {
        return this.jsonObject;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", this.jsonObject.optString("_eventName"), this.isImplicit, this.jsonObject.toString());
    }
    
    private static class SerializationProxyV1 implements Serializable
    {
        private static final long serialVersionUID = -2488473066578201069L;
        private final boolean isImplicit;
        private final String jsonString;
        
        private SerializationProxyV1(final String jsonString, final boolean isImplicit) {
            this.jsonString = jsonString;
            this.isImplicit = isImplicit;
        }
        
        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit);
        }
    }
}
