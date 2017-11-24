package com.facebook.appevents;

import java.io.*;
import org.json.*;

private static class SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = -2488473066578201069L;
    private final boolean isImplicit;
    private final String jsonString;
    
    private SerializationProxyV1(final String jsonString, final boolean isImplicit) {
        this.jsonString = jsonString;
        this.isImplicit = isImplicit;
    }
    
    private Object readResolve() throws JSONException {
        return new AppEvent(this.jsonString, this.isImplicit);
    }
}
