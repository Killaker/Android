package com.google.gson;

enum LongSerializationPolicy$1
{
    @Override
    public JsonElement serialize(final Long n) {
        return new JsonPrimitive(n);
    }
}