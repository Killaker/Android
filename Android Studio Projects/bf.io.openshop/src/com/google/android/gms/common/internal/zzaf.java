package com.google.android.gms.common.internal;

import android.content.*;
import android.util.*;
import android.content.res.*;

public class zzaf
{
    public static String zza(final String s, final String s2, final Context context, final AttributeSet set, final boolean b, final boolean b2, final String s3) {
    Label_0109:
        while (true) {
            while (true) {
                String s4 = null;
                Label_0007: {
                    if (set == null) {
                        s4 = null;
                        break Label_0007;
                    }
                    Label_0152: {
                        break Label_0152;
                        while (true) {
                            final String substring = s4.substring("@string/".length());
                            final String packageName = context.getPackageName();
                            final TypedValue typedValue = new TypedValue();
                        Label_0205:
                            while (true) {
                                try {
                                    context.getResources().getValue(packageName + ":string/" + substring, typedValue, true);
                                    if (typedValue.string != null) {
                                        s4 = typedValue.string.toString();
                                        if (b2 && s4 == null) {
                                            Log.w(s3, "Required XML attribute \"" + s2 + "\" missing");
                                        }
                                        return s4;
                                    }
                                    break Label_0205;
                                    s4 = set.getAttributeValue(s, s2);
                                    break;
                                }
                                catch (Resources$NotFoundException ex) {
                                    Log.w(s3, "Could not find resource for " + s2 + ": " + s4);
                                    continue;
                                }
                                break;
                            }
                            Log.w(s3, "Resource " + s2 + " was not a string: " + typedValue);
                            continue Label_0109;
                        }
                    }
                }
                if (s4 != null && s4.startsWith("@string/") && b) {
                    continue;
                }
                break;
            }
            continue Label_0109;
        }
    }
}
