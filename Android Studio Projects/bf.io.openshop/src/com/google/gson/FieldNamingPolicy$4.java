package com.google.gson;

import java.lang.reflect.*;
import java.util.*;

enum FieldNamingPolicy$4
{
    @Override
    public String translateName(final Field field) {
        return FieldNamingPolicy.access$200(field.getName(), "_").toLowerCase(Locale.ENGLISH);
    }
}