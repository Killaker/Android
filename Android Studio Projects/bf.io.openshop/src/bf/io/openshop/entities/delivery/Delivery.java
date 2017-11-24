package bf.io.openshop.entities.delivery;

import java.util.*;
import com.google.gson.annotations.*;

public class Delivery
{
    @SerializedName("personal_pickup")
    private List<Shipping> personalPickup;
    private List<Shipping> shipping;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final Delivery delivery = (Delivery)o;
            Label_0061: {
                if (this.personalPickup != null) {
                    if (this.personalPickup.equals(delivery.personalPickup)) {
                        break Label_0061;
                    }
                }
                else if (delivery.personalPickup == null) {
                    break Label_0061;
                }
                return false;
            }
            if (this.shipping != null) {
                if (this.shipping.equals(delivery.shipping)) {
                    return true;
                }
            }
            else if (delivery.shipping == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public List<Shipping> getPersonalPickup() {
        return this.personalPickup;
    }
    
    public List<Shipping> getShipping() {
        return this.shipping;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.personalPickup != null) {
            hashCode = this.personalPickup.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final List<Shipping> shipping = this.shipping;
        int hashCode2 = 0;
        if (shipping != null) {
            hashCode2 = this.shipping.hashCode();
        }
        return n + hashCode2;
    }
    
    public void setPersonalPickup(final List<Shipping> personalPickup) {
        this.personalPickup = personalPickup;
    }
    
    public void setShipping(final List<Shipping> shipping) {
        this.shipping = shipping;
    }
    
    @Override
    public String toString() {
        return "Delivery{personalPickup=" + this.personalPickup + ", shipping=" + this.shipping + '}';
    }
}
