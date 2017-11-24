package bf.io.openshop.entities.cart;

import com.google.gson.annotations.*;

public class CartInfo
{
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
                final Class<? extends CartInfo> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final CartInfo cartInfo = (CartInfo)o;
                    final int productCount = this.productCount;
                    final int productCount2 = cartInfo.productCount;
                    b2 = false;
                    if (productCount == productCount2) {
                        final int compare = Double.compare(cartInfo.totalPrice, this.totalPrice);
                        b2 = false;
                        if (compare == 0) {
                            if (this.totalPriceFormatted != null) {
                                if (this.totalPriceFormatted.equals(cartInfo.totalPriceFormatted)) {
                                    return b;
                                }
                            }
                            else if (cartInfo.totalPriceFormatted == null) {
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
        final int productCount = this.productCount;
        final long doubleToLongBits = Double.doubleToLongBits(this.totalPrice);
        final int n = 31 * (productCount * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32));
        int hashCode;
        if (this.totalPriceFormatted != null) {
            hashCode = this.totalPriceFormatted.hashCode();
        }
        else {
            hashCode = 0;
        }
        return n + hashCode;
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
        return "CartInfo{productCount=" + this.productCount + ", totalPrice=" + this.totalPrice + ", totalPriceFormatted='" + this.totalPriceFormatted + '\'' + '}';
    }
}
