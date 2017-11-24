package okhttp3;

enum ParseResult
{
    INVALID_HOST, 
    INVALID_PORT, 
    MISSING_SCHEME, 
    SUCCESS, 
    UNSUPPORTED_SCHEME;
}
