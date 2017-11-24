package bf.io.openshop.entities.delivery;

import com.google.gson.annotations.*;

public class Payment
{
    private String currency;
    private String description;
    private long id;
    private String name;
    private double price;
    @SerializedName("price_formatted")
    private String priceFormatted;
    @SerializedName("total_price")
    private double totalPrice;
    @SerializedName("total_price_formatted")
    private String totalPriceFormatted;
    
    public Payment() {
    }
    
    public Payment(final String name, final String description) {
        this.name = name;
        this.description = description;
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
                final Class<? extends Payment> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Payment payment = (Payment)o;
                    final long n = lcmp(this.id, payment.id);
                    b2 = false;
                    if (n == 0) {
                        final int compare = Double.compare(payment.price, this.price);
                        b2 = false;
                        if (compare == 0) {
                            final int compare2 = Double.compare(payment.totalPrice, this.totalPrice);
                            b2 = false;
                            if (compare2 == 0) {
                                if (this.name != null) {
                                    final boolean equals = this.name.equals(payment.name);
                                    b2 = false;
                                    if (!equals) {
                                        return b2;
                                    }
                                }
                                else if (payment.name != null) {
                                    return false;
                                }
                                if (this.description != null) {
                                    final boolean equals2 = this.description.equals(payment.description);
                                    b2 = false;
                                    if (!equals2) {
                                        return b2;
                                    }
                                }
                                else if (payment.description != null) {
                                    return false;
                                }
                                if (this.priceFormatted != null) {
                                    final boolean equals3 = this.priceFormatted.equals(payment.priceFormatted);
                                    b2 = false;
                                    if (!equals3) {
                                        return b2;
                                    }
                                }
                                else if (payment.priceFormatted != null) {
                                    return false;
                                }
                                if (this.currency != null) {
                                    final boolean equals4 = this.currency.equals(payment.currency);
                                    b2 = false;
                                    if (!equals4) {
                                        return b2;
                                    }
                                }
                                else if (payment.currency != null) {
                                    return false;
                                }
                                if (this.totalPriceFormatted != null) {
                                    if (this.totalPriceFormatted.equals(payment.totalPriceFormatted)) {
                                        return b;
                                    }
                                }
                                else if (payment.totalPriceFormatted == null) {
                                    return b;
                                }
                                b = false;
                                return b;
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public String getPriceFormatted() {
        return this.priceFormatted;
    }
    
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    public String getTotalPriceFormatted() {
        return this.totalPriceFormatted;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.description != null) {
            hashCode2 = this.description.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = n2 + hashCode2;
        final long doubleToLongBits = Double.doubleToLongBits(this.price);
        final int n4 = 31 * (n3 * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32));
        int hashCode3;
        if (this.priceFormatted != null) {
            hashCode3 = this.priceFormatted.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n5 = 31 * (n4 + hashCode3);
        int hashCode4;
        if (this.currency != null) {
            hashCode4 = this.currency.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n6 = n5 + hashCode4;
        final long doubleToLongBits2 = Double.doubleToLongBits(this.totalPrice);
        final int n7 = 31 * (n6 * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32));
        final String totalPriceFormatted = this.totalPriceFormatted;
        int hashCode5 = 0;
        if (totalPriceFormatted != null) {
            hashCode5 = this.totalPriceFormatted.hashCode();
        }
        return n7 + hashCode5;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setPrice(final double price) {
        this.price = price;
    }
    
    public void setPriceFormatted(final String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }
    
    public void setTotalPrice(final double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setTotalPriceFormatted(final String totalPriceFormatted) {
        this.totalPriceFormatted = totalPriceFormatted;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
