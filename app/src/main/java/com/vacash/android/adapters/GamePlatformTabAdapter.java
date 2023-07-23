package com.vacash.android.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vacash.android.GameTab;

public class GamePlatformTabAdapter extends FragmentStateAdapter {

    public GamePlatformTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return GameTab.newInstance(1, "Mobile");
            case 1: return GameTab.newInstance(2, "PC");
            case 2: return GameTab.newInstance(3, "Console");
        }

        return GameTab.newInstance(1, "Mobile");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
