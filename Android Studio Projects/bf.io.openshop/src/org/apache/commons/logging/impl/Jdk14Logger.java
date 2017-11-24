package org.apache.commons.logging.impl;

import org.apache.commons.logging.*;
import java.io.*;
import java.util.logging.*;

@Deprecated
public class Jdk14Logger implements Log, Serializable
{
    protected static final Level dummyLevel;
    protected transient Logger logger;
    protected String name;
    
    static {
        dummyLevel = null;
    }
    
    public Jdk14Logger(final String s) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void debug(final Object o) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void debug(final Object o, final Throwable t) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void error(final Object o) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void error(final Object o, final Throwable t) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void fatal(final Object o) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void fatal(final Object o, final Throwable t) {
        throw new RuntimeException("Stub!");
    }
    
    public Logger getLogger() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void info(final Object o) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void info(final Object o, final Throwable t) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean isDebugEnabled() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean isErrorEnabled() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean isFatalEnabled() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean isInfoEnabled() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean isTraceEnabled() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public boolean isWarnEnabled() {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void trace(final Object o) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void trace(final Object o, final Throwable t) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void warn(final Object o) {
        throw new RuntimeException("Stub!");
    }
    
    @Override
    public void warn(final Object o, final Throwable t) {
        throw new RuntimeException("Stub!");
    }
}
