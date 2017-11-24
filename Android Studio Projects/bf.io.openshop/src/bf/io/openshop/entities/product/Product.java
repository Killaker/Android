package bf.io.openshop.entities.product;

import com.google.gson.annotations.*;
import java.util.*;

public class Product
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
    private ArrayList<Product> related;
    @SerializedName("remote_id")
    private long remoteId;
    private String url;
    private List<ProductVariant> variants;
    
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
                final Class<? extends Product> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Product product = (Product)o;
                    final long n = lcmp(this.id, product.id);
                    b2 = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.remoteId, product.remoteId);
                        b2 = false;
                        if (n2 == 0) {
                            final int compare = Double.compare(product.price, this.price);
                            b2 = false;
                            if (compare == 0) {
                                final int compare2 = Double.compare(product.discountPrice, this.discountPrice);
                                b2 = false;
                                if (compare2 == 0) {
                                    final long n3 = lcmp(this.category, product.category);
                                    b2 = false;
                                    if (n3 == 0) {
                                        if (this.url != null) {
                                            final boolean equals = this.url.equals(product.url);
                                            b2 = false;
                                            if (!equals) {
                                                return b2;
                                            }
                                        }
                                        else if (product.url != null) {
                                            return false;
                                        }
                                        if (this.name != null) {
                                            final boolean equals2 = this.name.equals(product.name);
                                            b2 = false;
                                            if (!equals2) {
                                                return b2;
                                            }
                                        }
                                        else if (product.name != null) {
                                            return false;
                                        }
                                        if (this.priceFormatted != null) {
                                            final boolean equals3 = this.priceFormatted.equals(product.priceFormatted);
                                            b2 = false;
                                            if (!equals3) {
                                                return b2;
                                            }
                                        }
                                        else if (product.priceFormatted != null) {
                                            return false;
                                        }
                                        if (this.discountPriceFormatted != null) {
                                            final boolean equals4 = this.discountPriceFormatted.equals(product.discountPriceFormatted);
                                            b2 = false;
                                            if (!equals4) {
                                                return b2;
                                            }
                                        }
                                        else if (product.discountPriceFormatted != null) {
                                            return false;
                                        }
                                        if (this.currency != null) {
                                            final boolean equals5 = this.currency.equals(product.currency);
                                            b2 = false;
                                            if (!equals5) {
                                                return b2;
                                            }
                                        }
                                        else if (product.currency != null) {
                                            return false;
                                        }
                                        if (this.code != null) {
                                            final boolean equals6 = this.code.equals(product.code);
                                            b2 = false;
                                            if (!equals6) {
                                                return b2;
                                            }
                                        }
                                        else if (product.code != null) {
                                            return false;
                                        }
                                        if (this.description != null) {
                                            final boolean equals7 = this.description.equals(product.description);
                                            b2 = false;
                                            if (!equals7) {
                                                return b2;
                                            }
                                        }
                                        else if (product.description != null) {
                                            return false;
                                        }
                                        if (this.mainImage != null) {
                                            final boolean equals8 = this.mainImage.equals(product.mainImage);
                                            b2 = false;
                                            if (!equals8) {
                                                return b2;
                                            }
                                        }
                                        else if (product.mainImage != null) {
                                            return false;
                                        }
                                        if (this.mainImageHighRes != null) {
                                            final boolean equals9 = this.mainImageHighRes.equals(product.mainImageHighRes);
                                            b2 = false;
                                            if (!equals9) {
                                                return b2;
                                            }
                                        }
                                        else if (product.mainImageHighRes != null) {
                                            return false;
                                        }
                                        if (this.variants != null) {
                                            final boolean equals10 = this.variants.equals(product.variants);
                                            b2 = false;
                                            if (!equals10) {
                                                return b2;
                                            }
                                        }
                                        else if (product.variants != null) {
                                            return false;
                                        }
                                        if (this.related != null) {
                                            if (this.related.equals(product.related)) {
                                                return b;
                                            }
                                        }
                                        else if (product.related == null) {
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
    
    public ArrayList<Product> getRelated() {
        return this.related;
    }
    
    public long getRemoteId() {
        return this.remoteId;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public List<ProductVariant> getVariants() {
        return this.variants;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + (int)(this.remoteId ^ this.remoteId >>> 32));
        int hashCode;
        if (this.url != null) {
            hashCode = this.url.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.name != null) {
            hashCode2 = this.name.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = n2 + hashCode2;
        final long doubleToLongBits = Double.doubleToLongBits(this.price);
        final int n4 = 31 * (n3 * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32));
        int hashCode3;
        if (this.priceFormatted != null) {
            hashCode3 = this.priceFormatted.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n5 = n4 + hashCode3;
        final long doubleToLongBits2 = Double.doubleToLongBits(this.discountPrice);
        final int n6 = 31 * (n5 * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32));
        int hashCode4;
        if (this.discountPriceFormatted != null) {
            hashCode4 = this.discountPriceFormatted.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n7 = 31 * (31 * (n6 + hashCode4) + (int)(this.category ^ this.category >>> 32));
        int hashCode5;
        if (this.currency != null) {
            hashCode5 = this.currency.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n8 = 31 * (n7 + hashCode5);
        int hashCode6;
        if (this.code != null) {
            hashCode6 = this.code.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        final int n9 = 31 * (n8 + hashCode6);
        int hashCode7;
        if (this.description != null) {
            hashCode7 = this.description.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        final int n10 = 31 * (n9 + hashCode7);
        int hashCode8;
        if (this.mainImage != null) {
            hashCode8 = this.mainImage.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        final int n11 = 31 * (n10 + hashCode8);
        int hashCode9;
        if (this.mainImageHighRes != null) {
            hashCode9 = this.mainImageHighRes.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        final int n12 = 31 * (n11 + hashCode9);
        int hashCode10;
        if (this.variants != null) {
            hashCode10 = this.variants.hashCode();
        }
        else {
            hashCode10 = 0;
        }
        final int n13 = 31 * (n12 + hashCode10);
        final ArrayList<Product> related = this.related;
        int hashCode11 = 0;
        if (related != null) {
            hashCode11 = this.related.hashCode();
        }
        return n13 + hashCode11;
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
    
    public void setRelated(final ArrayList<Product> related) {
        this.related = related;
    }
    
    public void setRemoteId(final long remoteId) {
        this.remoteId = remoteId;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public void setVariants(final List<ProductVariant> variants) {
        this.variants = variants;
    }
    
    @Override
    public String toString() {
        return "Product{id=" + this.id + ", remoteId=" + this.remoteId + ", url='" + this.url + '\'' + ", name='" + this.name + '\'' + ", price=" + this.price + ", priceFormatted='" + this.priceFormatted + '\'' + ", discountPrice=" + this.discountPrice + ", discountPriceFormatted='" + this.discountPriceFormatted + '\'' + ", category=" + this.category + ", currency='" + this.currency + '\'' + ", code='" + this.code + '\'' + ", description='" + this.description + '\'' + ", mainImage='" + this.mainImage + '\'' + ", mainImageHighRes='" + this.mainImageHighRes + '\'' + ", variants=" + this.variants + ", related=" + this.related + '}';
    }
}
