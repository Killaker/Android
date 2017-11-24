package bf.io.openshop.entities.wishlist;

import com.google.gson.annotations.*;

public class WishlistProductVariant
{
    private long category;
    private String code;
    private String currency;
    private String description;
    @SerializedName("discount_price")
    private double discountPrice;
    @SerializedName("discount_price_formatted")
    private String discountPriceFormatted;
    private long id;
    @SerializedName("main_image")
    private String mainImage;
    @SerializedName("main_image_high_res")
    private String mainImageHighRes;
    private String name;
    private double price;
    @SerializedName("price_formatted")
    private String priceFormatted;
    @SerializedName("product_id")
    private long productId;
    
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
                final Class<? extends WishlistProductVariant> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final WishlistProductVariant wishlistProductVariant = (WishlistProductVariant)o;
                    final long n = lcmp(this.id, wishlistProductVariant.id);
                    b2 = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.productId, wishlistProductVariant.productId);
                        b2 = false;
                        if (n2 == 0) {
                            final long n3 = lcmp(this.category, wishlistProductVariant.category);
                            b2 = false;
                            if (n3 == 0) {
                                final int compare = Double.compare(wishlistProductVariant.price, this.price);
                                b2 = false;
                                if (compare == 0) {
                                    final int compare2 = Double.compare(wishlistProductVariant.discountPrice, this.discountPrice);
                                    b2 = false;
                                    if (compare2 == 0) {
                                        if (this.name != null) {
                                            final boolean equals = this.name.equals(wishlistProductVariant.name);
                                            b2 = false;
                                            if (!equals) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.name != null) {
                                            return false;
                                        }
                                        if (this.priceFormatted != null) {
                                            final boolean equals2 = this.priceFormatted.equals(wishlistProductVariant.priceFormatted);
                                            b2 = false;
                                            if (!equals2) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.priceFormatted != null) {
                                            return false;
                                        }
                                        if (this.discountPriceFormatted != null) {
                                            final boolean equals3 = this.discountPriceFormatted.equals(wishlistProductVariant.discountPriceFormatted);
                                            b2 = false;
                                            if (!equals3) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.discountPriceFormatted != null) {
                                            return false;
                                        }
                                        if (this.currency != null) {
                                            final boolean equals4 = this.currency.equals(wishlistProductVariant.currency);
                                            b2 = false;
                                            if (!equals4) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.currency != null) {
                                            return false;
                                        }
                                        if (this.code != null) {
                                            final boolean equals5 = this.code.equals(wishlistProductVariant.code);
                                            b2 = false;
                                            if (!equals5) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.code != null) {
                                            return false;
                                        }
                                        if (this.description != null) {
                                            final boolean equals6 = this.description.equals(wishlistProductVariant.description);
                                            b2 = false;
                                            if (!equals6) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.description != null) {
                                            return false;
                                        }
                                        if (this.mainImage != null) {
                                            final boolean equals7 = this.mainImage.equals(wishlistProductVariant.mainImage);
                                            b2 = false;
                                            if (!equals7) {
                                                return b2;
                                            }
                                        }
                                        else if (wishlistProductVariant.mainImage != null) {
                                            return false;
                                        }
                                        if (this.mainImageHighRes != null) {
                                            if (this.mainImageHighRes.equals(wishlistProductVariant.mainImageHighRes)) {
                                                return b;
                                            }
                                        }
                                        else if (wishlistProductVariant.mainImageHighRes == null) {
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
    
    public long getCategory() {
        return this.category;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public double getDiscountPrice() {
        return this.discountPrice;
    }
    
    public String getDiscountPriceFormatted() {
        return this.discountPriceFormatted;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getMainImage() {
        return this.mainImage;
    }
    
    public String getMainImageHighRes() {
        return this.mainImageHighRes;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public String getPriceFormatted() {
        return this.priceFormatted;
    }
    
    public long getProductId() {
        return this.productId;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + (int)(this.productId ^ this.productId >>> 32));
        int hashCode;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode) + (int)(this.category ^ this.category >>> 32);
        final long doubleToLongBits = Double.doubleToLongBits(this.price);
        final int n3 = n2 * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.discountPrice);
        final int n4 = 31 * (n3 * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32));
        int hashCode2;
        if (this.priceFormatted != null) {
            hashCode2 = this.priceFormatted.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n5 = 31 * (n4 + hashCode2);
        int hashCode3;
        if (this.discountPriceFormatted != null) {
            hashCode3 = this.discountPriceFormatted.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n6 = 31 * (n5 + hashCode3);
        int hashCode4;
        if (this.currency != null) {
            hashCode4 = this.currency.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n7 = 31 * (n6 + hashCode4);
        int hashCode5;
        if (this.code != null) {
            hashCode5 = this.code.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n8 = 31 * (n7 + hashCode5);
        int hashCode6;
        if (this.description != null) {
            hashCode6 = this.description.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        final int n9 = 31 * (n8 + hashCode6);
        int hashCode7;
        if (this.mainImage != null) {
            hashCode7 = this.mainImage.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        final int n10 = 31 * (n9 + hashCode7);
        final String mainImageHighRes = this.mainImageHighRes;
        int hashCode8 = 0;
        if (mainImageHighRes != null) {
            hashCode8 = this.mainImageHighRes.hashCode();
        }
        return n10 + hashCode8;
    }
    
    public void setCategory(final long category) {
        this.category = category;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setDiscountPrice(final double discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    public void setDiscountPriceFormatted(final String discountPriceFormatted) {
        this.discountPriceFormatted = discountPriceFormatted;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setMainImage(final String mainImage) {
        this.mainImage = mainImage;
    }
    
    public void setMainImageHighRes(final String mainImageHighRes) {
        this.mainImageHighRes = mainImageHighRes;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setPrice(final double price) {
        this.price = price;
    }
    
    public void setPriceFormatted(final String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }
    
    public void setProductId(final long productId) {
        this.productId = productId;
    }
    
    @Override
    public String toString() {
        return "WishlistProductVariant{id=" + this.id + ", productId=" + this.productId + ", name='" + this.name + '\'' + ", category=" + this.category + ", price=" + this.price + ", discountPrice=" + this.discountPrice + ", priceFormatted='" + this.priceFormatted + '\'' + ", discountPriceFormatted='" + this.discountPriceFormatted + '\'' + ", currency='" + this.currency + '\'' + ", code='" + this.code + '\'' + ", description='" + this.description + '\'' + ", mainImage='" + this.mainImage + '\'' + ", mainImageHighRes='" + this.mainImageHighRes + '\'' + '}';
    }
}
