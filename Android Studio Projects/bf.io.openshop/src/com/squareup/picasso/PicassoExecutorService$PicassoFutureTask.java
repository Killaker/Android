package com.squareup.picasso;

import java.util.concurrent.*;

private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask>
{
    private final BitmapHunter hunter;
    
    public PicassoFutureTask(final BitmapHunter hunter) {
        super(hunter, null);
        this.hunter = hunter;
    }
    
    @Override
    public int compareTo(final PicassoFutureTask picassoFutureTask) {
        final Picasso.Priority priority = this.hunter.getPriority();
        final Picasso.Priority priority2 = picassoFutureTask.hunter.getPriority();
        if (priority == priority2) {
            return this.hunter.sequence - picassoFutureTask.hunter.sequence;
        }
        return priority2.ordinal() - priority.ordinal();
    }
}
