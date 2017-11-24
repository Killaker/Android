package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import bf.io.openshop.entities.filtr.*;

public static class ViewHolderColor extends ViewHolder
{
    public TextView colorName;
    public Spinner colorSpinner;
    public FilterColorSpinnerAdapter colorSpinnerAdapter;
    private FilterTypeColor filterTypeColor;
    
    public ViewHolderColor(final View view, final Context context) {
        super(view);
        this.colorName = (TextView)view.findViewById(2131624323);
        this.colorSpinner = (Spinner)view.findViewById(2131624324);
        (this.colorSpinnerAdapter = new FilterColorSpinnerAdapter(context)).setDropDownViewResource(17367049);
        this.colorSpinner.setAdapter((SpinnerAdapter)this.colorSpinnerAdapter);
        this.colorSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (ViewHolderColor.this.filterTypeColor.getValues().get(n).getId() != -131L) {
                    ViewHolderColor.this.filterTypeColor.setSelectedValue(ViewHolderColor.this.filterTypeColor.getValues().get(n));
                    return;
                }
                ViewHolderColor.this.filterTypeColor.setSelectedValue(null);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void bindContent(final FilterTypeColor filterTypeColor) {
        this.filterTypeColor = filterTypeColor;
    }
}
