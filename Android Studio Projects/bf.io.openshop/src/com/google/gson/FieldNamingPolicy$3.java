package com.google.gson;

import java.lang.reflect.*;

enum FieldNamingPolicy$3
{
    @Override
    public String translateName(final Field field) {
        return FieldNamingPolicy.access$100(FieldNamingPolicy.access$200(field.getName(), " "));
    }
}