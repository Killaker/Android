package com.google.android.gms.common.internal;

import android.accounts.*;
import android.view.*;
import com.google.android.gms.internal.*;
import java.util.*;
import android.content.*;
import com.google.android.gms.common.api.*;

public final class zzf
{
    private final Account zzTI;
    private final String zzUW;
    private final Set<Scope> zzagh;
    private final int zzagj;
    private final View zzagk;
    private final String zzagl;
    private final Set<Scope> zzalb;
    private final Map<Api<?>, zza> zzalc;
    private final zzro zzald;
    private Integer zzale;
    
    public zzf(final Account zzTI, final Set<Scope> set, Map<Api<?>, zza> empty_MAP, final int zzagj, final View zzagk, final String zzUW, final String zzagl, final zzro zzald) {
        this.zzTI = zzTI;
        Set<Scope> zzagh;
        if (set == null) {
            zzagh = (Set<Scope>)Collections.EMPTY_SET;
        }
        else {
            zzagh = Collections.unmodifiableSet((Set<? extends Scope>)set);
        }
        this.zzagh = zzagh;
        if (empty_MAP == null) {
            empty_MAP = Collections.EMPTY_MAP;
        }
        this.zzalc = (Map<Api<?>, zza>)empty_MAP;
        this.zzagk = zzagk;
        this.zzagj = zzagj;
        this.zzUW = zzUW;
        this.zzagl = zzagl;
        this.zzald = zzald;
        final HashSet<Scope> set2 = new HashSet<Scope>(this.zzagh);
        final Iterator<zza> iterator = this.zzalc.values().iterator();
        while (iterator.hasNext()) {
            set2.addAll((Collection<?>)iterator.next().zzXf);
        }
        this.zzalb = (Set<Scope>)Collections.unmodifiableSet((Set<?>)set2);
    }
    
    public static zzf zzat(final Context context) {
        return new GoogleApiClient.Builder(context).zzoY();
    }
    
    public Account getAccount() {
        return this.zzTI;
    }
    
    @Deprecated
    public String getAccountName() {
        if (this.zzTI != null) {
            return this.zzTI.name;
        }
        return null;
    }
    
    public void zza(final Integer zzale) {
        this.zzale = zzale;
    }
    
    public Set<Scope> zzb(final Api<?> api) {
        final zza zza = this.zzalc.get(api);
        if (zza == null || zza.zzXf.isEmpty()) {
            return this.zzagh;
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzagh);
        set.addAll(zza.zzXf);
        return (Set<Scope>)set;
    }
    
    public Account zzqq() {
        if (this.zzTI != null) {
            return this.zzTI;
        }
        return new Account("<<default account>>", "com.google");
    }
    
    public int zzqr() {
        return this.zzagj;
    }
    
    public Set<Scope> zzqs() {
        return this.zzagh;
    }
    
    public Set<Scope> zzqt() {
        return this.zzalb;
    }
    
    public Map<Api<?>, zza> zzqu() {
        return this.zzalc;
    }
    
    public String zzqv() {
        return this.zzUW;
    }
    
    public String zzqw() {
        return this.zzagl;
    }
    
    public View zzqx() {
        return this.zzagk;
    }
    
    public zzro zzqy() {
        return this.zzald;
    }
    
    public Integer zzqz() {
        return this.zzale;
    }
    
    public static final class zza
    {
        public final Set<Scope> zzXf;
        public final boolean zzalf;
        
        public zza(final Set<Scope> set, final boolean zzalf) {
            zzx.zzz(set);
            this.zzXf = Collections.unmodifiableSet((Set<? extends Scope>)set);
            this.zzalf = zzalf;
        }
    }
}
