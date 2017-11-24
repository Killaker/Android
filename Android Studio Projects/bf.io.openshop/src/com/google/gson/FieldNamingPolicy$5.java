package com.google.gson;

import java.lang.reflect.*;
import java.util.*;

enum FieldNamingPolicy$5
{
    @Override
    public String translateName(final Field field) {
        return FieldNamingPolicy.access$200(field.getName(), "-").toLowerCase(Locale.ENGLISH);
    }
}