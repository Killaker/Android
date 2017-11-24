package bf.io.openshop.entities.product;

public class ProductQuantity
{
    private int quantity;
    private String value;
    
    public ProductQuantity() {
    }
    
    public ProductQuantity(final int quantity, final String value) {
        this.quantity = quantity;
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ProductQuantity productQuantity = (ProductQuantity)o;
            if (this.quantity != productQuantity.quantity) {
                return false;
            }
            if (this.value != null) {
                if (this.value.equals(productQuantity.value)) {
                    return true;
                }
            }
            else if (productQuantity.value == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public String getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * this.quantity;
        int hashCode;
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        else {
            hashCode = 0;
        }
        return n + hashCode;
    }
    
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
