package bf.io.openshop.entities.delivery;

import java.util.*;
import com.google.gson.annotations.*;

public class BranchesRequest
{
    @SerializedName("records")
    private List<Branch> branches;
    
    public BranchesRequest(final List<Branch> branches) {
        this.branches = branches;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final BranchesRequest branchesRequest = (BranchesRequest)o;
            if (this.branches != null) {
                if (this.branches.equals(branchesRequest.branches)) {
                    return true;
                }
            }
            else if (branchesRequest.branches == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public List<Branch> getBranches() {
        return this.branches;
    }
    
    @Override
    public int hashCode() {
        if (this.branches != null) {
            return this.branches.hashCode();
        }
        return 0;
    }
    
    public void setBranches(final List<Branch> branches) {
        this.branches = branches;
    }
    
    @Override
    public String toString() {
        return "BranchesRequest{branches=" + this.branches + '}';
    }
}
