package com.google.gson.internal;

import java.io.*;
import java.lang.reflect.*;

private static final class WildcardTypeImpl implements WildcardType, Serializable
{
    private static final long serialVersionUID;
    private final Type lowerBound;
    private final Type upperBound;
    
    public WildcardTypeImpl(final Type[] array, final Type[] array2) {
        int n = 1;
        boolean b;
        if (array2.length <= n) {
            b = (n != 0);
        }
        else {
            b = false;
        }
        $Gson$Preconditions.checkArgument(b);
        boolean b2;
        if (array.length == n) {
            b2 = (n != 0);
        }
        else {
            b2 = false;
        }
        $Gson$Preconditions.checkArgument(b2);
        if (array2.length == n) {
            $Gson$Preconditions.checkNotNull(array2[0]);
            $Gson$Types.access$000(array2[0]);
            if (array[0] != Object.class) {
                n = 0;
            }
            $Gson$Preconditions.checkArgument(n != 0);
            this.lowerBound = $Gson$Types.canonicalize(array2[0]);
            this.upperBound = Object.class;
            return;
        }
        $Gson$Preconditions.checkNotNull(array[0]);
        $Gson$Types.access$000(array[0]);
        this.lowerBound = null;
        this.upperBound = $Gson$Types.canonicalize(array[0]);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof WildcardType && $Gson$Types.equals(this, (Type)o);
    }
    
    @Override
    public Type[] getLowerBounds() {
        if (this.lowerBound != null) {
            return new Type[] { this.lowerBound };
        }
        return $Gson$Types.EMPTY_TYPE_ARRAY;
    }
    
    @Override
    public Type[] getUpperBounds() {
        return new Type[] { this.upperBound };
    }
    
    @Override
    public int hashCode() {
        int n;
        if (this.lowerBound != null) {
            n = 31 + this.lowerBound.hashCode();
        }
        else {
            n = 1;
        }
        return n ^ 31 + this.upperBound.hashCode();
    }
    
    @Override
    public String toString() {
        if (this.lowerBound != null) {
            return "? super " + $Gson$Types.typeToString(this.lowerBound);
        }
        if (this.upperBound == Object.class) {
            return "?";
        }
        return "? extends " + $Gson$Types.typeToString(this.upperBound);
    }
}
