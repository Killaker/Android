package bf.io.openshop.entities.order;

import bf.io.openshop.entities.product.*;

public class ProductInOrder
{
    private long id;
    private Product product;
    private int quantity;
    
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
                final Class<? extends ProductInOrder> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final ProductInOrder productInOrder = (ProductInOrder)o;
                    final long n = lcmp(this.id, productInOrder.id);
                    b2 = false;
                    if (n == 0) {
                        final int quantity = this.quantity;
                        final int quantity2 = productInOrder.quantity;
                        b2 = false;
                        if (quantity == quantity2) {
                            if (this.product != null) {
                                if (this.product.equals(productInOrder.product)) {
                                    return b;
                                }
                            }
                            else if (productInOrder.product == null) {
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
    
    public Product getProduct() {
        return this.product;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + this.quantity);
        int hashCode;
        if (this.product != null) {
            hashCode = this.product.hashCode();
        }
        else {
            hashCode = 0;
        }
        return n + hashCode;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setProduct(final Product product) {
        this.product = product;
    }
    
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "ProductInOrder{id=" + this.id + ", quantity=" + this.quantity + ", product=" + this.product + '}';
    }
}
