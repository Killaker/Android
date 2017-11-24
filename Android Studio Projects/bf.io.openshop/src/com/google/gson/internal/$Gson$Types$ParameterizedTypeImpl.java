package com.google.gson.internal;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable
{
    private static final long serialVersionUID;
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;
    
    public ParameterizedTypeImpl(final Type type, final Type type2, final Type... array) {
        if (type2 instanceof Class) {
            final Class clazz = (Class)type2;
            int n;
            if (Modifier.isStatic(clazz.getModifiers()) || clazz.getEnclosingClass() == null) {
                n = 1;
            }
            else {
                n = 0;
            }
            boolean b = false;
            Label_0054: {
                if (type == null) {
                    b = false;
                    if (n == 0) {
                        break Label_0054;
                    }
                }
                b = true;
            }
            $Gson$Preconditions.checkArgument(b);
        }
        Type canonicalize;
        if (type == null) {
            canonicalize = null;
        }
        else {
            canonicalize = $Gson$Types.canonicalize(type);
        }
        this.ownerType = canonicalize;
        this.rawType = $Gson$Types.canonicalize(type2);
        this.typeArguments = array.clone();
        for (int i = 0; i < this.typeArguments.length; ++i) {
            $Gson$Preconditions.checkNotNull(this.typeArguments[i]);
            $Gson$Types.access$000(this.typeArguments[i]);
            this.typeArguments[i] = $Gson$Types.canonicalize(this.typeArguments[i]);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ParameterizedType && $Gson$Types.equals(this, (Type)o);
    }
    
    @Override
    public Type[] getActualTypeArguments() {
        return this.typeArguments.clone();
    }
    
    @Override
    public Type getOwnerType() {
        return this.ownerType;
    }
    
    @Override
    public Type getRawType() {
        return this.rawType;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ $Gson$Types.access$100(this.ownerType);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(30 * (1 + this.typeArguments.length));
        sb.append($Gson$Types.typeToString(this.rawType));
        if (this.typeArguments.length == 0) {
            return sb.toString();
        }
        sb.append("<").append($Gson$Types.typeToString(this.typeArguments[0]));
        for (int i = 1; i < this.typeArguments.length; ++i) {
            sb.append(", ").append($Gson$Types.typeToString(this.typeArguments[i]));
        }
        return sb.append(">").toString();
    }
}
