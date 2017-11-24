package com.google.android.gms.common;

import android.accounts.*;
import java.util.*;
import android.content.*;
import com.google.android.gms.common.internal.*;
import java.io.*;
import android.os.*;

public final class AccountPicker
{
    public static Intent newChooseAccountIntent(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle) {
        return zza(account, list, array, b, s, s2, array2, bundle, false);
    }
    
    public static Intent zza(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle, final boolean b2) {
        return zza(account, list, array, b, s, s2, array2, bundle, b2, 0, 0);
    }
    
    public static Intent zza(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle, final boolean b2, final int n, final int n2) {
        return zza(account, list, array, b, s, s2, array2, bundle, b2, n, n2, null, false);
    }
    
    public static Intent zza(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle, final boolean b2, final int n, final int n2, final String s3, final boolean b3) {
        final Intent intent = new Intent();
        if (!b3) {
            zzx.zzb(s3 == null, (Object)"We only support hostedDomain filter for account chip styled account picker");
        }
        String action;
        if (b3) {
            action = "com.google.android.gms.common.account.CHOOSE_ACCOUNT_USERTILE";
        }
        else {
            action = "com.google.android.gms.common.account.CHOOSE_ACCOUNT";
        }
        intent.setAction(action);
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", (Serializable)list);
        intent.putExtra("allowableAccountTypes", array);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", (Parcelable)account);
        intent.putExtra("alwaysPromptForAccount", b);
        intent.putExtra("descriptionTextOverride", s);
        intent.putExtra("authTokenType", s2);
        intent.putExtra("addAccountRequiredFeatures", array2);
        intent.putExtra("setGmsCoreAccount", b2);
        intent.putExtra("overrideTheme", n);
        intent.putExtra("overrideCustomTheme", n2);
        intent.putExtra("hostedDomainFilter", s3);
        return intent;
    }
}
