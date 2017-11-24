package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.content.*;
import java.util.*;
import bf.io.openshop.entities.filtr.*;
import bf.io.openshop.views.*;
import timber.log.*;
import android.view.*;
import android.widget.*;

public class FilterRecyclerAdapter extends Adapter<ViewHolder>
{
    public static final long DEFAULT_ID = -131L;
    private static final int TYPE_ITEM_COLOR = 0;
    private static final int TYPE_ITEM_RANGE = 2;
    private static final int TYPE_ITEM_SELECT = 1;
    private final Context context;
    private final List<FilterType> filterTypeList;
    LayoutInflater layoutInflater;
    
    public FilterRecyclerAdapter(final Context context, final Filters filters) {
        this.filterTypeList = new ArrayList<FilterType>();
        this.context = context;
        for (final FilterType filterType : filters.getFilters()) {
            if ("color".equals(filterType.getType())) {
                ((FilterTypeColor)filterType).getValues().add(0, new FilterValueColor(-131L, context.getString(2131230790)));
            }
            else {
                if (!"select".equals(filterType.getType())) {
                    continue;
                }
                ((FilterTypeSelect)filterType).getValues().add(0, new FilterValueSelect(-131L, context.getString(2131230790)));
            }
        }
        this.filterTypeList.addAll(filters.getFilters());
    }
    
    @Override
    public int getItemCount() {
        return this.filterTypeList.size();
    }
    
    @Override
    public int getItemViewType(final int n) {
        final String type = this.filterTypeList.get(n).getType();
        if ("range".equals(type)) {
            return 2;
        }
        if ("color".equals(type)) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        if (viewHolder instanceof ViewHolderColor) {
            final ViewHolderColor viewHolderColor = (ViewHolderColor)viewHolder;
            final FilterTypeColor filterTypeColor = this.filterTypeList.get(n);
            viewHolderColor.bindContent(filterTypeColor);
            viewHolderColor.colorName.setText((CharSequence)filterTypeColor.getName());
            viewHolderColor.colorSpinnerAdapter.setColorValuesList(filterTypeColor.getValues());
            return;
        }
        if (viewHolder instanceof ViewHolderSelect) {
            final ViewHolderSelect viewHolderSelect = (ViewHolderSelect)viewHolder;
            final FilterTypeSelect filterTypeSelect = this.filterTypeList.get(n);
            viewHolderSelect.bindContent(filterTypeSelect);
            viewHolderSelect.selectName.setText((CharSequence)filterTypeSelect.getName());
            viewHolderSelect.filterSelectSpinnerAdapter.setSelectValuesList(filterTypeSelect.getValues());
            return;
        }
        if (viewHolder instanceof ViewHolderRange) {
            final ViewHolderRange viewHolderRange = (ViewHolderRange)viewHolder;
            final FilterTypeRange filterTypeRange = this.filterTypeList.get(n);
            viewHolderRange.rangeName.setText((CharSequence)filterTypeRange.getName());
            final RangeSeekBar rangeSeekBar = new RangeSeekBar<Integer>(this.context);
            rangeSeekBar.setRangeValues(filterTypeRange.getMin(), filterTypeRange.getMax());
            rangeSeekBar.setNotifyWhileDragging(true);
            viewHolderRange.seekBarLayout.removeAllViews();
            viewHolderRange.seekBarLayout.addView((View)rangeSeekBar);
            if (filterTypeRange.getSelectedMin() < 0 && filterTypeRange.getSelectedMax() <= 0) {
                filterTypeRange.setSelectedMin(filterTypeRange.getMin());
                filterTypeRange.setSelectedMax(filterTypeRange.getMax());
            }
            rangeSeekBar.setSelectedMinValue(filterTypeRange.getSelectedMin());
            rangeSeekBar.setSelectedMaxValue(filterTypeRange.getSelectedMax());
            viewHolderRange.rangeResult.setText((CharSequence)this.context.getString(2131230939, new Object[] { filterTypeRange.getSelectedMin(), filterTypeRange.getRangeTitle(), filterTypeRange.getSelectedMax(), filterTypeRange.getRangeTitle() }));
            rangeSeekBar.setOnRangeSeekBarChangeListener((RangeSeekBar.OnRangeSeekBarChangeListener<Integer>)new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
                public void onRangeSeekBarValuesChanged(final RangeSeekBar<?> rangeSeekBar, final Integer n, final Integer n2) {
                    viewHolderRange.rangeResult.setText((CharSequence)FilterRecyclerAdapter.this.context.getString(2131230939, new Object[] { n, filterTypeRange.getRangeTitle(), n2, filterTypeRange.getRangeTitle() }));
                    filterTypeRange.setSelectedMin(n);
                    filterTypeRange.setSelectedMax(n2);
                }
            });
            return;
        }
        Timber.e(new RuntimeException(), "Unknown ViewHolder in class: " + CartRecyclerAdapter.class.getSimpleName(), new Object[0]);
    }
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        if (n == 0) {
            return new ViewHolderColor(this.layoutInflater.inflate(2130968650, viewGroup, false), this.context);
        }
        if (n == 1) {
            return new ViewHolderSelect(this.layoutInflater.inflate(2130968650, viewGroup, false), this.context);
        }
        return new ViewHolderRange(this.layoutInflater.inflate(2130968649, viewGroup, false));
    }
    
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
    
    public static class ViewHolderRange extends ViewHolder
    {
        public TextView rangeName;
        public TextView rangeResult;
        public LinearLayout seekBarLayout;
        
        public ViewHolderRange(final View view) {
            super(view);
            this.rangeName = (TextView)view.findViewById(2131624320);
            this.rangeResult = (TextView)view.findViewById(2131624322);
            this.seekBarLayout = (LinearLayout)view.findViewById(2131624321);
        }
    }
    
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
}
