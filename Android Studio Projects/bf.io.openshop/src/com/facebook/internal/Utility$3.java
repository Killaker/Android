package com.facebook.internal;

import java.io.*;
import java.util.regex.*;

static final class Utility$3 implements FilenameFilter {
    @Override
    public boolean accept(final File file, final String s) {
        return Pattern.matches("cpu[0-9]+", s);
    }
}