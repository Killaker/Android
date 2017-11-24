package com.google.android.gms.analytics.internal;

import android.text.*;
import org.xmlpull.v1.*;
import java.io.*;
import android.content.res.*;

abstract class zzq<T extends zzp> extends zzc
{
    zza<T> zzRx;
    
    public zzq(final zzf zzf, final zza<T> zzRx) {
        super(zzf);
        this.zzRx = zzRx;
    }
    
    private T zza(final XmlResourceParser xmlResourceParser) {
        while (true) {
            while (true) {
                String lowerCase = null;
                try {
                    xmlResourceParser.next();
                    for (int i = xmlResourceParser.getEventType(); i != 1; i = xmlResourceParser.next()) {
                        if (xmlResourceParser.getEventType() == 2) {
                            lowerCase = xmlResourceParser.getName().toLowerCase();
                            if (lowerCase.equals("screenname")) {
                                final String attributeValue = xmlResourceParser.getAttributeValue((String)null, "name");
                                final String trim = xmlResourceParser.nextText().trim();
                                if (!TextUtils.isEmpty((CharSequence)attributeValue) && !TextUtils.isEmpty((CharSequence)trim)) {
                                    this.zzRx.zzj(attributeValue, trim);
                                }
                            }
                            else {
                                if (!lowerCase.equals("string")) {
                                    goto Label_0193;
                                }
                                final String attributeValue2 = xmlResourceParser.getAttributeValue((String)null, "name");
                                final String trim2 = xmlResourceParser.nextText().trim();
                                if (!TextUtils.isEmpty((CharSequence)attributeValue2) && trim2 != null) {
                                    this.zzRx.zzk(attributeValue2, trim2);
                                }
                            }
                        }
                    }
                    goto Label_0183;
                }
                catch (XmlPullParserException ex) {
                    this.zze("Error parsing tracker configuration file", ex);
                }
                catch (IOException ex2) {
                    this.zze("Error parsing tracker configuration file", ex2);
                    goto Label_0183;
                }
                try {
                    final String s;
                    final String s2;
                    this.zzRx.zzf(s, Boolean.parseBoolean(s2));
                    continue;
                }
                catch (NumberFormatException ex4) {}
                if (!lowerCase.equals("integer")) {
                    continue;
                }
                final String attributeValue3 = xmlResourceParser.getAttributeValue((String)null, "name");
                final String trim3 = xmlResourceParser.nextText().trim();
                if (!TextUtils.isEmpty((CharSequence)attributeValue3) && !TextUtils.isEmpty((CharSequence)trim3)) {
                    try {
                        this.zzRx.zzc(attributeValue3, Integer.parseInt(trim3));
                        continue;
                    }
                    catch (NumberFormatException ex3) {
                        this.zzc("Error parsing int configuration value", trim3, ex3);
                        continue;
                    }
                    continue;
                }
                continue;
            }
        }
    }
    
    public T zzah(final int n) {
        try {
            return this.zza(this.zzji().zzjx().getResources().getXml(n));
        }
        catch (Resources$NotFoundException ex) {
            this.zzd("inflate() called with unknown resourceId", ex);
            return null;
        }
    }
    
    public interface zza<U extends zzp>
    {
        void zzc(final String p0, final int p1);
        
        void zzf(final String p0, final boolean p1);
        
        void zzj(final String p0, final String p1);
        
        void zzk(final String p0, final String p1);
        
        U zzkq();
    }
}
