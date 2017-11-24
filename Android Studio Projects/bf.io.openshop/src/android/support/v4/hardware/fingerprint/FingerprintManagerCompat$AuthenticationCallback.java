package android.support.v4.hardware.fingerprint;

public abstract static class AuthenticationCallback
{
    public void onAuthenticationError(final int n, final CharSequence charSequence) {
    }
    
    public void onAuthenticationFailed() {
    }
    
    public void onAuthenticationHelp(final int n, final CharSequence charSequence) {
    }
    
    public void onAuthenticationSucceeded(final AuthenticationResult authenticationResult) {
    }
}
