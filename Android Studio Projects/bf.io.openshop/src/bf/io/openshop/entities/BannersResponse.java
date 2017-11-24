package bf.io.openshop.entities;

import java.util.*;

public class BannersResponse
{
    Metadata metadata;
    private List<Banner> records;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof BannersResponse)) {
                return false;
            }
            final BannersResponse bannersResponse = (BannersResponse)o;
            Label_0051: {
                if (this.getMetadata() != null) {
                    if (this.getMetadata().equals(bannersResponse.getMetadata())) {
                        break Label_0051;
                    }
                }
                else if (bannersResponse.getMetadata() == null) {
                    break Label_0051;
                }
                return false;
            }
            if (this.getRecords() != null) {
                if (this.getRecords().equals(bannersResponse.getRecords())) {
                    return true;
                }
            }
            else if (bannersResponse.getRecords() == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public Metadata getMetadata() {
        return this.metadata;
    }
    
    public List<Banner> getRecords() {
        return this.records;
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
        final List<Banner> records = this.getRecords();
        int hashCode2 = 0;
        if (records != null) {
            hashCode2 = this.getRecords().hashCode();
        }
        return n + hashCode2;
    }
    
    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }
    
    public void setRecords(final List<Banner> records) {
        this.records = records;
    }
    
    @Override
    public String toString() {
        return "BannersResponse{metadata=" + this.metadata + ", records=" + this.records + '}';
    }
}
