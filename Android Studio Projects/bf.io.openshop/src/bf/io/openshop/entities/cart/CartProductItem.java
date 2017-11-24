package bf.io.openshop.entities.cart;

import com.google.gson.annotations.*;

public class CartProductItem
{
    private int expiration;
    private long id;
    private int quantity;
    @SerializedName("remote_id")
    private long remoteId;
    @SerializedName("total_price")
    private double totalItemPrice;
    @SerializedName("total_item_price_formatted")
    private String totalItemPriceFormatted;
    private CartProductItemVariant variant;
    
    public CartProductItem() {
        this.expiration = 0;
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
                final Class<? extends CartProductItem> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final CartProductItem cartProductItem = (CartProductItem)o;
                    final long n = lcmp(this.id, cartProductItem.id);
                    b2 = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.remoteId, cartProductItem.remoteId);
                        b2 = false;
                        if (n2 == 0) {
                            final int quantity = this.quantity;
                            final int quantity2 = cartProductItem.quantity;
                            b2 = false;
                            if (quantity == quantity2) {
                                final int compare = Double.compare(cartProductItem.totalItemPrice, this.totalItemPrice);
                                b2 = false;
                                if (compare == 0) {
                                    final int expiration = this.expiration;
                                    final int expiration2 = cartProductItem.expiration;
                                    b2 = false;
                                    if (expiration == expiration2) {
                                        if (this.totalItemPriceFormatted != null) {
                                            final boolean equals = this.totalItemPriceFormatted.equals(cartProductItem.totalItemPriceFormatted);
                                            b2 = false;
                                            if (!equals) {
                                                return b2;
                                            }
                                        }
                                        else if (cartProductItem.totalItemPriceFormatted != null) {
                                            return false;
                                        }
                                        if (this.variant != null) {
                                            if (this.variant.equals(cartProductItem.variant)) {
                                                return b;
                                            }
                                        }
                                        else if (cartProductItem.variant == null) {
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
            }
        }
        return b2;
    }
    
    public int getExpiration() {
        return this.expiration;
    }
    
    public long getId() {
        return this.id;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public long getRemoteId() {
        return this.remoteId;
    }
    
    public double getTotalItemPrice() {
        return this.totalItemPrice;
    }
    
    public String getTotalItemPriceFormatted() {
        return this.totalItemPriceFormatted;
    }
    
    public CartProductItemVariant getVariant() {
        return this.variant;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + (int)(this.remoteId ^ this.remoteId >>> 32)) + this.quantity;
        final long doubleToLongBits = Double.doubleToLongBits(this.totalItemPrice);
        final int n2 = 31 * (n * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32));
        int hashCode;
        if (this.totalItemPriceFormatted != null) {
            hashCode = this.totalItemPriceFormatted.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n3 = 31 * (n2 + hashCode);
        final CartProductItemVariant variant = this.variant;
        int hashCode2 = 0;
        if (variant != null) {
            hashCode2 = this.variant.hashCode();
        }
        return 31 * (n3 + hashCode2) + this.expiration;
    }
    
    public void setExpiration(final int expiration) {
        this.expiration = expiration;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
    
    public void setRemoteId(final long remoteId) {
        this.remoteId = remoteId;
    }
    
    public void setTotalItemPrice(final double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
    
    public void setTotalItemPriceFormatted(final String totalItemPriceFormatted) {
        this.totalItemPriceFormatted = totalItemPriceFormatted;
    }
    
    public void setVariant(final CartProductItemVariant variant) {
        this.variant = variant;
    }
    
    @Override
    public String toString() {
        return "CartProductItem{id=" + this.id + ", remoteId=" + this.remoteId + ", quantity=" + this.quantity + ", totalItemPrice=" + this.totalItemPrice + ", totalItemPriceFormatted='" + this.totalItemPriceFormatted + '\'' + ", variant=" + this.variant + ", expiration=" + this.expiration + '}';
    }
}
