package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import java.util.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import timber.log.*;
import android.app.*;
import bf.io.openshop.api.*;
import com.android.volley.*;
import bf.io.openshop.ux.adapters.*;
import bf.io.openshop.*;
import android.os.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.utils.*;
import android.content.*;
import android.widget.*;
import android.view.*;
import bf.io.openshop.ux.dialogs.*;

public class SettingsFragment extends Fragment
{
    private ProgressDialog progressDialog;
    private Spinner spinShopSelection;
    
    private void requestShops() {
        if (this.progressDialog != null) {
            this.progressDialog.show();
        }
        final GsonRequest<Object> gsonRequest = new GsonRequest<Object>(0, EndPoints.SHOPS, null, (Class<Object>)ShopResponse.class, (Response.Listener<Object>)new Response.Listener<ShopResponse>() {
            public void onResponse(@NonNull final ShopResponse shopResponse) {
                Timber.d("Available shops response:" + shopResponse.toString(), new Object[0]);
                SettingsFragment.this.setSpinShops(shopResponse.getShopList());
                if (SettingsFragment.this.progressDialog != null) {
                    SettingsFragment.this.progressDialog.cancel();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (SettingsFragment.this.progressDialog != null) {
                    SettingsFragment.this.progressDialog.cancel();
                }
                MsgUtils.logAndShowErrorMessage(SettingsFragment.this.getActivity(), volleyError);
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue(gsonRequest, "settings_requests");
    }
    
    private void setSpinShops(final List<Shop> list) {
        final ShopSpinnerAdapter adapter = new ShopSpinnerAdapter(this.getActivity(), list);
        adapter.setDropDownViewResource(17367049);
        this.spinShopSelection.setAdapter((SpinnerAdapter)adapter);
        int n = 0;
        int selection;
        while (true) {
            final int size = list.size();
            selection = 0;
            if (n >= size) {
                break;
            }
            if (list.get(n).getId() == SettingsMy.getActualNonNullShop(this.getActivity()).getId()) {
                selection = n;
                break;
            }
            ++n;
        }
        this.spinShopSelection.setSelection(selection);
        this.spinShopSelection.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final Shop shop = (Shop)adapterView.getItemAtPosition(n);
                if (shop != null && shop.getId() != SettingsMy.getActualNonNullShop(SettingsFragment.this.getActivity()).getId()) {
                    RestartDialogFragment.newInstance(shop).show(SettingsFragment.this.getFragmentManager(), RestartDialogFragment.class.getSimpleName());
                    return;
                }
                Timber.e("Selected null or same shop.", new Object[0]);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
                Timber.d("Nothing selected", new Object[0]);
            }
        });
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968640, viewGroup, false);
        MainActivity.setActionBarTitle(this.getString(2131230893));
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.spinShopSelection = (Spinner)inflate.findViewById(2131624292);
        ((LinearLayout)inflate.findViewById(2131624293)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                new LicensesDialogFragment().show(SettingsFragment.this.getFragmentManager(), LicensesDialogFragment.class.getSimpleName());
            }
        });
        this.requestShops();
        return inflate;
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("settings_requests");
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        super.onStop();
    }
}
