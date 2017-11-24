package bf.io.openshop.entities.product;

import com.google.gson.annotations.*;

public class ProductColor
{
    private String code;
    private long id;
    private String img;
    @SerializedName("remote_id")
    private long remoteId;
    private String value;
    
    public ProductColor() {
        this.id = 0L;
    }
    
    public ProductColor(final long id, final String value) {
        this.id = 0L;
        this.id = id;
        this.value = value;
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
                final Class<? extends ProductColor> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final ProductColor productColor = (ProductColor)o;
                    final long n = lcmp(this.id, productColor.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.value != null) {
                            final boolean equals = this.value.equals(productColor.value);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (productColor.value != null) {
                            return false;
                        }
                        if (this.code != null) {
                            final boolean equals2 = this.code.equals(productColor.code);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (productColor.code != null) {
                            return false;
                        }
                        if (this.img != null) {
                            if (this.img.equals(productColor.img)) {
                                return b;
                            }
                        }
                        else if (productColor.img == null) {
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
    
    public boolean equalsColors(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ProductColor productColor = (ProductColor)o;
            if (this.id != productColor.id) {
                return false;
            }
            if (this.value != null) {
                if (this.value.equals(productColor.value)) {
                    return true;
                }
            }
            else if (productColor.value == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getImg() {
        return this.img;
    }
    
    public long getRemoteId() {
        return this.remoteId;
    }
    
    public String getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + (int)(this.remoteId ^ this.remoteId >>> 32));
        int hashCode;
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.code != null) {
            hashCode2 = this.code.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        final String img = this.img;
        int hashCode3 = 0;
        if (img != null) {
            hashCode3 = this.img.hashCode();
        }
        return n3 + hashCode3;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setImg(final String img) {
        this.img = img;
    }
    
    public void setRemoteId(final long remoteId) {
        this.remoteId = remoteId;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "ProductColor{id=" + this.id + ", remoteId=" + this.remoteId + ", value='" + this.value + '\'' + ", code='" + this.code + '\'' + ", img='" + this.img + '\'' + '}';
    }
}
