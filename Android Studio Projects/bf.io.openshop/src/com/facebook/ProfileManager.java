package com.facebook;

import android.support.v4.content.*;
import android.content.*;
import android.os.*;
import com.facebook.internal.*;

final class ProfileManager
{
    static final String ACTION_CURRENT_PROFILE_CHANGED = "com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED";
    static final String EXTRA_NEW_PROFILE = "com.facebook.sdk.EXTRA_NEW_PROFILE";
    static final String EXTRA_OLD_PROFILE = "com.facebook.sdk.EXTRA_OLD_PROFILE";
    private static volatile ProfileManager instance;
    private Profile currentProfile;
    private final LocalBroadcastManager localBroadcastManager;
    private final ProfileCache profileCache;
    
    ProfileManager(final LocalBroadcastManager localBroadcastManager, final ProfileCache profileCache) {
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(profileCache, "profileCache");
        this.localBroadcastManager = localBroadcastManager;
        this.profileCache = profileCache;
    }
    
    static ProfileManager getInstance() {
        Label_0041: {
            if (ProfileManager.instance != null) {
                break Label_0041;
            }
            synchronized (ProfileManager.class) {
                if (ProfileManager.instance == null) {
                    ProfileManager.instance = new ProfileManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new ProfileCache());
                }
                return ProfileManager.instance;
            }
        }
    }
    
    private void sendCurrentProfileChangedBroadcast(final Profile profile, final Profile profile2) {
        final Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", (Parcelable)profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", (Parcelable)profile2);
        this.localBroadcastManager.sendBroadcast(intent);
    }
    
    private void setCurrentProfile(final Profile currentProfile, final boolean b) {
        final Profile currentProfile2 = this.currentProfile;
        this.currentProfile = currentProfile;
        if (b) {
            if (currentProfile != null) {
                this.profileCache.save(currentProfile);
            }
            else {
                this.profileCache.clear();
            }
        }
        if (!Utility.areObjectsEqual(currentProfile2, currentProfile)) {
            this.sendCurrentProfileChangedBroadcast(currentProfile2, currentProfile);
        }
    }
    
    Profile getCurrentProfile() {
        return this.currentProfile;
    }
    
    boolean loadCurrentProfile() {
        final Profile load = this.profileCache.load();
        boolean b = false;
        if (load != null) {
            this.setCurrentProfile(load, false);
            b = true;
        }
        return b;
    }
    
    void setCurrentProfile(final Profile profile) {
        this.setCurrentProfile(profile, true);
    }
}
