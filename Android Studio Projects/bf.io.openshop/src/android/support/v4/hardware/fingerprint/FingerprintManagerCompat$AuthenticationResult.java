package android.support.v4.hardware.fingerprint;

public static final class AuthenticationResult
{
    private CryptoObject mCryptoObject;
    
    public AuthenticationResult(final CryptoObject mCryptoObject) {
        this.mCryptoObject = mCryptoObject;
    }
    
    public CryptoObject getCryptoObject() {
        return this.mCryptoObject;
    }
}
