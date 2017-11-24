package com.facebook.internal;

class WorkQueue$1 implements Runnable {
    final /* synthetic */ WorkNode val$node;
    
    @Override
    public void run() {
        try {
            this.val$node.getCallback().run();
        }
        finally {
            WorkQueue.access$000(WorkQueue.this, this.val$node);
        }
    }
}