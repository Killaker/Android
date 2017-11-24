package com.google.gson.internal;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public final class $Gson$Types
{
    static final Type[] EMPTY_TYPE_ARRAY;
    
    static {
        EMPTY_TYPE_ARRAY = new Type[0];
    }
    
    private $Gson$Types() {
        throw new UnsupportedOperationException();
    }
    
    public static GenericArrayType arrayOf(final Type type) {
        return new GenericArrayTypeImpl(type);
    }
    
    public static Type canonicalize(final Type type) {
        if (type instanceof Class) {
            final Class clazz = (Class)type;
            Serializable s;
            if (clazz.isArray()) {
                s = new GenericArrayTypeImpl(canonicalize(clazz.getComponentType()));
            }
            else {
                s = clazz;
            }
            return (GenericArrayTypeImpl)s;
        }
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType)type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType)type).getGenericComponentType());
        }
        if (type instanceof WildcardType) {
            final WildcardType wildcardType = (WildcardType)type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
        return type;
    }
    
    private static void checkNotPrimitive(final Type type) {
        $Gson$Preconditions.checkArgument(!(type instanceof Class) || !((Class)type).isPrimitive());
    }
    
    private static Class<?> declaringClassOf(final TypeVariable<?> typeVariable) {
        final Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class<?>)genericDeclaration;
        }
        return null;
    }
    
    static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static boolean equals(final Type type, final Type type2) {
        boolean b = true;
        boolean b2;
        if (type == type2) {
            b2 = b;
        }
        else {
            if (type instanceof Class) {
                return type.equals(type2);
            }
            if (type instanceof ParameterizedType) {
                final boolean b3 = type2 instanceof ParameterizedType;
                b2 = false;
                if (b3) {
                    final ParameterizedType parameterizedType = (ParameterizedType)type;
                    final ParameterizedType parameterizedType2 = (ParameterizedType)type2;
                    if (!equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                        b = false;
                    }
                    return b;
                }
            }
            else if (type instanceof GenericArrayType) {
                final boolean b4 = type2 instanceof GenericArrayType;
                b2 = false;
                if (b4) {
                    return equals(((GenericArrayType)type).getGenericComponentType(), ((GenericArrayType)type2).getGenericComponentType());
                }
            }
            else if (type instanceof WildcardType) {
                final boolean b5 = type2 instanceof WildcardType;
                b2 = false;
                if (b5) {
                    final WildcardType wildcardType = (WildcardType)type;
                    final WildcardType wildcardType2 = (WildcardType)type2;
                    if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                        b = false;
                    }
                    return b;
                }
            }
            else {
                final boolean b6 = type instanceof TypeVariable;
                b2 = false;
                if (b6) {
                    final boolean b7 = type2 instanceof TypeVariable;
                    b2 = false;
                    if (b7) {
                        final TypeVariable typeVariable = (TypeVariable)type;
                        final TypeVariable typeVariable2 = (TypeVariable)type2;
                        if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || !typeVariable.getName().equals(typeVariable2.getName())) {
                            b = false;
                        }
                        return b;
                    }
                }
            }
        }
        return b2;
    }
    
    public static Type getArrayComponentType(final Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType)type).getGenericComponentType();
        }
        return ((Class)type).getComponentType();
    }
    
    public static Type getCollectionElementType(final Type type, final Class<?> clazz) {
        Type supertype = getSupertype(type, clazz, Collection.class);
        if (supertype instanceof WildcardType) {
            supertype = ((WildcardType)supertype).getUpperBounds()[0];
        }
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType)supertype).getActualTypeArguments()[0];
        }
        return Object.class;
    }
    
    static Type getGenericSupertype(final Type type, Class<?> clazz, final Class<?> clazz2) {
        if (clazz2 == clazz) {
            return type;
        }
        if (clazz2.isInterface()) {
            final Class<?>[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i] == clazz2) {
                    return clazz.getGenericInterfaces()[i];
                }
                if (clazz2.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(clazz.getGenericInterfaces()[i], interfaces[i], clazz2);
                }
            }
        }
        if (!clazz.isInterface()) {
            while (clazz != Object.class) {
                final Class<? super Object> superclass = clazz.getSuperclass();
                if (superclass == clazz2) {
                    return clazz.getGenericSuperclass();
                }
                if (clazz2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(clazz.getGenericSuperclass(), superclass, clazz2);
                }
                clazz = (Class<Object>)superclass;
            }
        }
        return clazz2;
    }
    
    public static Type[] getMapKeyAndValueTypes(final Type type, final Class<?> clazz) {
        if (type == Properties.class) {
            return new Type[] { String.class, String.class };
        }
        final Type supertype = getSupertype(type, clazz, Map.class);
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType)supertype).getActualTypeArguments();
        }
        return new Type[] { Object.class, Object.class };
    }
    
    public static Class<?> getRawType(final Type type) {
        if (type instanceof Class) {
            return (Class<?>)type;
        }
        if (type instanceof ParameterizedType) {
            final Type rawType = ((ParameterizedType)type).getRawType();
            $Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class<?>)rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType)type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType)type).getUpperBounds()[0]);
        }
        String name;
        if (type == null) {
            name = "null";
        }
        else {
            name = type.getClass().getName();
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + name);
    }
    
    static Type getSupertype(final Type type, final Class<?> clazz, final Class<?> clazz2) {
        $Gson$Preconditions.checkArgument(clazz2.isAssignableFrom(clazz));
        return resolve(type, clazz, getGenericSupertype(type, clazz, clazz2));
    }
    
    private static int hashCodeOrZero(final Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }
    
    private static int indexOf(final Object[] array, final Object o) {
        for (int i = 0; i < array.length; ++i) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
    
    public static ParameterizedType newParameterizedTypeWithOwner(final Type type, final Type type2, final Type... array) {
        return new ParameterizedTypeImpl(type, type2, array);
    }
    
    public static Type resolve(final Type type, final Class<?> clazz, Type resolveTypeVariable) {
        while (resolveTypeVariable instanceof TypeVariable) {
            final TypeVariable typeVariable = (TypeVariable)resolveTypeVariable;
            resolveTypeVariable = resolveTypeVariable(type, clazz, typeVariable);
            if (resolveTypeVariable == typeVariable) {
                return resolveTypeVariable;
            }
        }
        if (resolveTypeVariable instanceof Class && ((Class)resolveTypeVariable).isArray()) {
            Object array = resolveTypeVariable;
            final Class componentType = ((Class)array).getComponentType();
            final Type resolve = resolve(type, clazz, componentType);
            if (componentType != resolve) {
                array = arrayOf(resolve);
            }
            return (Type)array;
        }
        if (resolveTypeVariable instanceof GenericArrayType) {
            final Type type2 = resolveTypeVariable;
            final Type genericComponentType = ((GenericArrayType)type2).getGenericComponentType();
            final Type resolve2 = resolve(type, clazz, genericComponentType);
            if (genericComponentType != resolve2) {
                return arrayOf(resolve2);
            }
            return type2;
        }
        else if (resolveTypeVariable instanceof ParameterizedType) {
            final Type type2 = resolveTypeVariable;
            final Type ownerType = ((ParameterizedType)type2).getOwnerType();
            final Type resolve3 = resolve(type, clazz, ownerType);
            int n;
            if (resolve3 != ownerType) {
                n = 1;
            }
            else {
                n = 0;
            }
            Type[] actualTypeArguments = ((ParameterizedType)type2).getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; ++i) {
                final Type resolve4 = resolve(type, clazz, actualTypeArguments[i]);
                if (resolve4 != actualTypeArguments[i]) {
                    if (n == 0) {
                        actualTypeArguments = actualTypeArguments.clone();
                        n = 1;
                    }
                    actualTypeArguments[i] = resolve4;
                }
            }
            if (n != 0) {
                return newParameterizedTypeWithOwner(resolve3, ((ParameterizedType)type2).getRawType(), actualTypeArguments);
            }
            return type2;
        }
        else {
            if (!(resolveTypeVariable instanceof WildcardType)) {
                return resolveTypeVariable;
            }
            final Type type2 = resolveTypeVariable;
            final Type[] lowerBounds = ((WildcardType)type2).getLowerBounds();
            final Type[] upperBounds = ((WildcardType)type2).getUpperBounds();
            if (lowerBounds.length == 1) {
                final Type resolve5 = resolve(type, clazz, lowerBounds[0]);
                if (resolve5 != lowerBounds[0]) {
                    return supertypeOf(resolve5);
                }
                return type2;
            }
            else {
                if (upperBounds.length != 1) {
                    return type2;
                }
                final Type resolve6 = resolve(type, clazz, upperBounds[0]);
                if (resolve6 != upperBounds[0]) {
                    return subtypeOf(resolve6);
                }
                return type2;
            }
        }
    }
    
    static Type resolveTypeVariable(final Type type, final Class<?> clazz, final TypeVariable<?> typeVariable) {
        final Class<?> declaringClass = declaringClassOf(typeVariable);
        if (declaringClass != null) {
            final Type genericSupertype = getGenericSupertype(type, clazz, declaringClass);
            if (genericSupertype instanceof ParameterizedType) {
                return ((ParameterizedType)genericSupertype).getActualTypeArguments()[indexOf(declaringClass.getTypeParameters(), typeVariable)];
            }
        }
        return typeVariable;
    }
    
    public static WildcardType subtypeOf(final Type type) {
        return new WildcardTypeImpl(new Type[] { type }, $Gson$Types.EMPTY_TYPE_ARRAY);
    }
    
    public static WildcardType supertypeOf(final Type type) {
        return new WildcardTypeImpl(new Type[] { Object.class }, new Type[] { type });
    }
    
    public static String typeToString(final Type type) {
        if (type instanceof Class) {
            return ((Class)type).getName();
        }
        return type.toString();
    }
    
    private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable
    {
        private static final long serialVersionUID;
        private final Type componentType;
        
        public GenericArrayTypeImpl(final Type type) {
            this.componentType = $Gson$Types.canonicalize(type);
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof GenericArrayType && $Gson$Types.equals(this, (Type)o);
        }
        
        @Override
        public Type getGenericComponentType() {
            return this.componentType;
        }
        
        @Override
        public int hashCode() {
            return this.componentType.hashCode();
        }
        
        @Override
        public String toString() {
            return $Gson$Types.typeToString(this.componentType) + "[]";
        }
    }
    
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
                checkNotPrimitive(this.typeArguments[i]);
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
            return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ hashCodeOrZero(this.ownerType);
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
                checkNotPrimitive(array2[0]);
                if (array[0] != Object.class) {
                    n = 0;
                }
                $Gson$Preconditions.checkArgument(n != 0);
                this.lowerBound = $Gson$Types.canonicalize(array2[0]);
                this.upperBound = Object.class;
                return;
            }
            $Gson$Preconditions.checkNotNull(array[0]);
            checkNotPrimitive(array[0]);
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
}
