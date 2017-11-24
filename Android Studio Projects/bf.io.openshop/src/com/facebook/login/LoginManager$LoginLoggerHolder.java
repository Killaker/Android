package com.facebook.login;

import android.content.*;
import com.facebook.*;

private static class LoginLoggerHolder
{
    private static volatile LoginLogger logger;
    
    private static LoginLogger getLogger(Context applicationContext) {
        // monitorenter(LoginLoggerHolder.class)
        Label_0018: {
            if (applicationContext == null) {
                break Label_0018;
            }
        Label_0013_Outer:
            while (true) {
                Label_0025: {
                    if (applicationContext != null) {
                        break Label_0025;
                    }
                    LoginLogger logger = null;
                    Label_0045_Outer:Block_3_Outer:
                    while (true) {
                        return logger;
                        try {
                            applicationContext = FacebookSdk.getApplicationContext();
                            continue Label_0013_Outer;
                            while (true) {
                                while (true) {
                                    logger = LoginLoggerHolder.logger;
                                    continue Label_0045_Outer;
                                    LoginLoggerHolder.logger = new LoginLogger(applicationContext, FacebookSdk.getApplicationId());
                                    continue Block_3_Outer;
                                }
                                continue;
                            }
                        }
                        // iftrue(Label_0045:, LoginLoggerHolder.logger != null)
                        finally {
                        }
                        // monitorexit(LoginLoggerHolder.class)
                        break;
                    }
                }
                break;
            }
        }
    }
    // monitorexit(LoginLoggerHolder.class)
}
