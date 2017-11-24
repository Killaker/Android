package android.support.v4.hardware.fingerprint;

public static final class AuthenticationResultInternal
{
    private CryptoObject mCryptoObject;
    
    public AuthenticationResultInternal(final CryptoObject mCryptoObject) {
        this.mCryptoObject = mCryptoObject;
    }
    
    public CryptoObject getCryptoObject() {
        return this.mCryptoObject;
    }
}
