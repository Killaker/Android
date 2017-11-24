package android.support.v4.content;

import android.content.*;
import android.support.annotation.*;

private static class EditorHelperBaseImpl implements Helper
{
    @Override
    public void apply(@NonNull final SharedPreferences$Editor sharedPreferences$Editor) {
        sharedPreferences$Editor.commit();
    }
}
