package android.support.v4.app;

import android.view.*;
import android.support.annotation.*;

class Fragment$1 extends FragmentContainer {
    @Nullable
    @Override
    public View onFindViewById(final int n) {
        if (Fragment.this.mView == null) {
            throw new IllegalStateException("Fragment does not have a view");
        }
        return Fragment.this.mView.findViewById(n);
    }
    
    @Override
    public boolean onHasView() {
        return Fragment.this.mView != null;
    }
}