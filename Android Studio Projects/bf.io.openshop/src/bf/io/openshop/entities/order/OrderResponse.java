package bf.io.openshop.entities.order;

import bf.io.openshop.entities.*;
import java.util.*;
import com.google.gson.annotations.*;

public class OrderResponse
{
    private Metadata metadata;
    @SerializedName("records")
    private List<Order> orders;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final OrderResponse orderResponse = (OrderResponse)o;
            Label_0059: {
                if (this.metadata != null) {
                    if (this.metadata.equals(orderResponse.metadata)) {
                        break Label_0059;
                    }
                }
                else if (orderResponse.metadata == null) {
                    break Label_0059;
                }
                return false;
            }
            if (this.orders != null) {
                if (this.orders.equals(orderResponse.orders)) {
                    return true;
                }
            }
            else if (orderResponse.orders == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public Metadata getMetadata() {
        return this.metadata;
    }
    
    public List<Order> getOrders() {
        return this.orders;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.metadata != null) {
            hashCode = this.metadata.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final List<Order> orders = this.orders;
        int hashCode2 = 0;
        if (orders != null) {
            hashCode2 = this.orders.hashCode();
        }
        return n + hashCode2;
    }
    
    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }
    
    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }
    
    @Override
    public String toString() {
        return "OrderResponse{metadata=" + this.metadata + ", orders=" + this.orders + '}';
    }
}
