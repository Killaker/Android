package com.squareup.picasso;

static final class BitmapHunter$6 implements Runnable {
    final /* synthetic */ Transformation val$transformation;
    
    @Override
    public void run() {
        throw new IllegalStateException("Transformation " + this.val$transformation.key() + " mutated input Bitmap but failed to recycle the original.");
    }
}