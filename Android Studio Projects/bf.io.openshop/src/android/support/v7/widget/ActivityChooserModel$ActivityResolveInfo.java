package android.support.v7.widget;

import android.content.pm.*;
import java.math.*;

public final class ActivityResolveInfo implements Comparable<ActivityResolveInfo>
{
    public final ResolveInfo resolveInfo;
    public float weight;
    
    public ActivityResolveInfo(final ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
    }
    
    @Override
    public int compareTo(final ActivityResolveInfo activityResolveInfo) {
        return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityResolveInfo)o).weight)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 + Float.floatToIntBits(this.weight);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("resolveInfo:").append(this.resolveInfo.toString());
        sb.append("; weight:").append(new BigDecimal(this.weight));
        sb.append("]");
        return sb.toString();
    }
}
