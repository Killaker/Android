package com.google.android.gms.analytics;

import java.util.*;
import com.google.android.gms.analytics.internal.*;

public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder>
{
    public ExceptionBuilder() {
        this.set("&t", "exception");
    }
    
    public ExceptionBuilder setDescription(final String s) {
        this.set("&exd", s);
        return this;
    }
    
    public ExceptionBuilder setFatal(final boolean b) {
        this.set("&exf", zzam.zzK(b));
        return this;
    }
}
