package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import bf.io.openshop.entities.filtr.*;

public static class ViewHolderSelect extends ViewHolder
{
    public FilterSelectSpinnerAdapter filterSelectSpinnerAdapter;
    private FilterTypeSelect filterTypeSelect;
    public TextView selectName;
    public Spinner selectSpinner;
    
    public ViewHolderSelect(final View view, final Context context) {
        super(view);
        this.selectName = (TextView)view.findViewById(2131624323);
        this.selectSpinner = (Spinner)view.findViewById(2131624324);
        (this.filterSelectSpinnerAdapter = new FilterSelectSpinnerAdapter(context)).setDropDownViewResource(17367049);
        this.selectSpinner.setAdapter((SpinnerAdapter)this.filterSelectSpinnerAdapter);
        this.selectSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (ViewHolderSelect.this.filterTypeSelect.getValues().get(n).getId() != -131L) {
                    ViewHolderSelect.this.filterTypeSelect.setSelectedValue(ViewHolderSelect.this.filterTypeSelect.getValues().get(n));
                    return;
                }
                ViewHolderSelect.this.filterTypeSelect.setSelectedValue(null);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void bindContent(final FilterTypeSelect filterTypeSelect) {
        this.filterTypeSelect = filterTypeSelect;
    }
}
