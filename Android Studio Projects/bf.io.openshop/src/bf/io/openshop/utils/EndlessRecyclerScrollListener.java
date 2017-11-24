package bf.io.openshop.utils;

import android.support.v7.widget.*;

public abstract class EndlessRecyclerScrollListener extends OnScrollListener
{
    private static final long VISIBLE_THRESHOLD = 6L;
    private int current_page;
    private int firstVisibleItem;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading;
    private int previousTotal;
    private int totalItemCount;
    private int visibleItemCount;
    
    public EndlessRecyclerScrollListener(final GridLayoutManager gridLayoutManager) {
        this.previousTotal = 0;
        this.loading = true;
        this.current_page = 1;
        this.gridLayoutManager = gridLayoutManager;
    }
    
    public EndlessRecyclerScrollListener(final LinearLayoutManager linearLayoutManager) {
        this.previousTotal = 0;
        this.loading = true;
        this.current_page = 1;
        this.linearLayoutManager = linearLayoutManager;
    }
    
    public void clean() {
        this.previousTotal = 0;
        this.loading = true;
        this.firstVisibleItem = 0;
        this.visibleItemCount = 0;
        this.totalItemCount = 0;
        this.current_page = 1;
    }
    
    public abstract void onLoadMore(final int p0);
    
    @Override
    public void onScrolled(final RecyclerView recyclerView, final int n, final int n2) {
        super.onScrolled(recyclerView, n, n2);
        this.visibleItemCount = recyclerView.getChildCount();
        if (this.gridLayoutManager != null) {
            this.totalItemCount = ((RecyclerView.LayoutManager)this.gridLayoutManager).getItemCount();
            this.firstVisibleItem = this.gridLayoutManager.findFirstVisibleItemPosition();
        }
        else {
            this.totalItemCount = ((RecyclerView.LayoutManager)this.linearLayoutManager).getItemCount();
            this.firstVisibleItem = this.linearLayoutManager.findFirstVisibleItemPosition();
        }
        if (this.loading && this.totalItemCount != this.previousTotal) {
            this.loading = false;
            this.previousTotal = this.totalItemCount;
        }
        if (!this.loading && this.totalItemCount - this.visibleItemCount <= 6L + this.firstVisibleItem) {
            this.onLoadMore(++this.current_page);
            this.loading = true;
        }
    }
    
    public void resetLoading() {
        this.loading = false;
    }
}
