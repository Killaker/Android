package bf.io.openshop.entities.cart;

import com.google.gson.annotations.*;

public class Discount
{
    private long id;
    @SerializedName("min_cart_amount")
    private String minCartAmount;
    private String name;
    private String type;
    private String value;
    @SerializedName("value_formatted")
    private String valueFormatted;
    
    public Discount() {
    }
    
    public Discount(final long id, final String name, final String type, final String value, final String valueFormatted, final String minCartAmount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.valueFormatted = valueFormatted;
        this.minCartAmount = minCartAmount;
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
                final Class<? extends Discount> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Discount discount = (Discount)o;
                    final long n = lcmp(this.id, discount.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.name != null) {
                            final boolean equals = this.name.equals(discount.name);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (discount.name != null) {
                            return false;
                        }
                        if (this.type != null) {
                            final boolean equals2 = this.type.equals(discount.type);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (discount.type != null) {
                            return false;
                        }
                        if (this.value != null) {
                            final boolean equals3 = this.value.equals(discount.value);
                            b2 = false;
                            if (!equals3) {
                                return b2;
                            }
                        }
                        else if (discount.value != null) {
                            return false;
                        }
                        if (this.valueFormatted != null) {
                            final boolean equals4 = this.valueFormatted.equals(discount.valueFormatted);
                            b2 = false;
                            if (!equals4) {
                                return b2;
                            }
                        }
                        else if (discount.valueFormatted != null) {
                            return false;
                        }
                        if (this.minCartAmount != null) {
                            if (this.minCartAmount.equals(discount.minCartAmount)) {
                                return b;
                            }
                        }
                        else if (discount.minCartAmount == null) {
                            return b;
                        }
                        b = false;
                        return b;
                    }
                }
            }
        }
        return b2;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getMinCartAmount() {
        return this.minCartAmount;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getValueFormatted() {
        return this.valueFormatted;
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
        if (this.type != null) {
            hashCode2 = this.type.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.value != null) {
            hashCode3 = this.value.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (n3 + hashCode3);
        int hashCode4;
        if (this.valueFormatted != null) {
            hashCode4 = this.valueFormatted.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n5 = 31 * (n4 + hashCode4);
        final String minCartAmount = this.minCartAmount;
        int hashCode5 = 0;
        if (minCartAmount != null) {
            hashCode5 = this.minCartAmount.hashCode();
        }
        return n5 + hashCode5;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setMinCartAmount(final String minCartAmount) {
        this.minCartAmount = minCartAmount;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public void setValueFormatted(final String valueFormatted) {
        this.valueFormatted = valueFormatted;
    }
    
    @Override
    public String toString() {
        return "Discount{id=" + this.id + ", name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", value='" + this.value + '\'' + ", valueFormatted='" + this.valueFormatted + '\'' + ", minCartAmount='" + this.minCartAmount + '\'' + '}';
    }
}
