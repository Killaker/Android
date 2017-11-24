package com.google.android.gms.internal;

import android.widget.*;
import android.net.*;
import android.graphics.*;

public final class zzmc extends ImageView
{
    private Uri zzakr;
    private int zzaks;
    private int zzakt;
    private zza zzaku;
    private int zzakv;
    private float zzakw;
    
    protected void onDraw(final Canvas canvas) {
        if (this.zzaku != null) {
            canvas.clipPath(this.zzaku.zzl(this.getWidth(), this.getHeight()));
        }
        super.onDraw(canvas);
        if (this.zzakt != 0) {
            canvas.drawColor(this.zzakt);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        int measuredHeight = 0;
        int measuredWidth = 0;
        switch (this.zzakv) {
            default: {
                return;
            }
            case 1: {
                measuredHeight = this.getMeasuredHeight();
                measuredWidth = (int)(measuredHeight * this.zzakw);
                break;
            }
            case 2: {
                measuredWidth = this.getMeasuredWidth();
                measuredHeight = (int)(measuredWidth / this.zzakw);
                break;
            }
        }
        this.setMeasuredDimension(measuredWidth, measuredHeight);
    }
    
    public void zzbO(final int zzaks) {
        this.zzaks = zzaks;
    }
    
    public void zzm(final Uri zzakr) {
        this.zzakr = zzakr;
    }
    
    public int zzqp() {
        return this.zzaks;
    }
    
    public interface zza
    {
        Path zzl(final int p0, final int p1);
    }
}
