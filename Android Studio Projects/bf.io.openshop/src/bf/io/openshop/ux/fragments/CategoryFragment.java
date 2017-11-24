package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import bf.io.openshop.entities.filtr.*;
import android.app.*;
import java.net.*;
import timber.log.*;
import bf.io.openshop.entities.*;
import android.support.annotation.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import java.io.*;
import bf.io.openshop.entities.drawerMenu.*;
import android.content.*;
import android.support.v7.widget.*;
import bf.io.openshop.listeners.*;
import bf.io.openshop.entities.product.*;
import android.os.*;
import android.transition.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.ux.adapters.*;
import android.widget.*;
import android.view.*;
import android.support.design.widget.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.utils.*;
import android.view.animation.*;

public class CategoryFragment extends Fragment
{
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";
    public static final String SEARCH_QUERY = "search_query";
    public static final String TYPE = "type";
    private long categoryId;
    private String categoryType;
    private TextView emptyContentView;
    private EndlessRecyclerScrollListener endlessRecyclerScrollListener;
    private ImageView filterButton;
    private String filterParameters;
    private Filters filters;
    boolean firstTimeSort;
    private boolean isList;
    private View loadMoreProgress;
    private Metadata productsMetadata;
    private RecyclerView productsRecycler;
    private ProductsRecyclerAdapter productsRecyclerAdapter;
    private GridLayoutManager productsRecyclerLayoutManager;
    private String searchQuery;
    private Spinner sortSpinner;
    private ImageSwitcher switchLayoutManager;
    private int toolbarOffset;
    
    public CategoryFragment() {
        this.firstTimeSort = true;
        this.searchQuery = null;
        this.filterParameters = null;
        this.toolbarOffset = -1;
        this.isList = false;
    }
    
    private void animateRecyclerLayoutChange(final int n) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ((Animation)alphaAnimation).setInterpolator((Interpolator)new DecelerateInterpolator());
        ((Animation)alphaAnimation).setDuration(400L);
        ((Animation)alphaAnimation).setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                CategoryFragment.this.productsRecyclerLayoutManager.setSpanCount(n);
                ((RecyclerView.LayoutManager)CategoryFragment.this.productsRecyclerLayoutManager).requestLayout();
                final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                ((Animation)alphaAnimation).setInterpolator((Interpolator)new AccelerateInterpolator());
                ((Animation)alphaAnimation).setDuration(400L);
                CategoryFragment.this.productsRecycler.startAnimation((Animation)alphaAnimation);
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        this.productsRecycler.startAnimation((Animation)alphaAnimation);
    }
    
    private void checkEmptyContent() {
        if (this.productsRecyclerAdapter != null && this.productsRecyclerAdapter.getItemCount() > 0) {
            this.emptyContentView.setVisibility(4);
            this.productsRecycler.setVisibility(0);
            return;
        }
        this.emptyContentView.setVisibility(0);
        this.productsRecycler.setVisibility(4);
    }
    
    private void getProducts(String s) {
        this.loadMoreProgress.setVisibility(0);
        while (true) {
            final String format;
            Label_0233: {
                if (s != null) {
                    break Label_0233;
                }
                if (this.endlessRecyclerScrollListener != null) {
                    this.endlessRecyclerScrollListener.clean();
                }
                this.productsRecyclerAdapter.clear();
                format = String.format(EndPoints.PRODUCTS, SettingsMy.getActualNonNullShop(this.getActivity()).getId());
                if (this.searchQuery == null) {
                    break Label_0233;
                }
                while (true) {
                    try {
                        final String s2 = URLEncoder.encode(this.searchQuery, "UTF-8");
                        Timber.d("GetFirstProductsInCategory isSearch: " + this.searchQuery, new Object[0]);
                        s = format + "?search=" + s2;
                        if (this.filterParameters != null && !this.filterParameters.isEmpty()) {
                            s += this.filterParameters;
                        }
                        final SortItem sortItem = (SortItem)this.sortSpinner.getSelectedItem();
                        if (sortItem != null) {
                            s = s + "&sort=" + sortItem.getValue();
                        }
                        final GsonRequest<Object> gsonRequest = new GsonRequest<Object>(0, s, null, (Class<Object>)ProductListResponse.class, (Response.Listener<Object>)new Response.Listener<ProductListResponse>() {
                            public void onResponse(@NonNull final ProductListResponse productListResponse) {
                                CategoryFragment.this.firstTimeSort = false;
                                CategoryFragment.this.productsRecyclerAdapter.addProducts(productListResponse.getProducts());
                                CategoryFragment.this.productsMetadata = productListResponse.getMetadata();
                                if (CategoryFragment.this.filters == null) {
                                    CategoryFragment.this.filters = CategoryFragment.this.productsMetadata.getFilters();
                                }
                                CategoryFragment.this.checkEmptyContent();
                                CategoryFragment.this.loadMoreProgress.setVisibility(8);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(final VolleyError volleyError) {
                                if (CategoryFragment.this.loadMoreProgress != null) {
                                    CategoryFragment.this.loadMoreProgress.setVisibility(8);
                                }
                                CategoryFragment.this.checkEmptyContent();
                                MsgUtils.logAndShowErrorMessage(CategoryFragment.this.getActivity(), volleyError);
                            }
                        });
                        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                        gsonRequest.setShouldCache(false);
                        MyApplication.getInstance().addToRequestQueue(gsonRequest, "category_requests");
                        return;
                    }
                    catch (UnsupportedEncodingException ex) {
                        Timber.e(ex, "Unsupported encoding exception", new Object[0]);
                        final String s2 = URLEncoder.encode(this.searchQuery);
                        continue;
                    }
                    break;
                }
            }
            s = format + "?" + this.categoryType + "=" + this.categoryId;
            continue;
        }
    }
    
    public static CategoryFragment newInstance(final long n, final String s, final String s2, final String s3) {
        final Bundle arguments = new Bundle();
        arguments.putLong("categoryId", n);
        arguments.putString("categoryName", s);
        arguments.putString("type", s2);
        arguments.putString("search_query", s3);
        final CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setArguments(arguments);
        return categoryFragment;
    }
    
    public static CategoryFragment newInstance(final DrawerItemCategory drawerItemCategory) {
        if (drawerItemCategory != null) {
            return newInstance(drawerItemCategory.getOriginalId(), drawerItemCategory.getName(), drawerItemCategory.getType(), null);
        }
        Timber.e(new RuntimeException(), "Creating category with null arguments", new Object[0]);
        return null;
    }
    
    public static CategoryFragment newInstance(final String s) {
        final Bundle arguments = new Bundle();
        arguments.putString("search_query", s);
        final CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setArguments(arguments);
        return categoryFragment;
    }
    
    private void prepareProductRecycler(final View view) {
        (this.productsRecycler = (RecyclerView)view.findViewById(2131624218)).addItemDecoration((RecyclerView.ItemDecoration)new RecyclerMarginDecorator((Context)this.getActivity(), RecyclerMarginDecorator.ORIENTATION.BOTH));
        this.productsRecycler.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        this.productsRecycler.setHasFixedSize(true);
        this.switchLayoutManager.setFactory((ViewSwitcher$ViewFactory)new ViewSwitcher$ViewFactory() {
            public View makeView() {
                return (View)new ImageView(CategoryFragment.this.getContext());
            }
        });
        if (this.isList) {
            this.switchLayoutManager.setImageResource(2130837673);
            this.productsRecyclerLayoutManager = new GridLayoutManager((Context)this.getActivity(), 1);
        }
        else {
            this.switchLayoutManager.setImageResource(2130837674);
            this.productsRecyclerLayoutManager = new GridLayoutManager((Context)this.getActivity(), 2);
        }
        this.productsRecycler.setLayoutManager((RecyclerView.LayoutManager)this.productsRecyclerLayoutManager);
        this.endlessRecyclerScrollListener = new EndlessRecyclerScrollListener(this.productsRecyclerLayoutManager) {
            @Override
            public void onLoadMore(final int n) {
                Timber.e("Load more", new Object[0]);
                if (CategoryFragment.this.productsMetadata != null && CategoryFragment.this.productsMetadata.getLinks() != null && CategoryFragment.this.productsMetadata.getLinks().getNext() != null) {
                    CategoryFragment.this.getProducts(CategoryFragment.this.productsMetadata.getLinks().getNext());
                    return;
                }
                Timber.d("CustomLoadMoreDataFromApi NO MORE DATA", new Object[0]);
            }
        };
        this.productsRecycler.addOnScrollListener((RecyclerView.OnScrollListener)this.endlessRecyclerScrollListener);
        this.productsRecycler.setAdapter((RecyclerView.Adapter)this.productsRecyclerAdapter);
        this.switchLayoutManager.setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (CategoryFragment.this.isList) {
                    CategoryFragment.this.isList = false;
                    CategoryFragment.this.switchLayoutManager.setImageResource(2130837674);
                    CategoryFragment.this.productsRecyclerAdapter.defineImagesQuality(false);
                    CategoryFragment.this.animateRecyclerLayoutChange(2);
                    return;
                }
                CategoryFragment.this.isList = true;
                CategoryFragment.this.switchLayoutManager.setImageResource(2130837673);
                CategoryFragment.this.productsRecyclerAdapter.defineImagesQuality(true);
                CategoryFragment.this.animateRecyclerLayoutChange(1);
            }
        });
    }
    
    private void prepareRecyclerAdapter() {
        this.productsRecyclerAdapter = new ProductsRecyclerAdapter((Context)this.getActivity(), new CategoryRecyclerInterface() {
            @Override
            public void onProductSelected(final View view, final Product product) {
                if (Build$VERSION.SDK_INT > 21) {
                    CategoryFragment.this.setReenterTransition(TransitionInflater.from((Context)CategoryFragment.this.getActivity()).inflateTransition(17760258));
                }
                ((MainActivity)CategoryFragment.this.getActivity()).onProductSelected(product.getId());
            }
        });
    }
    
    private void prepareSortSpinner() {
        this.sortSpinner.setAdapter((SpinnerAdapter)new SortSpinnerAdapter(this.getActivity()));
        this.sortSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)null);
        this.sortSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            private int lastSortSpinnerPosition = -1;
            
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int lastSortSpinnerPosition, final long n) {
                if (CategoryFragment.this.firstTimeSort) {
                    CategoryFragment.this.firstTimeSort = false;
                    return;
                }
                Timber.d("Selected pos: " + lastSortSpinnerPosition, new Object[0]);
                if (lastSortSpinnerPosition != this.lastSortSpinnerPosition) {
                    Timber.d("OnItemSelected change", new Object[0]);
                    this.lastSortSpinnerPosition = lastSortSpinnerPosition;
                    CategoryFragment.this.getProducts(null);
                    return;
                }
                Timber.d("OnItemSelected no change", new Object[0]);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
                Timber.d("OnNothingSelected - no change", new Object[0]);
            }
        });
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d("CategoryFragment OnCreateView", new Object[0]);
        final View inflate = layoutInflater.inflate(2130968632, viewGroup, false);
        this.emptyContentView = (TextView)inflate.findViewById(2131624219);
        this.loadMoreProgress = inflate.findViewById(2131624220);
        this.sortSpinner = (Spinner)inflate.findViewById(2131624217);
        this.switchLayoutManager = (ImageSwitcher)inflate.findViewById(2131624215);
        final Bundle arguments = this.getArguments();
        if (arguments == null) {
            MsgUtils.showToast(this.getActivity(), 2, this.getString(2131230833), MsgUtils.ToastLength.LONG);
            Timber.e(new RuntimeException(), "Run category fragment without arguments.", new Object[0]);
            return inflate;
        }
        this.categoryId = arguments.getLong("categoryId", 0L);
        String actionBarTitle = arguments.getString("categoryName", "");
        this.categoryType = arguments.getString("type", "category");
        this.searchQuery = arguments.getString("search_query", (String)null);
        final String searchQuery = this.searchQuery;
        boolean b = false;
        if (searchQuery != null) {
            final boolean empty = this.searchQuery.isEmpty();
            b = false;
            if (!empty) {
                b = true;
                this.categoryId = -10L;
                actionBarTitle = this.searchQuery;
            }
        }
        Timber.d("Category type: " + this.categoryType + ". CategoryId: " + this.categoryId + ". FilterUrl: " + this.filterParameters, new Object[0]);
        final AppBarLayout appBarLayout = (AppBarLayout)inflate.findViewById(2131624213);
        if (this.toolbarOffset != -1) {
            appBarLayout.offsetTopAndBottom(this.toolbarOffset);
        }
        appBarLayout.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener)new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, final int n) {
                CategoryFragment.this.toolbarOffset = n;
            }
        });
        MainActivity.setActionBarTitle(actionBarTitle);
        (this.filterButton = (ImageView)inflate.findViewById(2131624216)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (CategoryFragment.this.filters == null) {
                    MsgUtils.showToast(CategoryFragment.this.getActivity(), 1, CategoryFragment.this.getString(2131230826), MsgUtils.ToastLength.SHORT);
                    return;
                }
                final FilterDialogFragment instance = FilterDialogFragment.newInstance(CategoryFragment.this.filters, new FilterDialogInterface() {
                    @Override
                    public void onFilterCancelled() {
                        CategoryFragment.this.filterParameters = null;
                        CategoryFragment.this.filterButton.setImageResource(2130837672);
                        CategoryFragment.this.getProducts(null);
                    }
                    
                    @Override
                    public void onFilterSelected(final String s) {
                        CategoryFragment.this.filterParameters = s;
                        CategoryFragment.this.filterButton.setImageResource(2130837671);
                        CategoryFragment.this.getProducts(null);
                    }
                });
                if (instance != null) {
                    instance.show(CategoryFragment.this.getFragmentManager(), "filterDialogFragment");
                    return;
                }
                MsgUtils.showToast(CategoryFragment.this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
            }
        });
        if (this.filterParameters != null && !this.filterParameters.isEmpty()) {
            this.filterButton.setImageResource(2130837671);
        }
        else {
            this.filterButton.setImageResource(2130837672);
        }
        if (this.productsRecyclerAdapter == null || this.productsRecyclerAdapter.getItemCount() == 0) {
            this.prepareRecyclerAdapter();
            this.prepareProductRecycler(inflate);
            this.prepareSortSpinner();
            this.getProducts(null);
            Analytics.logCategoryView(this.categoryId, actionBarTitle, b);
            return inflate;
        }
        this.prepareProductRecycler(inflate);
        Timber.d("Restore previous category state. (Products already loaded) ", new Object[0]);
        return inflate;
    }
    
    @Override
    public void onDestroyView() {
        if (this.productsRecycler != null) {
            this.productsRecycler.clearOnScrollListeners();
        }
        super.onDestroyView();
    }
    
    @Override
    public void onStop() {
        if (this.loadMoreProgress != null) {
            if (this.loadMoreProgress.getVisibility() == 0 && this.endlessRecyclerScrollListener != null) {
                this.endlessRecyclerScrollListener.resetLoading();
            }
            this.loadMoreProgress.setVisibility(8);
        }
        MyApplication.getInstance().cancelPendingRequests("category_requests");
        super.onStop();
    }
    
    @Override
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        final Animation loadAnimation = AnimationUtils.loadAnimation(this.getContext(), 2131034128);
        final Animation loadAnimation2 = AnimationUtils.loadAnimation(this.getContext(), 17432577);
        this.switchLayoutManager.setInAnimation(loadAnimation);
        this.switchLayoutManager.setOutAnimation(loadAnimation2);
    }
}
