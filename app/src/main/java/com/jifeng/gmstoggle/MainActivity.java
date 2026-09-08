package com.jifeng.gmstoggle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());
            int top = systemBars.top;
            int bottom = Math.max(systemBars.bottom, ime.bottom);

            v.setPadding(0, top, 0, bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        if (savedInstanceState == null) {
            HomeFragment home = new HomeFragment();
            TileFragment tile = new TileFragment();
            AboutFragment about = new AboutFragment();
            getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .add(R.id.fragment_container, about, "about").hide(about)
                    .add(R.id.fragment_container, tile, "tile").hide(tile)
                    .add(R.id.fragment_container, home, "home")
                    .commit();
            activeFragment = home;
        } else {
            activeFragment = getSupportFragmentManager().findFragmentByTag(
                    savedInstanceState.getString("active_tag", "home"));
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                switchFragment("home");
                return true;
            } else if (id == R.id.nav_tile) {
                switchFragment("tile");
                return true;
            } else if (id == R.id.nav_about) {
                switchFragment("about");
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (activeFragment != null && activeFragment.getTag() != null) {
            outState.putString("active_tag", activeFragment.getTag());
        }
    }

    private void switchFragment(String tag) {
        Fragment target = getSupportFragmentManager().findFragmentByTag(tag);
        if (target == null || target == activeFragment) return;
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_NONE)
                .hide(activeFragment)
                .show(target)
                .commit();
        activeFragment = target;
    }
}
