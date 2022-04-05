package com.example.rmaproject1.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rmaproject1.fragments.NewTicketFragment;
import com.example.rmaproject1.fragments.ProfileFragment;
import com.example.rmaproject1.fragments.StatsFragment;
import com.example.rmaproject1.fragments.TabFragment;


public class PagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 4;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;
    public static final int FRAGMENT_4 = 3;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case FRAGMENT_1: fragment = new StatsFragment(); break;
            case FRAGMENT_2: fragment = new NewTicketFragment(); break;
            case FRAGMENT_3: fragment = new TabFragment(); break;
            case FRAGMENT_4: fragment = new ProfileFragment(); break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "Stats";
            case FRAGMENT_2: return "New";
            case FRAGMENT_3: return "Tickets";
            default: return "Profile";
        }
    }
}
