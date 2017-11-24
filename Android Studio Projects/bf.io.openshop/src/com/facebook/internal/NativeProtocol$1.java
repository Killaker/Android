package com.facebook.internal;

import java.util.*;
import android.content.*;
import android.os.*;
import android.content.pm.*;

static final class NativeProtocol$1 implements Runnable {
    @Override
    public void run() {
        try {
            final Iterator<NativeAppInfo> iterator = NativeProtocol.access$500().iterator();
            while (iterator.hasNext()) {
                iterator.next().fetchAvailableVersions(true);
            }
        }
        finally {
            NativeProtocol.access$700().set(false);
        }
        NativeProtocol.access$700().set(false);
    }
}