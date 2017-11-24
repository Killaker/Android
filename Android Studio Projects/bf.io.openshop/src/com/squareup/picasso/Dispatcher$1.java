package com.squareup.picasso;

class Dispatcher$1 implements Runnable {
    @Override
    public void run() {
        Dispatcher.this.receiver.unregister();
    }
}