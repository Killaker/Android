package com.google.android.gms.auth.firstparty.shared;

public enum zzd
{
    zzYC("ClientLoginDisabled"), 
    zzYD("DeviceManagementRequiredOrSyncDisabled"), 
    zzYE("SocketTimeout"), 
    zzYF("Ok"), 
    zzYG("UNKNOWN_ERR"), 
    zzYH("NetworkError"), 
    zzYI("ServiceUnavailable"), 
    zzYJ("InternalError"), 
    zzYK("BadAuthentication"), 
    zzYL("EmptyConsumerPackageOrSig"), 
    zzYM("InvalidSecondFactor"), 
    zzYN("PostSignInFlowRequired"), 
    zzYO("NeedsBrowser"), 
    zzYP("Unknown"), 
    zzYQ("NotVerified"), 
    zzYR("TermsNotAgreed"), 
    zzYS("AccountDisabled"), 
    zzYT("CaptchaRequired"), 
    zzYU("AccountDeleted"), 
    zzYV("ServiceDisabled"), 
    zzYW("NeedPermission"), 
    zzYX("INVALID_SCOPE"), 
    zzYY("UserCancel"), 
    zzYZ("PermissionDenied"), 
    zzZa("ThirdPartyDeviceManagementRequired"), 
    zzZb("DeviceManagementInternalError"), 
    zzZc("DeviceManagementSyncDisabled"), 
    zzZd("DeviceManagementAdminBlocked"), 
    zzZe("DeviceManagementAdminPendingApproval"), 
    zzZf("DeviceManagementStaleSyncRequired"), 
    zzZg("DeviceManagementDeactivated"), 
    zzZh("DeviceManagementRequired"), 
    zzZi("ReauthRequired"), 
    zzZj("ALREADY_HAS_GMAIL"), 
    zzZk("WeakPassword"), 
    zzZl("BadRequest"), 
    zzZm("BadUsername"), 
    zzZn("DeletedGmail"), 
    zzZo("ExistingUsername"), 
    zzZp("LoginFail"), 
    zzZq("NotLoggedIn"), 
    zzZr("NoGmail"), 
    zzZs("RequestDenied"), 
    zzZt("ServerError"), 
    zzZu("UsernameUnavailable"), 
    zzZv("GPlusOther"), 
    zzZw("GPlusNickname"), 
    zzZx("GPlusInvalidChar"), 
    zzZy("GPlusInterstitial"), 
    zzZz("ProfileUpgradeError");
    
    private final String zzZA;
    
    private zzd(final String zzZA) {
        this.zzZA = zzZA;
    }
    
    public static boolean zza(final zzd zzd) {
        return zzd.zzYK.equals(zzd) || zzd.zzYT.equals(zzd) || zzd.zzYW.equals(zzd) || zzd.zzYO.equals(zzd) || zzd.zzYY.equals(zzd) || zzd.zzZa.equals(zzd) || zzb(zzd);
    }
    
    public static boolean zzb(final zzd zzd) {
        return zzd.zzYD.equals(zzd) || zzd.zzZb.equals(zzd) || zzd.zzZc.equals(zzd) || zzd.zzZd.equals(zzd) || zzd.zzZe.equals(zzd) || zzd.zzZf.equals(zzd) || zzd.zzZg.equals(zzd) || zzd.zzZh.equals(zzd);
    }
    
    public static final zzd zzbY(final String s) {
        zzd zzd = null;
        final zzd[] values = values();
        zzd zzd2;
        for (int length = values.length, i = 0; i < length; ++i, zzd = zzd2) {
            zzd2 = values[i];
            if (!zzd2.zzZA.equals(s)) {
                zzd2 = zzd;
            }
        }
        return zzd;
    }
    
    public static boolean zzc(final zzd zzd) {
        return zzd.zzYH.equals(zzd) || zzd.zzYI.equals(zzd);
    }
}
