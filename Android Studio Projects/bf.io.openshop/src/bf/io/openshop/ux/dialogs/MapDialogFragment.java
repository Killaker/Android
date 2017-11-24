package bf.io.openshop.ux.dialogs;

import android.os.*;
import android.app.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import bf.io.openshop.listeners.*;
import timber.log.*;
import bf.io.openshop.entities.delivery.*;
import android.widget.*;
import android.view.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapDialogFragment extends DialogFragment implements OnMapReadyCallback
{
    private Branch branch;
    private Shipping shipping;
    public ShippingDialogFragment shippingDialogFragment;
    private SupportMapFragment supportMapFragment;
    
    public MapDialogFragment() {
        (this.supportMapFragment = new SupportMapFragment()).getMapAsync(this);
    }
    
    public static MapDialogFragment newInstance(final Branch branch) {
        return newInstance(null, null, branch);
    }
    
    public static MapDialogFragment newInstance(final ShippingDialogFragment shippingDialogFragment, final Shipping shipping, final Branch branch) {
        final MapDialogFragment mapDialogFragment = new MapDialogFragment();
        mapDialogFragment.branch = branch;
        mapDialogFragment.shipping = shipping;
        mapDialogFragment.shippingDialogFragment = shippingDialogFragment;
        return mapDialogFragment;
    }
    
    public SupportMapFragment getFragment() {
        return this.supportMapFragment;
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        final Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        return onCreateDialog;
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130968623, viewGroup, false);
        this.getChildFragmentManager().beginTransaction().add(2131624139, this.supportMapFragment).commit();
        inflate.findViewById(2131624138).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MapDialogFragment.this.dismiss();
            }
        });
        final Button button = (Button)inflate.findViewById(2131624145);
        if (this.shippingDialogFragment == null || this.shipping == null) {
            button.setVisibility(8);
        }
        else {
            button.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
                @Override
                public void onSingleClick(final View view) {
                    if (MapDialogFragment.this.shippingDialogFragment != null && MapDialogFragment.this.shipping != null) {
                        MapDialogFragment.this.shippingDialogFragment.onShippingSelected(MapDialogFragment.this.shipping);
                        MapDialogFragment.this.dismiss();
                        return;
                    }
                    Timber.e("Something is null", new Object[0]);
                }
            });
        }
        final TextView textView = (TextView)inflate.findViewById(2131624137);
        final TextView textView2 = (TextView)inflate.findViewById(2131624141);
        final TextView textView3 = (TextView)inflate.findViewById(2131624143);
        final TextView textView4 = (TextView)inflate.findViewById(2131624144);
        final LinearLayout linearLayout = (LinearLayout)inflate.findViewById(2131624142);
        if (this.branch != null) {
            textView.setText((CharSequence)this.branch.getName());
            textView2.setText((CharSequence)this.branch.getAddress());
            if (this.branch.getOpeningHoursList() != null && this.branch.getOpeningHoursList().size() > 0) {
                textView3.setVisibility(0);
                String text = "";
                for (int i = 0; i < this.branch.getOpeningHoursList().size(); ++i) {
                    final OpeningHours openingHours = this.branch.getOpeningHoursList().get(i);
                    text = text + openingHours.getDay() + " " + openingHours.getOpening();
                    if (i != -1 + this.branch.getOpeningHoursList().size()) {
                        text += "\n";
                    }
                }
                textView3.setText((CharSequence)text);
            }
            else {
                textView3.setVisibility(8);
            }
            if (this.branch.getNote() != null && !this.branch.getNote().isEmpty()) {
                textView4.setVisibility(0);
                textView4.setText((CharSequence)this.branch.getNote());
            }
            else {
                textView4.setVisibility(8);
            }
            ((ImageView)inflate.findViewById(2131624140)).setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                final /* synthetic */ ScrollView val$parentScrollView = (ScrollView)inflate.findViewById(2131624136);
                
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        default: {
                            return true;
                        }
                        case 0: {
                            this.val$parentScrollView.requestDisallowInterceptTouchEvent(true);
                            return false;
                        }
                        case 1: {
                            this.val$parentScrollView.requestDisallowInterceptTouchEvent(false);
                            return true;
                        }
                        case 2: {
                            this.val$parentScrollView.requestDisallowInterceptTouchEvent(true);
                            return false;
                        }
                    }
                }
            });
            return inflate;
        }
        Timber.e(new RuntimeException(), "Null branch in MapDialogFragment", new Object[0]);
        return inflate;
    }
    
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (this.branch != null && this.branch.getCoordinates() != null) {
            final LatLng latLng = new LatLng(this.branch.getCoordinates().getLat(), this.branch.getCoordinates().getLon());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0f));
            googleMap.addMarker(new MarkerOptions().title(this.branch.getName()).snippet(this.branch.getAddress()).position(latLng).draggable(false).anchor(0.917f, 0.903f));
        }
    }
}
