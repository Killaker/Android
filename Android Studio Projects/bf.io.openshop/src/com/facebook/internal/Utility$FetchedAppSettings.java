package com.facebook.internal;

import java.util.*;

public static class FetchedAppSettings
{
    private Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap;
    private FacebookRequestErrorClassification errorClassification;
    private String nuxContent;
    private boolean nuxEnabled;
    private boolean supportsImplicitLogging;
    
    private FetchedAppSettings(final boolean supportsImplicitLogging, final String nuxContent, final boolean nuxEnabled, final Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap, final FacebookRequestErrorClassification errorClassification) {
        this.supportsImplicitLogging = supportsImplicitLogging;
        this.nuxContent = nuxContent;
        this.nuxEnabled = nuxEnabled;
        this.dialogConfigMap = dialogConfigMap;
        this.errorClassification = errorClassification;
    }
    
    public Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations() {
        return this.dialogConfigMap;
    }
    
    public FacebookRequestErrorClassification getErrorClassification() {
        return this.errorClassification;
    }
    
    public String getNuxContent() {
        return this.nuxContent;
    }
    
    public boolean getNuxEnabled() {
        return this.nuxEnabled;
    }
    
    public boolean supportsImplicitLogging() {
        return this.supportsImplicitLogging;
    }
}
