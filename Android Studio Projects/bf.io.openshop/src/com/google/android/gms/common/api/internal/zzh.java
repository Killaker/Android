package com.google.android.gms.common.api.internal;

import android.content.*;
import java.util.concurrent.locks.*;
import com.google.android.gms.internal.*;
import java.util.concurrent.*;
import com.google.android.gms.common.*;
import android.util.*;
import android.app.*;
import java.util.*;
import com.google.android.gms.common.api.*;
import java.lang.ref.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import android.support.annotation.*;
import com.google.android.gms.signin.internal.*;

public class zzh implements zzk
{
    private final Context mContext;
    private final Lock zzXG;
    private final com.google.android.gms.common.zzc zzags;
    private final Api.zza<? extends zzrn, zzro> zzagt;
    private final Map<Api<?>, Integer> zzahA;
    private ArrayList<Future<?>> zzahB;
    private final zzl zzahj;
    private ConnectionResult zzahm;
    private int zzahn;
    private int zzaho;
    private int zzahp;
    private final Bundle zzahq;
    private final Set<Api.zzc> zzahr;
    private zzrn zzahs;
    private int zzaht;
    private boolean zzahu;
    private boolean zzahv;
    private zzp zzahw;
    private boolean zzahx;
    private boolean zzahy;
    private final com.google.android.gms.common.internal.zzf zzahz;
    
    public zzh(final zzl zzahj, final com.google.android.gms.common.internal.zzf zzahz, final Map<Api<?>, Integer> zzahA, final com.google.android.gms.common.zzc zzags, final Api.zza<? extends zzrn, zzro> zzagt, final Lock zzXG, final Context mContext) {
        this.zzaho = 0;
        this.zzahq = new Bundle();
        this.zzahr = new HashSet<Api.zzc>();
        this.zzahB = new ArrayList<Future<?>>();
        this.zzahj = zzahj;
        this.zzahz = zzahz;
        this.zzahA = zzahA;
        this.zzags = zzags;
        this.zzagt = zzagt;
        this.zzXG = zzXG;
        this.mContext = mContext;
    }
    
    private void zzZ(final boolean b) {
        if (this.zzahs != null) {
            if (((Api.zzb)this.zzahs).isConnected() && b) {
                this.zzahs.zzFG();
            }
            ((Api.zzb)this.zzahs).disconnect();
            this.zzahw = null;
        }
    }
    
    private void zza(final SignInResponse signInResponse) {
        if (!this.zzbz(0)) {
            return;
        }
        final ConnectionResult zzqY = signInResponse.zzqY();
        if (zzqY.isSuccess()) {
            final ResolveAccountResponse zzFP = signInResponse.zzFP();
            final ConnectionResult zzqY2 = zzFP.zzqY();
            if (!zzqY2.isSuccess()) {
                Log.wtf("GoogleApiClientConnecting", "Sign-in succeeded with resolve account failure: " + zzqY2, (Throwable)new Exception());
                this.zzg(zzqY2);
                return;
            }
            this.zzahv = true;
            this.zzahw = zzFP.zzqX();
            this.zzahx = zzFP.zzqZ();
            this.zzahy = zzFP.zzra();
            this.zzpv();
        }
        else {
            if (this.zzf(zzqY)) {
                this.zzpy();
                this.zzpv();
                return;
            }
            this.zzg(zzqY);
        }
    }
    
    private boolean zza(final int n, final int n2, final ConnectionResult connectionResult) {
        return (n2 != 1 || this.zze(connectionResult)) && (this.zzahm == null || n < this.zzahn);
    }
    
    private void zzb(final ConnectionResult zzahm, final Api<?> api, final int n) {
        if (n != 2) {
            final int priority = api.zzoP().getPriority();
            if (this.zza(priority, n, zzahm)) {
                this.zzahm = zzahm;
                this.zzahn = priority;
            }
        }
        this.zzahj.zzaio.put(api.zzoR(), zzahm);
    }
    
    private String zzbA(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 0: {
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            }
            case 1: {
                return "STEP_GETTING_REMOTE_SERVICE";
            }
        }
    }
    
    private boolean zzbz(final int n) {
        if (this.zzaho != n) {
            Log.i("GoogleApiClientConnecting", this.zzahj.zzagW.zzpH());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + this.zzbA(this.zzaho) + " but received callback for step " + this.zzbA(n), (Throwable)new Exception());
            this.zzg(new ConnectionResult(8, null));
            return false;
        }
        return true;
    }
    
    private boolean zze(final ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zzags.zzbu(connectionResult.getErrorCode()) != null;
    }
    
    private boolean zzf(final ConnectionResult connectionResult) {
        return this.zzaht == 2 || (this.zzaht == 1 && !connectionResult.hasResolution());
    }
    
    private void zzg(final ConnectionResult connectionResult) {
        this.zzpz();
        this.zzZ(!connectionResult.hasResolution());
        this.zzahj.zzh(connectionResult);
        this.zzahj.zzais.zzd(connectionResult);
    }
    
    private Set<Scope> zzpA() {
        if (this.zzahz == null) {
            return Collections.emptySet();
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzahz.zzqs());
        final Map<Api<?>, com.google.android.gms.common.internal.zzf.zza> zzqu = this.zzahz.zzqu();
        for (final Api<?> api : zzqu.keySet()) {
            if (!this.zzahj.zzaio.containsKey(api.zzoR())) {
                set.addAll(((com.google.android.gms.common.internal.zzf.zza)zzqu.get(api)).zzXf);
            }
        }
        return (Set<Scope>)set;
    }
    
    private boolean zzpu() {
        --this.zzahp;
        if (this.zzahp > 0) {
            return false;
        }
        if (this.zzahp < 0) {
            Log.i("GoogleApiClientConnecting", this.zzahj.zzagW.zzpH());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", (Throwable)new Exception());
            this.zzg(new ConnectionResult(8, null));
            return false;
        }
        if (this.zzahm != null) {
            this.zzahj.zzair = this.zzahn;
            this.zzg(this.zzahm);
            return false;
        }
        return true;
    }
    
    private void zzpv() {
        if (this.zzahp == 0 && (!this.zzahu || this.zzahv)) {
            this.zzpw();
        }
    }
    
    private void zzpw() {
        final ArrayList<Api.zzb> list = new ArrayList<Api.zzb>();
        this.zzaho = 1;
        this.zzahp = this.zzahj.zzahT.size();
        for (final Api.zzc zzc : this.zzahj.zzahT.keySet()) {
            if (this.zzahj.zzaio.containsKey(zzc)) {
                if (!this.zzpu()) {
                    continue;
                }
                this.zzpx();
            }
            else {
                list.add(this.zzahj.zzahT.get(zzc));
            }
        }
        if (!list.isEmpty()) {
            this.zzahB.add(zzm.zzpN().submit(new zzc(list)));
        }
    }
    
    private void zzpx() {
        this.zzahj.zzpL();
        zzm.zzpN().execute(new Runnable() {
            @Override
            public void run() {
                zzh.this.zzags.zzal(zzh.this.mContext);
            }
        });
        if (this.zzahs != null) {
            if (this.zzahx) {
                this.zzahs.zza(this.zzahw, this.zzahy);
            }
            this.zzZ(false);
        }
        final Iterator<Api.zzc<?>> iterator = this.zzahj.zzaio.keySet().iterator();
        while (iterator.hasNext()) {
            this.zzahj.zzahT.get((Api.zzc)iterator.next()).disconnect();
        }
        Bundle zzahq;
        if (this.zzahq.isEmpty()) {
            zzahq = null;
        }
        else {
            zzahq = this.zzahq;
        }
        this.zzahj.zzais.zzi(zzahq);
    }
    
    private void zzpy() {
        this.zzahu = false;
        this.zzahj.zzagW.zzahU = Collections.emptySet();
        for (final Api.zzc zzc : this.zzahr) {
            if (!this.zzahj.zzaio.containsKey(zzc)) {
                this.zzahj.zzaio.put(zzc, new ConnectionResult(17, null));
            }
        }
    }
    
    private void zzpz() {
        final Iterator<Future<?>> iterator = this.zzahB.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel(true);
        }
        this.zzahB.clear();
    }
    
    @Override
    public void begin() {
        this.zzahj.zzaio.clear();
        this.zzahu = false;
        this.zzahm = null;
        this.zzaho = 0;
        this.zzaht = 2;
        this.zzahv = false;
        this.zzahx = false;
        final HashMap<Api.zzb, zza> hashMap = new HashMap<Api.zzb, zza>();
        final Iterator<Api<?>> iterator = this.zzahA.keySet().iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            final Api<?> api = iterator.next();
            final Api.zzb zzb = this.zzahj.zzahT.get(api.zzoR());
            final int intValue = this.zzahA.get(api);
            boolean b2;
            if (api.zzoP().getPriority() == 1) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final boolean b3 = b2 | b;
            if (zzb.zzmE()) {
                this.zzahu = true;
                if (intValue < this.zzaht) {
                    this.zzaht = intValue;
                }
                if (intValue != 0) {
                    this.zzahr.add((Api.zzc)api.zzoR());
                }
            }
            hashMap.put(zzb, new zza(this, api, intValue));
            b = b3;
        }
        if (b) {
            this.zzahu = false;
        }
        if (this.zzahu) {
            this.zzahz.zza(this.zzahj.zzagW.getSessionId());
            final zze zze = new zze();
            this.zzahs = (zzrn)this.zzagt.zza(this.mContext, this.zzahj.zzagW.getLooper(), this.zzahz, this.zzahz.zzqy(), zze, zze);
        }
        this.zzahp = this.zzahj.zzahT.size();
        this.zzahB.add(zzm.zzpN().submit(new zzb((Map<Api.zzb, GoogleApiClient.zza>)hashMap)));
    }
    
    @Override
    public void connect() {
    }
    
    @Override
    public boolean disconnect() {
        this.zzpz();
        this.zzZ(true);
        this.zzahj.zzh(null);
        return true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.zzbz(1)) {
            if (bundle != null) {
                this.zzahq.putAll(bundle);
            }
            if (this.zzpu()) {
                this.zzpx();
            }
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzg(new ConnectionResult(8, null));
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends com.google.android.gms.common.api.internal.zza.zza<R, A>> T zza(final T t) {
        this.zzahj.zzagW.zzahN.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
        if (this.zzbz(1)) {
            this.zzb(connectionResult, api, n);
            if (this.zzpu()) {
                this.zzpx();
            }
        }
    }
    
    @Override
    public <A extends Api.zzb, T extends com.google.android.gms.common.api.internal.zza.zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
    
    private static class zza implements GoogleApiClient.zza
    {
        private final Api<?> zzagT;
        private final int zzagU;
        private final WeakReference<zzh> zzahD;
        
        public zza(final zzh zzh, final Api<?> zzagT, final int zzagU) {
            this.zzahD = new WeakReference<zzh>(zzh);
            this.zzagT = zzagT;
            this.zzagU = zzagU;
        }
        
        @Override
        public void zza(@NonNull final ConnectionResult connectionResult) {
            final zzh zzh = this.zzahD.get();
            if (zzh == null) {
                return;
            }
            final Looper myLooper = Looper.myLooper();
            final Looper looper = zzh.zzahj.zzagW.getLooper();
            boolean b = false;
            if (myLooper == looper) {
                b = true;
            }
            zzx.zza(b, (Object)"onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zzh.zzXG.lock();
            try {
                if (!zzh.zzbz(0)) {
                    return;
                }
                if (!connectionResult.isSuccess()) {
                    zzh.zzb(connectionResult, this.zzagT, this.zzagU);
                }
                if (zzh.zzpu()) {
                    zzh.zzpv();
                }
            }
            finally {
                zzh.zzXG.unlock();
            }
        }
    }
    
    private class zzb extends zzf
    {
        private final Map<Api.zzb, GoogleApiClient.zza> zzahE;
        
        public zzb(final Map<Api.zzb, GoogleApiClient.zza> zzahE) {
            this.zzahE = zzahE;
        }
        
        @WorkerThread
        public void zzpt() {
            final int googlePlayServicesAvailable = zzh.this.zzags.isGooglePlayServicesAvailable(zzh.this.mContext);
            if (googlePlayServicesAvailable != 0) {
                zzh.this.zzahj.zza((zzl.zza)new zzl.zza(zzh.this) {
                    final /* synthetic */ ConnectionResult zzahF = new ConnectionResult(googlePlayServicesAvailable, null);
                    
                    public void zzpt() {
                        zzh.this.zzg(this.zzahF);
                    }
                });
            }
            else {
                if (zzh.this.zzahu) {
                    zzh.this.zzahs.connect();
                }
                for (final Api.zzb zzb : this.zzahE.keySet()) {
                    zzb.zza(this.zzahE.get(zzb));
                }
            }
        }
    }
    
    private class zzc extends zzf
    {
        private final ArrayList<Api.zzb> zzahH;
        
        public zzc(final ArrayList<Api.zzb> zzahH) {
            this.zzahH = zzahH;
        }
        
        @WorkerThread
        public void zzpt() {
            zzh.this.zzahj.zzagW.zzahU = zzh.this.zzpA();
            final Iterator<Api.zzb> iterator = this.zzahH.iterator();
            while (iterator.hasNext()) {
                iterator.next().zza(zzh.this.zzahw, zzh.this.zzahj.zzagW.zzahU);
            }
        }
    }
    
    private static class zzd extends zzb
    {
        private final WeakReference<zzh> zzahD;
        
        zzd(final zzh zzh) {
            this.zzahD = new WeakReference<zzh>(zzh);
        }
        
        @BinderThread
        @Override
        public void zzb(final SignInResponse signInResponse) {
            final zzh zzh = this.zzahD.get();
            if (zzh == null) {
                return;
            }
            zzh.zzahj.zza((zzl.zza)new zzl.zza(zzh) {
                public void zzpt() {
                    zzh.zza(signInResponse);
                }
            });
        }
    }
    
    private class zze implements ConnectionCallbacks, OnConnectionFailedListener
    {
        @Override
        public void onConnected(final Bundle bundle) {
            zzh.this.zzahs.zza(new zzd(zzh.this));
        }
        
        @Override
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            zzh.this.zzXG.lock();
            try {
                if (zzh.this.zzf(connectionResult)) {
                    zzh.this.zzpy();
                    zzh.this.zzpv();
                }
                else {
                    zzh.this.zzg(connectionResult);
                }
            }
            finally {
                zzh.this.zzXG.unlock();
            }
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
        }
    }
    
    private abstract class zzf implements Runnable
    {
        @WorkerThread
        @Override
        public void run() {
            zzh.this.zzXG.lock();
            try {
                if (Thread.interrupted()) {
                    return;
                }
                this.zzpt();
            }
            catch (RuntimeException ex) {
                zzh.this.zzahj.zza(ex);
            }
            finally {
                zzh.this.zzXG.unlock();
            }
        }
        
        @WorkerThread
        protected abstract void zzpt();
    }
}
