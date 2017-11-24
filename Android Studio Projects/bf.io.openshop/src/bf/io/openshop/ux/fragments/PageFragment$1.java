package bf.io.openshop.ux.fragments;

import com.android.volley.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;

class PageFragment$1 implements Listener<Page> {
    public void onResponse(@NonNull final Page page) {
        PageFragment.access$000(PageFragment.this, page);
    }
}