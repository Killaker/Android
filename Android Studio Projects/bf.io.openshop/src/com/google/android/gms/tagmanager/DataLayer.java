package com.google.android.gms.tagmanager;

import java.util.regex.*;
import java.io.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;
import java.lang.reflect.*;
import sun.misc.*;
import java.util.*;

public class DataLayer
{
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT;
    static final String[] zzbir;
    private static final Pattern zzbis;
    private final ConcurrentHashMap<zzb, Integer> zzbit;
    private final Map<String, Object> zzbiu;
    private final ReentrantLock zzbiv;
    private final LinkedList<Map<String, Object>> zzbiw;
    private final zzc zzbix;
    private final CountDownLatch zzbiy;
    
    static {
        OBJECT_NOT_PRESENT = new Object();
        zzbir = "gtm.lifetime".toString().split("\\.");
        zzbis = Pattern.compile("(\\d+)\\s*([smhd]?)");
    }
    
    DataLayer() {
        this((zzc)new zzc() {
            @Override
            public void zza(final zzc.zza zza) {
                zza.zzB(new ArrayList<DataLayer.zza>());
            }
            
            @Override
            public void zza(final List<DataLayer.zza> list, final long n) {
            }
            
            @Override
            public void zzfZ(final String s) {
            }
        });
    }
    
    DataLayer(final zzc zzbix) {
        this.zzbix = zzbix;
        this.zzbit = new ConcurrentHashMap<zzb, Integer>();
        this.zzbiu = new HashMap<String, Object>();
        this.zzbiv = new ReentrantLock();
        this.zzbiw = new LinkedList<Map<String, Object>>();
        this.zzbiy = new CountDownLatch(1);
        this.zzGn();
    }
    
    public static List<Object> listOf(final Object... array) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length; ++i) {
            list.add(array[i]);
        }
        return list;
    }
    
    public static Map<String, Object> mapOf(final Object... array) {
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i += 2) {
            if (!(array[i] instanceof String)) {
                throw new IllegalArgumentException("key is not a string: " + array[i]);
            }
            hashMap.put((String)array[i], array[i + 1]);
        }
        return hashMap;
    }
    
    private void zzGn() {
        this.zzbix.zza((zzc.zza)new zzc.zza() {
            @Override
            public void zzB(final List<DataLayer.zza> list) {
                for (final DataLayer.zza zza : list) {
                    DataLayer.this.zzS(DataLayer.this.zzn(zza.zzvs, zza.zzNc));
                }
                DataLayer.this.zzbiy.countDown();
            }
        });
    }
    
    private void zzGo() {
        int n = 0;
        while (true) {
            final Map<String, Object> map = this.zzbiw.poll();
            if (map == null) {
                return;
            }
            this.zzX(map);
            final int n2 = n + 1;
            if (n2 > 500) {
                this.zzbiw.clear();
                throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
            }
            n = n2;
        }
    }
    
    private void zzS(final Map<String, Object> map) {
        this.zzbiv.lock();
        try {
            this.zzbiw.offer(map);
            if (this.zzbiv.getHoldCount() == 1) {
                this.zzGo();
            }
            this.zzT(map);
        }
        finally {
            this.zzbiv.unlock();
        }
    }
    
    private void zzT(final Map<String, Object> map) {
        final Long zzU = this.zzU(map);
        if (zzU == null) {
            return;
        }
        final List<zza> zzW = this.zzW(map);
        zzW.remove("gtm.lifetime");
        this.zzbix.zza(zzW, zzU);
    }
    
    private Long zzU(final Map<String, Object> map) {
        final Object zzV = this.zzV(map);
        if (zzV == null) {
            return null;
        }
        return zzfY(zzV.toString());
    }
    
    private Object zzV(final Map<String, Object> map) {
        final String[] zzbir = DataLayer.zzbir;
        final int length = zzbir.length;
        int i = 0;
        Map<String, Object> map2 = map;
        while (i < length) {
            final String s = zzbir[i];
            if (!(map2 instanceof Map)) {
                map2 = null;
                break;
            }
            final Object value = map2.get(s);
            ++i;
            map2 = (Map<String, Object>)value;
        }
        return map2;
    }
    
    private List<zza> zzW(final Map<String, Object> map) {
        final ArrayList<zza> list = new ArrayList<zza>();
        this.zza(map, "", list);
        return list;
    }
    
    private void zzX(final Map<String, Object> map) {
        synchronized (this.zzbiu) {
            for (final String s : map.keySet()) {
                this.zzd(this.zzn(s, map.get(s)), this.zzbiu);
            }
        }
        // monitorexit(map2)
        this.zzY(map);
    }
    
    private void zzY(final Map<String, Object> map) {
        final Iterator<zzb> iterator = this.zzbit.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().zzQ(map);
        }
    }
    
    private void zza(final Map<String, Object> map, final String s, final Collection<zza> collection) {
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            final StringBuilder append = new StringBuilder().append(s);
            String s2;
            if (s.length() == 0) {
                s2 = "";
            }
            else {
                s2 = ".";
            }
            final String string = append.append(s2).append(entry.getKey()).toString();
            if (entry.getValue() instanceof Map) {
                this.zza(entry.getValue(), string, collection);
            }
            else {
                if (string.equals("gtm.lifetime")) {
                    continue;
                }
                collection.add(new zza(string, entry.getValue()));
            }
        }
    }
    
    static Long zzfY(final String s) {
        final Matcher matcher = DataLayer.zzbis.matcher(s);
        if (!matcher.matches()) {
            zzbg.zzaJ("unknown _lifetime: " + s);
            return null;
        }
        long long1;
        while (true) {
            try {
                long1 = Long.parseLong(matcher.group(1));
                if (long1 <= 0L) {
                    zzbg.zzaJ("non-positive _lifetime: " + s);
                    return null;
                }
            }
            catch (NumberFormatException ex) {
                zzbg.zzaK("illegal number in _lifetime value: " + s);
                long1 = 0L;
                continue;
            }
            break;
        }
        final String group = matcher.group(2);
        if (group.length() == 0) {
            return long1;
        }
        switch (group.charAt(0)) {
            default: {
                zzbg.zzaK("unknown units in _lifetime: " + s);
                return null;
            }
            case 's': {
                return long1 * 1000L;
            }
            case 'm': {
                return 60L * (long1 * 1000L);
            }
            case 'h': {
                return 60L * (60L * (long1 * 1000L));
            }
            case 'd': {
                return 24L * (60L * (60L * (long1 * 1000L)));
            }
        }
    }
    
    public Object get(final String s) {
        while (true) {
            while (true) {
                int n;
                final Object value;
                synchronized (this.zzbiu) {
                    final Map<String, Object> zzbiu = this.zzbiu;
                    final String[] split = s.split("\\.");
                    final int length = split.length;
                    final Object o = zzbiu;
                    n = 0;
                    if (n >= length) {
                        return o;
                    }
                    final String s2 = split[n];
                    if (!(o instanceof Map)) {
                        return null;
                    }
                    value = ((Map<String, Object>)o).get(s2);
                    if (value == null) {
                        return null;
                    }
                }
                ++n;
                final Object o = value;
                continue;
            }
        }
    }
    
    public void push(final String s, final Object o) {
        this.push(this.zzn(s, o));
    }
    
    public void push(final Map<String, Object> map) {
        while (true) {
            try {
                this.zzbiy.await();
                this.zzS(map);
            }
            catch (InterruptedException ex) {
                zzbg.zzaK("DataLayer.push: unexpected InterruptedException");
                continue;
            }
            break;
        }
    }
    
    public void pushEvent(final String s, final Map<String, Object> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>((Map<? extends String, ? extends String>)map);
        hashMap.put("event", s);
        this.push((Map<String, Object>)hashMap);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb;
        synchronized (this.zzbiu) {
            sb = new StringBuilder();
            for (final Map.Entry<String, Object> entry : this.zzbiu.entrySet()) {
                sb.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
            }
        }
        // monitorexit(map)
        return sb.toString();
    }
    
    void zza(final zzb zzb) {
        this.zzbit.put(zzb, 0);
    }
    
    void zzb(final List<Object> list, final List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); ++i) {
            final ArrayList<Object> value = list.get(i);
            if (value instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList<Object>());
                }
                this.zzb(value, list2.get(i));
            }
            else if (value instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                this.zzd((Map<String, Object>)value, (Map)list2.get(i));
            }
            else if (value != DataLayer.OBJECT_NOT_PRESENT) {
                list2.set(i, value);
            }
        }
    }
    
    void zzd(final Map<String, Object> map, final Map<String, Object> map2) {
        for (final String s : map.keySet()) {
            final ArrayList<Object> value = map.get(s);
            if (value instanceof List) {
                if (!(map2.get(s) instanceof List)) {
                    map2.put(s, new ArrayList<Object>());
                }
                this.zzb(value, map2.get(s));
            }
            else if (value instanceof Map) {
                if (!(map2.get(s) instanceof Map)) {
                    map2.put(s, new HashMap());
                }
                this.zzd((Map<String, Object>)value, (Map)map2.get(s));
            }
            else {
                map2.put(s, value);
            }
        }
    }
    
    void zzfX(final String s) {
        this.push(s, null);
        this.zzbix.zzfZ(s);
    }
    
    Map<String, Object> zzn(final String s, final Object o) {
        final HashMap<String, Map<String, Object>> hashMap = (HashMap<String, Map<String, Object>>)new HashMap<String, Map<String, Map<String, Object>>>();
        final String[] split = s.toString().split("\\.");
        int i = 0;
        Map<String, Object> map = (Map<String, Object>)hashMap;
        while (i < -1 + split.length) {
            final HashMap<String, Map<String, Map<String, Object>>> hashMap2 = new HashMap<String, Map<String, Map<String, Object>>>();
            map.put(split[i], hashMap2);
            ++i;
            map = (Map<String, Object>)hashMap2;
        }
        map.put(split[-1 + split.length], o);
        return (Map<String, Object>)hashMap;
    }
    
    static final class zza
    {
        public final Object zzNc;
        public final String zzvs;
        
        zza(final String zzvs, final Object zzNc) {
            this.zzvs = zzvs;
            this.zzNc = zzNc;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof zza) {
                final zza zza = (zza)o;
                if (this.zzvs.equals(zza.zzvs) && this.zzNc.equals(zza.zzNc)) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(new Integer[] { this.zzvs.hashCode(), this.zzNc.hashCode() });
        }
        
        @Override
        public String toString() {
            return "Key: " + this.zzvs + " value: " + this.zzNc.toString();
        }
    }
    
    interface zzb
    {
        void zzQ(final Map<String, Object> p0);
    }
    
    interface zzc
    {
        void zza(final zza p0);
        
        void zza(final List<DataLayer.zza> p0, final long p1);
        
        void zzfZ(final String p0);
        
        public interface zza
        {
            void zzB(final List<DataLayer.zza> p0);
        }
    }
}
