package bf.io.openshop.entities.delivery;

import java.io.*;
import java.util.*;
import com.google.gson.annotations.*;

public class DeliveryType implements Serializable
{
    private long id;
    private String name;
    @SerializedName("shipping_list")
    private List<Shipping> shippingList;
    
    public DeliveryType(final long id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Shipping> getShippingList() {
        return this.shippingList;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setShippingList(final List<Shipping> shippingList) {
        this.shippingList = shippingList;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
