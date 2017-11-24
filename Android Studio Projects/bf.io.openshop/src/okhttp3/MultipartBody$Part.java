package okhttp3;

public static final class Part
{
    private final RequestBody body;
    private final Headers headers;
    
    private Part(final Headers headers, final RequestBody body) {
        this.headers = headers;
        this.body = body;
    }
    
    public static Part create(final Headers headers, final RequestBody requestBody) {
        if (requestBody == null) {
            throw new NullPointerException("body == null");
        }
        if (headers != null && headers.get("Content-Type") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        }
        if (headers != null && headers.get("Content-Length") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
        return new Part(headers, requestBody);
    }
    
    public static Part create(final RequestBody requestBody) {
        return create(null, requestBody);
    }
    
    public static Part createFormData(final String s, final String s2) {
        return createFormData(s, null, RequestBody.create(null, s2));
    }
    
    public static Part createFormData(final String s, final String s2, final RequestBody requestBody) {
        if (s == null) {
            throw new NullPointerException("name == null");
        }
        final StringBuilder sb = new StringBuilder("form-data; name=");
        MultipartBody.appendQuotedString(sb, s);
        if (s2 != null) {
            sb.append("; filename=");
            MultipartBody.appendQuotedString(sb, s2);
        }
        return create(Headers.of("Content-Disposition", sb.toString()), requestBody);
    }
}
