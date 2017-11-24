package android.support.v7.widget;

import android.view.*;
import java.lang.reflect.*;
import android.widget.*;
import java.lang.ref.*;

static final class AppCompatPopupWindow$1 implements ViewTreeObserver$OnScrollChangedListener {
    final /* synthetic */ Field val$fieldAnchor;
    final /* synthetic */ ViewTreeObserver$OnScrollChangedListener val$originalListener;
    final /* synthetic */ PopupWindow val$popup;
    
    public void onScrollChanged() {
        try {
            final WeakReference weakReference = (WeakReference)this.val$fieldAnchor.get(this.val$popup);
            if (weakReference != null) {
                if (weakReference.get() == null) {
                    return;
                }
                this.val$originalListener.onScrollChanged();
            }
        }
        catch (IllegalAccessException ex) {}
    }
}