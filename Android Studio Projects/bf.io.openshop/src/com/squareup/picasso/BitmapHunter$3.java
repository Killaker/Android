package com.squareup.picasso;

static final class BitmapHunter$3 implements Runnable {
    final /* synthetic */ RuntimeException val$e;
    final /* synthetic */ Transformation val$transformation;
    
    @Override
    public void run() {
        throw new RuntimeException("Transformation " + this.val$transformation.key() + " crashed with exception.", this.val$e);
    }
}