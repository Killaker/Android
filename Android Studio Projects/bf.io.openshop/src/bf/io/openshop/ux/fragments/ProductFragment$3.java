package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.*;
import timber.log.*;
import com.facebook.share.widget.*;
import android.app.*;
import com.facebook.share.model.*;
import android.net.*;
import android.content.*;
import bf.io.openshop.utils.*;
import com.facebook.internal.*;

class ProductFragment$3 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        if (MyApplication.getInstance().isDataConnected()) {
            Timber.d("FragmentProductDetail share link clicked", new Object[0]);
            try {
                final MessageDialog messageDialog = new MessageDialog(ProductFragment.this.getActivity());
                if (MessageDialog.canShow(ShareLinkContent.class)) {
                    ((FacebookDialogBase<ShareLinkContent, RESULT>)messageDialog).show(((ShareLinkContent.Builder)((ShareContent.Builder<P, ShareLinkContent.Builder>)new ShareLinkContent.Builder().setContentTitle(ProductFragment.access$300(ProductFragment.this).getName()).setContentDescription(ProductFragment.access$300(ProductFragment.this).getDescription())).setContentUrl(Uri.parse(ProductFragment.access$300(ProductFragment.this).getUrl()))).setImageUrl(Uri.parse(ProductFragment.access$300(ProductFragment.this).getMainImage())).build());
                    return;
                }
                Timber.e("FragmentProductDetail - APP is NOT installed", new Object[0]);
                try {
                    ProductFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.facebook.orca")));
                    return;
                }
                catch (ActivityNotFoundException ex2) {
                    ProductFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.facebook.orca")));
                    return;
                }
            }
            catch (Exception ex) {
                Timber.e(ex, "Create share dialog exception", new Object[0]);
                return;
            }
        }
        MsgUtils.showToast(ProductFragment.this.getActivity(), 3, null, MsgUtils.ToastLength.SHORT);
    }
}