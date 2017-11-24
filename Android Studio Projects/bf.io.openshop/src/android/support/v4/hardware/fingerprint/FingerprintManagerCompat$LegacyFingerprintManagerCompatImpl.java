package android.support.v4.hardware.fingerprint;

import android.content.*;
import android.support.v4.os.*;
import android.os.*;

private static class LegacyFingerprintManagerCompatImpl implements FingerprintManagerCompatImpl
{
    @Override
    public void authenticate(final Context context, final CryptoObject cryptoObject, final int n, final CancellationSignal cancellationSignal, final AuthenticationCallback authenticationCallback, final Handler handler) {
    }
    
    @Override
    public boolean hasEnrolledFingerprints(final Context context) {
        return false;
    }
    
    @Override
    public boolean isHardwareDetected(final Context context) {
        return false;
    }
}
