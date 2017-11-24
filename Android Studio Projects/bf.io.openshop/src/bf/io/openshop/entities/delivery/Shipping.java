package bf.io.openshop.entities.delivery;

import com.google.gson.annotations.*;
import java.util.*;

public class Shipping
{
    private String availabilityDate;
    private String availabilityTime;
    private Branch branch;
    private String currency;
    private String description;
    private long id;
    @SerializedName("min_cart_amount")
    private int minCartAmount;
    private String name;
    private List<Payment> payment;
    private int price;
    @SerializedName("price_formatted")
    private String priceFormatted;
    @SerializedName("total_price")
    private double totalPrice;
    @SerializedName("total_price_formatted")
    private String totalPriceFormatted;
    
    public Shipping() {
    }
    
    public Shipping(final long id, final String name, final int price, final String currency, final int minCartAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.minCartAmount = minCartAmount;
    }
    
    public Shipping(final String name) {
        this.name = name;
    }
    
    public Shipping(final String name, final String availabilityTime, final String availabilityDate, final String description) {
        this.name = name;
        this.availabilityTime = availabilityTime;
        this.availabilityDate = availabilityDate;
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
                final Class<? extends Shipping> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Shipping shipping = (Shipping)o;
                    final long n = lcmp(this.id, shipping.id);
                    b2 = false;
                    if (n == 0) {
                        final int price = this.price;
                        final int price2 = shipping.price;
                        b2 = false;
                        if (price == price2) {
                            final int compare = Double.compare(shipping.totalPrice, this.totalPrice);
                            b2 = false;
                            if (compare == 0) {
                                final int minCartAmount = this.minCartAmount;
                                final int minCartAmount2 = shipping.minCartAmount;
                                b2 = false;
                                if (minCartAmount == minCartAmount2) {
                                    if (this.name != null) {
                                        final boolean equals = this.name.equals(shipping.name);
                                        b2 = false;
                                        if (!equals) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.name != null) {
                                        return false;
                                    }
                                    if (this.priceFormatted != null) {
                                        final boolean equals2 = this.priceFormatted.equals(shipping.priceFormatted);
                                        b2 = false;
                                        if (!equals2) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.priceFormatted != null) {
                                        return false;
                                    }
                                    if (this.totalPriceFormatted != null) {
                                        final boolean equals3 = this.totalPriceFormatted.equals(shipping.totalPriceFormatted);
                                        b2 = false;
                                        if (!equals3) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.totalPriceFormatted != null) {
                                        return false;
                                    }
                                    if (this.currency != null) {
                                        final boolean equals4 = this.currency.equals(shipping.currency);
                                        b2 = false;
                                        if (!equals4) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.currency != null) {
                                        return false;
                                    }
                                    if (this.payment != null) {
                                        final boolean equals5 = this.payment.equals(shipping.payment);
                                        b2 = false;
                                        if (!equals5) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.payment != null) {
                                        return false;
                                    }
                                    if (this.branch != null) {
                                        final boolean equals6 = this.branch.equals(shipping.branch);
                                        b2 = false;
                                        if (!equals6) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.branch != null) {
                                        return false;
                                    }
                                    if (this.description != null) {
                                        final boolean equals7 = this.description.equals(shipping.description);
                                        b2 = false;
                                        if (!equals7) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.description != null) {
                                        return false;
                                    }
                                    if (this.availabilityTime != null) {
                                        final boolean equals8 = this.availabilityTime.equals(shipping.availabilityTime);
                                        b2 = false;
                                        if (!equals8) {
                                            return b2;
                                        }
                                    }
                                    else if (shipping.availabilityTime != null) {
                                        return false;
                                    }
                                    if (this.availabilityDate != null) {
                                        if (this.availabilityDate.equals(shipping.availabilityDate)) {
                                            return b;
                                        }
                                    }
                                    else if (shipping.availabilityDate == null) {
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
        }
        return b2;
    }
    
    public String getAvailabilityDate() {
        return this.availabilityDate;
    }
    
    public String getAvailabilityTime() {
        return this.availabilityTime;
    }
    
    public Branch getBranch() {
        return this.branch;
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
    
    public int getMinCartAmount() {
        return this.minCartAmount;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Payment> getPayment() {
        return this.payment;
    }
    
    public int getPrice() {
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
        final int n2 = 31 * (31 * (n + hashCode) + this.price);
        int hashCode2;
        if (this.priceFormatted != null) {
            hashCode2 = this.priceFormatted.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = n2 + hashCode2;
        final long doubleToLongBits = Double.doubleToLongBits(this.totalPrice);
        final int n4 = 31 * (n3 * 31 + (int)(doubleToLongBits ^ doubleToLongBits >>> 32));
        int hashCode3;
        if (this.totalPriceFormatted != null) {
            hashCode3 = this.totalPriceFormatted.hashCode();
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
        final int n6 = 31 * (31 * (n5 + hashCode4) + this.minCartAmount);
        int hashCode5;
        if (this.payment != null) {
            hashCode5 = this.payment.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n7 = 31 * (n6 + hashCode5);
        int hashCode6;
        if (this.branch != null) {
            hashCode6 = this.branch.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        final int n8 = 31 * (n7 + hashCode6);
        int hashCode7;
        if (this.description != null) {
            hashCode7 = this.description.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        final int n9 = 31 * (n8 + hashCode7);
        int hashCode8;
        if (this.availabilityTime != null) {
            hashCode8 = this.availabilityTime.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        final int n10 = 31 * (n9 + hashCode8);
        final String availabilityDate = this.availabilityDate;
        int hashCode9 = 0;
        if (availabilityDate != null) {
            hashCode9 = this.availabilityDate.hashCode();
        }
        return n10 + hashCode9;
    }
    
    public void setAvailabilityDate(final String availabilityDate) {
        this.availabilityDate = availabilityDate;
    }
    
    public void setAvailabilityTime(final String availabilityTime) {
        this.availabilityTime = availabilityTime;
    }
    
    public void setBranch(final Branch branch) {
        this.branch = branch;
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
    
    public void setMinCartAmount(final int minCartAmount) {
        this.minCartAmount = minCartAmount;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setPayment(final List<Payment> payment) {
        this.payment = payment;
    }
    
    public void setPrice(final int price) {
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
