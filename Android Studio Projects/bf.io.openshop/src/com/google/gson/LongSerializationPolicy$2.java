package com.google.gson;

enum LongSerializationPolicy$2
{
    @Override
    public JsonElement serialize(final Long n) {
        return new JsonPrimitive(String.valueOf(n));
    }
}