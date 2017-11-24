package bf.io.openshop.utils;

import com.facebook.appevents.*;
import timber.log.*;
import android.os.*;
import bf.io.openshop.entities.delivery.*;
import android.app.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.entities.*;
import android.content.*;
import com.google.android.gms.analytics.*;
import bf.io.openshop.*;
import java.util.*;

public class Analytics
{
    private static final String TRACKER_APP = "App";
    private static final String TRACKER_GLOBAL = "Global";
    private static String campaignUri;
    private static AppEventsLogger facebookLogger;
    public static HashMap<String, Tracker> mTrackers;
    
    static {
        Analytics.mTrackers = new HashMap<String, Tracker>();
    }
    
    public static void deleteAppTrackers() {
        if (Analytics.mTrackers != null && Analytics.mTrackers.containsKey("App")) {
            Timber.d("Removing GA app tracker.", new Object[0]);
            Analytics.mTrackers.remove("App");
        }
    }
    
    public static void logAddProductToCart(final long n, final String s, final double n2) {
        final Bundle bundle = new Bundle();
        bundle.putString("fb_content_type", "product");
        bundle.putLong("fb_content_id", n);
        bundle.putString("fb_description", s);
        logFbEvent("fb_mobile_add_to_cart", null, bundle);
        sendEventToAppTrackers(new HitBuilders.EventBuilder().setCategory("ADDED_TO_CART").setAction("ADDED_TO_CART").setLabel("ADDED TO CART product id: " + n + " product name: " + s + " price: " + n2).build());
    }
    
    public static void logCategoryView(final long n, final String s, final boolean b) {
        final Bundle bundle = new Bundle();
        bundle.putString("fb_content_type", "category");
        if (n == 0L) {
            Timber.e("Is categoryId = 0.", new Object[0]);
            return;
        }
        bundle.putLong("fb_content_id", n);
        bundle.putString("fb_description", s);
        logFbEvent("fb_mobile_content_view", null, bundle);
        final HitBuilders.EventBuilder setCategory = new HitBuilders.EventBuilder().setCategory("VIEW_CATEGORY");
        String action;
        if (b) {
            action = "SEARCH";
        }
        else {
            action = "VIEW_CATEGORY";
        }
        final HitBuilders.EventBuilder setAction = setCategory.setAction(action);
        String label;
        if (b) {
            label = "Search: " + s;
        }
        else {
            label = "CategoryId: " + n + ". CategoryName: " + s;
        }
        sendEventToAppTrackers(setAction.setLabel(label).build());
    }
    
    private static void logFbEvent(final String s, final Double n, final Bundle bundle) {
        if (Analytics.facebookLogger == null) {
            Timber.e(new RuntimeException(), "null FB facebookLogger", new Object[0]);
            return;
        }
        if (bundle == null) {
            Analytics.facebookLogger.logEvent(s);
            return;
        }
        if (n == null) {
            Analytics.facebookLogger.logEvent(s, bundle);
            return;
        }
        Analytics.facebookLogger.logEvent(s, n, bundle);
    }
    
    public static void logOpenedByNotification(final String s) {
        sendEventToAppTrackers(new HitBuilders.EventBuilder().setAction("OPENED_BY_NOTIFICATION").setLabel("OPENED_BY_NOTIFICATION with link:" + s + ".").build());
    }
    
    public static void logOrderCreatedEvent(final Cart cart, final String s, final Double n, final Shipping shipping) {
        sendEventToAppTrackers(new HitBuilders.EventBuilder().setCategory("POST_ORDER").setAction("POST_ORDER").setLabel("POST_ORDER").build());
        sendEventToAppTrackers(new HitBuilders.TransactionBuilder().setTransactionId(s).setAffiliation(SettingsMy.getActualNonNullShop(null).getName()).setRevenue(n).setTax(0.0).setShipping(shipping.getPrice()).setCurrencyCode(cart.getCurrency()).build());
        final Bundle bundle = new Bundle();
        bundle.putString("fb_content_type", "cart");
        bundle.putString("fb_content_id", s);
        bundle.putInt("fb_num_items", cart.getItems().size());
        bundle.putString("fb_currency", cart.getCurrency());
        logFbEvent("fb_mobile_initiated_checkout", n, bundle);
        final Bundle bundle2 = new Bundle();
        bundle2.putString("fb_content_type", "shipping");
        bundle2.putString("fb_content_id", s);
        bundle2.putString("fb_currency", cart.getCurrency());
        logFbEvent("fb_mobile_purchase", (double)shipping.getPrice(), bundle2);
        for (int i = 0; i < cart.getItems().size(); ++i) {
            final CartProductItem cartProductItem = cart.getItems().get(i);
            Double n2 = cartProductItem.getVariant().getPrice();
            if (cartProductItem.getVariant().getDiscountPrice() > 0.0) {
                n2 = cartProductItem.getVariant().getDiscountPrice();
            }
            sendEventToAppTrackers(new HitBuilders.ItemBuilder().setTransactionId(s).setName(cartProductItem.getVariant().getName()).setSku("Product id: " + cartProductItem.getVariant().getRemoteId()).setCategory("Category id: " + cartProductItem.getVariant().getCategory()).setPrice(n2).setQuantity(cartProductItem.getQuantity()).setCurrencyCode(cart.getCurrency()).build());
            final Bundle bundle3 = new Bundle();
            bundle3.putString("fb_content_type", "product");
            bundle3.putLong("fb_content_id", cartProductItem.getVariant().getRemoteId());
            bundle3.putInt("fb_num_items", cartProductItem.getQuantity());
            bundle3.putString("fb_currency", cart.getCurrency());
            logFbEvent("fb_mobile_purchase", n2 * cartProductItem.getQuantity(), bundle3);
        }
    }
    
    public static void logProductView(final long n, final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fb_content_type", "product");
        bundle.putLong("fb_content_id", n);
        bundle.putString("fb_description", s);
        logFbEvent("fb_mobile_content_view", null, bundle);
        sendEventToAppTrackers(new HitBuilders.EventBuilder().setCategory("product").setAction("view").setLabel("product with id: " + n + ", name: " + s).build());
    }
    
    public static void logShopChange(final Shop shop, final Shop shop2) {
        if (shop != null && shop2 != null) {
            final String string = "From (id=" + shop.getId() + ",name=" + shop.getName() + ") to (id=" + shop2.getId() + ",name=" + shop2.getId() + ")";
            final Bundle bundle = new Bundle();
            bundle.putString("fb_description", string);
            logFbEvent("fb_mobile_achievement_unlocked", null, bundle);
            sendEventToAppTrackers(new HitBuilders.EventBuilder().setCategory("CHANGE_SHOP").setAction("CHANGE_SHOP").setLabel(string).build());
            return;
        }
        Timber.e(new RuntimeException(), "Try log shop change with null parameters", new Object[0]);
    }
    
    public static void prepareTrackersAndFbLogger(final Shop shop, final Context context) {
        while (true) {
            while (true) {
                Label_0224: {
                    Label_0204: {
                        synchronized (Analytics.class) {
                            final GoogleAnalytics instance = GoogleAnalytics.getInstance(context);
                            if (shop == null) {
                                deleteAppTrackers();
                            }
                            else {
                                if (Analytics.mTrackers.containsKey("App") || instance == null) {
                                    break Label_0224;
                                }
                                if (shop.getGoogleUa() == null || shop.getGoogleUa().isEmpty()) {
                                    break Label_0204;
                                }
                                Timber.d("Set new app tracker with id: " + shop.getGoogleUa(), new Object[0]);
                                final Tracker tracker = instance.newTracker(shop.getGoogleUa());
                                tracker.enableAutoActivityTracking(true);
                                tracker.enableExceptionReporting(false);
                                tracker.enableAdvertisingIdCollection(true);
                                Analytics.mTrackers.put("App", tracker);
                            }
                            if (!Analytics.mTrackers.containsKey("Global") && instance != null) {
                                Timber.d("Set new global tracker.", new Object[0]);
                                final Tracker tracker2 = instance.newTracker(2131099648);
                                tracker2.enableAutoActivityTracking(true);
                                tracker2.enableExceptionReporting(true);
                                tracker2.enableAdvertisingIdCollection(true);
                                Analytics.mTrackers.put("Global", tracker2);
                                sendCampaignInfo();
                            }
                            Analytics.facebookLogger = AppEventsLogger.newLogger((Context)MyApplication.getInstance());
                            return;
                        }
                    }
                    Timber.e(new RuntimeException(), "Creating GA app tracker with empty Google UA", new Object[0]);
                    continue;
                }
                Timber.e("Trackers for this app already exist.", new Object[0]);
                continue;
            }
        }
    }
    
    private static void sendCampaignInfo() {
        while (true) {
            synchronized (Analytics.class) {
                if (Analytics.campaignUri != null && !Analytics.campaignUri.isEmpty()) {
                    try {
                        Timber.d("Sending campaign uri.", new Object[0]);
                        if (Analytics.mTrackers.isEmpty()) {
                            Timber.e("Empty app trackers set", new Object[0]);
                        }
                        else {
                            final Iterator<String> iterator = Analytics.mTrackers.keySet().iterator();
                            while (iterator.hasNext()) {
                                final Tracker tracker = Analytics.mTrackers.get(iterator.next());
                                tracker.setScreenName("OpenShop");
                                final Map build = ((HitBuilders.ScreenViewBuilder)((HitBuilders.HitBuilder<HitBuilders.ScreenViewBuilder>)new HitBuilders.ScreenViewBuilder()).setCampaignParamsFromUrl(Analytics.campaignUri)).build();
                                Timber.e("Send campaign: " + build.toString(), new Object[0]);
                                tracker.send(build);
                            }
                        }
                        return;
                    }
                    catch (Exception ex) {
                        Timber.e(ex, "Send campaign info exception.", new Object[0]);
                    }
                }
            }
            Timber.e("Campaign uri is null", new Object[0]);
        }
    }
    
    private static void sendEventToAppTrackers(final Map<String, String> map) {
        Timber.d("Send event to GA: " + map.toString(), new Object[0]);
        if (Analytics.mTrackers == null || Analytics.mTrackers.isEmpty()) {
            Timber.e(new RuntimeException(), "SendEventToAppTrackers, ERROR empty app trackers set", new Object[0]);
        }
        else {
            final Set<String> keySet = Analytics.mTrackers.keySet();
            if (keySet.contains("Global")) {
                Analytics.mTrackers.get("Global").send(map);
            }
            if (keySet.contains("App")) {
                Analytics.mTrackers.get("App").send(map);
            }
        }
    }
    
    public static void setCampaignUriString(final String campaignUri) {
        synchronized (Analytics.class) {
            Timber.d("Set campaign uri:" + campaignUri, new Object[0]);
            Analytics.campaignUri = campaignUri;
            if (Analytics.mTrackers != null && !Analytics.mTrackers.isEmpty()) {
                sendCampaignInfo();
            }
        }
    }
}
