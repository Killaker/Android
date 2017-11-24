package com.google.android.gms.dynamic;

import android.os.*;
import java.util.*;
import com.google.android.gms.common.*;
import com.google.android.gms.common.internal.*;
import android.widget.*;
import android.content.*;
import android.view.*;
import android.app.*;

public abstract class zza<T extends LifecycleDelegate>
{
    private final zzf<T> zzavA;
    private T zzavx;
    private Bundle zzavy;
    private LinkedList<zza> zzavz;
    
    public zza() {
        this.zzavA = new zzf<T>() {
            @Override
            public void zza(final T t) {
                zza.this.zzavx = t;
                final Iterator iterator = zza.this.zzavz.iterator();
                while (iterator.hasNext()) {
                    iterator.next().zzb(zza.this.zzavx);
                }
                zza.this.zzavz.clear();
                zza.this.zzavy = null;
            }
        };
    }
    
    private void zza(final Bundle bundle, final zza zza) {
        if (this.zzavx != null) {
            zza.zzb(this.zzavx);
            return;
        }
        if (this.zzavz == null) {
            this.zzavz = new LinkedList<zza>();
        }
        this.zzavz.add(zza);
        if (bundle != null) {
            if (this.zzavy == null) {
                this.zzavy = (Bundle)bundle.clone();
            }
            else {
                this.zzavy.putAll(bundle);
            }
        }
        this.zza(this.zzavA);
    }
    
    public static void zzb(final FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        final String zzc = zzg.zzc(context, googlePlayServicesAvailable, zze.zzao(context));
        final String zzh = zzg.zzh(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)zzc);
        linearLayout.addView((View)textView);
        if (zzh != null) {
            final Button button = new Button(context);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)zzh);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    context.startActivity(GooglePlayServicesUtil.zzbv(googlePlayServicesAvailable));
                }
            });
        }
    }
    
    private void zzeJ(final int n) {
        while (!this.zzavz.isEmpty() && this.zzavz.getLast().getState() >= n) {
            this.zzavz.removeLast();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        this.zza(bundle, (zza)new zza() {
            @Override
            public int getState() {
                return 1;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.zzavx.onCreate(bundle);
            }
        });
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        this.zza(bundle, (zza)new zza() {
            @Override
            public int getState() {
                return 2;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.zzavx.onCreateView(layoutInflater, viewGroup, bundle));
            }
        });
        if (this.zzavx == null) {
            this.zza(frameLayout);
        }
        return (View)frameLayout;
    }
    
    public void onDestroy() {
        if (this.zzavx != null) {
            this.zzavx.onDestroy();
            return;
        }
        this.zzeJ(1);
    }
    
    public void onDestroyView() {
        if (this.zzavx != null) {
            this.zzavx.onDestroyView();
            return;
        }
        this.zzeJ(2);
    }
    
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.zza(bundle2, (zza)new zza() {
            @Override
            public int getState() {
                return 0;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.zzavx.onInflate(activity, bundle, bundle2);
            }
        });
    }
    
    public void onLowMemory() {
        if (this.zzavx != null) {
            this.zzavx.onLowMemory();
        }
    }
    
    public void onPause() {
        if (this.zzavx != null) {
            this.zzavx.onPause();
            return;
        }
        this.zzeJ(5);
    }
    
    public void onResume() {
        this.zza(null, (zza)new zza() {
            @Override
            public int getState() {
                return 5;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.zzavx.onResume();
            }
        });
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.zzavx != null) {
            this.zzavx.onSaveInstanceState(bundle);
        }
        else if (this.zzavy != null) {
            bundle.putAll(this.zzavy);
        }
    }
    
    public void onStart() {
        this.zza(null, (zza)new zza() {
            @Override
            public int getState() {
                return 4;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.zzavx.onStart();
            }
        });
    }
    
    public void onStop() {
        if (this.zzavx != null) {
            this.zzavx.onStop();
            return;
        }
        this.zzeJ(4);
    }
    
    protected void zza(final FrameLayout frameLayout) {
        zzb(frameLayout);
    }
    
    protected abstract void zza(final zzf<T> p0);
    
    public T zztU() {
        return this.zzavx;
    }
    
    private interface zza
    {
        int getState();
        
        void zzb(final LifecycleDelegate p0);
    }
}
