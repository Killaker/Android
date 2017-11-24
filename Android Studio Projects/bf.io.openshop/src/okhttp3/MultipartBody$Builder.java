package okhttp3;

import okio.*;
import java.util.*;

public static final class Builder
{
    private final ByteString boundary;
    private final List<Part> parts;
    private MediaType type;
    
    public Builder() {
        this(UUID.randomUUID().toString());
    }
    
    public Builder(final String s) {
        this.type = MultipartBody.MIXED;
        this.parts = new ArrayList<Part>();
        this.boundary = ByteString.encodeUtf8(s);
    }
    
    public Builder addFormDataPart(final String s, final String s2) {
        return this.addPart(Part.createFormData(s, s2));
    }
    
    public Builder addFormDataPart(final String s, final String s2, final RequestBody requestBody) {
        return this.addPart(Part.createFormData(s, s2, requestBody));
    }
    
    public Builder addPart(final Headers headers, final RequestBody requestBody) {
        return this.addPart(Part.create(headers, requestBody));
    }
    
    public Builder addPart(final Part part) {
        if (part == null) {
            throw new NullPointerException("part == null");
        }
        this.parts.add(part);
        return this;
    }
    
    public Builder addPart(final RequestBody requestBody) {
        return this.addPart(Part.create(requestBody));
    }
    
    public MultipartBody build() {
        if (this.parts.isEmpty()) {
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
        return new MultipartBody(this.boundary, this.type, this.parts);
    }
    
    public Builder setType(final MediaType type) {
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (!type.type().equals("multipart")) {
            throw new IllegalArgumentException("multipart != " + type);
        }
        this.type = type;
        return this;
    }
}
