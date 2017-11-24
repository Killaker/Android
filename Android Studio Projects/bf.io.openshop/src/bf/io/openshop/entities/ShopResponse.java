package bf.io.openshop.entities;

import java.util.*;
import com.google.gson.annotations.*;

public class ShopResponse
{
    Metadata metadata;
    @SerializedName("records")
    List<Shop> shopList;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ShopResponse)) {
                return false;
            }
            final ShopResponse shopResponse = (ShopResponse)o;
            Label_0051: {
                if (this.getMetadata() != null) {
                    if (this.getMetadata().equals(shopResponse.getMetadata())) {
                        break Label_0051;
                    }
                }
                else if (shopResponse.getMetadata() == null) {
                    break Label_0051;
                }
                return false;
            }
            if (this.getShopList() != null) {
                if (this.getShopList().equals(shopResponse.getShopList())) {
                    return true;
                }
            }
            else if (shopResponse.getShopList() == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public Metadata getMetadata() {
        return this.metadata;
    }
    
    public List<Shop> getShopList() {
        return this.shopList;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.getMetadata() != null) {
            hashCode = this.getMetadata().hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final List<Shop> shopList = this.getShopList();
        int hashCode2 = 0;
        if (shopList != null) {
            hashCode2 = this.getShopList().hashCode();
        }
        return n + hashCode2;
    }
    
    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }
    
    public void setShopList(final List<Shop> shopList) {
        this.shopList = shopList;
    }
    
    @Override
    public String toString() {
        return "ShopResponse{metadata=" + this.metadata + ", shopList=" + this.shopList + '}';
    }
}
