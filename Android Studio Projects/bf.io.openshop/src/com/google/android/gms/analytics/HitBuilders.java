package com.google.android.gms.analytics;

import com.google.android.gms.analytics.ecommerce.*;
import com.google.android.gms.analytics.internal.*;
import android.text.*;
import java.util.*;

public class HitBuilders
{
    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder>
    {
        public AppViewBuilder() {
            this.set("&t", "screenview");
        }
    }
    
    public static class EventBuilder extends HitBuilder<EventBuilder>
    {
        public EventBuilder() {
            this.set("&t", "event");
        }
        
        public EventBuilder(final String category, final String action) {
            this();
            this.setCategory(category);
            this.setAction(action);
        }
        
        public EventBuilder setAction(final String s) {
            this.set("&ea", s);
            return this;
        }
        
        public EventBuilder setCategory(final String s) {
            this.set("&ec", s);
            return this;
        }
        
        public EventBuilder setLabel(final String s) {
            this.set("&el", s);
            return this;
        }
        
        public EventBuilder setValue(final long n) {
            this.set("&ev", Long.toString(n));
            return this;
        }
    }
    
    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder>
    {
        public ExceptionBuilder() {
            this.set("&t", "exception");
        }
        
        public ExceptionBuilder setDescription(final String s) {
            this.set("&exd", s);
            return this;
        }
        
        public ExceptionBuilder setFatal(final boolean b) {
            this.set("&exf", zzam.zzK(b));
            return this;
        }
    }
    
    protected static class HitBuilder<T extends HitBuilder>
    {
        private Map<String, String> zzPm;
        ProductAction zzPn;
        Map<String, List<Product>> zzPo;
        List<Promotion> zzPp;
        List<Product> zzPq;
        
        protected HitBuilder() {
            this.zzPm = new HashMap<String, String>();
            this.zzPo = new HashMap<String, List<Product>>();
            this.zzPp = new ArrayList<Promotion>();
            this.zzPq = new ArrayList<Product>();
        }
        
        public T addImpression(final Product product, String s) {
            if (product == null) {
                zzae.zzaK("product should be non-null");
                return (T)this;
            }
            if (s == null) {
                s = "";
            }
            if (!this.zzPo.containsKey(s)) {
                this.zzPo.put(s, new ArrayList<Product>());
            }
            this.zzPo.get(s).add(product);
            return (T)this;
        }
        
        public T addProduct(final Product product) {
            if (product == null) {
                zzae.zzaK("product should be non-null");
                return (T)this;
            }
            this.zzPq.add(product);
            return (T)this;
        }
        
        public T addPromotion(final Promotion promotion) {
            if (promotion == null) {
                zzae.zzaK("promotion should be non-null");
                return (T)this;
            }
            this.zzPp.add(promotion);
            return (T)this;
        }
        
        public Map<String, String> build() {
            final HashMap<String, String> hashMap = new HashMap<String, String>(this.zzPm);
            if (this.zzPn != null) {
                hashMap.putAll((Map<?, ?>)this.zzPn.build());
            }
            final Iterator<Promotion> iterator = this.zzPp.iterator();
            int n = 1;
            while (iterator.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator.next().zzba(zzc.zzZ(n)));
                ++n;
            }
            final Iterator<Product> iterator2 = this.zzPq.iterator();
            int n2 = 1;
            while (iterator2.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator2.next().zzba(zzc.zzX(n2)));
                ++n2;
            }
            final Iterator<Map.Entry<String, List<Product>>> iterator3 = this.zzPo.entrySet().iterator();
            int n3 = 1;
            while (iterator3.hasNext()) {
                final Map.Entry<String, List<Product>> entry = iterator3.next();
                final List<Product> list = entry.getValue();
                final String zzac = zzc.zzac(n3);
                final Iterator<Product> iterator4 = list.iterator();
                int n4 = 1;
                while (iterator4.hasNext()) {
                    hashMap.putAll((Map<?, ?>)iterator4.next().zzba(zzac + zzc.zzab(n4)));
                    ++n4;
                }
                if (!TextUtils.isEmpty((CharSequence)entry.getKey())) {
                    hashMap.put(zzac + "nm", entry.getKey());
                }
                ++n3;
            }
            return hashMap;
        }
        
        protected String get(final String s) {
            return this.zzPm.get(s);
        }
        
        public final T set(final String s, final String s2) {
            if (s != null) {
                this.zzPm.put(s, s2);
                return (T)this;
            }
            zzae.zzaK(" HitBuilder.set() called with a null paramName.");
            return (T)this;
        }
        
        public final T setAll(final Map<String, String> map) {
            if (map == null) {
                return (T)this;
            }
            this.zzPm.putAll(new HashMap<String, String>(map));
            return (T)this;
        }
        
        public T setCampaignParamsFromUrl(final String s) {
            final String zzbu = zzam.zzbu(s);
            if (TextUtils.isEmpty((CharSequence)zzbu)) {
                return (T)this;
            }
            final Map<String, String> zzbs = zzam.zzbs(zzbu);
            this.set("&cc", zzbs.get("utm_content"));
            this.set("&cm", zzbs.get("utm_medium"));
            this.set("&cn", zzbs.get("utm_campaign"));
            this.set("&cs", zzbs.get("utm_source"));
            this.set("&ck", zzbs.get("utm_term"));
            this.set("&ci", zzbs.get("utm_id"));
            this.set("&anid", zzbs.get("anid"));
            this.set("&gclid", zzbs.get("gclid"));
            this.set("&dclid", zzbs.get("dclid"));
            this.set("&aclid", zzbs.get("aclid"));
            this.set("&gmob_t", zzbs.get("gmob_t"));
            return (T)this;
        }
        
        public T setCustomDimension(final int n, final String s) {
            this.set(zzc.zzT(n), s);
            return (T)this;
        }
        
        public T setCustomMetric(final int n, final float n2) {
            this.set(zzc.zzV(n), Float.toString(n2));
            return (T)this;
        }
        
        protected T setHitType(final String s) {
            this.set("&t", s);
            return (T)this;
        }
        
        public T setNewSession() {
            this.set("&sc", "start");
            return (T)this;
        }
        
        public T setNonInteraction(final boolean b) {
            this.set("&ni", zzam.zzK(b));
            return (T)this;
        }
        
        public T setProductAction(final ProductAction zzPn) {
            this.zzPn = zzPn;
            return (T)this;
        }
        
        public T setPromotionAction(final String s) {
            this.zzPm.put("&promoa", s);
            return (T)this;
        }
    }
    
    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder>
    {
        public ItemBuilder() {
            this.set("&t", "item");
        }
        
        public ItemBuilder setCategory(final String s) {
            this.set("&iv", s);
            return this;
        }
        
        public ItemBuilder setCurrencyCode(final String s) {
            this.set("&cu", s);
            return this;
        }
        
        public ItemBuilder setName(final String s) {
            this.set("&in", s);
            return this;
        }
        
        public ItemBuilder setPrice(final double n) {
            this.set("&ip", Double.toString(n));
            return this;
        }
        
        public ItemBuilder setQuantity(final long n) {
            this.set("&iq", Long.toString(n));
            return this;
        }
        
        public ItemBuilder setSku(final String s) {
            this.set("&ic", s);
            return this;
        }
        
        public ItemBuilder setTransactionId(final String s) {
            this.set("&ti", s);
            return this;
        }
    }
    
    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder>
    {
        public ScreenViewBuilder() {
            this.set("&t", "screenview");
        }
    }
    
    public static class SocialBuilder extends HitBuilder<SocialBuilder>
    {
        public SocialBuilder() {
            this.set("&t", "social");
        }
        
        public SocialBuilder setAction(final String s) {
            this.set("&sa", s);
            return this;
        }
        
        public SocialBuilder setNetwork(final String s) {
            this.set("&sn", s);
            return this;
        }
        
        public SocialBuilder setTarget(final String s) {
            this.set("&st", s);
            return this;
        }
    }
    
    public static class TimingBuilder extends HitBuilder<TimingBuilder>
    {
        public TimingBuilder() {
            this.set("&t", "timing");
        }
        
        public TimingBuilder(final String category, final String variable, final long value) {
            this();
            this.setVariable(variable);
            this.setValue(value);
            this.setCategory(category);
        }
        
        public TimingBuilder setCategory(final String s) {
            this.set("&utc", s);
            return this;
        }
        
        public TimingBuilder setLabel(final String s) {
            this.set("&utl", s);
            return this;
        }
        
        public TimingBuilder setValue(final long n) {
            this.set("&utt", Long.toString(n));
            return this;
        }
        
        public TimingBuilder setVariable(final String s) {
            this.set("&utv", s);
            return this;
        }
    }
    
    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder>
    {
        public TransactionBuilder() {
            this.set("&t", "transaction");
        }
        
        public TransactionBuilder setAffiliation(final String s) {
            this.set("&ta", s);
            return this;
        }
        
        public TransactionBuilder setCurrencyCode(final String s) {
            this.set("&cu", s);
            return this;
        }
        
        public TransactionBuilder setRevenue(final double n) {
            this.set("&tr", Double.toString(n));
            return this;
        }
        
        public TransactionBuilder setShipping(final double n) {
            this.set("&ts", Double.toString(n));
            return this;
        }
        
        public TransactionBuilder setTax(final double n) {
            this.set("&tt", Double.toString(n));
            return this;
        }
        
        public TransactionBuilder setTransactionId(final String s) {
            this.set("&ti", s);
            return this;
        }
    }
}
