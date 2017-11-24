package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import android.os.*;
import android.app.*;
import android.content.*;
import android.view.*;
import bf.io.openshop.ux.*;
import android.widget.*;
import android.support.v4.content.*;
import android.text.*;
import android.support.annotation.*;

public class OrderCreateSuccessDialogFragment extends DialogFragment
{
    private boolean sampleApplication;
    
    public OrderCreateSuccessDialogFragment() {
        this.sampleApplication = false;
    }
    
    public static OrderCreateSuccessDialogFragment newInstance(final boolean sampleApplication) {
        final OrderCreateSuccessDialogFragment orderCreateSuccessDialogFragment = new OrderCreateSuccessDialogFragment();
        orderCreateSuccessDialogFragment.sampleApplication = sampleApplication;
        return orderCreateSuccessDialogFragment;
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity(), 2131427695);
        final View inflate = this.getActivity().getLayoutInflater().inflate(2130968625, (ViewGroup)null);
        ((Button)inflate.findViewById(2131624151)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (OrderCreateSuccessDialogFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)OrderCreateSuccessDialogFragment.this.getActivity()).onDrawerBannersSelected();
                }
                OrderCreateSuccessDialogFragment.this.dismiss();
            }
        });
        final TextView textView = (TextView)inflate.findViewById(2131624149);
        final TextView textView2 = (TextView)inflate.findViewById(2131624150);
        if (this.sampleApplication) {
            textView.setText(2131230909);
            textView2.setTextColor(ContextCompat.getColor(this.getContext(), 2131558532));
            textView2.setText(2131230887);
        }
        else {
            textView.setText(2131230906);
            textView2.setTextColor(ContextCompat.getColor(this.getContext(), 2131558426));
            textView2.setText((CharSequence)Html.fromHtml(this.getString(2131230916)));
        }
        alertDialog$Builder.setView(inflate);
        return (Dialog)alertDialog$Builder.create();
    }
}
