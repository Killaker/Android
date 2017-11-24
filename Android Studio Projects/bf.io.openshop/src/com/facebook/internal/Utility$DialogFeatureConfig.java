package com.facebook.internal;

import android.net.*;
import org.json.*;

public static class DialogFeatureConfig
{
    private String dialogName;
    private Uri fallbackUrl;
    private String featureName;
    private int[] featureVersionSpec;
    
    private DialogFeatureConfig(final String dialogName, final String featureName, final Uri fallbackUrl, final int[] featureVersionSpec) {
        this.dialogName = dialogName;
        this.featureName = featureName;
        this.fallbackUrl = fallbackUrl;
        this.featureVersionSpec = featureVersionSpec;
    }
    
    private static DialogFeatureConfig parseDialogConfig(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("name");
        if (!Utility.isNullOrEmpty(optString)) {
            final String[] split = optString.split("\\|");
            if (split.length == 2) {
                final String s = split[0];
                final String s2 = split[1];
                if (!Utility.isNullOrEmpty(s) && !Utility.isNullOrEmpty(s2)) {
                    final String optString2 = jsonObject.optString("url");
                    final boolean nullOrEmpty = Utility.isNullOrEmpty(optString2);
                    Uri parse = null;
                    if (!nullOrEmpty) {
                        parse = Uri.parse(optString2);
                    }
                    return new DialogFeatureConfig(s, s2, parse, parseVersionSpec(jsonObject.optJSONArray("versions")));
                }
            }
        }
        return null;
    }
    
    private static int[] parseVersionSpec(final JSONArray jsonArray) {
        int[] array = null;
        if (jsonArray != null) {
            final int length = jsonArray.length();
            array = new int[length];
            int i = 0;
        Label_0062_Outer:
            while (i < length) {
                int n = jsonArray.optInt(i, -1);
                while (true) {
                    if (n != -1) {
                        break Label_0062;
                    }
                    final String optString = jsonArray.optString(i);
                    if (Utility.isNullOrEmpty(optString)) {
                        break Label_0062;
                    }
                    try {
                        n = Integer.parseInt(optString);
                        array[i] = n;
                        ++i;
                        continue Label_0062_Outer;
                    }
                    catch (NumberFormatException ex) {
                        Utility.logd("FacebookSDK", ex);
                        n = -1;
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        return array;
    }
    
    public String getDialogName() {
        return this.dialogName;
    }
    
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }
    
    public String getFeatureName() {
        return this.featureName;
    }
    
    public int[] getVersionSpec() {
        return this.featureVersionSpec;
    }
}
