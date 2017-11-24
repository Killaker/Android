package bf.io.openshop.entities.delivery;

public class DeliveryRequest
{
    private Delivery delivery;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final DeliveryRequest deliveryRequest = (DeliveryRequest)o;
            if (this.delivery != null) {
                if (this.delivery.equals(deliveryRequest.delivery)) {
                    return true;
                }
            }
            else if (deliveryRequest.delivery == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public Delivery getDelivery() {
        return this.delivery;
    }
    
    @Override
    public int hashCode() {
        if (this.delivery != null) {
            return this.delivery.hashCode();
        }
        return 0;
    }
    
    public void setDelivery(final Delivery delivery) {
        this.delivery = delivery;
    }
    
    @Override
    public String toString() {
        return "DeliveryRequest{delivery=" + this.delivery + '}';
    }
}
