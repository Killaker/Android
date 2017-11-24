package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.*;
import java.util.*;

public final class Batch extends zzb<BatchResult>
{
    private int zzafZ;
    private boolean zzaga;
    private boolean zzagb;
    private final PendingResult<?>[] zzagc;
    private final Object zzpV;
    
    private Batch(final List<PendingResult<?>> list, final GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzpV = new Object();
        this.zzafZ = list.size();
        this.zzagc = (PendingResult<?>[])new PendingResult[this.zzafZ];
        if (list.isEmpty()) {
            this.zza(new BatchResult(Status.zzagC, this.zzagc));
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
                (this.zzagc[i] = list.get(i)).zza((PendingResult.zza)new PendingResult.zza() {
                    @Override
                    public void zzu(final Status status) {
                        while (true) {
                        Label_0101:
                            while (true) {
                                synchronized (Batch.this.zzpV) {
                                    if (Batch.this.isCanceled()) {
                                        return;
                                    }
                                    if (status.isCanceled()) {
                                        Batch.this.zzagb = true;
                                        Batch.this.zzafZ--;
                                        if (Batch.this.zzafZ == 0) {
                                            if (!Batch.this.zzagb) {
                                                break Label_0101;
                                            }
                                            Batch.this.cancel();
                                        }
                                        return;
                                    }
                                }
                                if (!status.isSuccess()) {
                                    Batch.this.zzaga = true;
                                    continue;
                                }
                                continue;
                            }
                            Status zzagC;
                            if (Batch.this.zzaga) {
                                zzagC = new Status(13);
                            }
                            else {
                                zzagC = Status.zzagC;
                            }
                            Batch.this.zza(new BatchResult(zzagC, Batch.this.zzagc));
                        }
                    }
                });
            }
        }
    }
    
    @Override
    public void cancel() {
        super.cancel();
        final PendingResult<?>[] zzagc = this.zzagc;
        for (int length = zzagc.length, i = 0; i < length; ++i) {
            zzagc[i].cancel();
        }
    }
    
    public BatchResult createFailedResult(final Status status) {
        return new BatchResult(status, this.zzagc);
    }
    
    public static final class Builder
    {
        private GoogleApiClient zzaaj;
        private List<PendingResult<?>> zzage;
        
        public Builder(final GoogleApiClient zzaaj) {
            this.zzage = new ArrayList<PendingResult<?>>();
            this.zzaaj = zzaaj;
        }
        
        public <R extends Result> BatchResultToken<R> add(final PendingResult<R> pendingResult) {
            final BatchResultToken<R> batchResultToken = new BatchResultToken<R>(this.zzage.size());
            this.zzage.add(pendingResult);
            return batchResultToken;
        }
        
        public Batch build() {
            return new Batch(this.zzage, this.zzaaj, null);
        }
    }
}
