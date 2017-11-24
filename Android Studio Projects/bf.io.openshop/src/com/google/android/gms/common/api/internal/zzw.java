package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.*;
import android.support.v4.app.*;
import android.util.*;
import com.google.android.gms.common.api.*;
import java.io.*;
import android.os.*;
import com.google.android.gms.common.*;
import android.app.*;
import android.content.*;
import android.support.annotation.*;

public class zzw extends Fragment implements DialogInterface$OnCancelListener
{
    private boolean mStarted;
    private int zzaiA;
    private ConnectionResult zzaiB;
    private final Handler zzaiC;
    protected zzn zzaiD;
    private final SparseArray<zza> zzaiE;
    private boolean zzaiz;
    
    public zzw() {
        this.zzaiA = -1;
        this.zzaiC = new Handler(Looper.getMainLooper());
        this.zzaiE = (SparseArray<zza>)new SparseArray();
    }
    
    @Nullable
    public static zzw zza(final FragmentActivity fragmentActivity) {
        zzx.zzcD("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            zzw zzw = (zzw)supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFrag");
            if (zzw == null || zzw.isRemoving()) {
                zzw = null;
            }
            return zzw;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFrag is not a SupportLifecycleFragment", ex);
        }
    }
    
    private void zza(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFrag", "Unresolved error while connecting client. Stopping auto-manage.");
        final zza zza = (zza)this.zzaiE.get(n);
        if (zza != null) {
            this.zzbD(n);
            final GoogleApiClient.OnConnectionFailedListener zzaiH = zza.zzaiH;
            if (zzaiH != null) {
                zzaiH.onConnectionFailed(connectionResult);
            }
        }
        this.zzpP();
    }
    
    public static zzw zzb(final FragmentActivity fragmentActivity) {
        zzw zzw = zza(fragmentActivity);
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        if (zzw == null) {
            zzw = zzpO();
            if (zzw == null) {
                Log.w("GmsSupportLifecycleFrag", "Unable to find connection error message resources (Did you include play-services-base and the proper proguard rules?); error dialogs may be unavailable.");
                zzw = new zzw();
            }
            supportFragmentManager.beginTransaction().add(zzw, "GmsSupportLifecycleFrag").commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
        }
        return zzw;
    }
    
    private static String zzi(final ConnectionResult connectionResult) {
        return connectionResult.getErrorMessage() + " (" + connectionResult.getErrorCode() + ": " + zze.getErrorString(connectionResult.getErrorCode()) + ')';
    }
    
    @Nullable
    private static zzw zzpO() {
    Label_0045_Outer:
        while (true) {
            Label_0027: {
                Class<?> forName = null;
                try {
                    forName = Class.forName("com.google.android.gms.common.api.internal.SupportLifecycleFragmentImpl");
                    if (forName != null) {
                        final Class<?> clazz = forName;
                        final Object o = clazz.newInstance();
                        final zzw zzw = (zzw)o;
                        return zzw;
                    }
                    goto Label_0069;
                }
                catch (ClassNotFoundException ex) {}
                catch (LinkageError linkageError) {
                    break Label_0027;
                }
                catch (SecurityException linkageError) {
                    break Label_0027;
                }
                try {
                    final Class<?> clazz = forName;
                    final Object o = clazz.newInstance();
                    final zzw zzw2;
                    final zzw zzw = zzw2 = (zzw)o;
                    return zzw2;
                    // iftrue(Label_0045:, !Log.isLoggable("GmsSupportLifecycleFrag", 3))
                    while (true) {
                        Block_8: {
                            break Block_8;
                            forName = null;
                            continue Label_0045_Outer;
                        }
                        final LinkageError linkageError;
                        Log.d("GmsSupportLifecycleFrag", "Unable to find SupportLifecycleFragmentImpl class", (Throwable)linkageError);
                        continue;
                    }
                }
                catch (java.lang.InstantiationException ex2) {}
                catch (RuntimeException ex3) {}
                catch (IllegalAccessException ex4) {}
                catch (ExceptionInInitializerError exceptionInInitializerError) {}
            }
            break;
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        for (int i = 0; i < this.zzaiE.size(); ++i) {
            ((zza)this.zzaiE.valueAt(i)).dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        boolean b = true;
        Label_0031: {
            switch (n) {
                case 2: {
                    if (this.zzpQ().isGooglePlayServicesAvailable((Context)this.getActivity()) == 0) {
                        break Label_0031;
                    }
                    break;
                }
                case 1: {
                    if (n2 == -1) {
                        break Label_0031;
                    }
                    if (n2 == 0) {
                        this.zzaiB = new ConnectionResult(13, null);
                        break;
                    }
                    break;
                }
            }
            b = false;
        }
        if (b) {
            this.zzpP();
            return;
        }
        this.zza(this.zzaiA, this.zzaiB);
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zza(this.zzaiA, new ConnectionResult(13, null));
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzaiz = bundle.getBoolean("resolving_error", false);
            this.zzaiA = bundle.getInt("failed_client_id", -1);
            if (this.zzaiA >= 0) {
                this.zzaiB = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution"));
            }
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzaiz);
        if (this.zzaiA >= 0) {
            bundle.putInt("failed_client_id", this.zzaiA);
            bundle.putInt("failed_status", this.zzaiB.getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)this.zzaiB.getResolution());
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.mStarted = true;
        if (!this.zzaiz) {
            for (int i = 0; i < this.zzaiE.size(); ++i) {
                ((zza)this.zzaiE.valueAt(i)).zzaiG.connect();
            }
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.mStarted = false;
        for (int i = 0; i < this.zzaiE.size(); ++i) {
            ((zza)this.zzaiE.valueAt(i)).zzaiG.disconnect();
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzx.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzx.zza(this.zzaiE.indexOfKey(n) < 0, (Object)("Already managing a GoogleApiClient with id " + n));
        this.zzaiE.put(n, (Object)new zza(n, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.zzaiz) {
            googleApiClient.connect();
        }
    }
    
    protected void zzb(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFrag", "Failed to connect due to user resolvable error " + zzi(connectionResult));
        this.zza(n, connectionResult);
    }
    
    public void zzbD(final int n) {
        final zza zza = (zza)this.zzaiE.get(n);
        this.zzaiE.remove(n);
        if (zza != null) {
            zza.zzpR();
        }
    }
    
    protected void zzc(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFrag", "Unable to connect, GooglePlayServices is updating.");
        this.zza(n, connectionResult);
    }
    
    protected void zzpP() {
        this.zzaiz = false;
        this.zzaiA = -1;
        this.zzaiB = null;
        if (this.zzaiD != null) {
            this.zzaiD.unregister();
            this.zzaiD = null;
        }
        for (int i = 0; i < this.zzaiE.size(); ++i) {
            ((zza)this.zzaiE.valueAt(i)).zzaiG.connect();
        }
    }
    
    protected zzc zzpQ() {
        return zzc.zzoK();
    }
    
    private class zza implements OnConnectionFailedListener
    {
        public final int zzaiF;
        public final GoogleApiClient zzaiG;
        public final OnConnectionFailedListener zzaiH;
        
        public zza(final int zzaiF, final GoogleApiClient zzaiG, final OnConnectionFailedListener zzaiH) {
            this.zzaiF = zzaiF;
            this.zzaiG = zzaiG;
            this.zzaiH = zzaiH;
            zzaiG.registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
        }
        
        public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
            printWriter.append(s).append("GoogleApiClient #").print(this.zzaiF);
            printWriter.println(":");
            this.zzaiG.dump(s + "  ", fileDescriptor, printWriter, array);
        }
        
        @Override
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            zzw.this.zzaiC.post((Runnable)new zzb(this.zzaiF, connectionResult));
        }
        
        public void zzpR() {
            this.zzaiG.unregisterConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
            this.zzaiG.disconnect();
        }
    }
    
    private class zzb implements Runnable
    {
        private final int zzaiJ;
        private final ConnectionResult zzaiK;
        
        public zzb(final int zzaiJ, final ConnectionResult zzaiK) {
            this.zzaiJ = zzaiJ;
            this.zzaiK = zzaiK;
        }
        
        @MainThread
        @Override
        public void run() {
            if (!zzw.this.mStarted || zzw.this.zzaiz) {
                return;
            }
            zzw.this.zzaiz = true;
            zzw.this.zzaiA = this.zzaiJ;
            zzw.this.zzaiB = this.zzaiK;
            if (this.zzaiK.hasResolution()) {
                try {
                    this.zzaiK.startResolutionForResult(zzw.this.getActivity(), 1 + (1 + zzw.this.getActivity().getSupportFragmentManager().getFragments().indexOf(zzw.this) << 16));
                    return;
                }
                catch (IntentSender$SendIntentException ex) {
                    zzw.this.zzpP();
                    return;
                }
            }
            if (zzw.this.zzpQ().isUserResolvableError(this.zzaiK.getErrorCode())) {
                zzw.this.zzb(this.zzaiJ, this.zzaiK);
                return;
            }
            if (this.zzaiK.getErrorCode() == 18) {
                zzw.this.zzc(this.zzaiJ, this.zzaiK);
                return;
            }
            zzw.this.zza(this.zzaiJ, this.zzaiK);
        }
    }
}
