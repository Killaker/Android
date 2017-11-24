package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import android.webkit.*;
import android.widget.*;
import bf.io.openshop.entities.*;
import android.app.*;
import android.support.annotation.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import android.os.*;
import android.view.*;
import timber.log.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.utils.*;
import android.content.*;

public class PageFragment extends Fragment
{
    public static final String PAGE_ID = "page_id";
    private static final long TERMS_AND_CONDITIONS = -131L;
    private View layoutContent;
    private View layoutEmpty;
    private WebView pageContent;
    private TextView pageTitle;
    private ProgressDialog progressDialog;
    
    private void getPage(final long n) {
        String s;
        if (n == -131L) {
            s = String.format(EndPoints.PAGES_TERMS_AND_COND, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
        }
        else {
            s = String.format(EndPoints.PAGES_SINGLE, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), n);
        }
        this.progressDialog.show();
        final GsonRequest gsonRequest = new GsonRequest<Object>(0, s, null, (Class<Object>)Page.class, (Response.Listener<Object>)new Response.Listener<Page>() {
            public void onResponse(@NonNull final Page page) {
                PageFragment.this.handleResponse(page);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (PageFragment.this.progressDialog != null) {
                    PageFragment.this.progressDialog.cancel();
                }
                PageFragment.this.setContentVisible(false);
                MsgUtils.logAndShowErrorMessage(PageFragment.this.getActivity(), volleyError);
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "page_requests");
    }
    
    private void handleResponse(final Page page) {
        if (page != null && page.getText() != null && !page.getText().isEmpty()) {
            this.setContentVisible(true);
            this.pageTitle.setText((CharSequence)page.getTitle());
            this.pageContent.loadData("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><html>  <head>  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"></head>  <body>" + page.getText() + "</body></html>", "text/html; charset=UTF-8", (String)null);
        }
        else {
            this.setContentVisible(false);
        }
        new Handler().postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                if (PageFragment.this.progressDialog != null) {
                    PageFragment.this.progressDialog.cancel();
                }
            }
        }, 200L);
    }
    
    public static PageFragment newInstance() {
        final Bundle arguments = new Bundle();
        arguments.putLong("page_id", -131L);
        final PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }
    
    public static PageFragment newInstance(final long n) {
        final Bundle arguments = new Bundle();
        arguments.putLong("page_id", n);
        final PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }
    
    private void setContentVisible(final boolean b) {
        if (this.layoutEmpty != null && this.layoutContent != null) {
            if (!b) {
                this.layoutEmpty.setVisibility(0);
                this.layoutContent.setVisibility(8);
                return;
            }
            this.layoutEmpty.setVisibility(8);
            this.layoutContent.setVisibility(0);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968638, viewGroup, false);
        MainActivity.setActionBarTitle(this.getString(2131230950));
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
        this.layoutEmpty = inflate.findViewById(2131624264);
        this.layoutContent = inflate.findViewById(2131624265);
        this.pageTitle = (TextView)inflate.findViewById(2131624266);
        this.pageContent = (WebView)inflate.findViewById(2131624267);
        if (this.getArguments() != null && this.getArguments().getLong("page_id") != 0L) {
            this.getPage(this.getArguments().getLong("page_id"));
            return inflate;
        }
        Timber.e(new RuntimeException(), "Created fragment with null arguments.", new Object[0]);
        this.setContentVisible(false);
        MsgUtils.showToast(this.getActivity(), 2, "", MsgUtils.ToastLength.LONG);
        return inflate;
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("page_requests");
        if (this.progressDialog != null) {
            this.progressDialog.cancel();
        }
        super.onStop();
    }
}
