package com.squareup.picasso;

enum PicassoKind
{
    FULL(2, -1, -1), 
    MICRO(3, 96, 96), 
    MINI(1, 512, 384);
    
    final int androidKind;
    final int height;
    final int width;
    
    private PicassoKind(final int androidKind, final int width, final int height) {
        this.androidKind = androidKind;
        this.width = width;
        this.height = height;
    }
}
