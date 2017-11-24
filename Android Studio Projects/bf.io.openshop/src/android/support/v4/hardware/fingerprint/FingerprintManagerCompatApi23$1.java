package android.support.v4.hardware.fingerprint;

import android.hardware.fingerprint.*;

static final class FingerprintManagerCompatApi23$1 extends FingerprintManager$AuthenticationCallback {
    final /* synthetic */ AuthenticationCallback val$callback;
    
    public void onAuthenticationError(final int n, final CharSequence charSequence) {
        this.val$callback.onAuthenticationError(n, charSequence);
    }
    
    public void onAuthenticationFailed() {
        this.val$callback.onAuthenticationFailed();
    }
    
    public void onAuthenticationHelp(final int n, final CharSequence charSequence) {
        this.val$callback.onAuthenticationHelp(n, charSequence);
    }
    
    public void onAuthenticationSucceeded(final FingerprintManager$AuthenticationResult fingerprintManager$AuthenticationResult) {
        this.val$callback.onAuthenticationSucceeded(new AuthenticationResultInternal(FingerprintManagerCompatApi23.access$000(fingerprintManager$AuthenticationResult.getCryptoObject())));
    }
}