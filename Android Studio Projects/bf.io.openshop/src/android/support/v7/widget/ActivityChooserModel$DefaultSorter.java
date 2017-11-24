package android.support.v7.widget;

import android.content.*;
import java.util.*;

private final class DefaultSorter implements ActivitySorter
{
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
    private final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap;
    
    private DefaultSorter() {
        this.mPackageNameToActivityMap = new HashMap<ComponentName, ActivityResolveInfo>();
    }
    
    @Override
    public void sort(final Intent intent, final List<ActivityResolveInfo> list, final List<HistoricalRecord> list2) {
        final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap = this.mPackageNameToActivityMap;
        mPackageNameToActivityMap.clear();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final ActivityResolveInfo activityResolveInfo = list.get(i);
            activityResolveInfo.weight = 0.0f;
            mPackageNameToActivityMap.put(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), activityResolveInfo);
        }
        final int n = -1 + list2.size();
        float n2 = 1.0f;
        for (int j = n; j >= 0; --j) {
            final HistoricalRecord historicalRecord = list2.get(j);
            final ActivityResolveInfo activityResolveInfo2 = mPackageNameToActivityMap.get(historicalRecord.activity);
            if (activityResolveInfo2 != null) {
                activityResolveInfo2.weight += n2 * historicalRecord.weight;
                n2 *= 0.95f;
            }
        }
        Collections.sort((List<Comparable>)list);
    }
}
