package bf.io.openshop.entities.product;

import bf.io.openshop.entities.*;
import java.util.*;
import com.google.gson.annotations.*;

public class ProductListResponse
{
    private Metadata metadata;
    @SerializedName("records")
    private List<Product> products;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ProductListResponse)) {
                return false;
            }
            final ProductListResponse productListResponse = (ProductListResponse)o;
            Label_0051: {
                if (this.getMetadata() != null) {
                    if (this.getMetadata().equals(productListResponse.getMetadata())) {
                        break Label_0051;
                    }
                }
                else if (productListResponse.getMetadata() == null) {
                    break Label_0051;
                }
                return false;
            }
            if (this.getProducts() != null) {
                if (this.getProducts().equals(productListResponse.getProducts())) {
                    return true;
                }
            }
            else if (productListResponse.getProducts() == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public Metadata getMetadata() {
        return this.metadata;
    }
    
    public List<Product> getProducts() {
        return this.products;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.getMetadata() != null) {
            hashCode = this.getMetadata().hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final List<Product> products = this.getProducts();
        int hashCode2 = 0;
        if (products != null) {
            hashCode2 = this.getProducts().hashCode();
        }
        return n + hashCode2;
    }
    
    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }
    
    public void setProducts(final List<Product> products) {
        this.products = products;
    }
    
    @Override
    public String toString() {
        return "ProductListResponse{metadata=" + this.metadata + ", products=" + this.products + '}';
    }
}
