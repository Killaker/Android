package com.facebook.internal;

import android.os.*;

public interface ParameterProvider
{
    Bundle getLegacyParameters();
    
    Bundle getParameters();
}
