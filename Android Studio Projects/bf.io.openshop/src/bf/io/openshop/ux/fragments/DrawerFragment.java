package bf.io.openshop.ux.fragments;

import android.support.v4.app.*;
import bf.io.openshop.ux.adapters.*;
import android.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.content.*;
import android.view.animation.*;
import timber.log.*;
import android.app.*;
import android.support.annotation.*;
import bf.io.openshop.utils.*;
import bf.io.openshop.api.*;
import bf.io.openshop.*;
import com.android.volley.*;
import bf.io.openshop.entities.drawerMenu.*;
import bf.io.openshop.interfaces.*;
import android.view.*;
import android.os.*;
import android.support.v7.widget.*;
import java.util.*;

public class DrawerFragment extends Fragment
{
    public static final int BANNERS_ID = -123;
    private FragmentDrawerListener drawerListener;
    private boolean drawerLoading;
    private ProgressBar drawerProgress;
    private RecyclerView drawerRecycler;
    private DrawerRecyclerAdapter drawerRecyclerAdapter;
    private Button drawerRetryBtn;
    private LinearLayout drawerSubmenuLayout;
    private DrawerSubmenuRecyclerAdapter drawerSubmenuRecyclerAdapter;
    private TextView drawerSubmenuTitle;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    
    public DrawerFragment() {
        this.drawerLoading = false;
    }
    
    private void animateSubListHide() {
        final Animation loadAnimation = AnimationUtils.loadAnimation((Context)this.getActivity(), 2131034132);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            final /* synthetic */ Animation val$slideAwayAppear = AnimationUtils.loadAnimation((Context)this.getActivity(), 2131034131);
            
            public void onAnimationEnd(final Animation animation) {
                DrawerFragment.this.drawerSubmenuLayout.setVisibility(8);
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
                DrawerFragment.this.drawerRecycler.setVisibility(0);
                DrawerFragment.this.drawerRecycler.startAnimation(this.val$slideAwayAppear);
            }
        });
        this.drawerSubmenuLayout.startAnimation(loadAnimation);
    }
    
    private void animateSubListShow(final DrawerItemCategory drawerItemCategory) {
        if (drawerItemCategory != null) {
            this.drawerSubmenuTitle.setText((CharSequence)drawerItemCategory.getName());
            this.drawerSubmenuRecyclerAdapter.changeDrawerItems(drawerItemCategory.getChildren());
            final Animation loadAnimation = AnimationUtils.loadAnimation((Context)this.getActivity(), 2131034134);
            loadAnimation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
                final /* synthetic */ Animation val$slideInAppear = AnimationUtils.loadAnimation((Context)this.getActivity(), 2131034133);
                
                public void onAnimationEnd(final Animation animation) {
                    DrawerFragment.this.drawerRecycler.setVisibility(8);
                }
                
                public void onAnimationRepeat(final Animation animation) {
                }
                
                public void onAnimationStart(final Animation animation) {
                    DrawerFragment.this.drawerSubmenuLayout.setVisibility(0);
                    DrawerFragment.this.drawerSubmenuLayout.startAnimation(this.val$slideInAppear);
                }
            });
            this.drawerRecycler.startAnimation(loadAnimation);
            return;
        }
        Timber.e("Populate submenu with null category drawer item.", new Object[0]);
    }
    
    private void getDrawerItems() {
        this.drawerLoading = true;
        this.drawerProgress.setVisibility(0);
        this.drawerRetryBtn.setVisibility(8);
        final GsonRequest<Object> gsonRequest = new GsonRequest<Object>(0, String.format(EndPoints.NAVIGATION_DRAWER, SettingsMy.getActualNonNullShop(this.getActivity()).getId()), null, (Class<Object>)DrawerResponse.class, (Response.Listener<Object>)new Response.Listener<DrawerResponse>() {
            public void onResponse(@NonNull final DrawerResponse drawerResponse) {
                DrawerFragment.this.drawerRecyclerAdapter.addDrawerItem(new DrawerItemCategory(-123L, -123L, DrawerFragment.this.getString(2131230835)));
                DrawerFragment.this.drawerRecyclerAdapter.addDrawerItemList(drawerResponse.getNavigation());
                DrawerFragment.this.drawerRecyclerAdapter.addPageItemList(drawerResponse.getPages());
                ((RecyclerView.Adapter)DrawerFragment.this.drawerRecyclerAdapter).notifyDataSetChanged();
                if (DrawerFragment.this.drawerListener != null) {
                    DrawerFragment.this.drawerListener.prepareSearchSuggestions(drawerResponse.getNavigation());
                }
                DrawerFragment.this.drawerLoading = false;
                if (DrawerFragment.this.drawerRecycler != null) {
                    DrawerFragment.this.drawerRecycler.setVisibility(0);
                }
                if (DrawerFragment.this.drawerProgress != null) {
                    DrawerFragment.this.drawerProgress.setVisibility(8);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                MsgUtils.logAndShowErrorMessage(DrawerFragment.this.getActivity(), volleyError);
                DrawerFragment.this.drawerLoading = false;
                if (DrawerFragment.this.drawerProgress != null) {
                    DrawerFragment.this.drawerProgress.setVisibility(8);
                }
                if (DrawerFragment.this.drawerRetryBtn != null) {
                    DrawerFragment.this.drawerRetryBtn.setVisibility(0);
                }
            }
        });
        gsonRequest.setRetryPolicy(MyApplication.getDefaultRetryPolice());
        gsonRequest.setShouldCache(false);
        MyApplication.getInstance().addToRequestQueue(gsonRequest, "drawer_requests");
    }
    
    private void prepareDrawerRecycler(final View view) {
        this.drawerRecycler = (RecyclerView)view.findViewById(2131624221);
        this.drawerRecyclerAdapter = new DrawerRecyclerAdapter(this.getContext(), new DrawerRecyclerInterface() {
            @Override
            public void onCategorySelected(final View view, final DrawerItemCategory drawerItemCategory) {
                if (drawerItemCategory.getChildren() != null && !drawerItemCategory.getChildren().isEmpty()) {
                    DrawerFragment.this.animateSubListShow(drawerItemCategory);
                    return;
                }
                if (DrawerFragment.this.drawerListener != null) {
                    if (drawerItemCategory.getId() == -123L) {
                        DrawerFragment.this.drawerListener.onDrawerBannersSelected();
                    }
                    else {
                        DrawerFragment.this.drawerListener.onDrawerItemCategorySelected(drawerItemCategory);
                    }
                    DrawerFragment.this.closeDrawerMenu();
                    return;
                }
                Timber.e(new RuntimeException(), "Null drawer listener. WTF.", new Object[0]);
            }
            
            @Override
            public void onHeaderSelected() {
                if (DrawerFragment.this.drawerListener != null) {
                    DrawerFragment.this.drawerListener.onAccountSelected();
                    DrawerFragment.this.closeDrawerMenu();
                    return;
                }
                Timber.e(new RuntimeException(), "Null drawer listener. WTF.", new Object[0]);
            }
            
            @Override
            public void onPageSelected(final View view, final DrawerItemPage drawerItemPage) {
                if (DrawerFragment.this.drawerListener != null) {
                    DrawerFragment.this.drawerListener.onDrawerItemPageSelected(drawerItemPage);
                    DrawerFragment.this.closeDrawerMenu();
                    return;
                }
                Timber.e(new RuntimeException(), "Null drawer listener. WTF.", new Object[0]);
            }
        });
        this.drawerRecycler.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(this.getContext()));
        this.drawerRecycler.setHasFixedSize(true);
        this.drawerRecycler.setAdapter((RecyclerView.Adapter)this.drawerRecyclerAdapter);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(2131624225);
        this.drawerSubmenuRecyclerAdapter = new DrawerSubmenuRecyclerAdapter(new DrawerSubmenuRecyclerInterface() {
            @Override
            public void onSubCategorySelected(final View view, final DrawerItemCategory drawerItemCategory) {
                if (DrawerFragment.this.drawerListener != null) {
                    DrawerFragment.this.drawerListener.onDrawerItemCategorySelected(drawerItemCategory);
                    DrawerFragment.this.closeDrawerMenu();
                }
            }
        });
        recyclerView.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator((RecyclerView.ItemAnimator)new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter((RecyclerView.Adapter)this.drawerSubmenuRecyclerAdapter);
    }
    
    public void closeDrawerMenu() {
        if (this.mDrawerLayout != null) {
            this.mDrawerLayout.closeDrawer(8388611);
        }
    }
    
    public void invalidateHeader() {
        if (this.drawerRecyclerAdapter != null) {
            Timber.d("Invalidate drawer menu header.", new Object[0]);
            ((RecyclerView.Adapter)this.drawerRecyclerAdapter).notifyItemChanged(0);
        }
    }
    
    public boolean onBackHide() {
        if (this.mDrawerLayout != null && this.mDrawerLayout.isDrawerVisible(8388611)) {
            if (this.drawerSubmenuLayout.getVisibility() == 0) {
                this.animateSubListHide();
            }
            else {
                this.mDrawerLayout.closeDrawer(8388611);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130968633, viewGroup, false);
        this.drawerSubmenuLayout = (LinearLayout)inflate.findViewById(2131624222);
        this.drawerSubmenuTitle = (TextView)inflate.findViewById(2131624224);
        this.drawerProgress = (ProgressBar)inflate.findViewById(2131624226);
        (this.drawerRetryBtn = (Button)inflate.findViewById(2131624227)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (!DrawerFragment.this.drawerLoading) {
                    DrawerFragment.this.getDrawerItems();
                }
            }
        });
        this.prepareDrawerRecycler(inflate);
        ((Button)inflate.findViewById(2131624223)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            private long mLastClickTime = 0L;
            
            public void onClick(final View view) {
                if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000L) {
                    return;
                }
                this.mLastClickTime = SystemClock.elapsedRealtime();
                DrawerFragment.this.animateSubListHide();
            }
        });
        this.getDrawerItems();
        return inflate;
    }
    
    @Override
    public void onDestroy() {
        this.mDrawerLayout.removeDrawerListener((DrawerLayout.DrawerListener)this.mDrawerToggle);
        super.onDestroy();
    }
    
    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests("drawer_requests");
        if (this.drawerLoading) {
            if (this.drawerProgress != null) {
                this.drawerProgress.setVisibility(8);
            }
            if (this.drawerRetryBtn != null) {
                this.drawerRetryBtn.setVisibility(0);
            }
            this.drawerLoading = false;
        }
        super.onStop();
    }
    
    public void setUp(final DrawerLayout mDrawerLayout, final Toolbar toolbar, final FragmentDrawerListener drawerListener) {
        this.mDrawerLayout = mDrawerLayout;
        this.drawerListener = drawerListener;
        this.mDrawerToggle = new ActionBarDrawerToggle(this.getActivity(), mDrawerLayout, toolbar, 2131230931, 2131230927) {
            @Override
            public void onDrawerClosed(final View view) {
                super.onDrawerClosed(view);
                DrawerFragment.this.getActivity().invalidateOptionsMenu();
            }
            
            @Override
            public void onDrawerOpened(final View view) {
                super.onDrawerOpened(view);
                DrawerFragment.this.getActivity().invalidateOptionsMenu();
            }
            
            @Override
            public void onDrawerSlide(final View view, final float n) {
                super.onDrawerSlide(view, n);
            }
        };
        toolbar.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DrawerFragment.this.toggleDrawerMenu();
            }
        });
        this.mDrawerLayout.addDrawerListener((DrawerLayout.DrawerListener)this.mDrawerToggle);
        this.mDrawerLayout.post((Runnable)new Runnable() {
            @Override
            public void run() {
                DrawerFragment.this.mDrawerToggle.syncState();
            }
        });
    }
    
    public void toggleDrawerMenu() {
        if (this.mDrawerLayout != null) {
            if (!this.mDrawerLayout.isDrawerVisible(8388611)) {
                this.mDrawerLayout.openDrawer(8388611);
                return;
            }
            this.mDrawerLayout.closeDrawer(8388611);
        }
    }
    
    public interface FragmentDrawerListener
    {
        void onAccountSelected();
        
        void onDrawerBannersSelected();
        
        void onDrawerItemCategorySelected(final DrawerItemCategory p0);
        
        void onDrawerItemPageSelected(final DrawerItemPage p0);
        
        void prepareSearchSuggestions(final List<DrawerItemCategory> p0);
    }
}
