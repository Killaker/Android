package com.google.android.gms.tagmanager;

import android.content.*;
import com.google.android.gms.internal.*;
import java.util.regex.*;
import com.google.android.gms.analytics.*;
import com.google.android.gms.analytics.ecommerce.*;
import java.util.*;

public class zzdg extends zzdd
{
    private static final String ID;
    private static final String zzblN;
    private static final String zzblO;
    private static final String zzblP;
    private static final String zzblQ;
    private static final String zzblR;
    private static final String zzblS;
    private static final String zzblT;
    private static final String zzblU;
    private static final String zzblV;
    private static final List<String> zzblW;
    private static final Pattern zzblX;
    private static final Pattern zzblY;
    private static Map<String, String> zzblZ;
    private static Map<String, String> zzbma;
    private final DataLayer zzbhN;
    private final Set<String> zzbmb;
    private final zzdc zzbmc;
    
    static {
        ID = zzad.zzcZ.toString();
        zzblN = zzae.zzdG.toString();
        zzblO = zzae.zzdQ.toString();
        zzblP = zzae.zzfn.toString();
        zzblQ = zzae.zzfh.toString();
        zzblR = zzae.zzfg.toString();
        zzblS = zzae.zzdP.toString();
        zzblT = zzae.zzhO.toString();
        zzblU = zzae.zzhR.toString();
        zzblV = zzae.zzhT.toString();
        zzblW = Arrays.asList("detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund");
        zzblX = Pattern.compile("dimension(\\d+)");
        zzblY = Pattern.compile("metric(\\d+)");
    }
    
    public zzdg(final Context context, final DataLayer dataLayer) {
        this(context, dataLayer, new zzdc(context));
    }
    
    zzdg(final Context context, final DataLayer zzbhN, final zzdc zzbmc) {
        super(zzdg.ID, new String[0]);
        this.zzbhN = zzbhN;
        this.zzbmc = zzbmc;
        (this.zzbmb = new HashSet<String>()).add("");
        this.zzbmb.add("0");
        this.zzbmb.add("false");
    }
    
    private Double zzV(final Object o) {
        if (o instanceof String) {
            try {
                return Double.valueOf((String)o);
            }
            catch (NumberFormatException ex) {
                throw new RuntimeException("Cannot convert the object to Double: " + ex.getMessage());
            }
        }
        if (o instanceof Integer) {
            return (double)o;
        }
        if (o instanceof Double) {
            return (Double)o;
        }
        throw new RuntimeException("Cannot convert the object to Double: " + o.toString());
    }
    
    private Integer zzW(final Object o) {
        if (o instanceof String) {
            try {
                return Integer.valueOf((String)o);
            }
            catch (NumberFormatException ex) {
                throw new RuntimeException("Cannot convert the object to Integer: " + ex.getMessage());
            }
        }
        if (o instanceof Double) {
            return (int)o;
        }
        if (o instanceof Integer) {
            return (Integer)o;
        }
        throw new RuntimeException("Cannot convert the object to Integer: " + o.toString());
    }
    
    private Promotion zzZ(final Map<String, String> map) {
        final Promotion promotion = new Promotion();
        final String s = map.get("id");
        if (s != null) {
            promotion.setId(String.valueOf(s));
        }
        final String s2 = map.get("name");
        if (s2 != null) {
            promotion.setName(String.valueOf(s2));
        }
        final String s3 = map.get("creative");
        if (s3 != null) {
            promotion.setCreative(String.valueOf(s3));
        }
        final String s4 = map.get("position");
        if (s4 != null) {
            promotion.setPosition(String.valueOf(s4));
        }
        return promotion;
    }
    
    private void zza(final Tracker tracker, final Map<String, zzag.zza> map) {
        final String zzgy = this.zzgy("transactionId");
        if (zzgy == null) {
            zzbg.e("Cannot find transactionId in data layer.");
        }
        else {
            final LinkedList<Map<String, String>> list = new LinkedList<Map<String, String>>();
            Map<String, String> zzm;
            try {
                zzm = this.zzm(map.get(zzdg.zzblS));
                zzm.put("&t", "transaction");
                for (final Map.Entry<String, String> entry : this.zzab(map).entrySet()) {
                    this.zze(zzm, entry.getValue(), this.zzgy(entry.getKey()));
                }
            }
            catch (IllegalArgumentException ex) {
                zzbg.zzb("Unable to send transaction", ex);
                return;
            }
            list.add(zzm);
            final List<Map<String, String>> zzgz = this.zzgz("transactionProducts");
            if (zzgz != null) {
                for (final Map<String, String> map2 : zzgz) {
                    if (map2.get("name") == null) {
                        zzbg.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    final Map<String, String> zzm2 = this.zzm(map.get(zzdg.zzblS));
                    zzm2.put("&t", "item");
                    zzm2.put("&ti", zzgy);
                    for (final Map.Entry<String, String> entry2 : this.zzac(map).entrySet()) {
                        this.zze(zzm2, entry2.getValue(), map2.get(entry2.getKey()));
                    }
                    list.add(zzm2);
                }
            }
            final Iterator<Object> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                tracker.send(iterator4.next());
            }
        }
    }
    
    private Product zzaa(final Map<String, Object> map) {
        final Product product = new Product();
        final Object value = map.get("id");
        if (value != null) {
            product.setId(String.valueOf(value));
        }
        final Object value2 = map.get("name");
        if (value2 != null) {
            product.setName(String.valueOf(value2));
        }
        final Object value3 = map.get("brand");
        if (value3 != null) {
            product.setBrand(String.valueOf(value3));
        }
        final Object value4 = map.get("category");
        if (value4 != null) {
            product.setCategory(String.valueOf(value4));
        }
        final Object value5 = map.get("variant");
        if (value5 != null) {
            product.setVariant(String.valueOf(value5));
        }
        final Object value6 = map.get("coupon");
        if (value6 != null) {
            product.setCouponCode(String.valueOf(value6));
        }
        final Object value7 = map.get("position");
        if (value7 != null) {
            product.setPosition(this.zzW(value7));
        }
        final Object value8 = map.get("price");
        if (value8 != null) {
            product.setPrice(this.zzV(value8));
        }
        final Object value9 = map.get("quantity");
        if (value9 != null) {
            product.setQuantity(this.zzW(value9));
        }
        for (final String s : map.keySet()) {
            final Matcher matcher = zzdg.zzblX.matcher(s);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(s)));
                }
                catch (NumberFormatException ex) {
                    zzbg.zzaK("illegal number in custom dimension value: " + s);
                }
            }
            else {
                final Matcher matcher2 = zzdg.zzblY.matcher(s);
                if (!matcher2.matches()) {
                    continue;
                }
                try {
                    product.setCustomMetric(Integer.parseInt(matcher2.group(1)), this.zzW(map.get(s)));
                }
                catch (NumberFormatException ex2) {
                    zzbg.zzaK("illegal number in custom metric value: " + s);
                }
            }
        }
        return product;
    }
    
    private Map<String, String> zzab(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzdg.zzblU);
        if (zza != null) {
            return this.zzc(zza);
        }
        if (zzdg.zzblZ == null) {
            final HashMap<String, String> zzblZ = new HashMap<String, String>();
            zzblZ.put("transactionId", "&ti");
            zzblZ.put("transactionAffiliation", "&ta");
            zzblZ.put("transactionTax", "&tt");
            zzblZ.put("transactionShipping", "&ts");
            zzblZ.put("transactionTotal", "&tr");
            zzblZ.put("transactionCurrency", "&cu");
            zzdg.zzblZ = zzblZ;
        }
        return zzdg.zzblZ;
    }
    
    private Map<String, String> zzac(final Map<String, zzag.zza> map) {
        final zzag.zza zza = map.get(zzdg.zzblV);
        if (zza != null) {
            return this.zzc(zza);
        }
        if (zzdg.zzbma == null) {
            final HashMap<String, String> zzbma = new HashMap<String, String>();
            zzbma.put("name", "&in");
            zzbma.put("sku", "&ic");
            zzbma.put("category", "&iv");
            zzbma.put("price", "&ip");
            zzbma.put("quantity", "&iq");
            zzbma.put("currency", "&cu");
            zzdg.zzbma = zzbma;
        }
        return zzdg.zzbma;
    }
    
    private void zzb(final Tracker tracker, final Map<String, zzag.zza> map) {
        final HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
        final Map<String, String> zzm = this.zzm(map.get(zzdg.zzblS));
        screenViewBuilder.setAll(zzm);
        Object o;
        if (this.zzj(map, zzdg.zzblQ)) {
            final Object value = this.zzbhN.get("ecommerce");
            Map<?, String> map2;
            if (value instanceof Map) {
                map2 = (Map<?, String>)value;
            }
            else {
                map2 = null;
            }
            o = map2;
        }
        else {
            final Object zzl = zzdf.zzl(map.get(zzdg.zzblR));
            if (zzl instanceof Map) {
                o = zzl;
            }
            else {
                o = null;
            }
        }
        while (true) {
            String s;
            String value2;
            Object o2;
            int n;
            final String s2;
            Map map5;
            List<Map> list;
            ProductAction zzd;
            Label_0744:Label_0467_Outer:
            while (true) {
                if (o == null) {
                    break Label_0685;
                }
                s = zzm.get("&cu");
                if (s == null) {
                    s = ((Map<K, String>)o).get("currencyCode");
                }
                if (s != null) {
                    screenViewBuilder.set("&cu", s);
                }
                value2 = ((Map<K, String>)o).get("impressions");
                if (value2 instanceof List) {
                    for (final Map<K, String> map3 : (List<Map>)value2) {
                        try {
                            screenViewBuilder.addImpression(this.zzaa((Map<String, Object>)map3), map3.get("list"));
                        }
                        catch (RuntimeException ex) {
                            zzbg.e("Failed to extract a product from DataLayer. " + ex.getMessage());
                        }
                    }
                }
                if (((Map)o).containsKey("promoClick")) {
                    o2 = ((Map)((Map<K, String>)o).get("promoClick")).get("promotions");
                }
                else {
                    if (!((Map)o).containsKey("promoView")) {
                        break Label_0744;
                    }
                    o2 = ((Map)((Map<K, String>)o).get("promoView")).get("promotions");
                }
                Label_0644: {
                    while (true) {
                        Label_0638: {
                            if (o2 == null) {
                                break Label_0638;
                            }
                            for (final Map<String, String> map4 : o2) {
                                try {
                                    screenViewBuilder.addPromotion(this.zzZ(map4));
                                }
                                catch (RuntimeException ex2) {
                                    zzbg.e("Failed to extract a promotion from DataLayer. " + ex2.getMessage());
                                }
                            }
                            if (!((Map)o).containsKey("promoClick")) {
                                screenViewBuilder.set("&promoa", "view");
                                break Label_0638;
                            }
                            screenViewBuilder.set("&promoa", "click");
                            n = 0;
                            if (n != 0) {
                                for (final String s2 : zzdg.zzblW) {
                                    if (((Map)o).containsKey(s2)) {
                                        map5 = (Map)((Map<K, String>)o).get(s2);
                                        list = map5.get("products");
                                        if (list != null) {
                                            for (final Map<String, Object> map6 : list) {
                                                try {
                                                    screenViewBuilder.addProduct(this.zzaa(map6));
                                                }
                                                catch (RuntimeException ex3) {
                                                    zzbg.e("Failed to extract a product from DataLayer. " + ex3.getMessage());
                                                }
                                            }
                                        }
                                        break Label_0644;
                                    }
                                }
                                break Label_0685;
                            }
                            break Label_0685;
                        }
                        n = 1;
                        continue;
                    }
                    try {
                        if (map5.containsKey("actionField")) {
                            zzd = this.zzd(s2, (Map)map5.get("actionField"));
                        }
                        else {
                            zzd = new ProductAction(s2);
                        }
                        screenViewBuilder.setProductAction(zzd);
                        tracker.send(screenViewBuilder.build());
                        return;
                    }
                    catch (RuntimeException ex4) {
                        zzbg.e("Failed to extract a product action from DataLayer. " + ex4.getMessage());
                        continue Label_0467_Outer;
                    }
                }
                break;
            }
            o2 = null;
            continue;
        }
    }
    
    private Map<String, String> zzc(final zzag.zza zza) {
        final Object zzl = zzdf.zzl(zza);
        if (!(zzl instanceof Map)) {
            return null;
        }
        final Map<Object, V> map = (Map<Object, V>)zzl;
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }
    
    private ProductAction zzd(final String s, final Map<String, Object> map) {
        final ProductAction productAction = new ProductAction(s);
        final Object value = map.get("id");
        if (value != null) {
            productAction.setTransactionId(String.valueOf(value));
        }
        final Object value2 = map.get("affiliation");
        if (value2 != null) {
            productAction.setTransactionAffiliation(String.valueOf(value2));
        }
        final Object value3 = map.get("coupon");
        if (value3 != null) {
            productAction.setTransactionCouponCode(String.valueOf(value3));
        }
        final Object value4 = map.get("list");
        if (value4 != null) {
            productAction.setProductActionList(String.valueOf(value4));
        }
        final Object value5 = map.get("option");
        if (value5 != null) {
            productAction.setCheckoutOptions(String.valueOf(value5));
        }
        final Object value6 = map.get("revenue");
        if (value6 != null) {
            productAction.setTransactionRevenue(this.zzV(value6));
        }
        final Object value7 = map.get("tax");
        if (value7 != null) {
            productAction.setTransactionTax(this.zzV(value7));
        }
        final Object value8 = map.get("shipping");
        if (value8 != null) {
            productAction.setTransactionShipping(this.zzV(value8));
        }
        final Object value9 = map.get("step");
        if (value9 != null) {
            productAction.setCheckoutStep(this.zzW(value9));
        }
        return productAction;
    }
    
    private void zze(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null) {
            map.put(s, s2);
        }
    }
    
    private String zzgy(final String s) {
        final Object value = this.zzbhN.get(s);
        if (value == null) {
            return null;
        }
        return value.toString();
    }
    
    private List<Map<String, String>> zzgz(final String s) {
        final Object value = this.zzbhN.get(s);
        if (value == null) {
            return null;
        }
        if (!(value instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        final Iterator<Map<String, String>> iterator = ((List<Map<String, String>>)value).iterator();
        while (iterator.hasNext()) {
            if (!(iterator.next() instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List<Map<String, String>>)value;
    }
    
    private boolean zzj(final Map<String, zzag.zza> map, final String s) {
        final zzag.zza zza = map.get(s);
        return zza != null && zzdf.zzk(zza);
    }
    
    private Map<String, String> zzm(final zzag.zza zza) {
        if (zza == null) {
            return new HashMap<String, String>();
        }
        final Map<String, String> zzc = this.zzc(zza);
        if (zzc == null) {
            return new HashMap<String, String>();
        }
        final String s = zzc.get("&aip");
        if (s != null && this.zzbmb.contains(s.toLowerCase())) {
            zzc.remove("&aip");
        }
        return zzc;
    }
    
    @Override
    public void zzR(final Map<String, zzag.zza> map) {
        final Tracker zzgq = this.zzbmc.zzgq("_GTM_DEFAULT_TRACKER_");
        zzgq.enableAdvertisingIdCollection(this.zzj(map, "collect_adid"));
        if (this.zzj(map, zzdg.zzblP)) {
            this.zzb(zzgq, map);
            return;
        }
        if (this.zzj(map, zzdg.zzblO)) {
            zzgq.send(this.zzm(map.get(zzdg.zzblS)));
            return;
        }
        if (this.zzj(map, zzdg.zzblT)) {
            this.zza(zzgq, map);
            return;
        }
        zzbg.zzaK("Ignoring unknown tag.");
    }
}
