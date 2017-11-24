package com.facebook;

import com.facebook.internal.*;

public static class Factory
{
    public static CallbackManager create() {
        return new CallbackManagerImpl();
    }
}
