package com.google.android.gms.internal;

import java.lang.reflect.*;

public final class zzsv
{
    private static void zza(final String s, final Object o, final StringBuffer sb, final StringBuffer sb2) throws IllegalAccessException, InvocationTargetException {
        if (o != null) {
            Label_0435: {
                if (!(o instanceof zzsu)) {
                    break Label_0435;
                }
                final int length = sb.length();
                if (s != null) {
                    sb2.append(sb).append(zzgP(s)).append(" <\n");
                    sb.append("  ");
                }
                final Class<?> class1 = o.getClass();
                for (final Field field : class1.getFields()) {
                    final int modifiers = field.getModifiers();
                    final String name = field.getName();
                    if (!"cachedSize".equals(name) && (modifiers & 0x1) == 0x1 && (modifiers & 0x8) != 0x8 && !name.startsWith("_") && !name.endsWith("_")) {
                        final Class<?> type = field.getType();
                        final Object value = field.get(o);
                        if (type.isArray()) {
                            if (type.getComponentType() == Byte.TYPE) {
                                zza(name, value, sb, sb2);
                            }
                            else {
                                int length3;
                                if (value == null) {
                                    length3 = 0;
                                }
                                else {
                                    length3 = Array.getLength(value);
                                }
                                for (int j = 0; j < length3; ++j) {
                                    zza(name, Array.get(value, j), sb, sb2);
                                }
                            }
                        }
                        else {
                            zza(name, value, sb, sb2);
                        }
                    }
                }
                final Method[] methods = class1.getMethods();
                final int length4 = methods.length;
                int n = 0;
            Label_0356_Outer:
                while (true) {
                    Label_0413: {
                        if (n >= length4) {
                            break Label_0413;
                        }
                        final String name2 = methods[n].getName();
                    Label_0490_Outer:
                        while (true) {
                            if (!name2.startsWith("set")) {
                                break Label_0356;
                            }
                            final String substring = name2.substring(3);
                            try {
                                if (class1.getMethod("has" + substring, (Class<?>[])new Class[0]).invoke(o, new Object[0])) {
                                    try {
                                        zza(substring, class1.getMethod("get" + substring, (Class<?>[])new Class[0]).invoke(o, new Object[0]), sb, sb2);
                                        break Label_0356;
                                        // iftrue(Label_0498:, !o instanceof String)
                                        // iftrue(Label_0519:, !o instanceof byte[])
                                        // iftrue(Label_0004:, s == null)
                                    Block_19:
                                        while (true) {
                                        Label_0490:
                                            while (true) {
                                                while (true) {
                                                    sb2.append("\"").append(zzbZ((String)o)).append("\"");
                                                    break Label_0490;
                                                    sb.setLength(length);
                                                    sb2.append(sb).append(">\n");
                                                    return;
                                                    sb2.append(sb).append(zzgP(s)).append(": ");
                                                    continue Label_0490_Outer;
                                                }
                                                sb2.append("\n");
                                                return;
                                                Label_0498: {
                                                    break Block_19;
                                                }
                                                Label_0519:
                                                sb2.append(o);
                                                continue Label_0490;
                                            }
                                            continue;
                                        }
                                        zza((byte[])o, sb2);
                                    }
                                    catch (NoSuchMethodException ex) {}
                                }
                                ++n;
                                continue Label_0356_Outer;
                            }
                            catch (NoSuchMethodException ex2) {
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        Label_0004:;
    }
    
    private static void zza(final byte[] array, final StringBuffer sb) {
        if (array == null) {
            sb.append("\"\"");
            return;
        }
        sb.append('\"');
        for (int i = 0; i < array.length; ++i) {
            final int n = 0xFF & array[i];
            if (n == 92 || n == 34) {
                sb.append('\\').append((char)n);
            }
            else if (n >= 32 && n < 127) {
                sb.append((char)n);
            }
            else {
                sb.append(String.format("\\%03o", n));
            }
        }
        sb.append('\"');
    }
    
    private static String zzbZ(String string) {
        if (!string.startsWith("http") && string.length() > 200) {
            string = string.substring(0, 200) + "[...]";
        }
        return zzcU(string);
    }
    
    private static String zzcU(final String s) {
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= ' ' && char1 <= '~' && char1 != '\"' && char1 != '\'') {
                sb.append(char1);
            }
            else {
                sb.append(String.format("\\u%04x", (int)char1));
            }
        }
        return sb.toString();
    }
    
    public static <T extends zzsu> String zzf(final T t) {
        if (t == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        try {
            zza(null, t, new StringBuffer(), sb);
            return sb.toString();
        }
        catch (IllegalAccessException ex) {
            return "Error printing proto: " + ex.getMessage();
        }
        catch (InvocationTargetException ex2) {
            return "Error printing proto: " + ex2.getMessage();
        }
    }
    
    private static String zzgP(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(char1));
            }
            else if (Character.isUpperCase(char1)) {
                sb.append('_').append(Character.toLowerCase(char1));
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
}
