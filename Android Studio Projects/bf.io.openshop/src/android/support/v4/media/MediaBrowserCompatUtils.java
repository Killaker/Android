package android.support.v4.media;

import java.util.*;
import android.os.*;

public class MediaBrowserCompatUtils
{
    public static List<MediaBrowserCompat.MediaItem> applyOptions(final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) {
        final int int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
        final int int2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if (int1 == -1 && int2 == -1) {
            return list;
        }
        final int n = int2 * (int1 - 1);
        int size = n + int2;
        if (int1 < 1 || int2 < 1 || n >= list.size()) {
            return null;
        }
        if (size > list.size()) {
            size = list.size();
        }
        return list.subList(n, size);
    }
    
    public static boolean areSameOptions(final Bundle bundle, final Bundle bundle2) {
        if (bundle != bundle2) {
            if (bundle == null) {
                if (bundle2.getInt("android.media.browse.extra.PAGE", -1) != -1 || bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1) {
                    return false;
                }
            }
            else if (bundle2 == null) {
                if (bundle.getInt("android.media.browse.extra.PAGE", -1) != -1 || bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1) {
                    return false;
                }
            }
            else if (bundle.getInt("android.media.browse.extra.PAGE", -1) != bundle2.getInt("android.media.browse.extra.PAGE", -1) || bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) != bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean hasDuplicatedItems(final Bundle bundle, final Bundle bundle2) {
        int int1;
        if (bundle == null) {
            int1 = -1;
        }
        else {
            int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
        }
        int int2;
        if (bundle2 == null) {
            int2 = -1;
        }
        else {
            int2 = bundle2.getInt("android.media.browse.extra.PAGE", -1);
        }
        int int3;
        if (bundle == null) {
            int3 = -1;
        }
        else {
            int3 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        }
        int int4;
        if (bundle2 == null) {
            int4 = -1;
        }
        else {
            int4 = bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        }
        int n;
        int n2;
        if (int1 == -1 || int3 == -1) {
            n = 0;
            n2 = Integer.MAX_VALUE;
        }
        else {
            n = int3 * (int1 - 1);
            n2 = -1 + (n + int3);
        }
        int n3;
        int n4;
        if (int2 == -1 || int4 == -1) {
            n3 = 0;
            n4 = Integer.MAX_VALUE;
        }
        else {
            n3 = int4 * (int2 - 1);
            n4 = -1 + (n3 + int4);
        }
        return (n <= n3 && n3 <= n2) || (n <= n4 && n4 <= n2);
    }
}
