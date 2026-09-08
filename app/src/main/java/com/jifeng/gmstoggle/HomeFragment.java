package com.jifeng.gmstoggle;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class HomeFragment extends Fragment {

    private static final ComponentName GMS_SETTINGS = new ComponentName(
            GmsController.SECURITY_CENTER_PACKAGE,
            GmsController.GMS_SETTINGS_ACTIVITY
    );

    private MaterialTextView tvStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvStatus = view.findViewById(R.id.tv_status);
        MaterialButton btnOpen = view.findViewById(R.id.btn_open_settings);
        btnOpen.setOnClickListener(v -> openGmsSettings());
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        boolean installed = GmsController.isInstalled(requireContext());
        String text;
        if (!installed) {
            text = "Google Play 服务未安装";
        } else {
            text = "Google Play 服务" + (GmsController.isEnabled(requireContext()) ? "已启用" : "未启用");
        }
        tvStatus.setText(text);
    }

    private void openGmsSettings() {
        Intent intent = new Intent();
        intent.setComponent(GMS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Throwable e) {
            try {
                Intent fallback = new Intent(Settings.ACTION_SETTINGS);
                startActivity(fallback);
                Toast.makeText(requireContext(), "无法直接打开 GMS 页面，已打开系统设置", Toast.LENGTH_LONG).show();
            } catch (Throwable ignored) {
                Toast.makeText(requireContext(), "无法打开 GMS 设置页面：" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
