package com.vacash.android.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vacash.android.ConsoleTab;
import com.vacash.android.MobileTab;
import com.vacash.android.PCTab;

public class GamePlatformTabAdapter extends FragmentStateAdapter {

    public GamePlatformTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new MobileTab();
            case 1: return new PCTab();
            case 2: return new ConsoleTab();
        }

        return new MobileTab();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
