package com.google.android.gms.dynamic;

import android.annotation.*;
import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;

@SuppressLint({ "NewApi" })
public final class zzb extends zzc.zza
{
    private Fragment zzavH;
    
    private zzb(final Fragment zzavH) {
        this.zzavH = zzavH;
    }
    
    public static zzb zza(final Fragment fragment) {
        if (fragment != null) {
            return new zzb(fragment);
        }
        return null;
    }
    
    public Bundle getArguments() {
        return this.zzavH.getArguments();
    }
    
    public int getId() {
        return this.zzavH.getId();
    }
    
    public boolean getRetainInstance() {
        return this.zzavH.getRetainInstance();
    }
    
    public String getTag() {
        return this.zzavH.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.zzavH.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.zzavH.getUserVisibleHint();
    }
    
    public zzd getView() {
        return zze.zzC(this.zzavH.getView());
    }
    
    public boolean isAdded() {
        return this.zzavH.isAdded();
    }
    
    public boolean isDetached() {
        return this.zzavH.isDetached();
    }
    
    public boolean isHidden() {
        return this.zzavH.isHidden();
    }
    
    public boolean isInLayout() {
        return this.zzavH.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.zzavH.isRemoving();
    }
    
    public boolean isResumed() {
        return this.zzavH.isResumed();
    }
    
    public boolean isVisible() {
        return this.zzavH.isVisible();
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.zzavH.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.zzavH.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.zzavH.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.zzavH.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.zzavH.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.zzavH.startActivityForResult(intent, n);
    }
    
    public void zzn(final zzd zzd) {
        this.zzavH.registerForContextMenu((View)zze.zzp(zzd));
    }
    
    public void zzo(final zzd zzd) {
        this.zzavH.unregisterForContextMenu((View)zze.zzp(zzd));
    }
    
    public zzd zztV() {
        return zze.zzC(this.zzavH.getActivity());
    }
    
    public zzc zztW() {
        return zza(this.zzavH.getParentFragment());
    }
    
    public zzd zztX() {
        return zze.zzC(this.zzavH.getResources());
    }
    
    public zzc zztY() {
        return zza(this.zzavH.getTargetFragment());
    }
}
