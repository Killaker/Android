package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import mbanje.kurt.fabbutton.*;
import timber.log.*;
import android.app.*;
import android.support.annotation.*;
import bf.io.openshop.ux.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.api.*;
import bf.io.openshop.entities.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.content.*;
import org.json.*;
import android.graphics.*;
import bf.io.openshop.listeners.*;
import com.facebook.share.widget.*;
import com.facebook.share.model.*;
import android.net.*;
import android.content.*;
import android.support.v7.widget.*;
import android.util.*;
import android.widget.*;
import bf.io.openshop.ux.dialogs.*;
import bf.io.openshop.interfaces.*;
import bf.io.openshop.utils.*;
import android.text.method.*;
import android.text.*;
import bf.io.openshop.ux.adapters.*;
import java.util.*;
import bf.io.openshop.entities.product.*;
import android.view.*;
import com.facebook.internal.*;

public class ProductFragment extends Fragment
{
    public static final String PRODUCT_ID = "product_id";
    private ImageView addToCartImage;
    private ProgressBar addToCartProgress;
    private Spinner colorSpinner;
    private ScrollView contentScrollLayout;
    private boolean inWishlist;
    private View layoutEmpty;
    private Product product;
    private RelativeLayout productContainer;
    private ProductImagesRecyclerAdapter productImagesAdapter;
    private RecyclerView productImagesRecycler;
    private ArrayList<String> productImagesUrls;
    private TextView productInfoTv;
    private TextView productNameTv;
    private TextView productPriceDiscountPercentTv;
    private TextView productPriceDiscountTv;
    private TextView productPriceTv;
    private ProgressBar progressView;
    private RelatedProductsRecyclerAdapter relatedProductsAdapter;
    private ViewTreeObserver$OnScrollChangedListener scrollViewListener;
    private ProductVariant selectedProductVariant;
    private SizeVariantSpinnerAdapter sizeVariantSpinnerAdapter;
    private FabButton wishlistButton;
    private long wishlistId;
    
    public ProductFragment() {
        this.selectedProductVariant = null;
        this.inWishlist = false;
        this.wishlistId = -131L;
    }
    
    private void addRecommendedProducts(final ArrayList<Product> list) {
        if (list != null && list.size() > 0) {
            Timber.d("AddRecommendedProducts size : " + list.size(), new Object[0]);
            final Iterator<Product> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.relatedProductsAdapter.addLast(iterator.next());
            }
        }
        else {
            Timber.d("Related products are null or empty.", new Object[0]);
        }
    }
    
    private void getProduct(final long n) {
        final String format = String.format(EndPoints.PRODUCTS_SINGLE_RELATED, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), n);
        this.setContentVisible(CONST.VISIBLE.PROGRESS);
        final GsonRequest gsonRequest = new GsonRequest<Object>(0, format, null, Product.class, new Response.Listener<Product>() {
            public void onResponse(@NonNull final Product product) {
                MainActivity.setActionBarTitle(product.getName());
                if (product.getVariants() != null && product.getVariants().size() > 0) {
                    ProductFragment.this.getWishListInfo(n);
                }
                ProductFragment.this.addRecommendedProducts(product.getRelated());
                ProductFragment.this.refreshScreenData(product);
                ProductFragment.this.setContentVisible(CONST.VISIBLE.CONTENT);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                ProductFragment.this.setContentVisible(CONST.VISIBLE.EMPTY);
                MsgUtils.logAndShowErrorMessage(ProductFragment.this.getActivity(), volleyError);
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue((Request<Object>)gsonRequest, "product_requests");
    }
    
    private void getWishListInfo(final long n) {
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            final JsonRequest jsonRequest = new JsonRequest(0, String.format(EndPoints.WISHLIST_IS_IN_WISHLIST, SettingsMy.getActualNonNullShop(this.getActivity()).getId(), n), null, new Response.Listener<JSONObject>() {
                public void onResponse(final JSONObject jsonObject) {
                    ProductFragment.this.prepareWishListButton(jsonObject);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    MsgUtils.logAndShowErrorMessage(ProductFragment.this.getActivity(), volleyError);
                }
            }, this.getFragmentManager(), activeUser.getAccessToken());
            jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
            jsonRequest.setShouldCache(false);
            MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "product_requests");
        }
    }
    
    public static ProductFragment newInstance(final long n) {
        final Bundle arguments = new Bundle();
        arguments.putLong("product_id", n);
        final ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(arguments);
        return productFragment;
    }
    
    private void postProductToCart() {
        if (this.selectedProductVariant == null || this.selectedProductVariant.getSize() == null || (this.selectedProductVariant.getId() == -131L && this.selectedProductVariant.getSize().getId() == -131L)) {
            MsgUtils.showToast(this.getActivity(), 5, null, MsgUtils.ToastLength.SHORT);
            return;
        }
        final User activeUser = SettingsMy.getActiveUser();
        if (activeUser != null) {
            if (this.addToCartImage != null) {
                this.addToCartImage.setVisibility(4);
            }
            if (this.addToCartProgress != null) {
                this.addToCartProgress.setVisibility(0);
            }
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("product_variant_id", this.selectedProductVariant.getId());
                final JsonRequest jsonRequest = new JsonRequest(1, String.format(EndPoints.CART, SettingsMy.getActualNonNullShop(this.getActivity()).getId()), jsonObject, new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject jsonObject) {
                        if (ProductFragment.this.addToCartImage != null) {
                            ProductFragment.this.addToCartImage.setVisibility(0);
                        }
                        if (ProductFragment.this.addToCartProgress != null) {
                            ProductFragment.this.addToCartProgress.setVisibility(4);
                        }
                        Analytics.logAddProductToCart(ProductFragment.this.product.getRemoteId(), ProductFragment.this.product.getName(), ProductFragment.this.product.getDiscountPrice());
                        MainActivity.updateCartCountNotification();
                        final Snackbar setAction = Snackbar.make((View)ProductFragment.this.productContainer, ProductFragment.this.getString(2131230870) + " " + ProductFragment.this.getString(2131230924), 0).setActionTextColor(ContextCompat.getColor((Context)ProductFragment.this.getActivity(), 2131558426)).setAction(2131230829, (View$OnClickListener)new View$OnClickListener() {
                            public void onClick(final View view) {
                                if (ProductFragment.this.getActivity() instanceof MainActivity) {
                                    ((MainActivity)ProductFragment.this.getActivity()).onCartSelected();
                                }
                            }
                        });
                        ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(-1);
                        setAction.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError volleyError) {
                        if (ProductFragment.this.addToCartImage != null) {
                            ProductFragment.this.addToCartImage.setVisibility(0);
                        }
                        if (ProductFragment.this.addToCartProgress != null) {
                            ProductFragment.this.addToCartProgress.setVisibility(4);
                        }
                        MsgUtils.logAndShowErrorMessage(ProductFragment.this.getActivity(), volleyError);
                    }
                }, this.getFragmentManager(), activeUser.getAccessToken());
                jsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                jsonRequest.setShouldCache(false);
                MyApplication.getInstance().addToRequestQueue((Request<Object>)jsonRequest, "product_requests");
                return;
            }
            catch (JSONException ex) {
                Timber.e((Throwable)ex, "Create json add product to cart exception", new Object[0]);
                MsgUtils.showToast(this.getActivity(), 2, null, MsgUtils.ToastLength.SHORT);
                return;
            }
        }
        LoginDialogFragment.newInstance(new LoginDialogInterface() {
            @Override
            public void successfulLoginOrRegistration(final User user) {
                ProductFragment.this.postProductToCart();
            }
        }).show(this.getFragmentManager(), LoginDialogFragment.class.getSimpleName());
    }
    
    private void prepareButtons(final View view) {
        this.addToCartImage = (ImageView)view.findViewById(2131624283);
        this.addToCartProgress = (ProgressBar)view.findViewById(2131624284);
        this.addToCartProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor((Context)this.getActivity(), 2131558530), PorterDuff$Mode.MULTIPLY);
        view.findViewById(2131624282).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                ProductFragment.this.postProductToCart();
            }
        });
        ((Button)view.findViewById(2131624286)).setOnClickListener((View$OnClickListener)new OnSingleClickListener() {
            @Override
            public void onSingleClick(final View view) {
                if (MyApplication.getInstance().isDataConnected()) {
                    Timber.d("FragmentProductDetail share link clicked", new Object[0]);
                    try {
                        final MessageDialog messageDialog = new MessageDialog(ProductFragment.this.getActivity());
                        if (MessageDialog.canShow(ShareLinkContent.class)) {
                            ((FacebookDialogBase<ShareLinkContent, RESULT>)messageDialog).show(((ShareLinkContent.Builder)((ShareContent.Builder<P, ShareLinkContent.Builder>)new ShareLinkContent.Builder().setContentTitle(ProductFragment.this.product.getName()).setContentDescription(ProductFragment.this.product.getDescription())).setContentUrl(Uri.parse(ProductFragment.this.product.getUrl()))).setImageUrl(Uri.parse(ProductFragment.this.product.getMainImage())).build());
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
        });
    }
    
    private void prepareProductImagesLayout(final View view) {
        (this.productImagesRecycler = (RecyclerView)view.findViewById(2131624270)).setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this.getActivity(), 0, false));
        this.productImagesAdapter = new ProductImagesRecyclerAdapter((Context)this.getActivity(), new ProductImagesRecyclerInterface() {
            @Override
            public void onImageSelected(final View view, final int n) {
                final ProductImagesDialogFragment instance = ProductImagesDialogFragment.newInstance(ProductFragment.this.productImagesUrls, n);
                if (instance != null) {
                    instance.show(ProductFragment.this.getFragmentManager(), ProductImagesDialogFragment.class.getSimpleName());
                    return;
                }
                Timber.e(ProductImagesDialogFragment.class.getSimpleName() + "Called with empty image list", new Object[0]);
            }
        });
        this.productImagesRecycler.setAdapter((RecyclerView.Adapter)this.productImagesAdapter);
        final ViewGroup$LayoutParams layoutParams = this.productImagesRecycler.getLayoutParams();
        final DisplayMetrics displayMetrics = this.getActivity().getResources().getDisplayMetrics();
        if (displayMetrics.densityDpi <= 160) {
            layoutParams.height = (int)(0.4 * displayMetrics.heightPixels);
        }
        else {
            layoutParams.height = (int)(0.48 * displayMetrics.heightPixels);
        }
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(2131624287);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this.getActivity(), 0, false));
        recyclerView.addItemDecoration((RecyclerView.ItemDecoration)new RecyclerMarginDecorator(this.getContext(), RecyclerMarginDecorator.ORIENTATION.HORIZONTAL));
        recyclerView.setAdapter((RecyclerView.Adapter)(this.relatedProductsAdapter = new RelatedProductsRecyclerAdapter((Context)this.getActivity(), new RelatedProductsRecyclerInterface() {
            @Override
            public void onRelatedProductSelected(final View view, final int n, final Product product) {
                if (product != null && ProductFragment.this.getActivity() instanceof MainActivity) {
                    ((MainActivity)ProductFragment.this.getActivity()).onProductSelected(product.getId());
                }
            }
        })));
    }
    
    private void prepareScrollViewAndWishlist(final View view) {
        final View viewById = view.findViewById(2131624273);
        this.wishlistButton = (FabButton)view.findViewById(2131624288);
        this.scrollViewListener = (ViewTreeObserver$OnScrollChangedListener)new ViewTreeObserver$OnScrollChangedListener() {
            private boolean alphaFull = false;
            
            public void onScrollChanged() {
                final int scrollY = ProductFragment.this.contentScrollLayout.getScrollY();
                if (ProductFragment.this.productImagesRecycler == null) {
                    Timber.e("Null productImagesScroll", new Object[0]);
                    return;
                }
                if (2 * ProductFragment.this.wishlistButton.getWidth() > scrollY) {
                    ProductFragment.this.wishlistButton.setTranslationX((float)(scrollY / 4));
                }
                else {
                    ProductFragment.this.wishlistButton.setTranslationX((float)(ProductFragment.this.wishlistButton.getWidth() / 2));
                }
                float alpha;
                if (ProductFragment.this.productImagesRecycler.getHeight() > scrollY) {
                    ProductFragment.this.productImagesRecycler.setTranslationY((float)(scrollY / 2));
                    alpha = scrollY / ProductFragment.this.productImagesRecycler.getHeight();
                }
                else {
                    alpha = 1.0f;
                }
                if (this.alphaFull) {
                    if (alpha <= 0.99) {
                        this.alphaFull = false;
                    }
                    return;
                }
                if (alpha >= 0.9) {
                    this.alphaFull = true;
                }
                viewById.setAlpha(alpha);
            }
        };
    }
    
    private void prepareSizeSpinner(final View view) {
        final Spinner spinner = (Spinner)view.findViewById(2131624281);
        (this.sizeVariantSpinnerAdapter = new SizeVariantSpinnerAdapter((Context)this.getActivity())).setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)this.sizeVariantSpinnerAdapter);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final ProductVariant item = ProductFragment.this.sizeVariantSpinnerAdapter.getItem(n);
                if (item == null || item.getSize() == null) {
                    ProductFragment.this.selectedProductVariant = null;
                    Timber.e(new RuntimeException(), "User click on null product variant. WTF", new Object[0]);
                    MsgUtils.showToast(ProductFragment.this.getActivity(), 2, "", MsgUtils.ToastLength.SHORT);
                    return;
                }
                Timber.d("selectedVariant: " + item.getId() + ", selectedSize" + item.getSize().getValue(), new Object[0]);
                if (item.getId() == -131L && item.getSize().getId() != -131L) {
                    ProductFragment.this.selectedProductVariant = null;
                    return;
                }
                ProductFragment.this.selectedProductVariant = item;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
                ProductFragment.this.selectedProductVariant = null;
            }
        });
    }
    
    private void prepareWishListButton(final JSONObject jsonObject) {
        if (jsonObject.has("is_in_wishlist") && !jsonObject.isNull("is_in_wishlist") && jsonObject.has("wishlist_product_id")) {
            try {
                this.inWishlist = jsonObject.getBoolean("is_in_wishlist");
                if (jsonObject.isNull("wishlist_product_id")) {
                    this.wishlistId = -131L;
                }
                else {
                    this.wishlistId = jsonObject.getLong("wishlist_product_id");
                }
                if (this.inWishlist && this.wishlistId == -131L) {
                    Timber.e("Inconsistent data in is_in_wishlist response", new Object[0]);
                    return;
                }
            }
            catch (Exception ex) {
                Timber.e(ex, "Wishlist info parse exception", new Object[0]);
                return;
            }
            if (this.inWishlist) {
                this.wishlistButton.setIcon(2130837721, 2130837720);
            }
            else {
                this.wishlistButton.setIcon(2130837720, 2130837721);
            }
            this.wishlistButton.setVisibility(0);
            this.wishlistButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                private boolean running = false;
                
                public void onClick(final View view) {
                    if (!this.running) {
                        this.running = true;
                        final User activeUser = SettingsMy.getActiveUser();
                        if (activeUser == null) {
                            this.running = false;
                            new LoginExpiredDialogFragment().show(ProductFragment.this.getFragmentManager(), "loginExpiredDialogFragment");
                            return;
                        }
                        if (!ProductFragment.this.inWishlist) {
                            ProductFragment.this.inWishlist = true;
                            ProductFragment.this.wishlistButton.setIcon(2130837720, 2130837721);
                            ProductFragment.this.wishlistButton.showProgress(true);
                            WishlistFragment.addToWishList(ProductFragment.this.getActivity(), ProductFragment.this.product.getVariants().get(0).getId(), activeUser, "product_requests", new RequestListener() {
                                @Override
                                public void requestFailed(final VolleyError volleyError) {
                                    View$OnClickListener.this.running = false;
                                    ProductFragment.this.wishlistButton.showProgress(false);
                                }
                                
                                @Override
                                public void requestSuccess(final long n) {
                                    View$OnClickListener.this.running = false;
                                    ProductFragment.this.wishlistButton.onProgressCompleted();
                                    ProductFragment.this.wishlistId = n;
                                    final Snackbar setAction = Snackbar.make((View)ProductFragment.this.productContainer, ProductFragment.this.getString(2131230871), 0).setActionTextColor(ContextCompat.getColor((Context)ProductFragment.this.getActivity(), 2131558426)).setAction(2131230830, (View$OnClickListener)new View$OnClickListener() {
                                        public void onClick(final View view) {
                                            if (ProductFragment.this.getActivity() instanceof MainActivity) {
                                                ((MainActivity)ProductFragment.this.getActivity()).onWishlistSelected();
                                            }
                                        }
                                    });
                                    ((TextView)setAction.getView().findViewById(2131624091)).setTextColor(-1);
                                    setAction.show();
                                }
                            });
                            return;
                        }
                        ProductFragment.this.inWishlist = false;
                        ProductFragment.this.wishlistButton.setIcon(2130837721, 2130837720);
                        ProductFragment.this.wishlistButton.showProgress(true);
                        if (ProductFragment.this.wishlistId == -131L) {
                            this.running = false;
                            ProductFragment.this.wishlistButton.showProgress(false);
                            Timber.e(new RuntimeException(), "Trying remove product from wishlist with error consta", new Object[0]);
                            return;
                        }
                        WishlistFragment.removeFromWishList(ProductFragment.this.getActivity(), ProductFragment.this.wishlistId, activeUser, "product_requests", new RequestListener() {
                            @Override
                            public void requestFailed(final VolleyError volleyError) {
                                View$OnClickListener.this.running = false;
                                ProductFragment.this.wishlistButton.showProgress(false);
                            }
                            
                            @Override
                            public void requestSuccess(final long n) {
                                View$OnClickListener.this.running = false;
                                ProductFragment.this.wishlistButton.onProgressCompleted();
                                ProductFragment.this.wishlistId = -131L;
                            }
                        });
                    }
                }
            });
            return;
        }
        Timber.e("Missing is_in_wishlist data." + jsonObject, new Object[0]);
    }
    
    private void refreshScreenData(final Product spinners) {
        if (spinners != null) {
            Analytics.logProductView(spinners.getRemoteId(), spinners.getName());
            this.productNameTv.setText((CharSequence)spinners.getName());
            final double price = spinners.getPrice();
            final double discountPrice = spinners.getDiscountPrice();
            if (price == discountPrice || Math.abs(price - discountPrice) / Math.max(Math.abs(price), Math.abs(discountPrice)) < 1.0E-6) {
                this.productPriceDiscountTv.setText((CharSequence)spinners.getDiscountPriceFormatted());
                this.productPriceDiscountTv.setTextColor(ContextCompat.getColor(this.getContext(), 2131558531));
                this.productPriceTv.setVisibility(8);
                this.productPriceDiscountPercentTv.setVisibility(8);
            }
            else {
                this.productPriceDiscountTv.setText((CharSequence)spinners.getDiscountPriceFormatted());
                this.productPriceDiscountTv.setTextColor(ContextCompat.getColor(this.getContext(), 2131558426));
                this.productPriceTv.setVisibility(0);
                this.productPriceTv.setText((CharSequence)spinners.getPriceFormatted());
                this.productPriceTv.setPaintFlags(17);
                this.productPriceDiscountPercentTv.setVisibility(0);
                this.productPriceDiscountPercentTv.setText((CharSequence)Utils.calculateDiscountPercent(this.getContext(), price, discountPrice));
            }
            if (spinners.getDescription() != null) {
                this.productInfoTv.setMovementMethod(LinkMovementMethod.getInstance());
                this.productInfoTv.setText((CharSequence)Utils.safeURLSpanLinks(Html.fromHtml(spinners.getDescription()), this.getActivity()));
            }
            this.setSpinners(spinners);
            return;
        }
        MsgUtils.showToast(this.getActivity(), 2, this.getString(2131230833), MsgUtils.ToastLength.LONG);
        Timber.e(new RuntimeException(), "Refresh product screen with null product", new Object[0]);
    }
    
    private void setContentVisible(final CONST.VISIBLE visible) {
        if (this.layoutEmpty == null || this.contentScrollLayout == null || this.progressView == null) {
            Timber.e(new RuntimeException(), "Setting content visibility with null views.", new Object[0]);
            return;
        }
        switch (visible) {
            default: {
                this.layoutEmpty.setVisibility(8);
                this.contentScrollLayout.setVisibility(0);
                this.progressView.setVisibility(8);
            }
            case EMPTY: {
                this.layoutEmpty.setVisibility(0);
                this.contentScrollLayout.setVisibility(4);
                this.progressView.setVisibility(8);
            }
            case PROGRESS: {
                this.layoutEmpty.setVisibility(8);
                this.contentScrollLayout.setVisibility(4);
                this.progressView.setVisibility(0);
            }
        }
    }
    
    private void setSpinners(final Product product) {
        if (product == null || product.getVariants() == null || product.getVariants().size() <= 0) {
            Timber.e("Setting spinners for null product variants.", new Object[0]);
            return;
        }
        this.product = product;
        final ArrayList<ProductColor> productColorList = new ArrayList<ProductColor>();
        final Iterator<ProductVariant> iterator = product.getVariants().iterator();
        while (iterator.hasNext()) {
            final ProductColor color = iterator.next().getColor();
            if (!productColorList.contains(color)) {
                productColorList.add(color);
            }
        }
        if (productColorList.size() > 1) {
            this.colorSpinner.setVisibility(0);
            final ColorSpinnerAdapter adapter = new ColorSpinnerAdapter((Context)this.getActivity());
            adapter.setProductColorList(productColorList);
            this.colorSpinner.setAdapter((SpinnerAdapter)adapter);
            this.colorSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    final ProductColor productColor = (ProductColor)adapterView.getItemAtPosition(n);
                    if (productColor != null) {
                        Timber.d("ColorPicker selected color: " + productColor.toString(), new Object[0]);
                        ProductFragment.this.updateImagesAndSizeSpinner(productColor);
                        return;
                    }
                    Timber.e("Retrieved null color from spinner.", new Object[0]);
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                    Timber.d("Nothing selected in product colors spinner.", new Object[0]);
                }
            });
            return;
        }
        this.colorSpinner.setVisibility(8);
        this.updateImagesAndSizeSpinner(product.getVariants().get(0).getColor());
    }
    
    private void updateImagesAndSizeSpinner(final ProductColor productColor) {
        if (this.product != null) {
            final ArrayList<ProductVariant> productSizeList = new ArrayList<ProductVariant>();
            this.productImagesUrls = new ArrayList<String>();
            for (final ProductVariant productVariant : this.product.getVariants()) {
                if (productVariant.getColor().equals(productColor)) {
                    productSizeList.add(productVariant);
                    if (productVariant.getImages() != null && productVariant.getImages().length > 0) {
                        for (final String s : productVariant.getImages()) {
                            if (!this.productImagesUrls.contains(s)) {
                                this.productImagesUrls.add(s);
                                Timber.d("getAvailableSizesForColor image: " + s, new Object[0]);
                            }
                        }
                    }
                    else {
                        if (this.productImagesUrls.contains(this.product.getMainImage())) {
                            continue;
                        }
                        this.productImagesUrls.add(this.product.getMainImage());
                        Timber.d("getAvailableSizesForColor images[] == null, setMain_image()", new Object[0]);
                    }
                }
            }
            if (productSizeList.size() > 1) {
                productSizeList.add(0, new ProductVariant(-131L, new ProductSize(-131L, -131L, this.getString(2131230891))));
            }
            this.sizeVariantSpinnerAdapter.setProductSizeList(productSizeList);
            if (this.productImagesAdapter != null) {
                this.productImagesAdapter.clearAll();
                final Iterator<String> iterator2 = this.productImagesUrls.iterator();
                while (iterator2.hasNext()) {
                    this.productImagesAdapter.addLast(iterator2.next());
                }
            }
        }
        else {
            Timber.e("UpdateImagesAndSizeSpinner with null product in memory.", new Object[0]);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Timber.d(this.getClass().getSimpleName() + " onCreateView", new Object[0]);
        MainActivity.setActionBarTitle(this.getString(2131230870));
        final View inflate = layoutInflater.inflate(2130968639, viewGroup, false);
        this.progressView = (ProgressBar)inflate.findViewById(2131624289);
        this.productContainer = (RelativeLayout)inflate.findViewById(2131624268);
        this.layoutEmpty = inflate.findViewById(2131624290);
        this.contentScrollLayout = (ScrollView)inflate.findViewById(2131624269);
        this.productNameTv = (TextView)inflate.findViewById(2131624275);
        this.productPriceDiscountPercentTv = (TextView)inflate.findViewById(2131624279);
        this.productPriceDiscountTv = (TextView)inflate.findViewById(2131624278);
        this.productPriceTv = (TextView)inflate.findViewById(2131624277);
        this.productInfoTv = (TextView)inflate.findViewById(2131624285);
        this.colorSpinner = (Spinner)inflate.findViewById(2131624280);
        this.prepareSizeSpinner(inflate);
        this.prepareButtons(inflate);
        this.prepareProductImagesLayout(inflate);
        this.prepareScrollViewAndWishlist(inflate);
        this.getProduct(this.getArguments().getLong("product_id", 0L));
        return inflate;
    }
    
    @Override
    public void onPause() {
        if (this.contentScrollLayout != null) {
            this.contentScrollLayout.getViewTreeObserver().removeOnScrollChangedListener(this.scrollViewListener);
        }
        super.onPause();
    }
    
    @Override
    public void onResume() {
        if (this.contentScrollLayout != null) {
            this.contentScrollLayout.getViewTreeObserver().addOnScrollChangedListener(this.scrollViewListener);
        }
        super.onResume();
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("product_requests");
        this.setContentVisible(CONST.VISIBLE.CONTENT);
        if (this.addToCartImage != null) {
            this.addToCartImage.setVisibility(0);
        }
        if (this.addToCartProgress != null) {
            this.addToCartProgress.setVisibility(4);
        }
        super.onStop();
    }
}
