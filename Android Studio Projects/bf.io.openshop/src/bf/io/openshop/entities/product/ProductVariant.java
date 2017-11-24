package bf.io.openshop.entities.product;

import java.util.*;

public class ProductVariant
{
    private String code;
    private ProductColor color;
    private long id;
    private String[] images;
    private ProductSize size;
    
    public ProductVariant() {
    }
    
    public ProductVariant(final long id, final ProductSize size) {
        this.id = id;
        this.size = size;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2;
        if (this == o) {
            b2 = b;
        }
        else {
            final boolean b3 = o instanceof ProductVariant;
            b2 = false;
            if (b3) {
                final ProductVariant productVariant = (ProductVariant)o;
                final long n = lcmp(this.getId(), productVariant.getId());
                b2 = false;
                if (n == 0) {
                    if (this.getColor() != null) {
                        final boolean equals = this.getColor().equals(productVariant.getColor());
                        b2 = false;
                        if (!equals) {
                            return b2;
                        }
                    }
                    else if (productVariant.getColor() != null) {
                        return false;
                    }
                    if (this.getSize() != null) {
                        final boolean equals2 = this.getSize().equals(productVariant.getSize());
                        b2 = false;
                        if (!equals2) {
                            return b2;
                        }
                    }
                    else if (productVariant.getSize() != null) {
                        return false;
                    }
                    final boolean equals3 = Arrays.equals(this.getImages(), productVariant.getImages());
                    b2 = false;
                    if (equals3) {
                        if (this.getCode() != null) {
                            if (this.getCode().equals(productVariant.getCode())) {
                                return b;
                            }
                        }
                        else if (productVariant.getCode() == null) {
                            return b;
                        }
                        b = false;
                        return b;
                    }
                }
            }
        }
        return b2;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public ProductColor getColor() {
        return this.color;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String[] getImages() {
        return this.images;
    }
    
    public ProductSize getSize() {
        return this.size;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.getId() ^ this.getId() >>> 32);
        int hashCode;
        if (this.getColor() != null) {
            hashCode = this.getColor().hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.getSize() != null) {
            hashCode2 = this.getSize().hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.getImages() != null) {
            hashCode3 = Arrays.hashCode(this.getImages());
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (n3 + hashCode3);
        final String code = this.getCode();
        int hashCode4 = 0;
        if (code != null) {
            hashCode4 = this.getCode().hashCode();
        }
        return n4 + hashCode4;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setColor(final ProductColor color) {
        this.color = color;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setImages(final String[] images) {
        this.images = images;
    }
    
    public void setSize(final ProductSize size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        return "ProductVariant{id=" + this.id + ", color=" + this.color + ", size=" + this.size + ", images=" + Arrays.toString(this.images) + ", code='" + this.code + '\'' + '}';
    }
}
