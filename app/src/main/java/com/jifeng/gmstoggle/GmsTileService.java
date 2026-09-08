package com.jifeng.gmstoggle;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

/**
 * HyperOS Quick Settings tile that opens Xiaomi's built-in GMS settings page.
 *
 * Component confirmed for the target HyperOS setup:
 * com.miui.securitycenter/com.miui.googlebase.ui.GmsCoreSettings
 */
public class GmsTileService extends TileService {
    private static final ComponentName GMS_SETTINGS = new ComponentName(
            GmsController.SECURITY_CENTER_PACKAGE,
            GmsController.GMS_SETTINGS_ACTIVITY
    );

    @Override
    public void onStartListening() {
        super.onStartListening();
        refresh();
    }

    @Override
    public void onClick() {
        super.onClick();
        openGmsSettings();
    }

    private void refresh() {
        Tile tile = getQsTile();
        if (tile == null) return;

        boolean installed = GmsController.isInstalled(this);
        tile.setIcon(Icon.createWithResource(this, R.drawable.ic_gms));
        tile.setLabel(getString(R.string.tile_label));

        if (!installed) {
            tile.setState(Tile.STATE_UNAVAILABLE);
        } else {
            tile.setState(GmsController.isEnabled(this)
                    ? Tile.STATE_INACTIVE
                    //? Tile.STATE_ACTIVE
                    : Tile.STATE_INACTIVE);
        }
        tile.updateTile();
    }

    private void openGmsSettings() {
        Intent intent = new Intent();
        intent.setComponent(GMS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            if (Build.VERSION.SDK_INT >= 34) {
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        this,
                        1001,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );
                startActivityAndCollapse(pendingIntent);
            } else {
                startActivityAndCollapse(intent);
            }
        } catch (Throwable e) {
            // Fallback: open Android Settings if Xiaomi removes/renames the component.
            try {
                Intent fallback = new Intent(Settings.ACTION_SETTINGS);
                fallback.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityAndCollapse(fallback);
                Toast.makeText(this, "无法直接打开 GMS 页面，已打开系统设置", Toast.LENGTH_SHORT).show();
            } catch (Throwable ignored) {
                Toast.makeText(this, "无法打开 GMS 设置页面", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
