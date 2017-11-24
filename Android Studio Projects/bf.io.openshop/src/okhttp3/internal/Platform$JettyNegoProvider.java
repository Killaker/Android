package okhttp3.internal;

import java.util.*;
import java.lang.reflect.*;

private static class JettyNegoProvider implements InvocationHandler
{
    private final List<String> protocols;
    private String selected;
    private boolean unsupported;
    
    public JettyNegoProvider(final List<String> protocols) {
        this.protocols = protocols;
    }
    
    @Override
    public Object invoke(final Object o, final Method method, Object[] empty_STRING_ARRAY) throws Throwable {
        final String name = method.getName();
        final Class<?> returnType = method.getReturnType();
        if (empty_STRING_ARRAY == null) {
            empty_STRING_ARRAY = Util.EMPTY_STRING_ARRAY;
        }
        if (name.equals("supports") && Boolean.TYPE == returnType) {
            return true;
        }
        if (name.equals("unsupported") && Void.TYPE == returnType) {
            this.unsupported = true;
            return null;
        }
        if (name.equals("protocols") && empty_STRING_ARRAY.length == 0) {
            return this.protocols;
        }
        if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && empty_STRING_ARRAY.length == 1 && empty_STRING_ARRAY[0] instanceof List) {
            final List list = (List)empty_STRING_ARRAY[0];
            for (int i = 0; i < list.size(); ++i) {
                if (this.protocols.contains(list.get(i))) {
                    return this.selected = list.get(i);
                }
            }
            return this.selected = this.protocols.get(0);
        }
        if ((name.equals("protocolSelected") || name.equals("selected")) && empty_STRING_ARRAY.length == 1) {
            this.selected = (String)empty_STRING_ARRAY[0];
            return null;
        }
        return method.invoke(this, empty_STRING_ARRAY);
    }
}
