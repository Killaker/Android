package com.facebook.share.widget;

import android.os.*;
import java.util.*;

public static final class Result
{
    String requestId;
    List<String> to;
    
    private Result(final Bundle bundle) {
        this.requestId = bundle.getString("request");
        this.to = new ArrayList<String>();
        while (bundle.containsKey(String.format("to[%d]", this.to.size()))) {
            this.to.add(bundle.getString(String.format("to[%d]", this.to.size())));
        }
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public List<String> getRequestRecipients() {
        return this.to;
    }
}
