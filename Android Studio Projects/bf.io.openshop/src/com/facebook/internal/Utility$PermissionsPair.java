package com.facebook.internal;

import java.util.*;

public static class PermissionsPair
{
    List<String> declinedPermissions;
    List<String> grantedPermissions;
    
    public PermissionsPair(final List<String> grantedPermissions, final List<String> declinedPermissions) {
        this.grantedPermissions = grantedPermissions;
        this.declinedPermissions = declinedPermissions;
    }
    
    public List<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }
    
    public List<String> getGrantedPermissions() {
        return this.grantedPermissions;
    }
}
