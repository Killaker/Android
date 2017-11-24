package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import java.util.*;
import android.os.*;
import timber.log.*;
import bf.io.openshop.ux.adapters.*;
import android.content.*;
import android.support.v4.view.*;
import android.widget.*;
import android.app.*;
import android.view.*;

public class ProductImagesDialogFragment extends DialogFragment
{
    private int defaultPosition;
    private ArrayList<String> images;
    private ViewPager imagesPager;
    
    public ProductImagesDialogFragment() {
        this.defaultPosition = 0;
    }
    
    public static ProductImagesDialogFragment newInstance(final ArrayList<String> images, final int defaultPosition) {
        if (images == null || images.size() == 0) {
            return null;
        }
        final ProductImagesDialogFragment productImagesDialogFragment = new ProductImagesDialogFragment();
        productImagesDialogFragment.images = images;
        productImagesDialogFragment.defaultPosition = defaultPosition;
        return productImagesDialogFragment;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 2131427707);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " - onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968626, viewGroup, false);
        (this.imagesPager = (ViewPager)inflate.findViewById(2131624152)).setAdapter(new ProductImagesPagerAdapter((Context)this.getActivity(), this.images));
        if (this.defaultPosition > 0 && this.defaultPosition < this.images.size()) {
            this.imagesPager.setCurrentItem(this.defaultPosition);
        }
        else {
            this.imagesPager.setCurrentItem(0);
        }
        ((ImageView)inflate.findViewById(2131624154)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ProductImagesDialogFragment.this.imagesPager.setCurrentItem(ProductImagesDialogFragment.this.imagesPager.getCurrentItem() - 1, true);
            }
        });
        ((ImageView)inflate.findViewById(2131624153)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ProductImagesDialogFragment.this.imagesPager.setCurrentItem(ProductImagesDialogFragment.this.imagesPager.getCurrentItem() + 1, true);
            }
        });
        ((Button)inflate.findViewById(2131624155)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ProductImagesDialogFragment.this.dismiss();
            }
        });
        return inflate;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final Window window = dialog.getWindow();
            window.setLayout(-1, -1);
            window.setWindowAnimations(2131427706);
        }
    }
}
