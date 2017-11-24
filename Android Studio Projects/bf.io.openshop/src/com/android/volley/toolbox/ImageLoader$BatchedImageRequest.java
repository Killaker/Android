package com.android.volley.toolbox;

import java.util.*;
import com.android.volley.*;
import android.graphics.*;

private class BatchedImageRequest
{
    private final LinkedList<ImageContainer> mContainers;
    private VolleyError mError;
    private final Request<?> mRequest;
    private Bitmap mResponseBitmap;
    
    public BatchedImageRequest(final Request<?> mRequest, final ImageContainer imageContainer) {
        this.mContainers = new LinkedList<ImageContainer>();
        this.mRequest = mRequest;
        this.mContainers.add(imageContainer);
    }
    
    public void addContainer(final ImageContainer imageContainer) {
        this.mContainers.add(imageContainer);
    }
    
    public VolleyError getError() {
        return this.mError;
    }
    
    public boolean removeContainerAndCancelIfNecessary(final ImageContainer imageContainer) {
        this.mContainers.remove(imageContainer);
        if (this.mContainers.size() == 0) {
            this.mRequest.cancel();
            return true;
        }
        return false;
    }
    
    public void setError(final VolleyError mError) {
        this.mError = mError;
    }
}
