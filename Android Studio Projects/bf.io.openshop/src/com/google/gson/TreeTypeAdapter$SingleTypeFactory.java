package com.google.gson;

import com.google.gson.reflect.*;
import com.google.gson.internal.*;

private static class SingleTypeFactory implements TypeAdapterFactory
{
    private final JsonDeserializer<?> deserializer;
    private final TypeToken<?> exactType;
    private final Class<?> hierarchyType;
    private final boolean matchRawType;
    private final JsonSerializer<?> serializer;
    
    private SingleTypeFactory(final Object o, final TypeToken<?> exactType, final boolean matchRawType, final Class<?> hierarchyType) {
        JsonSerializer<?> serializer;
        if (o instanceof JsonSerializer) {
            serializer = (JsonSerializer<?>)o;
        }
        else {
            serializer = null;
        }
        this.serializer = serializer;
        JsonDeserializer<?> deserializer;
        if (o instanceof JsonDeserializer) {
            deserializer = (JsonDeserializer<?>)o;
        }
        else {
            deserializer = null;
        }
        this.deserializer = deserializer;
        $Gson$Preconditions.checkArgument(this.serializer != null || this.deserializer != null);
        this.exactType = exactType;
        this.matchRawType = matchRawType;
        this.hierarchyType = hierarchyType;
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        int assignable;
        if (this.exactType != null) {
            if (this.exactType.equals(typeToken) || (this.matchRawType && this.exactType.getType() == typeToken.getRawType())) {
                assignable = 1;
            }
            else {
                assignable = 0;
            }
        }
        else {
            assignable = (this.hierarchyType.isAssignableFrom(typeToken.getRawType()) ? 1 : 0);
        }
        if (assignable != 0) {
            return new TreeTypeAdapter<T>(this.serializer, this.deserializer, gson, typeToken, this, null);
        }
        return null;
    }
}
