package com.squareup.picasso;

static final class BitmapHunter$5 implements Runnable {
    final /* synthetic */ Transformation val$transformation;
    
    @Override
    public void run() {
        throw new IllegalStateException("Transformation " + this.val$transformation.key() + " returned input Bitmap but recycled it.");
    }
}