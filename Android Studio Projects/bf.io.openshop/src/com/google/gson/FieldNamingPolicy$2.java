package com.google.gson;

import java.lang.reflect.*;

enum FieldNamingPolicy$2
{
    @Override
    public String translateName(final Field field) {
        return FieldNamingPolicy.access$100(field.getName());
    }
}