package bf.io.openshop.ux.dialogs;

import bf.io.openshop.interfaces.*;
import android.support.annotation.*;
import timber.log.*;
import android.content.*;
import android.support.v4.app.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import android.os.*;
import android.widget.*;
import bf.io.openshop.listeners.*;
import bf.io.openshop.entities.delivery.*;
import bf.io.openshop.ux.adapters.*;
import java.util.*;
import android.app.*;
import android.view.*;

public class ShippingDialogFragment extends DialogFragment
{
    private Delivery delivery;
    private ProgressBar progressBar;
    private Shipping selectedShippingType;
    private ShippingDialogInterface shippingDialogInterface;
    private View shippingEmpty;
    private ListView shippingList;
    private Fragment thisFragment;
    
    private void getOnlyBranches() {
        final GsonRequest<Object> gsonRequest = new GsonRequest<Object>(0, String.format(EndPoints.BRANCHES, SettingsMy.getActualNonNullShop(this.getActivity()).getId()), null, (Class<Object>)BranchesRequest.class, (Response.Listener<Object>)new Response.Listener<BranchesRequest>() {
            public void onResponse(@NonNull final BranchesRequest branchesRequest) {
                Timber.d("GetBranches response: " + branchesRequest.toString(), new Object[0]);
                ShippingDialogFragment.this.setContentVisible(true);
                if (branchesRequest.getBranches() != null && branchesRequest.getBranches().size() >= 0) {
                    ShippingDialogFragment.this.shippingEmpty.setVisibility(8);
                    ShippingDialogFragment.this.shippingList.setVisibility(0);
                    ShippingDialogFragment.this.shippingList.setAdapter((ListAdapter)new BranchesAdapter((Context)ShippingDialogFragment.this.getActivity(), branchesRequest.getBranches()));
                    ShippingDialogFragment.this.shippingList.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                        private long mLastClickTime = 0L;
                        
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            if (SystemClock.elapsedRealtime() - this.mLastClickTime >= 1000L) {
                                this.mLastClickTime = SystemClock.elapsedRealtime();
                                final Branch branch = (Branch)ShippingDialogFragment.this.shippingList.getItemAtPosition(n);
                                if (branch != null) {
                                    final FragmentManager fragmentManager = ShippingDialogFragment.this.thisFragment.getFragmentManager();
                                    final MapDialogFragment instance = MapDialogFragment.newInstance(branch);
                                    instance.setRetainInstance(true);
                                    instance.show(fragmentManager, MapDialogFragment.class.getSimpleName());
                                }
                            }
                        }
                    });
                    return;
                }
                ShippingDialogFragment.this.shippingEmpty.setVisibility(0);
                ShippingDialogFragment.this.shippingList.setVisibility(8);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Timber.e("Get branches error: " + volleyError.getMessage(), new Object[0]);
                ShippingDialogFragment.this.setContentVisible(true);
                MsgUtils.logAndShowErrorMessage(ShippingDialogFragment.this.getActivity(), volleyError);
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue(gsonRequest, "delivery_dialog_requests");
    }
    
    public static ShippingDialogFragment newInstance(final Delivery delivery, final Shipping selectedShippingType, final ShippingDialogInterface shippingDialogInterface) {
        final ShippingDialogFragment shippingDialogFragment = new ShippingDialogFragment();
        shippingDialogFragment.delivery = delivery;
        shippingDialogFragment.selectedShippingType = selectedShippingType;
        shippingDialogFragment.shippingDialogInterface = shippingDialogInterface;
        return shippingDialogFragment;
    }
    
    public static ShippingDialogFragment newInstance(final ShippingDialogInterface shippingDialogInterface) {
        final ShippingDialogFragment shippingDialogFragment = new ShippingDialogFragment();
        shippingDialogFragment.delivery = null;
        shippingDialogFragment.shippingDialogInterface = shippingDialogInterface;
        return shippingDialogFragment;
    }
    
    private void setContentVisible(final boolean b) {
        if (b) {
            this.progressBar.setVisibility(8);
            this.shippingList.setVisibility(0);
            return;
        }
        this.progressBar.setVisibility(0);
        this.shippingList.setVisibility(8);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        ((DialogFragment)(this.thisFragment = this)).setStyle(1, 2131427707);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(ShippingDialogFragment.class.getSimpleName() + " onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968682, viewGroup, false);
        this.shippingList = (ListView)inflate.findViewById(2131624386);
        this.progressBar = (ProgressBar)inflate.findViewById(2131624388);
        this.shippingEmpty = inflate.findViewById(2131624387);
        final TextView textView = (TextView)inflate.findViewById(2131624385);
        inflate.findViewById(2131624384).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                ShippingDialogFragment.this.dismiss();
            }
        });
        if (this.delivery == null) {
            textView.setText(2131230865);
            this.getOnlyBranches();
        }
        else {
            this.setContentVisible(true);
            if (this.delivery != null) {
                final ArrayList<DeliveryType> data = new ArrayList<DeliveryType>();
                if (this.delivery.getShipping() != null && !this.delivery.getShipping().isEmpty()) {
                    final DeliveryType deliveryType = new DeliveryType(-131L, this.getString(2131230894));
                    deliveryType.setShippingList(this.delivery.getShipping());
                    data.add(deliveryType);
                }
                if (this.delivery.getPersonalPickup() != null && !this.delivery.getPersonalPickup().isEmpty()) {
                    final DeliveryType deliveryType2 = new DeliveryType(1L, this.getString(2131230865));
                    deliveryType2.setShippingList(this.delivery.getPersonalPickup());
                    data.add(deliveryType2);
                }
                final ShippingSpinnerAdapter adapter = new ShippingSpinnerAdapter((Context)this.getActivity(), this);
                adapter.setData(data);
                adapter.preselectShipping(this.selectedShippingType);
                this.shippingList.setAdapter((ListAdapter)adapter);
                this.shippingList.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        ShippingDialogFragment.this.onShippingSelected(adapter.getItem(n));
                    }
                });
                return inflate;
            }
        }
        return inflate;
    }
    
    public void onShippingSelected(final Shipping shipping) {
        if (this.shippingDialogInterface != null) {
            this.shippingDialogInterface.onShippingSelected(shipping);
        }
        Timber.e("Shipping click: " + shipping.toString(), new Object[0]);
        this.dismiss();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final Window window = dialog.getWindow();
            window.setLayout(-1, -1);
            window.setWindowAnimations(2131427694);
        }
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("delivery_dialog_requests");
        super.onStop();
    }
}
