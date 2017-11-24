package bf.io.openshop.entities;

import com.google.gson.annotations.*;

public class User
{
    @SerializedName("access_token")
    private String accessToken;
    private String city;
    private String country;
    private String email;
    @SerializedName("fb_id")
    private String fbId;
    private String gender;
    @SerializedName("house_number")
    private String houseNumber;
    private long id;
    private String name;
    private String phone;
    private String street;
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
                final Class<? extends User> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final User user = (User)o;
                    final long n = lcmp(this.id, user.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.fbId != null) {
                            final boolean equals = this.fbId.equals(user.fbId);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (user.fbId != null) {
                            return false;
                        }
                        if (this.accessToken != null) {
                            final boolean equals2 = this.accessToken.equals(user.accessToken);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (user.accessToken != null) {
                            return false;
                        }
                        if (this.name != null) {
                            final boolean equals3 = this.name.equals(user.name);
                            b2 = false;
                            if (!equals3) {
                                return b2;
                            }
                        }
                        else if (user.name != null) {
                            return false;
                        }
                        if (this.street != null) {
                            final boolean equals4 = this.street.equals(user.street);
                            b2 = false;
                            if (!equals4) {
                                return b2;
                            }
                        }
                        else if (user.street != null) {
                            return false;
                        }
                        if (this.city != null) {
                            final boolean equals5 = this.city.equals(user.city);
                            b2 = false;
                            if (!equals5) {
                                return b2;
                            }
                        }
                        else if (user.city != null) {
                            return false;
                        }
                        if (this.houseNumber != null) {
                            final boolean equals6 = this.houseNumber.equals(user.houseNumber);
                            b2 = false;
                            if (!equals6) {
                                return b2;
                            }
                        }
                        else if (user.houseNumber != null) {
                            return false;
                        }
                        if (this.zip != null) {
                            final boolean equals7 = this.zip.equals(user.zip);
                            b2 = false;
                            if (!equals7) {
                                return b2;
                            }
                        }
                        else if (user.zip != null) {
                            return false;
                        }
                        if (this.email != null) {
                            final boolean equals8 = this.email.equals(user.email);
                            b2 = false;
                            if (!equals8) {
                                return b2;
                            }
                        }
                        else if (user.email != null) {
                            return false;
                        }
                        if (this.phone != null) {
                            final boolean equals9 = this.phone.equals(user.phone);
                            b2 = false;
                            if (!equals9) {
                                return b2;
                            }
                        }
                        else if (user.phone != null) {
                            return false;
                        }
                        if (this.gender != null) {
                            final boolean equals10 = this.gender.equals(user.gender);
                            b2 = false;
                            if (!equals10) {
                                return b2;
                            }
                        }
                        else if (user.gender != null) {
                            return false;
                        }
                        if (this.country != null) {
                            if (this.country.equals(user.country)) {
                                return b;
                            }
                        }
                        else if (user.country == null) {
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
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getFbId() {
        return this.fbId;
    }
    
    public String getGender() {
        return this.gender;
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
    
    public String getPhone() {
        return this.phone;
    }
    
    public String getStreet() {
        return this.street;
    }
    
    public String getZip() {
        return this.zip;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.fbId != null) {
            hashCode = this.fbId.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.accessToken != null) {
            hashCode2 = this.accessToken.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.name != null) {
            hashCode3 = this.name.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (n3 + hashCode3);
        int hashCode4;
        if (this.street != null) {
            hashCode4 = this.street.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n5 = 31 * (n4 + hashCode4);
        int hashCode5;
        if (this.city != null) {
            hashCode5 = this.city.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n6 = 31 * (n5 + hashCode5);
        int hashCode6;
        if (this.houseNumber != null) {
            hashCode6 = this.houseNumber.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        final int n7 = 31 * (n6 + hashCode6);
        int hashCode7;
        if (this.zip != null) {
            hashCode7 = this.zip.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        final int n8 = 31 * (n7 + hashCode7);
        int hashCode8;
        if (this.email != null) {
            hashCode8 = this.email.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        final int n9 = 31 * (n8 + hashCode8);
        int hashCode9;
        if (this.phone != null) {
            hashCode9 = this.phone.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        final int n10 = 31 * (n9 + hashCode9);
        int hashCode10;
        if (this.gender != null) {
            hashCode10 = this.gender.hashCode();
        }
        else {
            hashCode10 = 0;
        }
        final int n11 = 31 * (n10 + hashCode10);
        final String country = this.country;
        int hashCode11 = 0;
        if (country != null) {
            hashCode11 = this.country.hashCode();
        }
        return n11 + hashCode11;
    }
    
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public void setCountry(final String country) {
        this.country = country;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setFbId(final String fbId) {
        this.fbId = fbId;
    }
    
    public void setGender(final String gender) {
        this.gender = gender;
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
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    public void setStreet(final String street) {
        this.street = street;
    }
    
    public void setZip(final String zip) {
        this.zip = zip;
    }
    
    @Override
    public String toString() {
        return "User{id=" + this.id + ", fbId='" + this.fbId + '\'' + ", accessToken='" + this.accessToken + '\'' + ", name='" + this.name + '\'' + ", street='" + this.street + '\'' + ", city='" + this.city + '\'' + ", houseNumber='" + this.houseNumber + '\'' + ", zip='" + this.zip + '\'' + ", email='" + this.email + '\'' + ", phone='" + this.phone + '\'' + ", gender='" + this.gender + '\'' + ", country='" + this.country + '\'' + '}';
    }
}
