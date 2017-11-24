package android.support.v7.widget;

import android.content.*;
import java.math.*;

public static final class HistoricalRecord
{
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public HistoricalRecord(final ComponentName activity, final long time, final float weight) {
        this.activity = activity;
        this.time = time;
        this.weight = weight;
    }
    
    public HistoricalRecord(final String s, final long n, final float n2) {
        this(ComponentName.unflattenFromString(s), n, n2);
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
            final HistoricalRecord historicalRecord = (HistoricalRecord)o;
            if (this.activity == null) {
                if (historicalRecord.activity != null) {
                    return false;
                }
            }
            else if (!this.activity.equals((Object)historicalRecord.activity)) {
                return false;
            }
            if (this.time != historicalRecord.time) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(historicalRecord.weight)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.activity == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.activity.hashCode();
        }
        return 31 * (31 * (hashCode + 31) + (int)(this.time ^ this.time >>> 32)) + Float.floatToIntBits(this.weight);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("; activity:").append(this.activity);
        sb.append("; time:").append(this.time);
        sb.append("; weight:").append(new BigDecimal(this.weight));
        sb.append("]");
        return sb.toString();
    }
}
