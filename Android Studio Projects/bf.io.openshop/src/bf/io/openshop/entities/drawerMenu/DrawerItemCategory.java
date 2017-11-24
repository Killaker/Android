package bf.io.openshop.entities.drawerMenu;

import java.util.*;
import com.google.gson.annotations.*;

public class DrawerItemCategory
{
    private List<DrawerItemCategory> children;
    private long id;
    private String name;
    @SerializedName("original_id")
    private long originalId;
    private String type;
    
    public DrawerItemCategory() {
    }
    
    public DrawerItemCategory(final long id, final long originalId, final String name) {
        this.id = id;
        this.originalId = originalId;
        this.name = name;
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
                final Class<? extends DrawerItemCategory> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final DrawerItemCategory drawerItemCategory = (DrawerItemCategory)o;
                    final long n = lcmp(this.id, drawerItemCategory.id);
                    b2 = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.originalId, drawerItemCategory.originalId);
                        b2 = false;
                        if (n2 == 0) {
                            if (this.name != null) {
                                final boolean equals = this.name.equals(drawerItemCategory.name);
                                b2 = false;
                                if (!equals) {
                                    return b2;
                                }
                            }
                            else if (drawerItemCategory.name != null) {
                                return false;
                            }
                            if (this.children != null) {
                                final boolean equals2 = this.children.equals(drawerItemCategory.children);
                                b2 = false;
                                if (!equals2) {
                                    return b2;
                                }
                            }
                            else if (drawerItemCategory.children != null) {
                                return false;
                            }
                            if (this.type != null) {
                                if (this.type.equals(drawerItemCategory.type)) {
                                    return b;
                                }
                            }
                            else if (drawerItemCategory.type == null) {
                                return b;
                            }
                            b = false;
                            return b;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public List<DrawerItemCategory> getChildren() {
        return this.children;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getOriginalId() {
        return this.originalId;
    }
    
    public String getType() {
        return this.type;
    }
    
    public boolean hasChildren() {
        return this.children != null && this.children.size() > 0;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (int)(this.id ^ this.id >>> 32) + (int)(this.originalId ^ this.originalId >>> 32));
        int hashCode;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.children != null) {
            hashCode2 = this.children.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        final String type = this.type;
        int hashCode3 = 0;
        if (type != null) {
            hashCode3 = this.type.hashCode();
        }
        return n3 + hashCode3;
    }
    
    public void setChildren(final List<DrawerItemCategory> children) {
        this.children = children;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setOriginalId(final long originalId) {
        this.originalId = originalId;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "DrawerItemCategory{id=" + this.id + ", originalId=" + this.originalId + ", name='" + this.name + '\'' + ", children=" + this.children + ", type='" + this.type + '\'' + '}';
    }
}
