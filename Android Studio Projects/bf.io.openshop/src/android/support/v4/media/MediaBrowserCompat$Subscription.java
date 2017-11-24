package android.support.v4.media;

import android.os.*;
import java.util.*;

private static class Subscription
{
    private final List<SubscriptionCallback> mCallbacks;
    private final List<Bundle> mOptionsList;
    
    public Subscription() {
        this.mCallbacks = new ArrayList<SubscriptionCallback>();
        this.mOptionsList = new ArrayList<Bundle>();
    }
    
    public SubscriptionCallback getCallback(final Bundle bundle) {
        for (int i = 0; i < this.mOptionsList.size(); ++i) {
            if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                return this.mCallbacks.get(i);
            }
        }
        return null;
    }
    
    public List<SubscriptionCallback> getCallbacks() {
        return this.mCallbacks;
    }
    
    public List<Bundle> getOptionsList() {
        return this.mOptionsList;
    }
    
    public boolean isEmpty() {
        return this.mCallbacks.isEmpty();
    }
    
    public boolean remove(final Bundle bundle) {
        for (int i = 0; i < this.mOptionsList.size(); ++i) {
            if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                this.mCallbacks.remove(i);
                this.mOptionsList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void setCallbackForOptions(final SubscriptionCallback subscriptionCallback, final Bundle bundle) {
        for (int i = 0; i < this.mOptionsList.size(); ++i) {
            if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                this.mCallbacks.set(i, subscriptionCallback);
                return;
            }
        }
        this.mCallbacks.add(subscriptionCallback);
        this.mOptionsList.add(bundle);
    }
}
