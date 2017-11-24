package com.google.android.gms.common.internal;

import java.util.*;

public abstract class zze
{
    public static final zze zzakF;
    public static final zze zzakG;
    public static final zze zzakH;
    public static final zze zzakI;
    public static final zze zzakJ;
    public static final zze zzakK;
    public static final zze zzakL;
    public static final zze zzakM;
    public static final zze zzakN;
    public static final zze zzakO;
    public static final zze zzakP;
    public static final zze zzakQ;
    public static final zze zzakR;
    public static final zze zzakS;
    public static final zze zzakT;
    
    static {
        zzakF = zza("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000 \u180e\u202f").zza(zza('\u2000', '\u200a'));
        zzakG = zza("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000").zza(zza('\u2000', '\u2006')).zza(zza('\u2008', '\u200a'));
        zzakH = zza('\0', '\u007f');
        final zze zza = zza('0', '9');
        final char[] charArray = "\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".toCharArray();
        final int length = charArray.length;
        zze zza2 = zza;
        for (final char c : charArray) {
            zza2 = zza2.zza(zza(c, (char)(c + '\t')));
        }
        zzakI = zza2;
        zzakJ = zza('\t', '\r').zza(zza('\u001c', ' ')).zza(zzc('\u1680')).zza(zzc('\u180e')).zza(zza('\u2000', '\u2006')).zza(zza('\u2008', '\u200b')).zza(zza('\u2028', '\u2029')).zza(zzc('\u205f')).zza(zzc('\u3000'));
        zzakK = new zze() {
            @Override
            public boolean zzd(final char c) {
                return Character.isDigit(c);
            }
        };
        zzakL = new zze() {
            @Override
            public boolean zzd(final char c) {
                return Character.isLetter(c);
            }
        };
        zzakM = new zze() {
            @Override
            public boolean zzd(final char c) {
                return Character.isLetterOrDigit(c);
            }
        };
        zzakN = new zze() {
            @Override
            public boolean zzd(final char c) {
                return Character.isUpperCase(c);
            }
        };
        zzakO = new zze() {
            @Override
            public boolean zzd(final char c) {
                return Character.isLowerCase(c);
            }
        };
        zzakP = zza('\0', '\u001f').zza(zza('\u007f', '\u009f'));
        zzakQ = zza('\0', ' ').zza(zza('\u007f', ' ')).zza(zzc('\u00ad')).zza(zza('\u0600', '\u0603')).zza(zza("\u06dd\u070f\u1680\u17b4\u17b5\u180e")).zza(zza('\u2000', '\u200f')).zza(zza('\u2028', '\u202f')).zza(zza('\u205f', '\u2064')).zza(zza('\u206a', '\u206f')).zza(zzc('\u3000')).zza(zza('\ud800', '\uf8ff')).zza(zza("\ufeff\ufff9\ufffa\ufffb"));
        zzakR = zza('\0', '\u04f9').zza(zzc('\u05be')).zza(zza('\u05d0', '\u05ea')).zza(zzc('\u05f3')).zza(zzc('\u05f4')).zza(zza('\u0600', '\u06ff')).zza(zza('\u0750', '\u077f')).zza(zza('\u0e00', '\u0e7f')).zza(zza('\u1e00', '\u20af')).zza(zza('\u2100', '\u213a')).zza(zza('\ufb50', '\ufdff')).zza(zza('\ufe70', '\ufeff')).zza(zza('\uff61', '\uffdc'));
        zzakS = new zze() {
            @Override
            public zze zza(final zze zze) {
                zzx.zzz(zze);
                return this;
            }
            
            @Override
            public boolean zzb(final CharSequence charSequence) {
                zzx.zzz(charSequence);
                return true;
            }
            
            @Override
            public boolean zzd(final char c) {
                return true;
            }
        };
        zzakT = new zze() {
            @Override
            public zze zza(final zze zze) {
                return zzx.zzz(zze);
            }
            
            @Override
            public boolean zzb(final CharSequence charSequence) {
                return charSequence.length() == 0;
            }
            
            @Override
            public boolean zzd(final char c) {
                return false;
            }
        };
    }
    
    public static zze zza(final char c, final char c2) {
        zzx.zzac(c2 >= c);
        return new zze() {
            @Override
            public boolean zzd(final char c) {
                return c <= c && c <= c2;
            }
        };
    }
    
    public static zze zza(final CharSequence charSequence) {
        switch (charSequence.length()) {
            default: {
                final char[] charArray = charSequence.toString().toCharArray();
                Arrays.sort(charArray);
                return new zze() {
                    @Override
                    public boolean zzd(final char c) {
                        return Arrays.binarySearch(charArray, c) >= 0;
                    }
                };
            }
            case 0: {
                return zze.zzakT;
            }
            case 1: {
                return zzc(charSequence.charAt(0));
            }
            case 2: {
                return new zze() {
                    final /* synthetic */ char zzakU = charSequence.charAt(0);
                    final /* synthetic */ char zzakV = charSequence.charAt(1);
                    
                    @Override
                    public boolean zzd(final char c) {
                        return c == this.zzakU || c == this.zzakV;
                    }
                };
            }
        }
    }
    
    public static zze zzc(final char c) {
        return new zze() {
            @Override
            public zze zza(final zze zze) {
                if (zze.zzd(c)) {
                    return zze;
                }
                return super.zza(zze);
            }
            
            @Override
            public boolean zzd(final char c) {
                return c == c;
            }
        };
    }
    
    public zze zza(final zze zze) {
        return new zza(Arrays.asList(this, zzx.zzz(zze)));
    }
    
    public boolean zzb(final CharSequence charSequence) {
        for (int i = -1 + charSequence.length(); i >= 0; --i) {
            if (!this.zzd(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public abstract boolean zzd(final char p0);
    
    private static class zza extends zze
    {
        List<zze> zzala;
        
        zza(final List<zze> zzala) {
            this.zzala = zzala;
        }
        
        @Override
        public zze zza(final zze zze) {
            final ArrayList<zze> list = new ArrayList<zze>(this.zzala);
            list.add(zzx.zzz(zze));
            return new zza(list);
        }
        
        @Override
        public boolean zzd(final char c) {
            final Iterator<zze> iterator = this.zzala.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().zzd(c)) {
                    return true;
                }
            }
            return false;
        }
    }
}
