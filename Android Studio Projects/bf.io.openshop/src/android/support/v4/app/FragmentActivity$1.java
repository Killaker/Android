package android.support.v4.app;

import android.os.*;

class FragmentActivity$1 extends Handler {
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                super.handleMessage(message);
                break;
            }
            case 1: {
                if (FragmentActivity.this.mStopped) {
                    FragmentActivity.this.doReallyStop(false);
                    return;
                }
                break;
            }
            case 2: {
                FragmentActivity.this.onResumeFragments();
                FragmentActivity.this.mFragments.execPendingActions();
            }
        }
    }
}