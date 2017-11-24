package com.google.android.gms.dynamic;

import android.support.v4.app.*;
import android.os.*;
import android.content.*;
import android.view.*;

public final class zzh extends zzc.zza
{
    private Fragment zzalg;
    
    private zzh(final Fragment zzalg) {
        this.zzalg = zzalg;
    }
    
    public static zzh zza(final Fragment fragment) {
        if (fragment != null) {
            return new zzh(fragment);
        }
        return null;
    }
    
    public Bundle getArguments() {
        return this.zzalg.getArguments();
    }
    
    public int getId() {
        return this.zzalg.getId();
    }
    
    public boolean getRetainInstance() {
        return this.zzalg.getRetainInstance();
    }
    
    public String getTag() {
        return this.zzalg.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.zzalg.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.zzalg.getUserVisibleHint();
    }
    
    public zzd getView() {
        return zze.zzC(this.zzalg.getView());
    }
    
    public boolean isAdded() {
        return this.zzalg.isAdded();
    }
    
    public boolean isDetached() {
        return this.zzalg.isDetached();
    }
    
    public boolean isHidden() {
        return this.zzalg.isHidden();
    }
    
    public boolean isInLayout() {
        return this.zzalg.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.zzalg.isRemoving();
    }
    
    public boolean isResumed() {
        return this.zzalg.isResumed();
    }
    
    public boolean isVisible() {
        return this.zzalg.isVisible();
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.zzalg.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.zzalg.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.zzalg.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.zzalg.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.zzalg.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.zzalg.startActivityForResult(intent, n);
    }
    
    public void zzn(final zzd zzd) {
        this.zzalg.registerForContextMenu(zze.zzp(zzd));
    }
    
    public void zzo(final zzd zzd) {
        this.zzalg.unregisterForContextMenu(zze.zzp(zzd));
    }
    
    public zzd zztV() {
        return zze.zzC(this.zzalg.getActivity());
    }
    
    public zzc zztW() {
        return zza(this.zzalg.getParentFragment());
    }
    
    public zzd zztX() {
        return zze.zzC(this.zzalg.getResources());
    }
    
    public zzc zztY() {
        return zza(this.zzalg.getTargetFragment());
    }
}
