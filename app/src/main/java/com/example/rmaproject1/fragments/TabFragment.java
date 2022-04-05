package com.example.rmaproject1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rmaproject1.R;
import com.example.rmaproject1.viewpager.PagerAdapter;
import com.example.rmaproject1.viewpager.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class TabFragment extends Fragment {

    public TabFragment(){
        super(R.layout.fragment_tabs);
    }

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        viewPager = view.findViewById(R.id.viewPagerTabs);
        viewPager.setOffscreenPageLimit(3);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager.setAdapter(new TabPagerAdapter(this.requireActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
