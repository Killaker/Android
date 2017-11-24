package android.support.v7.widget;

import java.util.*;

class DefaultItemAnimator$1 implements Runnable {
    final /* synthetic */ ArrayList val$moves;
    
    @Override
    public void run() {
        for (final MoveInfo moveInfo : this.val$moves) {
            DefaultItemAnimator.access$000(DefaultItemAnimator.this, moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
        }
        this.val$moves.clear();
        DefaultItemAnimator.access$100(DefaultItemAnimator.this).remove(this.val$moves);
    }
}