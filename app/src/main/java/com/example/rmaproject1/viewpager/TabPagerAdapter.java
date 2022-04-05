package com.example.rmaproject1.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rmaproject1.fragments.DoneFragment;
import com.example.rmaproject1.fragments.InProgressFragment;
import com.example.rmaproject1.fragments.ToDoFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 3;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;

    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case FRAGMENT_1:
                fragment = new ToDoFragment();
                break;
            case FRAGMENT_2:
                fragment = new InProgressFragment();
                break;
            default:
                fragment = new DoneFragment();
                break;
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
        if (position == FRAGMENT_1) {
            return "ToDo";
        } else if (position == FRAGMENT_2) {
            return "In progress";
        }
        return "Done";
    }
}
