package com.jifeng.gmstoggle;

import android.content.Context;
import android.content.pm.PackageManager;

/** Read-only GMS status. The actual switch is controlled by Xiaomi's own GMS settings page. */
public final class GmsController {
    public static final String GMS_PACKAGE = "com.google.android.gms";
    public static final String SECURITY_CENTER_PACKAGE = "com.miui.securitycenter";
    public static final String GMS_SETTINGS_ACTIVITY = "com.miui.googlebase.ui.GmsCoreSettings";

    private GmsController() {}

    public static boolean isInstalled(Context context) {
        try {
            context.getPackageManager().getApplicationInfo(GMS_PACKAGE, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isEnabled(Context context) {
        try {
            int state = context.getPackageManager().getApplicationEnabledSetting(GMS_PACKAGE);
            return state != PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    && state != PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    && state != PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED;
        } catch (Throwable e) {
            try {
                return context.getPackageManager().getApplicationInfo(GMS_PACKAGE, 0).enabled;
            } catch (Throwable ignored) {
                return false;
            }
        }
    }
}
