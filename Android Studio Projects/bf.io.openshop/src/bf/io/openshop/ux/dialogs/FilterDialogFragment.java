package bf.io.openshop.ux.dialogs;

import android.support.v4.app.*;
import bf.io.openshop.interfaces.*;
import timber.log.*;
import android.content.*;
import android.support.v7.widget.*;
import bf.io.openshop.ux.adapters.*;
import java.util.*;
import android.os.*;
import bf.io.openshop.utils.*;
import android.widget.*;
import bf.io.openshop.entities.filtr.*;
import android.app.*;
import android.view.*;

public class FilterDialogFragment extends DialogFragment
{
    private Filters filterData;
    private FilterDialogInterface filterDialogInterface;
    private ProgressDialog progressDialog;
    
    public static FilterDialogFragment newInstance(final Filters filterData, final FilterDialogInterface filterDialogInterface) {
        final FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
        if (filterData == null || filterDialogInterface == null) {
            Timber.e(new RuntimeException(), "Created filterDialog with null parameters.", new Object[0]);
            return null;
        }
        filterDialogFragment.filterData = filterData;
        filterDialogFragment.filterDialogInterface = filterDialogInterface;
        return filterDialogFragment;
    }
    
    private void prepareFilterRecycler(final View view) {
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(2131624103);
        recyclerView.addItemDecoration((RecyclerView.ItemDecoration)new RecyclerMarginDecorator((Context)this.getActivity(), RecyclerMarginDecorator.ORIENTATION.VERTICAL));
        recyclerView.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this.getActivity()));
        recyclerView.setAdapter((RecyclerView.Adapter)new FilterRecyclerAdapter((Context)this.getActivity(), this.filterData));
    }
    
    public String buildFilterUrl() {
        String s = "";
        for (final FilterType filterType : this.filterData.getFilters()) {
            if ("color".equals(filterType.getType())) {
                final FilterTypeColor filterTypeColor = (FilterTypeColor)filterType;
                if (filterTypeColor.getSelectedValue() == null) {
                    continue;
                }
                s = s + "&" + filterType.getLabel() + "=" + filterTypeColor.getSelectedValue().getId();
            }
            else if ("select".equals(filterType.getType())) {
                final FilterTypeSelect filterTypeSelect = (FilterTypeSelect)filterType;
                if (filterTypeSelect.getSelectedValue() == null) {
                    continue;
                }
                s = s + "&" + filterType.getLabel() + "=" + filterTypeSelect.getSelectedValue().getId();
            }
            else if ("range".equals(filterType.getType())) {
                final FilterTypeRange filterTypeRange = (FilterTypeRange)filterType;
                if (filterTypeRange.getSelectedMin() < 0 || filterTypeRange.getSelectedMax() <= 0) {
                    continue;
                }
                s = s + "&" + filterType.getLabel() + "=" + filterTypeRange.getSelectedMin() + "|" + filterTypeRange.getSelectedMax();
            }
            else {
                Timber.e("Unknown filter type.", new Object[0]);
            }
        }
        Timber.d("BuildFilterUrl - " + s, new Object[0]);
        return s;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setStyle(1, 2131427707);
        this.progressDialog = Utils.generateProgressDialog((Context)this.getActivity(), false);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d("filterDialogFragment - onCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968621, viewGroup, false);
        this.prepareFilterRecycler(inflate);
        ((Button)inflate.findViewById(2131624106)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                FilterDialogFragment.this.filterDialogInterface.onFilterSelected(FilterDialogFragment.this.buildFilterUrl());
                FilterDialogFragment.this.dismiss();
            }
        });
        ((Button)inflate.findViewById(2131624105)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (FilterDialogFragment.this.filterData != null) {
                    for (final FilterType filterType : FilterDialogFragment.this.filterData.getFilters()) {
                        if ("range".equals(filterType.getType())) {
                            ((FilterTypeRange)filterType).setSelectedMin(-1);
                            ((FilterTypeRange)filterType).setSelectedMax(-1);
                        }
                        else if ("color".equals(filterType.getType())) {
                            ((FilterTypeColor)filterType).setSelectedValue(null);
                        }
                        else {
                            if (!"select".equals(filterType.getType())) {
                                continue;
                            }
                            ((FilterTypeSelect)filterType).setSelectedValue(null);
                        }
                    }
                }
                FilterDialogFragment.this.filterDialogInterface.onFilterCancelled();
                FilterDialogFragment.this.dismiss();
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
            window.setWindowAnimations(2131427694);
        }
    }
}
