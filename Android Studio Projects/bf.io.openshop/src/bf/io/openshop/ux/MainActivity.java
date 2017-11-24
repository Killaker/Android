package bf.io.openshop.ux;

import android.widget.*;
import timber.log.*;
import org.json.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.api.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.ux.dialogs.*;
import android.support.annotation.*;
import android.app.*;
import android.support.v4.app.*;
import android.database.*;
import bf.io.openshop.entities.*;
import android.support.v7.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v4.view.*;
import android.view.*;
import bf.io.openshop.entities.drawerMenu.*;
import bf.io.openshop.entities.order.*;
import com.facebook.appevents.*;
import android.support.v4.content.*;
import android.os.*;
import android.transition.*;
import android.content.*;
import java.util.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.ux.fragments.*;

public class MainActivity extends AppCompatActivity implements FragmentDrawerListener
{
    private static MainActivity mInstance;
    private int cartCountNotificationValue;
    private TextView cartCountView;
    public DrawerFragment drawerFragment;
    private boolean isAppReadyToFinish;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private SimpleCursorAdapter searchSuggestionsAdapter;
    private ArrayList<String> searchSuggestionsList;
    
    static {
        MainActivity.mInstance = null;
    }
    
    public MainActivity() {
        this.isAppReadyToFinish = false;
        this.cartCountNotificationValue = -131;
    }
    
    private void addInitialFragment() {
        final BannersFragment bannersFragment = new BannersFragment();
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(2131624068, bannersFragment).commit();
        supportFragmentManager.executePendingTransactions();
    }
    
    private void clearBackStack() {
        Timber.d("Clearing backStack", new Object[0]);
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack(supportFragmentManager.getBackStackEntryAt(0).getId(), 1);
        }
        Timber.d("backStack cleared.", new Object[0]);
    }
    
    private void getCartCount(final boolean b) {
        Timber.d("Obtaining cart count.", new Object[0]);
        if (this.cartCountView != null) {
            final User activeUser = SettingsMy.getActiveUser();
            if (activeUser == null) {
                Timber.d("Cannot update notify count. User is logged out.", new Object[0]);
                this.showNotifyCount(0);
            }
            else {
                if (b) {
                    final JsonRequest jsonRequest = new JsonRequest(0, String.format(EndPoints.CART, SettingsMy.getActualNonNullShop(this).getId()), null, new Response.Listener<JSONObject>() {
                        public void onResponse(final JSONObject jsonObject) {
                            Timber.d("getCartCount: " + jsonObject.toString(), new Object[0]);
                            try {
                                MainActivity.this.showNotifyCount(jsonObject.getInt("product_count"));
                            }
                            catch (Exception ex) {
                                Timber.e(ex, "Obtain cart count from response failed.", new Object[0]);
                                MainActivity.this.showNotifyCount(0);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError volleyError) {
                            MsgUtils.logErrorMessage(volleyError);
                            MainActivity.this.showNotifyCount(0);
                        }
                    }, this.getSupportFragmentManager(), activeUser.getAccessToken());
                    jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                    jsonRequest.setShouldCache(false);
                    MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "main_activity_requests");
                    return;
                }
                final GsonRequest gsonRequest = new GsonRequest<Object>(0, String.format(EndPoints.CART_INFO, SettingsMy.getActualNonNullShop(this).getId()), null, CartInfo.class, new Response.Listener<CartInfo>() {
                    public void onResponse(final CartInfo cartInfo) {
                        Timber.d("getCartCount: " + cartInfo.toString(), new Object[0]);
                        MainActivity.this.showNotifyCount(cartInfo.getProductCount());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        MsgUtils.logErrorMessage(volleyError);
                        MainActivity.this.showNotifyCount(0);
                    }
                }, this.getSupportFragmentManager(), activeUser.getAccessToken());
                gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                gsonRequest.setShouldCache(false);
                MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "main_activity_requests");
            }
        }
    }
    
    private static MainActivity getInstance() {
        synchronized (MainActivity.class) {
            return MainActivity.mInstance;
        }
    }
    
    public static void invalidateDrawerMenuHeader() {
        final MainActivity instance = getInstance();
        if (instance != null && instance.drawerFragment != null) {
            instance.drawerFragment.invalidateHeader();
            return;
        }
        Timber.e("MainActivity instance is null.", new Object[0]);
    }
    
    private void launchUserSpecificFragment(final Fragment fragment, final String s, final LoginDialogInterface loginDialogInterface) {
        if (SettingsMy.getActiveUser() != null) {
            this.replaceFragment(fragment, s);
            return;
        }
        LoginDialogFragment.newInstance(loginDialogInterface).show(this.getSupportFragmentManager(), LoginDialogFragment.class.getSimpleName());
    }
    
    private void onSearchSubmitted(final String s) {
        this.clearBackStack();
        Timber.d("Called onSearchSubmitted with text: " + s, new Object[0]);
        this.replaceFragment(CategoryFragment.newInstance(s), CategoryFragment.class.getSimpleName());
    }
    
    private void prepareSearchView(@NonNull final MenuItem menuItem) {
        final SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setSearchableInfo(((SearchManager)this.getSystemService("search")).getSearchableInfo(this.getComponentName()));
        final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(final String s) {
                Timber.d("Search query text changed to: " + s, new Object[0]);
                MainActivity.this.showSearchSuggestions(s, searchView);
                return false;
            }
            
            @Override
            public boolean onQueryTextSubmit(final String s) {
                MainActivity.this.onSearchSubmitted(s);
                searchView.setQuery("", false);
                searchView.setIconified(true);
                menuItem.collapseActionView();
                return true;
            }
        };
        searchView.setOnSuggestionListener((SearchView.OnSuggestionListener)new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(final int n) {
                MainActivity.this.onSearchSubmitted(((MatrixCursor)MainActivity.this.searchSuggestionsAdapter.getItem(n)).getString(1));
                searchView.setQuery("", false);
                searchView.setIconified(true);
                menuItem.collapseActionView();
                return true;
            }
            
            @Override
            public boolean onSuggestionSelect(final int n) {
                return false;
            }
        });
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener)onQueryTextListener);
    }
    
    private void replaceFragment(final Fragment fragment, final String s) {
        if (fragment != null) {
            final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
            final FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.addToBackStack(s);
            beginTransaction.replace(2131624068, fragment).commit();
            supportFragmentManager.executePendingTransactions();
            return;
        }
        Timber.e(new RuntimeException(), "Replace fragments with null newFragment parameter.", new Object[0]);
    }
    
    public static void setActionBarTitle(final String title) {
        final MainActivity instance = getInstance();
        if (instance != null) {
            instance.setTitle((CharSequence)title);
            return;
        }
        Timber.e("MainActivity instance is null.", new Object[0]);
    }
    
    private void showNotifyCount(final int cartCountNotificationValue) {
        this.cartCountNotificationValue = cartCountNotificationValue;
        Timber.d("Update cart count notification: " + this.cartCountNotificationValue, new Object[0]);
        if (this.cartCountView != null) {
            this.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (MainActivity.this.cartCountNotificationValue != 0 && MainActivity.this.cartCountNotificationValue != -131) {
                        MainActivity.this.cartCountView.setText((CharSequence)MainActivity.this.getString(2131230936, new Object[] { MainActivity.this.cartCountNotificationValue }));
                        MainActivity.this.cartCountView.setVisibility(0);
                        return;
                    }
                    MainActivity.this.cartCountView.setVisibility(8);
                }
            });
            return;
        }
        Timber.e("Cannot update cart count notification. Cart count view is null.", new Object[0]);
    }
    
    private void showSearchSuggestions(final String s, final SearchView searchView) {
        if (this.searchSuggestionsAdapter != null && this.searchSuggestionsList != null) {
            Timber.d("Populate search adapter - mySuggestions.size(): " + this.searchSuggestionsList.size(), new Object[0]);
            final MatrixCursor matrixCursor = new MatrixCursor(new String[] { "_id", "categories" });
            for (int i = 0; i < this.searchSuggestionsList.size(); ++i) {
                if (this.searchSuggestionsList.get(i) != null && this.searchSuggestionsList.get(i).toLowerCase().startsWith(s.toLowerCase())) {
                    matrixCursor.addRow(new Object[] { i, this.searchSuggestionsList.get(i) });
                }
            }
            searchView.setSuggestionsAdapter(this.searchSuggestionsAdapter);
            this.searchSuggestionsAdapter.changeCursor((Cursor)matrixCursor);
            return;
        }
        Timber.e("Search adapter is null or search data suggestions missing", new Object[0]);
    }
    
    public static void updateCartCountNotification() {
        final MainActivity instance = getInstance();
        if (instance != null) {
            instance.getCartCount(false);
            return;
        }
        Timber.e("MainActivity instance is null.", new Object[0]);
    }
    
    public void onAccountEditSelected() {
        this.launchUserSpecificFragment(new AccountEditFragment(), AccountEditFragment.class.getSimpleName(), new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                MainActivity.this.onAccountEditSelected();
            }
        });
    }
    
    @Override
    public void onAccountSelected() {
        this.replaceFragment(new AccountFragment(), AccountFragment.class.getSimpleName());
    }
    
    @Override
    public void onBackPressed() {
        if (this.drawerFragment == null || !this.drawerFragment.onBackHide()) {
            if (this.getSupportFragmentManager().getBackStackEntryCount() <= 0 && !this.isAppReadyToFinish) {
                MsgUtils.showToast(this, 1, this.getString(2131230792), MsgUtils.ToastLength.SHORT);
                this.isAppReadyToFinish = true;
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.this.isAppReadyToFinish = false;
                    }
                }, 2000L);
                return;
            }
            super.onBackPressed();
        }
    }
    
    public void onBannerSelected(final Banner banner) {
        if (banner == null) {
            Timber.e("onBannerSelected called with null parameters.", new Object[0]);
            return;
        }
        final String target = banner.getTarget();
        Timber.d("Open banner with target: " + target, new Object[0]);
        final String[] split = target.split(":");
        if (split.length < 2) {
            Timber.e(new RuntimeException(), "Parsed banner target has too less parameters.", new Object[0]);
            return;
        }
        final String s = split[0];
        switch (s) {
            default: {
                Timber.e("Unknown banner target type.", new Object[0]);
            }
            case "list": {
                this.replaceFragment(CategoryFragment.newInstance(Long.parseLong(split[1]), banner.getName(), null, null), CategoryFragment.class.getSimpleName() + " - banner");
            }
            case "detail": {
                this.replaceFragment(ProductFragment.newInstance(Long.parseLong(split[1])), ProductFragment.class.getSimpleName() + " - banner select");
            }
        }
    }
    
    public void onCartSelected() {
        this.launchUserSpecificFragment(new CartFragment(), CartFragment.class.getSimpleName(), new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                MainActivity.this.onCartSelected();
            }
        });
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        MainActivity.mInstance = this;
        Timber.d(MainActivity.class.getSimpleName() + " onCreate", new Object[0]);
        MyApplication.setAppLocale(SettingsMy.getActualNonNullShop(this).getLanguage());
        this.setContentView(2130968602);
        Analytics.prepareTrackersAndFbLogger(SettingsMy.getActualNonNullShop(this), this.getApplicationContext());
        final Toolbar supportActionBar = (Toolbar)this.findViewById(2131624067);
        this.setSupportActionBar(supportActionBar);
        final ActionBar supportActionBar2 = this.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayShowHomeEnabled(true);
        }
        else {
            Timber.e(new RuntimeException(), "GetSupportActionBar returned null.", new Object[0]);
        }
        (this.drawerFragment = (DrawerFragment)this.getSupportFragmentManager().findFragmentById(2131624069)).setUp((DrawerLayout)this.findViewById(2131624065), supportActionBar, (DrawerFragment.FragmentDrawerListener)this);
        this.searchSuggestionsList = new ArrayList<String>();
        this.mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (SettingsMy.getTokenSentToServer()) {
                    Timber.d("Gcm registration success.", new Object[0]);
                    return;
                }
                Timber.e("Gcm registration failed. Device isn't registered on server.", new Object[0]);
            }
        };
        this.registerGcmOnServer();
        this.addInitialFragment();
        if (this.getIntent() != null && this.getIntent().getExtras() != null) {
            final String string = this.getIntent().getExtras().getString("target", "");
            final String string2 = this.getIntent().getExtras().getString("title", "");
            Timber.d("Start notification with banner target: " + string, new Object[0]);
            final Banner banner = new Banner();
            banner.setTarget(string);
            banner.setName(string2);
            this.onBannerSelected(banner);
            Analytics.logOpenedByNotification(string);
        }
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(2131689472, menu);
        final MenuItem item = menu.findItem(2131624401);
        if (item != null) {
            this.prepareSearchView(item);
        }
        final MenuItem item2 = menu.findItem(2131624402);
        MenuItemCompat.setActionView(item2, 2130968601);
        final View actionView = MenuItemCompat.getActionView(item2);
        this.cartCountView = (TextView)actionView.findViewById(2131624064);
        this.showNotifyCount(this.cartCountNotificationValue);
        actionView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MainActivity.this.onCartSelected();
            }
        });
        if (this.cartCountNotificationValue == -131) {
            this.getCartCount(true);
        }
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    protected void onDestroy() {
        MainActivity.mInstance = null;
        super.onDestroy();
    }
    
    @Override
    public void onDrawerBannersSelected() {
        this.clearBackStack();
        final Fragment fragmentById = this.getSupportFragmentManager().findFragmentById(2131624068);
        if (fragmentById == null || !(fragmentById instanceof BannersFragment)) {
            this.replaceFragment(new BannersFragment(), BannersFragment.class.getSimpleName());
            return;
        }
        Timber.d("Banners already displayed.", new Object[0]);
    }
    
    @Override
    public void onDrawerItemCategorySelected(final DrawerItemCategory drawerItemCategory) {
        this.clearBackStack();
        this.replaceFragment(CategoryFragment.newInstance(drawerItemCategory), CategoryFragment.class.getSimpleName());
    }
    
    @Override
    public void onDrawerItemPageSelected(final DrawerItemPage drawerItemPage) {
        this.clearBackStack();
        this.replaceFragment(PageFragment.newInstance(drawerItemPage.getId()), PageFragment.class.getSimpleName());
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (itemId == 2131624403) {
            this.onWishlistSelected();
            return true;
        }
        if (itemId == 2131624402) {
            this.onCartSelected();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public void onOrderCreateSelected() {
        this.launchUserSpecificFragment(new OrderCreateFragment(), OrderCreateFragment.class.getSimpleName(), new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                MainActivity.this.onCartSelected();
            }
        });
    }
    
    public void onOrderSelected(final Order order) {
        if (order != null) {
            this.replaceFragment(OrderFragment.newInstance(order.getId()), OrderFragment.class.getSimpleName());
            return;
        }
        Timber.e("Creating order detail with null data.", new Object[0]);
    }
    
    public void onOrdersHistory() {
        this.launchUserSpecificFragment(new OrdersHistoryFragment(), OrdersHistoryFragment.class.getSimpleName(), new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                MainActivity.this.onOrdersHistory();
            }
        });
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp((Context)this);
        MyApplication.getInstance().cancelPendingRequests("main_activity_requests");
        LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.mRegistrationBroadcastReceiver);
    }
    
    public void onProductSelected(final long n) {
        final ProductFragment instance = ProductFragment.newInstance(n);
        if (Build$VERSION.SDK_INT > 21) {
            instance.setReturnTransition(TransitionInflater.from((Context)this).inflateTransition(17760258));
        }
        this.replaceFragment(instance, ProductFragment.class.getSimpleName());
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp((Context)this);
        LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.mRegistrationBroadcastReceiver, new IntentFilter("registrationComplete"));
    }
    
    public void onTermsAndConditionsSelected() {
        this.replaceFragment(PageFragment.newInstance(), PageFragment.class.getSimpleName());
    }
    
    public void onWishlistSelected() {
        this.launchUserSpecificFragment(new WishlistFragment(), WishlistFragment.class.getSimpleName(), new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                MainActivity.this.onWishlistSelected();
            }
        });
    }
    
    @Override
    public void prepareSearchSuggestions(final List<DrawerItemCategory> list) {
        this.searchSuggestionsAdapter = new SimpleCursorAdapter((Context)this, 17367043, null, new String[] { "categories" }, new int[] { 16908308 }, 2);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                if (!this.searchSuggestionsList.contains(list.get(i).getName())) {
                    this.searchSuggestionsList.add(list.get(i).getName());
                }
                if (list.get(i).hasChildren()) {
                    for (int j = 0; j < list.get(i).getChildren().size(); ++j) {
                        if (!this.searchSuggestionsList.contains(list.get(i).getChildren().get(j).getName())) {
                            this.searchSuggestionsList.add(list.get(i).getChildren().get(j).getName());
                        }
                    }
                }
            }
            this.searchSuggestionsAdapter.notifyDataSetChanged();
            return;
        }
        Timber.e("Search suggestions loading failed.", new Object[0]);
        this.searchSuggestionsAdapter = null;
    }
    
    public void registerGcmOnServer() {
        if (Utils.checkPlayServices(this)) {
            this.startService(new Intent((Context)this, (Class)MyRegistrationIntentService.class));
        }
    }
    
    public void startSettingsFragment() {
        this.replaceFragment(new SettingsFragment(), SettingsFragment.class.getSimpleName());
    }
}
