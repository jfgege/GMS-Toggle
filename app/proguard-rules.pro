# GMS Toggle currently does not enable minification-specific rules.
# Keep Shizuku reflection entry points and the TileService intact.
-keep class rikka.shizuku.** { *; }
-keep class com.jifeng.gmstoggle.** { *; }
