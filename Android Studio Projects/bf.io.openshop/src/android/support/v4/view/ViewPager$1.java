package android.support.v4.view;

import java.util.*;

static final class ViewPager$1 implements Comparator<ItemInfo> {
    @Override
    public int compare(final ItemInfo itemInfo, final ItemInfo itemInfo2) {
        return itemInfo.position - itemInfo2.position;
    }
}