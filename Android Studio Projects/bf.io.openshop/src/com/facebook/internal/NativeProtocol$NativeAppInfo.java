package com.facebook.internal;

import java.util.*;
import android.content.*;
import android.os.*;
import android.content.pm.*;

private abstract static class NativeAppInfo
{
    private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
    private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
    private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
    private static final HashSet<String> validAppSignatureHashes;
    private TreeSet<Integer> availableVersions;
    
    static {
        validAppSignatureHashes = buildAppSignatureHashes();
    }
    
    private static HashSet<String> buildAppSignatureHashes() {
        final HashSet<String> set = new HashSet<String>();
        set.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
        set.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
        set.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
        return set;
    }
    
    private void fetchAvailableVersions(final boolean b) {
        // monitorenter(this)
        Label_0013: {
            if (b) {
                break Label_0013;
            }
            try {
                if (this.availableVersions == null) {
                    this.availableVersions = (TreeSet<Integer>)NativeProtocol.access$000(this);
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public TreeSet<Integer> getAvailableVersions() {
        if (this.availableVersions == null) {
            this.fetchAvailableVersions(false);
        }
        return this.availableVersions;
    }
    
    protected abstract String getPackage();
    
    public boolean validateSignature(final Context context, final String s) {
        final String brand = Build.BRAND;
        final int flags = context.getApplicationInfo().flags;
        if (!brand.startsWith("generic") || (flags & 0x2) == 0x0) {
            try {
                final Signature[] signatures = context.getPackageManager().getPackageInfo(s, 64).signatures;
                for (int length = signatures.length, i = 0; i < length; ++i) {
                    if (NativeAppInfo.validAppSignatureHashes.contains(Utility.sha1hash(signatures[i].toByteArray()))) {
                        return true;
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                return false;
            }
            return false;
        }
        return true;
    }
}
