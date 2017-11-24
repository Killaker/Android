package bf.io.openshop.entities;

import com.google.gson.annotations.*;

public class Shop
{
    private String currency;
    private String description;
    @SerializedName("flag_icon")
    private String flagIcon;
    @SerializedName("google_ua")
    private String googleUa;
    private long id;
    private String language;
    private String logo;
    private String name;
    private String url;
    
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
                final Class<? extends Shop> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Shop shop = (Shop)o;
                    final long n = lcmp(this.id, shop.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.name != null) {
                            final boolean equals = this.name.equals(shop.name);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (shop.name != null) {
                            return false;
                        }
                        if (this.description != null) {
                            final boolean equals2 = this.description.equals(shop.description);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (shop.description != null) {
                            return false;
                        }
                        if (this.url != null) {
                            final boolean equals3 = this.url.equals(shop.url);
                            b2 = false;
                            if (!equals3) {
                                return b2;
                            }
                        }
                        else if (shop.url != null) {
                            return false;
                        }
                        if (this.logo != null) {
                            final boolean equals4 = this.logo.equals(shop.logo);
                            b2 = false;
                            if (!equals4) {
                                return b2;
                            }
                        }
                        else if (shop.logo != null) {
                            return false;
                        }
                        if (this.googleUa != null) {
                            final boolean equals5 = this.googleUa.equals(shop.googleUa);
                            b2 = false;
                            if (!equals5) {
                                return b2;
                            }
                        }
                        else if (shop.googleUa != null) {
                            return false;
                        }
                        if (this.language != null) {
                            final boolean equals6 = this.language.equals(shop.language);
                            b2 = false;
                            if (!equals6) {
                                return b2;
                            }
                        }
                        else if (shop.language != null) {
                            return false;
                        }
                        if (this.currency != null) {
                            final boolean equals7 = this.currency.equals(shop.currency);
                            b2 = false;
                            if (!equals7) {
                                return b2;
                            }
                        }
                        else if (shop.currency != null) {
                            return false;
                        }
                        if (this.flagIcon != null) {
                            if (this.flagIcon.equals(shop.flagIcon)) {
                                return b;
                            }
                        }
                        else if (shop.flagIcon == null) {
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
    
    public String getCurrency() {
        return this.currency;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getFlagIcon() {
        return this.flagIcon;
    }
    
    public String getGoogleUa() {
        return this.googleUa;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public String getLogo() {
        return this.logo;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUrl() {
        return this.url;
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
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.url != null) {
            hashCode3 = this.url.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (n3 + hashCode3);
        int hashCode4;
        if (this.logo != null) {
            hashCode4 = this.logo.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n5 = 31 * (n4 + hashCode4);
        int hashCode5;
        if (this.googleUa != null) {
            hashCode5 = this.googleUa.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n6 = 31 * (n5 + hashCode5);
        int hashCode6;
        if (this.language != null) {
            hashCode6 = this.language.hashCode();
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
        final int n8 = 31 * (n7 + hashCode7);
        final String flagIcon = this.flagIcon;
        int hashCode8 = 0;
        if (flagIcon != null) {
            hashCode8 = this.flagIcon.hashCode();
        }
        return n8 + hashCode8;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setFlagIcon(final String flagIcon) {
        this.flagIcon = flagIcon;
    }
    
    public void setGoogleUa(final String googleUa) {
        this.googleUa = googleUa;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setLanguage(final String language) {
        this.language = language;
    }
    
    public void setLogo(final String logo) {
        this.logo = logo;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "Shop{id=" + this.id + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", url='" + this.url + '\'' + ", logo='" + this.logo + '\'' + ", googleUa='" + this.googleUa + '\'' + ", language='" + this.language + '\'' + ", currency='" + this.currency + '\'' + ", flagIcon='" + this.flagIcon + '\'' + '}';
    }
}
