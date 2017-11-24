package bf.io.openshop.entities;

import com.google.gson.annotations.*;

public class Banner
{
    private long id;
    @SerializedName("image_url")
    private String imageUrl;
    private String name;
    private String target;
    
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
                final Class<? extends Banner> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Banner banner = (Banner)o;
                    final long n = lcmp(this.id, banner.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.name != null) {
                            final boolean equals = this.name.equals(banner.name);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (banner.name != null) {
                            return false;
                        }
                        if (this.target != null) {
                            final boolean equals2 = this.target.equals(banner.target);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (banner.target != null) {
                            return false;
                        }
                        if (this.imageUrl != null) {
                            if (this.imageUrl.equals(banner.imageUrl)) {
                                return b;
                            }
                        }
                        else if (banner.imageUrl == null) {
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
    
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTarget() {
        return this.target;
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
        if (this.target != null) {
            hashCode2 = this.target.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        final String imageUrl = this.imageUrl;
        int hashCode3 = 0;
        if (imageUrl != null) {
            hashCode3 = this.imageUrl.hashCode();
        }
        return n3 + hashCode3;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
    
    @Override
    public String toString() {
        return "Banner{id=" + this.id + ", name='" + this.name + '\'' + ", target='" + this.target + '\'' + ", imageUrl='" + this.imageUrl + '\'' + '}';
    }
}
