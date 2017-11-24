package com.google.gson;

import java.lang.reflect.*;

enum FieldNamingPolicy$1
{
    @Override
    public String translateName(final Field field) {
        return field.getName();
    }
}