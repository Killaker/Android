package com.google.gson;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public enum FieldNamingPolicy implements FieldNamingStrategy
{
    IDENTITY {
        @Override
        public String translateName(final Field field) {
            return field.getName();
        }
    }, 
    LOWER_CASE_WITH_DASHES {
        @Override
        public String translateName(final Field field) {
            return separateCamelCase(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    }, 
    LOWER_CASE_WITH_UNDERSCORES {
        @Override
        public String translateName(final Field field) {
            return separateCamelCase(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    }, 
    UPPER_CAMEL_CASE {
        @Override
        public String translateName(final Field field) {
            return upperCaseFirstLetter(field.getName());
        }
    }, 
    UPPER_CAMEL_CASE_WITH_SPACES {
        @Override
        public String translateName(final Field field) {
            return upperCaseFirstLetter(separateCamelCase(field.getName(), " "));
        }
    };
    
    private static String modifyString(final char c, final String s, final int n) {
        if (n < s.length()) {
            return c + s.substring(n);
        }
        return String.valueOf(c);
    }
    
    private static String separateCamelCase(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (Character.isUpperCase(char1) && sb.length() != 0) {
                sb.append(s2);
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    private static String upperCaseFirstLetter(String string) {
        final StringBuilder sb = new StringBuilder();
        int n;
        char c;
        for (n = 0, c = string.charAt(0); n < -1 + string.length() && !Character.isLetter(c); ++n, c = string.charAt(n)) {
            sb.append(c);
        }
        if (n == string.length()) {
            string = sb.toString();
        }
        else if (!Character.isUpperCase(c)) {
            return sb.append(modifyString(Character.toUpperCase(c), string, n + 1)).toString();
        }
        return string;
    }
}
