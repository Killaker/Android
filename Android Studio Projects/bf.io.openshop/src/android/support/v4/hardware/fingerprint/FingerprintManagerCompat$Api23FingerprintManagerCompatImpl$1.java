package android.support.v4.hardware.fingerprint;

static final class FingerprintManagerCompat$Api23FingerprintManagerCompatImpl$1 extends FingerprintManagerCompatApi23.AuthenticationCallback {
    final /* synthetic */ FingerprintManagerCompat.AuthenticationCallback val$callback;
    
    @Override
    public void onAuthenticationError(final int n, final CharSequence charSequence) {
        this.val$callback.onAuthenticationError(n, charSequence);
    }
    
    @Override
    public void onAuthenticationFailed() {
        this.val$callback.onAuthenticationFailed();
    }
    
    @Override
    public void onAuthenticationHelp(final int n, final CharSequence charSequence) {
        this.val$callback.onAuthenticationHelp(n, charSequence);
    }
    
    @Override
    public void onAuthenticationSucceeded(final AuthenticationResultInternal authenticationResultInternal) {
        this.val$callback.onAuthenticationSucceeded(new AuthenticationResult(Api23FingerprintManagerCompatImpl.access$000(authenticationResultInternal.getCryptoObject())));
    }
}