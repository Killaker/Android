package android.support.v4.app;

import android.support.annotation.*;

public interface DelegateProvider
{
    @Nullable
    Delegate getDrawerToggleDelegate();
}
