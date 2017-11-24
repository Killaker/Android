package bf.io.openshop.entities.cart;

import java.util.*;
import com.google.gson.annotations.*;

public class Cart
{
    private String currency;
    private List<CartDiscountItem> discounts;
    private long id;
    private List<CartProductItem> items;
    @SerializedName("product_count")
    private int productCount;
    @SerializedName("total_price")
    private double totalPrice;
    @SerializedName("total_price_formatted")
    private String totalPriceFormatted;
    
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
                final Class<? extends Cart> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Cart cart = (Cart)o;
                    final long n = lcmp(this.id, cart.id);
                    b2 = false;
                    if (n == 0) {
                        final int productCount = this.productCount;
                        final int productCount2 = cart.productCount;
                        b2 = false;
                        if (productCount == productCount2) {
                            final int compare = Double.compare(cart.totalPrice, this.totalPrice);
                            b2 = false;
                            if (compare == 0) {
                                if (this.totalPriceFormatted != null) {
                                    final boolean equals = this.totalPriceFormatted.equals(cart.totalPriceFormatted);
                                    b2 = false;
                                    if (!equals) {
                                        return b2;
                                    }
                                }
                                else if (cart.totalPriceFormatted != null) {
                                    return false;
                                }
                                if (this.currency != null) {
                                    final boolean equals2 = this.currency.equals(cart.currency);
                                    b2 = false;
                                    if (!equals2) {
                                        return b2;
                                    }
                                }
                                else if (cart.currency != null) {
                                    return false;
                                }
                                if (this.items != null) {
                                    final boolean equals3 = this.items.equals(cart.items);
                                    b2 = false;
                                    if (!equals3) {
                                        return b2;
                                    }
                                }
                                else if (cart.items != null) {
                                    return false;
                                }
                                if (this.discounts != null) {
                                    if (this.discounts.equals(cart.discounts)) {
                                        return b;
                                    }
                                }
                                else if (cart.discounts == null) {
                                    return b;
                                }
                                b = false;
                                return b;
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public List<CartDiscountItem> getDiscounts() {
        return this.discounts;
    }
    
    public long getId() {
        return this.id;
    }
    
    public List<CartProductItem> getItems() {
        return this.items;
    }
    
    public int getProductCount() {
        return this.productCount;
    }
    
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    public String getTotalPriceFormatted() {
        return this.totalPriceFormatted;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32) + this.productCount;
        final long doubleToLongBits = Double.doubleToLongBits(this.totalPrice);
        final int n2 = 31 * (n * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32));
        int hashCode;
        if (this.totalPriceFormatted != null) {
            hashCode = this.totalPriceFormatted.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n3 = 31 * (n2 + hashCode);
        int hashCode2;
        if (this.currency != null) {
            hashCode2 = this.currency.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n4 = 31 * (n3 + hashCode2);
        int hashCode3;
        if (this.items != null) {
            hashCode3 = this.items.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n5 = 31 * (n4 + hashCode3);
        final List<CartDiscountItem> discounts = this.discounts;
        int hashCode4 = 0;
        if (discounts != null) {
            hashCode4 = this.discounts.hashCode();
        }
        return n5 + hashCode4;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public void setDiscounts(final List<CartDiscountItem> discounts) {
        this.discounts = discounts;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setItems(final List<CartProductItem> items) {
        this.items = items;
    }
    
    public void setProductCount(final int productCount) {
        this.productCount = productCount;
    }
    
    public void setTotalPrice(final double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setTotalPriceFormatted(final String totalPriceFormatted) {
        this.totalPriceFormatted = totalPriceFormatted;
    }
    
    @Override
    public String toString() {
        return "Cart{id=" + this.id + ", productCount=" + this.productCount + ", totalPrice=" + this.totalPrice + ", totalPriceFormatted='" + this.totalPriceFormatted + '\'' + ", currency='" + this.currency + '\'' + ", items=" + this.items + ", discounts=" + this.discounts + '}';
    }
}
