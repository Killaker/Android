package com.squareup.picasso;

import java.io.*;

static class ContentLengthException extends IOException
{
    public ContentLengthException(final String s) {
        super(s);
    }
}
