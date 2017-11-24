package bf.io.openshop.entities.wishlist;

import java.util.*;
import com.google.gson.annotations.*;

public class WishlistResponse
{
    private long id;
    private List<WishlistItem> items;
    @SerializedName("product_count")
    private int productCount;
    
    public WishlistResponse() {
    }
    
    public WishlistResponse(final long id, final int productCount, final List<WishlistItem> items) {
        this.id = id;
        this.productCount = productCount;
        this.items = items;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2;
        if (this == o) {
            b2 = b;
        }
        else {
            b2 = false;
            if (o != null) {
                final Class<? extends WishlistResponse> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final WishlistResponse wishlistResponse = (WishlistResponse)o;
                    final long n = lcmp(this.id, wishlistResponse.id);
                    b2 = false;
                    if (n == 0) {
                        final int productCount = this.productCount;
                        final int productCount2 = wishlistResponse.productCount;
                        b2 = false;
                        if (productCount == productCount2) {
                            if (this.items != null) {
                                if (this.items.equals(wishlistResponse.items)) {
                                    return b;
                                }
                            }
                            else if (wishlistResponse.items == null) {
                                return b;
                            }
                            b = false;
                            return b;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public long getId() {
        return this.id;
    }
    
    public List<WishlistItem> getItems() {
        return this.items;
    }
    
    public int getProductCount() {
        return this.productCount;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + this.productCount);
        int hashCode;
        if (this.items != null) {
            hashCode = this.items.hashCode();
        }
        else {
            hashCode = 0;
        }
        return n + hashCode;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setItems(final List<WishlistItem> items) {
        this.items = items;
    }
    
    public void setProductCount(final int productCount) {
        this.productCount = productCount;
    }
    
    @Override
    public String toString() {
        return "WishlistResponse{id=" + this.id + ", productCount=" + this.productCount + ", items=" + this.items + '}';
    }
}
