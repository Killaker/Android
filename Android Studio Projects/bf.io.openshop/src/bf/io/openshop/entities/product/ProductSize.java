package bf.io.openshop.entities.product;

import com.google.gson.annotations.*;

public class ProductSize
{
    private long id;
    @SerializedName("remote_id")
    private long remoteId;
    private String value;
    
    public ProductSize() {
    }
    
    public ProductSize(final long id, final long remoteId, final String value) {
        this.id = id;
        this.remoteId = remoteId;
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
                final Class<? extends ProductSize> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final ProductSize productSize = (ProductSize)o;
                    final long n = lcmp(this.id, productSize.id);
                    b2 = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.remoteId, productSize.remoteId);
                        b2 = false;
                        if (n2 == 0) {
                            if (this.value != null) {
                                if (this.value.equals(productSize.value)) {
                                    return b;
                                }
                            }
                            else if (productSize.value == null) {
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
        return n + hashCode;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setRemoteId(final long remoteId) {
        this.remoteId = remoteId;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "ProductSize{id=" + this.id + ", remoteId=" + this.remoteId + ", value='" + this.value + '\'' + '}';
    }
}
