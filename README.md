# GMS Toggle

一个面向小米 HyperOS 的 Google 移动服务（GMS）快捷开关。

通过系统自带的 GMS 设置页面快速管理 Google 移动服务，无需 Root、Shizuku 或 Xposed。

## 功能

* 控制中心 GMS 快捷磁贴
* 快速进入系统 GMS 设置
* Material Design 3
* 动态取色
* Edge-to-Edge

## 工作原理

GMS Toggle 不直接修改系统设置，也不依赖 Root、Shizuku 或 Xposed。

应用通过调用 HyperOS 系统提供的 GMS 设置页面：

```text
com.miui.securitycenter/com.miui.googlebase.ui.GmsCoreSettings
```

具体功能和可用性取决于设备系统版本及厂商实现。

## 一些说明

在控制中心添加磁贴后，可能会偶发点击磁贴无反应的情况，实测发现部分其他开源项目同样有此问题，进入一次应用可暂时解决

## 测试环境

* Xiaomi 15 Pro 
* HyperOS 3.0.305.0.WOBCNXM.C09
* Android 16

## 构建

使用 Android Studio 打开项目，等待 Gradle 同步完成后即可构建。

```bash
./gradlew assembleDebug
```

## 开源协议

本项目采用 **GNU Affero General Public License v3.0（AGPL-3.0）** 开源。

完整协议内容见 [LICENSE](LICENSE)。

项目中包含的第三方资源仍遵循其原始许可证，具体信息见 [THIRD\_PARTY\_NOTICES.md](THIRD_PARTY_NOTICES.md)。

## 第三方资源

本项目部分资源来自其他开源项目。

相关版权声明、来源及许可证信息见：

[THIRD\_PARTY\_NOTICES.md](THIRD_PARTY_NOTICES.md)

## 免责声明

GMS Toggle 是独立的开源项目，与 Xiaomi、MIUI、HyperOS 或 Google 无隶属、合作或官方授权关系。

项目名称及相关商标归其各自所有者所有。

## 贡献

欢迎提交 Issue 和 Pull Request。

提交代码前请确保新增内容符合本项目的开源协议及相关第三方许可证要求。

