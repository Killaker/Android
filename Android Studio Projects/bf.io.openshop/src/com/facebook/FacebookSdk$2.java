package com.facebook;

import java.util.concurrent.*;
import java.io.*;

static final class FacebookSdk$2 implements Callable<File> {
    @Override
    public File call() throws Exception {
        return FacebookSdk.access$000().getCacheDir();
    }
}