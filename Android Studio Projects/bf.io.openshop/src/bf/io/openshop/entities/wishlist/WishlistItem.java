package bf.io.openshop.entities.wishlist;

public class WishlistItem
{
    private long id;
    private WishlistProductVariant variant;
    
    public WishlistItem() {
    }
    
    public WishlistItem(final long id, final WishlistProductVariant variant) {
        this.id = id;
        this.variant = variant;
    }
    
    public long getId() {
        return this.id;
    }
    
    public WishlistProductVariant getVariant() {
        return this.variant;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setVariant(final WishlistProductVariant variant) {
        this.variant = variant;
    }
    
    @Override
    public String toString() {
        return "WishlistItem{id=" + this.id + ", variant=" + this.variant + '}';
    }
}
