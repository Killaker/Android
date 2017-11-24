package android.support.v7.app;

import android.support.annotation.*;

public interface DelegateProvider
{
    @Nullable
    Delegate getDrawerToggleDelegate();
}
