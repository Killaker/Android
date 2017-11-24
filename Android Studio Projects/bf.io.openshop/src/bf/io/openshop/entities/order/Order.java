package bf.io.openshop.entities.order;

import com.google.gson.annotations.*;
import java.util.*;
import bf.io.openshop.entities.cart.*;
import bf.io.openshop.entities.*;

public class Order
{
    private String city;
    private String currency;
    @SerializedName("date_created")
    private String dateCreated;
    private String email;
    @SerializedName("house_number")
    private String houseNumber;
    private long id;
    private String name;
    private String note;
    @SerializedName("payment_type")
    private long paymentType;
    private String phone;
    @SerializedName("items")
    private List<CartProductItem> products;
    private Region region;
    @SerializedName("remote_id")
    private String remoteId;
    @SerializedName("shipping_name")
    private String shippingName;
    @SerializedName("shipping_price")
    private int shippingPrice;
    @SerializedName("shipping_price_formatted")
    private String shippingPriceFormatted;
    @SerializedName("shipping_type")
    private long shippingType;
    private String status;
    private String street;
    private int total;
    @SerializedName("total_formatted")
    private String totalFormatted;
    private String zip;
    
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
                final Class<? extends Order> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Order order = (Order)o;
                    final long n = lcmp(this.id, order.id);
                    b2 = false;
                    if (n == 0) {
                        final int total = this.total;
                        final int total2 = order.total;
                        b2 = false;
                        if (total == total2) {
                            final int shippingPrice = this.shippingPrice;
                            final int shippingPrice2 = order.shippingPrice;
                            b2 = false;
                            if (shippingPrice == shippingPrice2) {
                                final long n2 = lcmp(this.shippingType, order.shippingType);
                                b2 = false;
                                if (n2 == 0) {
                                    final long n3 = lcmp(this.paymentType, order.paymentType);
                                    b2 = false;
                                    if (n3 == 0) {
                                        if (this.remoteId != null) {
                                            final boolean equals = this.remoteId.equals(order.remoteId);
                                            b2 = false;
                                            if (!equals) {
                                                return b2;
                                            }
                                        }
                                        else if (order.remoteId != null) {
                                            return false;
                                        }
                                        if (this.dateCreated != null) {
                                            final boolean equals2 = this.dateCreated.equals(order.dateCreated);
                                            b2 = false;
                                            if (!equals2) {
                                                return b2;
                                            }
                                        }
                                        else if (order.dateCreated != null) {
                                            return false;
                                        }
                                        if (this.status != null) {
                                            final boolean equals3 = this.status.equals(order.status);
                                            b2 = false;
                                            if (!equals3) {
                                                return b2;
                                            }
                                        }
                                        else if (order.status != null) {
                                            return false;
                                        }
                                        if (this.totalFormatted != null) {
                                            final boolean equals4 = this.totalFormatted.equals(order.totalFormatted);
                                            b2 = false;
                                            if (!equals4) {
                                                return b2;
                                            }
                                        }
                                        else if (order.totalFormatted != null) {
                                            return false;
                                        }
                                        if (this.shippingName != null) {
                                            final boolean equals5 = this.shippingName.equals(order.shippingName);
                                            b2 = false;
                                            if (!equals5) {
                                                return b2;
                                            }
                                        }
                                        else if (order.shippingName != null) {
                                            return false;
                                        }
                                        if (this.shippingPriceFormatted != null) {
                                            final boolean equals6 = this.shippingPriceFormatted.equals(order.shippingPriceFormatted);
                                            b2 = false;
                                            if (!equals6) {
                                                return b2;
                                            }
                                        }
                                        else if (order.shippingPriceFormatted != null) {
                                            return false;
                                        }
                                        if (this.currency != null) {
                                            final boolean equals7 = this.currency.equals(order.currency);
                                            b2 = false;
                                            if (!equals7) {
                                                return b2;
                                            }
                                        }
                                        else if (order.currency != null) {
                                            return false;
                                        }
                                        if (this.name != null) {
                                            final boolean equals8 = this.name.equals(order.name);
                                            b2 = false;
                                            if (!equals8) {
                                                return b2;
                                            }
                                        }
                                        else if (order.name != null) {
                                            return false;
                                        }
                                        if (this.street != null) {
                                            final boolean equals9 = this.street.equals(order.street);
                                            b2 = false;
                                            if (!equals9) {
                                                return b2;
                                            }
                                        }
                                        else if (order.street != null) {
                                            return false;
                                        }
                                        if (this.houseNumber != null) {
                                            final boolean equals10 = this.houseNumber.equals(order.houseNumber);
                                            b2 = false;
                                            if (!equals10) {
                                                return b2;
                                            }
                                        }
                                        else if (order.houseNumber != null) {
                                            return false;
                                        }
                                        if (this.city != null) {
                                            final boolean equals11 = this.city.equals(order.city);
                                            b2 = false;
                                            if (!equals11) {
                                                return b2;
                                            }
                                        }
                                        else if (order.city != null) {
                                            return false;
                                        }
                                        if (this.region != null) {
                                            final boolean equals12 = this.region.equals(order.region);
                                            b2 = false;
                                            if (!equals12) {
                                                return b2;
                                            }
                                        }
                                        else if (order.region != null) {
                                            return false;
                                        }
                                        if (this.zip != null) {
                                            final boolean equals13 = this.zip.equals(order.zip);
                                            b2 = false;
                                            if (!equals13) {
                                                return b2;
                                            }
                                        }
                                        else if (order.zip != null) {
                                            return false;
                                        }
                                        if (this.products != null) {
                                            final boolean equals14 = this.products.equals(order.products);
                                            b2 = false;
                                            if (!equals14) {
                                                return b2;
                                            }
                                        }
                                        else if (order.products != null) {
                                            return false;
                                        }
                                        if (this.email != null) {
                                            final boolean equals15 = this.email.equals(order.email);
                                            b2 = false;
                                            if (!equals15) {
                                                return b2;
                                            }
                                        }
                                        else if (order.email != null) {
                                            return false;
                                        }
                                        if (this.phone != null) {
                                            final boolean equals16 = this.phone.equals(order.phone);
                                            b2 = false;
                                            if (!equals16) {
                                                return b2;
                                            }
                                        }
                                        else if (order.phone != null) {
                                            return false;
                                        }
                                        if (this.note != null) {
                                            if (this.note.equals(order.note)) {
                                                return b;
                                            }
                                        }
                                        else if (order.note == null) {
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
        }
        return b2;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public String getDateCreated() {
        return this.dateCreated;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getHouseNumber() {
        return this.houseNumber;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNote() {
        return this.note;
    }
    
    public long getPaymentType() {
        return this.paymentType;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public List<CartProductItem> getProducts() {
        return this.products;
    }
    
    public Region getRegion() {
        return this.region;
    }
    
    public String getRemoteId() {
        return this.remoteId;
    }
    
    public String getShippingName() {
        return this.shippingName;
    }
    
    public int getShippingPrice() {
        return this.shippingPrice;
    }
    
    public String getShippingPriceFormatted() {
        return this.shippingPriceFormatted;
    }
    
    public long getShippingType() {
        return this.shippingType;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getStreet() {
        return this.street;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public String getTotalFormatted() {
        return this.totalFormatted;
    }
    
    public String getZip() {
        return this.zip;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.remoteId != null) {
            hashCode = this.remoteId.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.dateCreated != null) {
            hashCode2 = this.dateCreated.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.status != null) {
            hashCode3 = this.status.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (31 * (n3 + hashCode3) + this.total);
        int hashCode4;
        if (this.totalFormatted != null) {
            hashCode4 = this.totalFormatted.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n5 = 31 * (n4 + hashCode4);
        int hashCode5;
        if (this.shippingName != null) {
            hashCode5 = this.shippingName.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n6 = 31 * (31 * (n5 + hashCode5) + this.shippingPrice);
        int hashCode6;
        if (this.shippingPriceFormatted != null) {
            hashCode6 = this.shippingPriceFormatted.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        final int n7 = 31 * (n6 + hashCode6);
        int hashCode7;
        if (this.currency != null) {
            hashCode7 = this.currency.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        final int n8 = 31 * (31 * (31 * (n7 + hashCode7) + (int)(this.shippingType ^ this.shippingType >>> 32)) + (int)(this.paymentType ^ this.paymentType >>> 32));
        int hashCode8;
        if (this.name != null) {
            hashCode8 = this.name.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        final int n9 = 31 * (n8 + hashCode8);
        int hashCode9;
        if (this.street != null) {
            hashCode9 = this.street.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        final int n10 = 31 * (n9 + hashCode9);
        int hashCode10;
        if (this.houseNumber != null) {
            hashCode10 = this.houseNumber.hashCode();
        }
        else {
            hashCode10 = 0;
        }
        final int n11 = 31 * (n10 + hashCode10);
        int hashCode11;
        if (this.city != null) {
            hashCode11 = this.city.hashCode();
        }
        else {
            hashCode11 = 0;
        }
        final int n12 = 31 * (n11 + hashCode11);
        int hashCode12;
        if (this.region != null) {
            hashCode12 = this.region.hashCode();
        }
        else {
            hashCode12 = 0;
        }
        final int n13 = 31 * (n12 + hashCode12);
        int hashCode13;
        if (this.zip != null) {
            hashCode13 = this.zip.hashCode();
        }
        else {
            hashCode13 = 0;
        }
        final int n14 = 31 * (n13 + hashCode13);
        int hashCode14;
        if (this.products != null) {
            hashCode14 = this.products.hashCode();
        }
        else {
            hashCode14 = 0;
        }
        final int n15 = 31 * (n14 + hashCode14);
        int hashCode15;
        if (this.email != null) {
            hashCode15 = this.email.hashCode();
        }
        else {
            hashCode15 = 0;
        }
        final int n16 = 31 * (n15 + hashCode15);
        int hashCode16;
        if (this.phone != null) {
            hashCode16 = this.phone.hashCode();
        }
        else {
            hashCode16 = 0;
        }
        final int n17 = 31 * (n16 + hashCode16);
        final String note = this.note;
        int hashCode17 = 0;
        if (note != null) {
            hashCode17 = this.note.hashCode();
        }
        return n17 + hashCode17;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public void setDateCreated(final String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setNote(final String note) {
        this.note = note;
    }
    
    public void setPaymentType(final long paymentType) {
        this.paymentType = paymentType;
    }
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    public void setProducts(final List<CartProductItem> products) {
        this.products = products;
    }
    
    public void setRegion(final Region region) {
        this.region = region;
    }
    
    public void setRemoteId(final String remoteId) {
        this.remoteId = remoteId;
    }
    
    public void setShippingName(final String shippingName) {
        this.shippingName = shippingName;
    }
    
    public void setShippingPrice(final int shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
    
    public void setShippingPriceFormatted(final String shippingPriceFormatted) {
        this.shippingPriceFormatted = shippingPriceFormatted;
    }
    
    public void setShippingType(final long shippingType) {
        this.shippingType = shippingType;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setStreet(final String street) {
        this.street = street;
    }
    
    public void setTotal(final int total) {
        this.total = total;
    }
    
    public void setTotalFormatted(final String totalFormatted) {
        this.totalFormatted = totalFormatted;
    }
    
    public void setZip(final String zip) {
        this.zip = zip;
    }
    
    @Override
    public String toString() {
        return "Order{id=" + this.id + ", remoteId='" + this.remoteId + '\'' + ", dateCreated='" + this.dateCreated + '\'' + ", status='" + this.status + '\'' + ", total=" + this.total + ", totalFormatted='" + this.totalFormatted + '\'' + ", shippingName='" + this.shippingName + '\'' + ", shippingPrice=" + this.shippingPrice + ", shippingPriceFormatted='" + this.shippingPriceFormatted + '\'' + ", currency='" + this.currency + '\'' + ", shippingType=" + this.shippingType + ", paymentType=" + this.paymentType + ", name='" + this.name + '\'' + ", street='" + this.street + '\'' + ", houseNumber='" + this.houseNumber + '\'' + ", city='" + this.city + '\'' + ", region=" + this.region + ", zip='" + this.zip + '\'' + ", products=" + this.products + ", email='" + this.email + '\'' + ", phone='" + this.phone + '\'' + ", note='" + this.note + '\'' + '}';
    }
}
