package com.facebook;

import android.content.*;
import com.facebook.internal.*;

public interface CallbackManager
{
    boolean onActivityResult(final int p0, final int p1, final Intent p2);
    
    public static class Factory
    {
        public static CallbackManager create() {
            return new CallbackManagerImpl();
        }
    }
}
