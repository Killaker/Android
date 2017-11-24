package bf.io.openshop.ux.fragments;

import bf.io.openshop.ux.adapters.*;
import android.app.*;
import android.support.annotation.*;
import timber.log.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.*;
import bf.io.openshop.ux.*;
import android.support.v4.app.*;
import android.content.*;
import android.support.v7.widget.*;
import android.widget.*;
import bf.io.openshop.listeners.*;
import android.view.*;
import android.os.*;
import bf.io.openshop.utils.*;

public class BannersFragment extends Fragment
{
    private Metadata bannersMetadata;
    private RecyclerView bannersRecycler;
    private BannersRecyclerAdapter bannersRecyclerAdapter;
    private View emptyContent;
    private EndlessRecyclerScrollListener endlessRecyclerScrollListener;
    private boolean mAlreadyLoaded;
    private ProgressDialog progressDialog;
    
    public BannersFragment() {
        this.mAlreadyLoaded = false;
    }
    
    private void loadBanners(String format) {
        this.progressDialog.show();
        if (format == null) {
            this.bannersRecyclerAdapter.clear();
            format = String.format(EndPoints.BANNERS, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        }
        final GsonRequest<Object> gsonRequest = new GsonRequest<Object>(0, format, null, (Class<Object>)BannersResponse.class, (Response.Listener<Object>)new Response.Listener<BannersResponse>() {
            public void onResponse(@NonNull final BannersResponse bannersResponse) {
                Timber.d("response:" + bannersResponse.toString(), new Object[0]);
                BannersFragment.this.bannersMetadata = bannersResponse.getMetadata();
                BannersFragment.this.bannersRecyclerAdapter.addBanners(bannersResponse.getRecords());
                if (BannersFragment.this.bannersRecyclerAdapter.getItemCount() > 0) {
                    BannersFragment.this.emptyContent.setVisibility(4);
                    BannersFragment.this.bannersRecycler.setVisibility(0);
                }
                else {
                    BannersFragment.this.emptyContent.setVisibility(0);
                    BannersFragment.this.bannersRecycler.setVisibility(4);
                }
                BannersFragment.this.progressDialog.cancel();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (BannersFragment.this.progressDialog != null) {
                    BannersFragment.this.progressDialog.cancel();
                }
                MsgUtils.logAndShowErrorMessage(BannersFragment.this.getActivity(), volleyError);
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue(gsonRequest, "banner_requests");
    }
    
    private void prepareContentViews(final View view, final boolean b) {
        this.bannersRecycler = (RecyclerView)view.findViewById(2131624200);
        if (b) {
            this.bannersRecyclerAdapter = new BannersRecyclerAdapter((Context)this.getActivity(), new BannersRecyclerInterface() {
                @Override
                public void onBannerSelected(final Banner banner) {
                    final FragmentActivity activity = BannersFragment.this.getActivity();
                    if (activity instanceof MainActivity) {
                        ((MainActivity)activity).onBannerSelected(banner);
                    }
                }
            });
        }
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.bannersRecycler.getContext());
        this.bannersRecycler.setLayoutManager((RecyclerView.LayoutManager)layoutManager);
        this.bannersRecycler.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        this.bannersRecycler.setHasFixedSize(true);
        this.bannersRecycler.setAdapter((RecyclerView.Adapter)this.bannersRecyclerAdapter);
        this.endlessRecyclerScrollListener = new EndlessRecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore(final int n) {
                if (BannersFragment.this.bannersMetadata != null && BannersFragment.this.bannersMetadata.getLinks() != null && BannersFragment.this.bannersMetadata.getLinks().getNext() != null) {
                    BannersFragment.this.loadBanners(BannersFragment.this.bannersMetadata.getLinks().getNext());
                    return;
                }
                Timber.d("CustomLoadMoreDataFromApi NO MORE DATA", new Object[0]);
            }
        };
        this.bannersRecycler.addOnScrollListener((RecyclerView.OnScrollListener)this.endlessRecyclerScrollListener);
    }
    
    private void prepareEmptyContent(final View view) {
        this.emptyContent = view.findViewById(2131624201);
        ((TextView)view.findViewById(2131624202)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                final FragmentActivity activity = BannersFragment.this.getActivity();
                if (activity instanceof MainActivity) {
                    final MainActivity mainActivity = (MainActivity)activity;
                    if (mainActivity.drawerFragment != null) {
                        mainActivity.drawerFragment.toggleDrawerMenu();
                    }
                }
            }
        });
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " - OnCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230835));
        final View inflate = layoutInflater.inflate(2130968630, viewGroup, false);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.prepareEmptyContent(inflate);
        if ((bundle == null && !this.mAlreadyLoaded) || this.bannersRecyclerAdapter == null || this.bannersRecyclerAdapter.isEmpty()) {
            Timber.d("Reloading banners.", new Object[0]);
            this.prepareContentViews(inflate, this.mAlreadyLoaded = true);
            this.loadBanners(null);
            return inflate;
        }
        Timber.d("Banners already loaded.", new Object[0]);
        this.prepareContentViews(inflate, false);
        return inflate;
    }
    
    @Override
    public void onDestroyView() {
        if (this.bannersRecycler != null) {
            this.bannersRecycler.clearOnScrollListeners();
        }
        super.onDestroyView();
    }
    
    @Override
    public void onStop() {
        if (this.progressDialog != null) {
            if (this.progressDialog.isShowing() && this.endlessRecyclerScrollListener != null) {
                this.endlessRecyclerScrollListener.resetLoading();
            }
            this.progressDialog.cancel();
        }
        MyApplication.getInstance().cancelPendingRequests("banner_requests");
        super.onStop();
    }
}
