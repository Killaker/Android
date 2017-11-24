package com.squareup.picasso;

static final class BitmapHunter$1 extends ThreadLocal<StringBuilder> {
    @Override
    protected StringBuilder initialValue() {
        return new StringBuilder("Picasso-");
    }
}