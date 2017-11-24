package bf.io.openshop.entities.cart;

public class CartDiscountItem
{
    private Discount discount;
    private long id;
    private int quantity;
    
    public Discount getDiscount() {
        return this.discount;
    }
    
    public long getId() {
        return this.id;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setDiscount(final Discount discount) {
        this.discount = discount;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "CartDiscountItem{id=" + this.id + ", quantity=" + this.quantity + ", discount=" + this.discount + '}';
    }
}
