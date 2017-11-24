package bolts;

import android.content.*;

static final class AppLinkNavigation$1 implements Continuation<AppLink, NavigationResult> {
    final /* synthetic */ Context val$context;
    
    @Override
    public NavigationResult then(final Task<AppLink> task) throws Exception {
        return AppLinkNavigation.navigate(this.val$context, task.getResult());
    }
}